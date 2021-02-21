package 우선순위큐;
import java.util.*;
import java.io.*;
public class 가운데를말해요 {
	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> max_heap = new PriorityQueue<>((a1,a2)->{return a2-a1;});
		PriorityQueue<Integer> min_heap = new PriorityQueue<>((a1,a2)->{return a1-a2;});
		int n = Integer.parseInt(br.readLine());
		int cnt=0;
		int end=0;
		int temp=0;
		
		for(int i=1; i<=n;i++)
		{
			temp = Integer.parseInt(br.readLine());
			if(max_heap.size()==0)
				max_heap.offer(temp);
			else
			{
				if(max_heap.size()>min_heap.size())
					min_heap.offer(temp);
				else
				{
					max_heap.offer(temp);
				}
				
				if(max_heap.peek()>min_heap.peek())
				{
					int temp2 = max_heap.poll();
					int temp3 = min_heap.poll();
					min_heap.offer(temp2);
					max_heap.offer(temp3);
				}
			}
			

			
			bw.append(""+max_heap.peek()+"\n");

			
		}

		bw.flush();
		
		
	
	}

}
