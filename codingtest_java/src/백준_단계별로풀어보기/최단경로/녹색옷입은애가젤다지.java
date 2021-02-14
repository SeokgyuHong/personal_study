package 백준_단계별로풀어보기.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 녹색옷입은애가젤다지{
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0, 1, 0, -1};
    static class Node implements Comparable<Node>{
        int cur;
        int x;
        int y;

        public Node(int cur, int x, int y) {
            this.cur = cur;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return this.cur - o.cur;
        }



    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int INF = 987654321;
        int cnt=1;
        while(true){
            int n = Integer.parseInt(bf.readLine());
            if(n==0)
                break;
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
               st = new StringTokenizer( bf.readLine());
                for (int j = 0; j < n; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    arr[i][j]=temp;
                }
            }
            int[][] dist = new int[n][n];
            for(int i=0; i<n; i++)
                Arrays.fill(dist[i], INF);
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(arr[0][0], 0, 0));
            dist[0][0] = arr[0][0];
            while(!queue.isEmpty())
            {
                Node temp =queue.poll();
                for(int i=0; i<dx.length; i++)
                {
                    int dir_x = temp.x + dx[i];
                    int dir_y = temp.y + dy[i];
                    if (0 <= dir_x && dir_x<n && 0<=dir_y && dir_y<n) {
                        if(dist[dir_x][dir_y]>temp.cur+arr[dir_x][dir_y])
                        {
                            dist[dir_x][dir_y]=temp.cur+arr[dir_x][dir_y];
                            queue.offer(new Node(dist[dir_x][dir_y], dir_x, dir_y));
                        }

                    }
                }
            }
            System.out.println("Problem "+cnt+++": "+dist[n-1][n-1]);



        }
    }
}
