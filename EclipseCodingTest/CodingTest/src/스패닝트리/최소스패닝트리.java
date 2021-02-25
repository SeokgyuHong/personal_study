package 스패닝트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최소스패닝트리 {
	static int[] parent;
	static class Node{
		int a;
		int b;
		int weight;
		Node(int a, int b, int weight)
		{
			this.a = a;
			this.b = b;
			this.weight = weight;
		}
		
	}

	static int find(int x)
	{
		if(parent[x]!=x)
			return parent[x] = find(parent[x]);
		else
			return parent[x];
	
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((a1,a2)->{return a1.weight-a2.weight;});
		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		parent = new int[v+1];
		for(int i=1; i<=v; i++)
			parent[i]=i;
		for(int i=1; i<=e; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a,b,c));
		}
		int cnt=0;
		int sum=0;
		while(cnt<(v-1))
		{
			Node temp = pq.poll();
			int a = find(temp.a);
			int b = find(temp.b);
			if(a!=b)
			{
				if(a>b)
					parent[a]=b;
				else
					parent[b]=a;
				sum+=temp.weight;
				cnt+=1;
				
			}
			
			
		}
		System.out.println(sum);

	}

}
