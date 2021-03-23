package 분리집합;

import java.io.*;
import java.util.*;
public class 사이클게임 {

	static int[] parent;
	static int n;
	static int m;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i=0; i<n; i++)
			parent[i]=i;
		int answer=0;
		for(int k=0; k<m; k++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p_a = find(a);
			int p_b = find(b);
			if(answer==0)
			{
				if(p_a>p_b)
					parent[p_a]=p_b;
				else if(p_b>p_a)
					parent[p_b] = p_a;
				else
					answer=k+1;
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
