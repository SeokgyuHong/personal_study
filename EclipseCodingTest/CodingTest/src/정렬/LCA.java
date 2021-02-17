package 정렬;

import java.util.*;
import java.util.StringTokenizer;
import java.io.*;


public class LCA {
	static int n;
	static int a,b;
	static int[] level;
	static ArrayList<Integer>[] tree;
	static ArrayList<Integer> answer = new ArrayList<>();
	static BufferedWriter bw;
	static int answer_flag=0;
	static int[] parent;
	public static void main(String[] args)throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		tree = new ArrayList[n+1];
		parent = new int[n+1];
		Queue<Integer> queue = new LinkedList<>();
		level = new int[n+1];
		
		for(int i=1; i<=n; i++)
			tree[i] = new ArrayList<Integer>();
		for(int i=1; i<n; i++)
		{

			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		queue.offer(1);
		level[1]=1;
		parent[1]=1;
		int cur;
		while(!queue.isEmpty())
		{
			cur = queue.poll();
			for(int i=0; i<tree[cur].size(); i++)
			{
				if(level[tree[cur].get(i)]==0)
				{
					parent[tree[cur].get(i)]=cur;
					level[tree[cur].get(i)]= level[cur]+1;
					queue.offer(tree[cur].get(i));
				}
			}
			
		}

		cur = Integer.parseInt(br.readLine());
		int level_a;
		int level_b;
		for(int z=0; z<cur; z++)
		{
			answer_flag=0;
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			level_a = level[a];
			level_b = level[b];
			if(level_a>=level_b)
			{
				while(level_a!=level_b)
				{
					a = parent[a];
					level_a = level[a]; 
				}

			}
			else
			{
				while(level_b!=level_a)
				{
					b = parent[b];
					level_b = level[b];
				}
				
			}
			while(parent[a]!=parent[b])
			{
				a = parent[a];
				b = parent[b];
			}
			if(a==b)
				bw.write(""+a+"\n");
			else
				bw.write(""+parent[a]+"\n");
			

		}

		bw.flush();
		
	}


}
