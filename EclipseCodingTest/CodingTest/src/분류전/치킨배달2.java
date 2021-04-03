package 분류전;

import java.io.*;
import java.util.*;


public class 치킨배달2 {
	static class Node{
		int x;
		int y;

		public Node(int x, int y)
		{
		this.x = x;
		this.y = y;

		}

		
	
		
		
	}

	static int answer=987654321;
	static Stack<Node> stack = new Stack<>();
	static boolean[] visited;
	static ArrayList<Node> home;
	static ArrayList<Node> chicken;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[chicken.size()];

		for(int i=1; i<=n; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++)
			{
				int temp = Integer.parseInt(st.nextToken());
				if(temp==1)
					home.add(new Node(i,j));
				else if(temp==2)
					chicken.add(new Node(i,j));
			}
		}
			


			combination(0,m);

		System.out.println(answer);

	}


	static void combination(int start, int m)
	{
		if(m==0)
		{
			make_answer();
			return;
		}
		for(int i=start; i<chicken.size(); i++)
		{
			stack.add(chicken.get(i));
			combination(i+1,m-1);
			stack.pop();
		}
	}
	
	static void make_answer(){
		int temp_answer=0;
		for(int i=0; i<home.size(); i++)
		{
		Iterator ite = stack.iterator();
		int min_val = 987654321;
		while(ite.hasNext())
		{
			Node b = (Node)ite.next();
			int temp =Math.abs(home.get(i).x-b.x)+Math.abs(home.get(i).y-b.y);
			if(temp<min_val)
				min_val=temp;
		}
		temp_answer+=min_val;
		}
		if(temp_answer<answer)
			answer=temp_answer;
			
		
		
	
	}
}
