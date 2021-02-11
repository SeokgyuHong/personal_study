package 백준_단계별로풀어보기.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    public int vertex;
    public int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node node) {
        if (this.vertex > node.vertex) {
            return 1;
        }
        else if(this.vertex==node.vertex)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
public class 최단경로{


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        ArrayList<Node>[] list = new ArrayList[v + 1];
        for(int i=0; i<v+1; i++)
        {
            list[i] = new ArrayList<>(); //각 리스트 초기화 해줘야함
        }
        for(int i=0; i<e; i++)
        {
            s = br.readLine();
            st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }
        int[] distance= new int[v+1];
        Arrays.fill(distance,9999999);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        distance[start]=0; //시작은 0
        queue.add(new Node(0,start)); //가중치와 인덱스
        while(!queue.isEmpty())
        {
            Node temp = queue.poll();
            int dist = temp.vertex;
            int cur = temp.weight; //현재 노드
            if(dist>distance[cur]) //이미다른곳에서 갱신되었기때문에
                continue;
            for(int i=0; i<list[cur].size(); i++)
            {
                temp = list[cur].get(i);
                if(dist+temp.weight<distance[temp.vertex])
                {
                    distance[temp.vertex] = dist + temp.weight;
                    queue.add(new Node(distance[temp.vertex], temp.vertex));
                }
            }

        }
        for (int i = 1; i < distance.length; i++) {
            if(distance[i]==9999999)
                System.out.println("INF");
            else
                System.out.println(distance[i]);
        }








    }

}
