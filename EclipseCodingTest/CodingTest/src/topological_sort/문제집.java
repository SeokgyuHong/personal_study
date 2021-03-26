package 분류전;

import java.io.*;
import java.util.*;
public class 문제집 {
	static class Node implements Comparable<Node>{
		int num;
		int priority;
		public Node(int num, int priority)
		{
			this.num = num;
			this.priority = priority;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.num-o.num;
		}
		
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub


		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int  n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] indegree = new int[n+1];
		boolean[] check = new boolean[n+1];
		ArrayList<Integer>[] arr =new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			arr[i] = new ArrayList<>();
		for(int i=0; i<m; i++)
		{
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			arr[front].add(back);
			indegree[back]+=1;
		}
		for(int i=1; i<=n; i++)
		{
			if(indegree[i]==0)
			{
				pq.offer(new Node(i,0));
			}
		}
		while(!pq.isEmpty())
		{
			Node cur = pq.poll();

			bw.append(""+cur.num+" ");
			for(int i=0; i<arr[cur.num].size(); i++)
			{
				indegree[arr[cur.num].get(i)]-=1;
				if(indegree[arr[cur.num].get(i)]==0)
					pq.offer(new Node(arr[cur.num].get(i),0));
			}
			
		}
			
		bw.flush();
		bw.close();
		
	}
	

}
