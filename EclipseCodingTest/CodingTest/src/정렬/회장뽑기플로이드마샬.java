package 정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class 회장뽑기플로이드마샬 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	StringTokenizer st;
	
	int n = Integer.parseInt(br.readLine());
	int[][] arr = new int[n+1][n+1];
	int INF = 987654321;
	for(int i=1; i<=n; i++)
	{
		for(int j=1; j<=n; j++)
		{
			if(i==j)
				arr[i][j]=0;
			else
				arr[i][j]=INF;
			
		}
	}

	
	while(true)
	{
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if(a==-1 && b==-1)
			break;
		arr[a][b]=1;
		arr[b][a]=1;
	}

	for(int k=1; k<=n; k++)
	{
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=n; j++)
			{
				if(i==j)
					continue;
				if(arr[i][k]+arr[k][j]<arr[i][j])
				{
					arr[i][j] = arr[i][k]+arr[k][j];
				}
			}
		}
	}

	int max = INF;
	for(int i=1; i<=n; i++)
	{
		int line_max=0;
		for(int j=1; j<=n; j++)
			if(arr[i][j]>line_max)
				line_max=arr[i][j];
		if(line_max<max)
			max=line_max;
	}
	int[] check = new int[n+1];
	int cnt=0;
	for(int i=1; i<=n; i++)
	{
		int line_max=0;
		for(int j=1; j<=n; j++)
			if(arr[i][j]>line_max)
				line_max=arr[i][j];
		if(line_max==max)
			{check[i]+=1;
			cnt+=1;
			}
				
	}
	System.out.println(max+" "+cnt);
	for(int i=1; i<=n; i++)
		if(check[i]==1)
			System.out.print(i+" ");
	
	

	
	
	}

}
