package 분류전;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.*;

public class 다각형의면적 {
	static class Node{
		long x;
		long y;
		public Node(long x,long y)
		{
			this.x = x;
			this.y =y;
		}
	}
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<Node> arr = new ArrayList<>();
		int  n =Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++)
		{
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			arr.add(new Node(x,y));
		}
		
		arr.add(arr.get(0));
		long Answer=0;
		for(int i=0; i<n; i++)
		{
			Node first = arr.get(i);
			Node sec = arr.get(i+1);
			long temp1 = first.x*sec.y;
			long temp2 = first.y*sec.x;
			long temp3 = (temp1-temp2);
			Answer += temp3;
			
		}
		Answer=Math.abs(Answer);
		if(Answer%2==0)
			System.out.println(Answer/2+".0");
		else
			System.out.println(Answer/2+".5");
		
	}
	
}
