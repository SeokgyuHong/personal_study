package topological_sort;

import java.io.*;
import java.util.*;
public class ACMCraft {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int Case = Integer.parseInt(br.readLine());
		for(int r=0; r<Case; r++)
		{
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			ArrayList<Integer>[] graph = new ArrayList[n+1];
			for(int i=1; i<=n; i++)
				graph[i] = new ArrayList<Integer>();
			Queue<Integer> queue =new LinkedList<>();
			int[] time = new int[n+1];
			int[] sum = new int[n+1];
			int[] indegree =new int[n+1];
			st = new StringTokenizer(br.readLine());
			int temp;
			for(int i=1; i<=n; i++)
			{
				temp= Integer.parseInt(st.nextToken());
				time[i] = temp;
				sum[i] = temp;
			}
			
			for(int i=1; i<=k; i++)
			{
				st =new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
				indegree[to]+=1;
			}
			
			for(int i=1; i<=n; i++)
			{
				if(indegree[i]==0)
					queue.offer(i);
					
			}
			while(!queue.isEmpty())
			{
				int cur = queue.poll();
				for(int i=0; i<graph[cur].size(); i++)
				{
					sum[graph[cur].get(i)] = Math.max(sum[graph[cur].get(i)], sum[cur]+time[graph[cur].get(i)]);
					indegree[graph[cur].get(i)]-=1;
					if(indegree[graph[cur].get(i)]==0)
						queue.offer(graph[cur].get(i));
				}
			}
			int answer = Integer.parseInt(br.readLine());
			bw.append(""+sum[answer]+"\n");
			
		}
		bw.flush();
		bw.close();
		
	}

}
