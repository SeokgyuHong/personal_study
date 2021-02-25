package topological_sort;

import java.io.*;
import java.util.*;
public class 게임개발 {
	static class Node{
		int from;
		int to;
		Node(int from, int to)
		{
			this.from = from;
			this.to = to;
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Integer> queue = new LinkedList<>();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			graph[i] = new ArrayList<Integer>();
		
		int[] indegree = new int[n+1];
		int[] time = new int[n+1];
		int[] sum = new int[n+1];
		boolean[] check = new boolean[n+1];
		for(int i=1; i<=n; i++)
		{
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			while(st.hasMoreTokens())
			{
				int temp =Integer.parseInt(st.nextToken());
				if(temp==-1)
					break;
				graph[temp].add(i);
				indegree[i]+=1;
			}
			time[i]+=weight;
			sum[i]+=weight;
			
		}
		
		for(int i=1; i<=n; i++)
		{
			if(indegree[i]==0)
			{
				queue.offer(i);
				check[i]=true;
			}
		}

		while(!queue.isEmpty())
		{
			int cur = queue.poll();
			int max=0;

			for(int i=0; i<graph[cur].size(); i++)
			{
				indegree[(int)graph[cur].get(i)]-=1;
				if(indegree[(int)graph[cur].get(i)]==0)
					queue.offer((Integer) graph[cur].get(i));
				sum[graph[cur].get(i)] = Math.max(sum[graph[cur].get(i)],sum[cur]+time[graph[cur].get(i)]);

			}


			
			
		}
		for(int i=1; i<=n; i++)
			bw.append(""+sum[i]+"\n");
		
		bw.flush();
		bw.close();
		
	}

}
