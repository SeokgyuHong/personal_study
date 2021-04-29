package 분류전;
import java.io.*;
import java.util.*;
public class 소수의연속합 {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> queue = new LinkedList<>();
		
		int n = Integer.parseInt(br.readLine());
		int[]arr = new int[n+1];
		ArrayList<Integer> primeList = new ArrayList<>();
		for(int i=1; i<=n; i++)
			arr[i]= 1;
		
		for(int i=2; i<=n; i++)
		{
			if(arr[i]==0)
				continue;
			for(int j=2*i; j<=n; j+=i)
			{
				arr[j]=0;
			}
			
		}
		
		for(int i=2; i<=n; i++)
			if(arr[i]!=0) primeList.add(i);

		
		int answer=0;
		int sum=0;
		for(int i=0; i<primeList.size(); i++)
		{
			queue.offer(primeList.get(i));
			sum+=primeList.get(i);
			if(sum==n)
				answer+=1;
			if(sum>n)
			{
				while(sum>n)
				{
					int temp=queue.poll();
					sum-=temp;
				}
				if(sum==n)
					answer+=1;
			}
		}
		System.out.println(answer);
	}

}
