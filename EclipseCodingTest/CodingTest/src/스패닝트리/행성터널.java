package 스패닝트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 행성터널 {
	static int[] parent;
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;
		public Node(int x,int y,int cost)
		{
			this.x =x;
			this.y =y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cost-o.cost;
		}
		
		
	}
	static class tempNode{
		int idx;
		int weight;
		public tempNode(int idx, int weight)
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
		ArrayList<tempNode> arr_x = new ArrayList<>();
		ArrayList<tempNode> arr_y = new ArrayList<>();
		ArrayList<tempNode> arr_z = new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();
		parent = new int[n];
		for(int i=0; i<n; i++)
		{
			parent[i] = i;
		}
		for(int i=0; i<n; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			arr_x.add(new tempNode(i, x));
			arr_y.add(new tempNode(i, y));
			arr_z.add(new tempNode(i, z));
		}
		long final_answer=0L;


			arr_x.sort((a1,a2)->{return a1.weight-a2.weight;});
			arr_y.sort((a1,a2)->{return a1.weight-a2.weight;});
			arr_z.sort((a1,a2)->{return a1.weight-a2.weight;});
			
		for(int i=0; i<n-1; i++)
		{
			int cost = Math.abs(arr_x.get(i).weight-arr_x.get(i+1).weight);
			int a = arr_x.get(i).idx;
			int b = arr_x.get(i+1).idx;
			pq.add(new Node(a,b,cost));
			
			 cost = Math.abs(arr_y.get(i).weight-arr_y.get(i+1).weight);
			 a = arr_y.get(i).idx;
			 b = arr_y.get(i+1).idx;
			pq.add(new Node(a,b,cost));
			
			 cost = Math.abs(arr_z.get(i).weight-arr_z.get(i+1).weight);
			 a = arr_z.get(i).idx;
			 b = arr_z.get(i+1).idx;
			pq.add(new Node(a,b,cost));
			
		}
		int cnt=0;
		int answer=0;
		while(cnt<n-1)
		{
			Node temp = pq.poll();
			int a= find(temp.x);
			int b = find(temp.y);
			if(a!=b)
			{
				if(a<b)
					parent[b]=a;
				else
					parent[a]=b;
				answer+=temp.cost;
				cnt+=1;
			}
			
			
		}



		System.out.println(answer);
		
	}
	static int find(int x)
	{
		if(parent[x]!=x)
			return parent[x] = find(parent[x]);
		else
			return parent[x];
		
	}

}
