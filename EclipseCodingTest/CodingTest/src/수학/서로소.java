package 수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 서로소 {
	public static void main(String[] args)throws IOException
	{
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			long phi = fact(n);
		
			bw.append(""+phi+"\n");
			
		}
		bw.flush();
		bw.close();
	}
	
	public static long fact(int n)
	{
		long phi = n;
		if(n%2 ==0)
		{
			phi = phi*(2-1)/2;
			do {n/=2;} while(n%2==0);
		}
		for(int i=3; i<=Math.sqrt(n); i+=2)
		{
			if(n%i==0)
			{
				phi = phi*(i-1)/i;
				do {n/=i;} while(n%i==0);
			}
		}
		if(n>2){
			phi = phi*(n-1)/n;
		}
		return phi;
	}

}
