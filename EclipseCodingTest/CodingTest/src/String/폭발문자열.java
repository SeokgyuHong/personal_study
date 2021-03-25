package String;

import java.io.*;
import java.util.*;
public class 폭발문자열 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Stack<Character> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String bomb = br.readLine();
		StringBuilder bomb_rev = new StringBuilder();
		for(int i=bomb.length()-1; i>=0; i--)
			bomb_rev.append(bomb.charAt(i));
		
		
		int[] check = new int[bomb.length()];
		

		for(int k=0; k<str.length(); k++)
		{
			stack.add(str.charAt(k));
			if(str.charAt(k)==bomb.charAt(bomb.length()-1))
			{
				Stack<Character> stack2 = new Stack<>();
				if(stack.size()>=bomb.length())
				{
					int cur=0;
					boolean flag = false;
					while(cur<bomb.length())
					{
						char temp = stack.pop();
						if(temp!=bomb_rev.charAt(cur))
						{
							stack.add(temp);
							flag=true;
							break;
						}
						else
						{
							stack2.add(temp);
							cur++;
						}
						
					}
					if(flag)
						while(!stack2.isEmpty())
							stack.add(stack2.pop());
				}
				
			}
		}

		while(!stack.isEmpty())
			sb.append(stack.pop());
		if(sb.length()!=0)
			sb.reverse();
		else
			sb.append("FRULA");
		bw.append(sb);
		bw.flush();
		bw.close();
		
	}

}
