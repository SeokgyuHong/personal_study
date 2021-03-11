package dp;

import java.io.*;
import java.util.*;

public class _1로만들기2 {
	static int[] parent;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		parent = new int[1000001];
		
		int[] dp = new int[1000001];
		dp[1] =0;
		parent[1]=1;
		
		dp[2] =1;
		parent[2] =1;
		dp[3]=1;
		parent[3] =1;
		int answer=0;
		if(n<=3)
		{
			answer=dp[n];
		}
		else
		{
			for(int i=4; i<=n; i++)
			{
				int temp=0;
				int idx=0;
				int temp2 =0;
				int p=0;
				if(i%6==0)
				{
					temp = Math.min(dp[i/3], dp[i/2]);
					if(dp[i/3]<dp[i/2])
						idx = i/3;
					else
						idx=i/2;
					
					temp2 = temp<dp[i-1]?temp:dp[i-1];
					p = temp<dp[i-1]?idx:(i-1);
				}
				else if(i%3==0)
				{
					temp = dp[i/3];
					idx = i/3;
					
					temp2 = temp<dp[i-1]?temp:dp[i-1];
					p = temp<dp[i-1]?idx:(i-1);
				}
				else if(i%2==0)
				{
					temp = dp[i/2];
					idx=i/2;
					
					temp2 = temp<dp[i-1]?temp:dp[i-1];
					p = temp<dp[i-1]?idx:(i-1);
				}
				else
				{
					p = i-1;
					temp2 = dp[i-1];
				}

				dp[i] = temp2+1;
				parent[i] =p;
				
				
			}
			
		}
		System.out.println(dp[n]);
		
		dfs(n);
			
		
		
	}
	static void dfs(int n)
	{
		if(parent[n]!=n)
		{
			System.out.print(n+" ");
			dfs(parent[n]);
		}
		else
		{
			System.out.println(parent[n]);
			return;
		}
		
	}
	

}
