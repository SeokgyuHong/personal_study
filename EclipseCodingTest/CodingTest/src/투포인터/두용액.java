package 투포인터;
import java.io.*;
import java.util.*;
public class 두용액 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		ArrayList<Integer> arr =new ArrayList<>();
		int n = Integer.parseInt(br.readLine());
		st =new StringTokenizer(br.readLine());
		while(st.hasMoreTokens())
			arr.add(Integer.parseInt(st.nextToken()));
		
		arr.sort((a1,a2)->{return a1-a2;});

		
		
		int left = 0;
		int right = n-1;
		int answer=2000000000;
		int answer_1=0;
		int answer_2=0;
		int sum=0;
		while(left<right)
		{
			sum =arr.get(left)+arr.get(right);
			if(Math.abs(sum)<Math.abs(answer))
			{
				answer=sum;
				answer_1 = arr.get(left);
				answer_2 = arr.get(right);
			}
			if(Math.abs(arr.get(left))<Math.abs(arr.get(right)))
				right--;
			else
				left++;
			
		}
		System.out.println(answer_1+" "+answer_2);
	}

}
