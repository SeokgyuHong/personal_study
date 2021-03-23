package 수학;

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

public class 선분교차 {
	static class Node implements Comparable<Node>{
		long x;
		long y;
		int check;
		public Node(long x, long y,int check)
		{
			this.x = x;
			this.y = y;
			this.check = check;
		}
		@Override
		public int compareTo(Node o) {
			long cur = this.x+this.y;
			long next = o.x + o.y;
			if(cur<next)
				return -1;
			else if(cur>next)
				return 1;
			else
			{
				return this.check-o.check;
			}
		}

	}

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	st = new StringTokenizer(br.readLine());

	long x1 = Long.parseLong(st.nextToken());
	long y1 = Long.parseLong(st.nextToken());
	Node a = new Node(x1,y1,1);

	long x2 = Long.parseLong(st.nextToken());
	long y2 = Long.parseLong(st.nextToken());
	 Node b = new Node(x2,y2,1);
	 
	st = new StringTokenizer(br.readLine());
	long x3 = Long.parseLong(st.nextToken());
	long y3 = Long.parseLong(st.nextToken());
	 Node c = new Node(x3,y3,2);
	 long x4 = Long.parseLong(st.nextToken());
	 long y4 = Long.parseLong(st.nextToken());
	 Node d = new Node(x4,y4,2);


	 int answer=0;
		int abc = ccw(a,b,c);
		int abd = ccw(a,b,d);
		int cda = ccw(c,d,a);
		int cdb = ccw(c,d,b);
	boolean flag =false;
	if(abc*abd ==0 && cda* cdb==0)
	{
		flag = true;

		if((Math.min(a.x,b.x)<=Math.max(c.x, d.x)) && (Math.min(c.x, d.x)<=Math.max(a.x,b.x))&&(Math.min(a.y,b.y)<=Math.max(c.y, d.y)) && (Math.min(c.y, d.y)<=Math.max(a.y,b.y)))
				answer=1;
	}
	if(abc*abd<=0 && cda*cdb<=0)
	{
		if(!flag)
			answer=1;
	}
	else
		answer=0;
		 
	 
	 System.out.println(answer);
	}
	
	static int ccw(Node a, Node b, Node c)
	{
		long temp = (b.x-a.x)*(c.y-a.y)-(c.x-a.x)*(b.y-a.y);
		if(temp>0) return 1;
		else if(temp==0) return 0;
		else return -1;
	}



}
