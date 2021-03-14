package 투포인터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 부분합 {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int n= Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		ArrayList<Integer> arr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr.add(Integer.parseInt(st.nextToken()));
		
		int left =0;
		int right =0;
		long sum=0;
		int answer=987654321;
		while(true)
		{
			
			if(right==n)
			{
				if(sum>=s)
				{
					if((right-left)<answer)
						answer=right-left;
					while(left<=right)
					{
						sum-=arr.get(left);
						if(sum<s)
							break;
						else
						{
							if((right-left-1)<answer)
							{
								answer=right-left-1;
							}
							left++;
						}
					}
				}
				break;
				
			}
			
			if(sum>=s)
			{
				if((right-left)<answer)
					answer=right-left;
				sum-=arr.get(left);
				left++;
			}
			else
			{

				sum+=arr.get(right);
				right++;
			}
			
		}

		if(answer==987654321)
			System.out.println(0);
		else
			System.out.println(answer);
		
	}
	

}
