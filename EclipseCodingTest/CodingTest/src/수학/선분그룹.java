package 분류전;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 선분그룹{
	static class Node{
		int x;
		int y;
		public Node(int x, int y)
		{
			this.x = x;
			this.y = y;

		}


	}
	static class Pos{
		int x1;
		int y1;
		int x2;
		int y2;
		public Pos(int x1, int y1, int x2, int y2)
		{
			this.x1 =x1;
			this.y1 =y1;
			this.x2 =x2;
			this.y2 = y2;
		}
	}

	static int[] parent;
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	int n = Integer.parseInt(br.readLine());
	parent = new int[n+1];
	Pos[] pos = new Pos[n+1]; 
	for(int i=1; i<=n; i++)
		parent[i]=i;
	for(int i=1; i<=n; i++)
	{
		if(i==1)
		{
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			pos[i] = new Pos(x1,y1,x2,y2);
			
		}
		else
		{
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			Node cur1 = new Node(x1,y1);
			Node cur2 = new Node(x2,y2);
			boolean flag =false;
			for(int j=1; j<i; j++)
			{
					Pos temp = pos[j];
					Node temp1 = new Node(temp.x1,temp.y1);
					Node temp2 = new Node(temp.x2, temp.y2);
					if(isCross(cur1,cur2,temp1,temp2))
					{
						int a = find(j);
						int b = find(i);
						if(a>b)
							parent[a]=b;
						else
							parent[b]=a;
					}
				
			}
				pos[i] = new Pos(x1,y1,x2,y2);

		}

	}
	for(int i=1; i<=n; i++)
		find(i);
	int group=0;
	int count=0;
	int[] check = new int[n+1];
	for(int i=1; i<=n; i++)
	{
		if(parent[i]==i)
			group++;
		check[parent[i]]++;
		
	}
	int max=-1;
	for(int i=1; i<=n; i++)
	{
		if(check[i]>max)
			max=check[i];
	}
	System.out.println(group);
	System.out.println(max);
		

	}
	static int find(int x)
	{
		if(parent[x]!=x)
			return parent[x] = find(parent[x]);
		else
			return parent[x];
	}
	static boolean isCross(Node a, Node b, Node c, Node d)
	{
		int abc = ccw(a,b,c);
		int abd = ccw(a,b,d);
		int cda = ccw(c,d,a);
		int cdb = ccw(c,d,b);
		boolean flag =false;
		if(abc*abd ==0 && cda* cdb==0)
		{
			flag = true;

			if((Math.min(a.x,b.x)<=Math.max(c.x, d.x)) && (Math.min(c.x, d.x)<=Math.max(a.x,b.x))&&(Math.min(a.y,b.y)<=Math.max(c.y, d.y)) && (Math.min(c.y, d.y)<=Math.max(a.y,b.y)))
					return true;
		}
		if(abc*abd<=0 && cda*cdb<=0)
		{
			if(!flag)
				return true;
		}
		else
			return false;
		return false;
		
	}
	
	static int ccw(Node a, Node b, Node c)
	{
		int temp = (b.x-a.x)*(c.y-a.y)-(c.x-a.x)*(b.y-a.y);
		if(temp>0) return 1;
		else if(temp==0) return 0;
		else return -1;
		
	}



}
