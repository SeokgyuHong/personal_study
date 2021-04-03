package 트리dp;

import java.io.*;
import java.util.*;
public class 트리색칠하기 {
	static int n;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int[][] treedp;
	static PriorityQueue<Node> heap;
	static class Node{
		int idx;
		int weight;
		public Node(int idx, int weight)
		{
			this.idx = idx;
			this.weight = weight;
		}
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub


		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		treedp = new int[n+1][(int)Math.ceil(Math.sqrt(n))+1];
		for(int i=1; i<=n; i++)
			graph[i] = new ArrayList<>();
		visited = new boolean[n+1];
		for(int i=0; i<n-1; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		
		
		visited[1]=true;
		dfs(1);
		int answer = Integer.MAX_VALUE;
		for(int i=1;i<treedp[1].length; i++)
		{
			if(treedp[1][i]<answer)
				answer=treedp[1][i];
			
		}
		System.out.println(answer);
		
		
		
	}
	static void dfs(int start)
	{
		for(int i=0; i<graph[start].size(); i++)
		{
			if(!visited[graph[start].get(i)])
			{
				visited[graph[start].get(i)]=true;
				dfs(graph[start].get(i));
				for(int j=1; j<treedp[start].length; j++)
				{

					Node temp1 = heap.poll();
					if(temp1.idx==j)
					{
						Node temp2 = heap.poll();
						treedp[start][j]+=temp2.weight;
						heap.offer(temp2);
					}
					else
					{
						treedp[start][j]+=temp1.weight;
						
					}
					heap.offer(temp1);
				}
				
			}
		}
		
		heap = new PriorityQueue<>((a1,a2)->{return a1.weight-a2.weight;});
		for(int i=1; i<treedp[start].length; i++)
		{

			
			treedp[start][i]+=i;
			heap.add(new Node(i,treedp[start][i]));
		}
		
		
	}

}
