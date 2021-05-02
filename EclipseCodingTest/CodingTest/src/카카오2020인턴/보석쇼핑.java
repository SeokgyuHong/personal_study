package 카카오2020인턴;

import java.util.*;
import java.io.*;
public class 보석쇼핑 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] insert = {"B"};
		Solution2.solution(insert);
		
	}
	
}
class Solution2 {
    public static int[] solution(String[] gems) {
    	Map<String,Integer> map = new HashMap<>();
        int[] answer = new int[2];
    	int idx=0;
    	for(int i=0; i<gems.length; i++)
    	{
    		if(!map.containsKey(gems[i]))
    			map.put(gems[i], idx++);
    	}

//    		System.out.println(map.entrySet());
//    		System.out.println(map.size());
    	int[] check = new int[map.size()];
    	int cur = 0;
    	int total=0; //총 몇개의 보석이 구간내에있는지 체
    	int cnt=0; //모든종류가 다 들어가있는지 체
    	Queue<Node> queue = new LinkedList<>();
    	int max=Integer.MAX_VALUE;
    	while(cur<gems.length)
    	{
    		if(cnt==map.size() && cnt<=total)
    		{

    			Node left = queue.peek();
    			if((cur-1)-left.idx<max)
    			{
    				max = (cur-1)-left.idx;
    				answer[0] = left.idx+1;
    				answer[1] = cur;
    			}
    			while(cnt==map.size() &&total>=cnt)
    			{

    				Node temp =queue.poll();
    				if(check[map.get(temp.gem)]==1)
    				{
    					total--;
    					cnt--;
        				check[map.get(temp.gem)]--;
    				}
    				else
    				{
    					total--;
        				check[map.get(temp.gem)]--;
    					if(cnt<=total && cnt==map.size())
    					{
    					Node start = queue.peek();
    	    			if((cur-1)-start.idx<max)
    	    			{
    	    				max = (cur-1)-start.idx;
    	    				answer[0] = start.idx+1;
    	    				answer[1] = cur;
    	    			}
    					}

    				}


    			}
    		}
    		if(cnt<map.size())
    		{
    			Node temp = new Node(cur,gems[cur]); //큐에넣을예정
    			queue.offer(temp);
    			if(check[map.get(gems[cur])]==0)
    			{
    				check[map.get(gems[cur])]++;
    				cnt++;
    				total++;
    			}
    			else
    			{
    				check[map.get(gems[cur])]++;
    				total++;
    			}

    			         
    		}
    		cur++;
    		
    		
    	}
    	while(!queue.isEmpty())
    	{

			Node temp = queue.peek();
    		if(cnt==map.size()&&total>=cnt)
    		{
    			if((cur-1)-temp.idx<max)
    			{
    				max = (cur-1)-temp.idx;
    				answer[0] = temp.idx+1;
    				answer[1] = cur;
    			}
    		}
    		if((check[map.get(temp.gem)]==1))
    		{
    			cnt--;
    			total--;
    			check[map.get(temp.gem)]--;
    		}
    		else
    		{
    			total--;
    			check[map.get(temp.gem)]--;
    		}
    		queue.poll();


    	}
    	System.out.println(answer[0]);
    	System.out.println(answer[1]);

    	
    	if(answer[0]>answer[1])
    	{
    		int temp = answer[0];
    		answer[0] = answer[1];
    		answer[1]= temp;
    	}
        return answer;
    }
}
class Node{
	int idx;
	String gem;
	public Node(int idx, String gem)
	{
		this.idx = idx;
		this.gem = gem;
	}
}