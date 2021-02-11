package 백준_단계별로풀어보기.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {
    static class Node implements Comparable<Node>{
        int weight;
        int vertex;

        public Node(int weight, int vertex) {
            this.weight = weight;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Node node) {
            if(this.weight> node.weight)
                return 1;
            else if(this.weight==node.weight)
                return 0;
            else
                return -1;
        }
    }
    static int n;
    static int e;
    static int a;
    static int b;
    static int c,first,second;
    static int INF;
    static int[] distance;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s = br.readLine();
        st = new StringTokenizer(s);
         n = Integer.parseInt(st.nextToken());
         e = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        for(int i=0; i<n+1; i++)
        {
            list[i] = new ArrayList<>();
        }
        for(int i=0; i<e; i++)
        {
            s = br.readLine();
            st = new StringTokenizer(s);
             a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(c,b));
            list[b].add(new Node(c, a));
        }
        s = br.readLine();
        st = new StringTokenizer(s);
        first = Integer.parseInt(st.nextToken());
        second = Integer.parseInt(st.nextToken());
        INF = 987654321;
        boolean flag1=true;
        boolean flag2=true;

        int temp1 = daikstra(1,first);
        int temp3 = daikstra(first, second);
        int temp5 = daikstra(second, n);


        int temp2 = daikstra(1, second);
        int temp4 = daikstra(first, n);
        if(temp1==INF || temp3==INF || temp5==INF)
        {
            flag1=false;
        }
        if(temp2==INF || temp3==INF || temp4==INF){
            flag2=false;
        }
        if(flag1==false && flag2==false)
            System.out.println(-1);
        else if(flag1==false && flag2==true)
            System.out.println(temp2+temp3+temp4);
        else if(flag2==false && flag1==true)
            System.out.println(temp1+temp3+temp5);
        else
            if(temp1+temp3+temp5<temp2+temp3+temp4)
                System.out.println(temp1+temp3+temp5);
            else
                System.out.println(temp2+temp3+temp4);





    }
    public static int daikstra(int start,int to)
    {
        distance = new int[n + 1];
        int weight;
        int vertex;
        Arrays.fill(distance, INF);
        distance[start]=0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, start));
        while (!pq.isEmpty()) {
            Node temp = pq.poll();
            weight = temp.weight;
            vertex = temp.vertex;
            if (distance[vertex] < weight) {
                continue;
            }
            for(int i=0; i<list[vertex].size(); i++)
            {
                temp = list[vertex].get(i); //하나식 꺼내서
                if (distance[temp.vertex] > weight + temp.weight) {
                    distance[temp.vertex] = weight + temp.weight;
                    pq.add(new Node(distance[temp.vertex], temp.vertex));
                }
            }

        }
        return distance[to];


    }
}
