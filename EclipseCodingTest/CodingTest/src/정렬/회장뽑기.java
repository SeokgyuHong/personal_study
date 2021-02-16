package 정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.StringTokenizer;

public class 회장뽑기{

	static ArrayList<Integer>[]arr;
	static boolean[] check;
	static boolean[] answer;
	static int max;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub\r(System.in));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Queue<Integer> queue = new LinkedList<>();
		
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		arr = new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			arr[i] = new ArrayList<Integer>();
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1)
				break;
			arr[a].add(b);
			arr[b].add(a);
		}
		
		int temp;
		int[] answer = new int[n+1];
		for(int i=1; i<=n; i++)
		{
			int[] visited = new int[n+1];
			visited[i] = 0;
			queue.offer(i);
			int max = 0;
			while(!queue.isEmpty())
			{
				temp = queue.poll();
				for(int j=0; j<arr[temp].size(); j++)
				{
					
					if(visited[arr[temp].get(j)]==0 && arr[temp].get(j)!=i)
					{
						visited[arr[temp].get(j)]=visited[temp]+1;
						if(max<visited[arr[temp].get(j)])
							max=visited[arr[temp].get(j)];
						queue.offer(arr[temp].get(j));
					}
					
				}
				
			}
		
		answer[i]=max;
		Arrays.fill(visited, 0);
		}
		int min=99999;
		int cnt=0;
		for(int i=1; i<=n; i++)
		{
			if(answer[i]<min)
				min=answer[i];
		}
		for(int i=1; i<=n; i++)
			if(answer[i]==min)
				cnt+=1;
		System.out.println(min+" "+cnt);
		for(int i=1; i<=n; i++)
			if(answer[i]==min)
				System.out.print(i+" ");
		
	}

}
