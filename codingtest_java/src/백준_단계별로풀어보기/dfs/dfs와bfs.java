package 백준_단계별로풀어보기.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class dfs와bfs {

    static int n;
    static int m;
    static int v;
    static boolean[] visited;
    static List<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(to);
            arr[to].add(from);
        }
        for(int i=1; i<n+1; i++)
        {
            arr[i].sort((a,b)->{return a-b;});
        }

        visited = new boolean[n + 1];
        Arrays.fill(visited, false);
        visited[v] = true;
        dfs(v, 0);
        System.out.println();
        Arrays.fill(visited,false);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;

        int cur;
        while(!queue.isEmpty())
        {
            cur = queue.poll();
            System.out.print(cur+" ");
            for (int i = 0; i < arr[cur].size(); i++) {
                if (visited[arr[cur].get(i)] == false) {
                    visited[arr[cur].get(i)]=true;
                    queue.offer(arr[cur].get(i));
                }
            }
        }

    }

    public static void dfs(int start, int cnt) {
        if (cnt == n + 1) {
            return;
        }
        System.out.print(start+" ");
        for (int i = 0; i < arr[start].size(); i++) {
            if (visited[arr[start].get(i)] == false) {
                visited[arr[start].get(i)] = true;
                dfs(arr[start].get(i), cnt + 1);
            }
        }



    }
}
