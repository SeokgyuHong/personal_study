package 카카오2019겨울인턴;

import java.util.*;
public class 불량사용자 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		Solution.solution(user_id, banned_id);
	}

}

class Solution {
	static Set<String> set;
	static ArrayList<String> ans;
	static int answer=0;
	static ArrayList<Map<String,Integer>> map = new ArrayList<>();
    public static int solution(String[] user_id, String[] banned_id) {
        ArrayList<Node>[] ban_list = new ArrayList[banned_id.length];
        ArrayList<String>[] list = new ArrayList[banned_id.length];
        for(int i=0; i<banned_id.length; i++)
        {
        	ban_list[i] = new ArrayList<Node>();
        	list[i] = new ArrayList<String>();
        }
        for(int i=0; i<ban_list.length; i++)
        {
        	boolean flag = false;
        	for(int j=0; j<banned_id[i].length(); j++)
        	{
        		if(banned_id[i].charAt(j)!='*')
        		{	flag = true;
        			ban_list[i].add(new Node(j,banned_id[i].charAt(j)));
        		}
        	}
        	if(flag==false)
        	{
        		ban_list[i].add(new Node(banned_id[i].length(),'*'));
        	}
        }

        for(int i=0; i<banned_id.length; i++)
        {
        	for(int j=0; j<user_id.length; j++)
        	{
        		boolean flag = false;
        		for(int k=0; k<ban_list[i].size(); k++) //한문자씩 비
        		{
        			Node temp = ban_list[i].get(k);
        			if(user_id[j].length()<banned_id[i].length())
        			{
        				flag=true;
        				break;
        			}
        			if(temp.idx==user_id[j].length() && temp.spel=='*')
        				break;
        			if(user_id[j].length() < temp.idx || user_id[j].length()> banned_id[i].length())
        			{
        				flag = true;
        				
        				break;
        			}
        			if(user_id[j].charAt(temp.idx)!=temp.spel) //틀렸을경우
        			{
        				flag = true;
        				break;
        			}
        		}
        		if(flag==false)
        			list[i].add(user_id[j]);
        	}

        }
        for(int i=0; i<list.length; i++)
        {
        	for(int j=0; j<list[i].size(); j++)
        		System.out.println(list[i].get(j));
        	System.out.println();
        }

    	ans = new ArrayList<>();
        for(int i=0; i<list[0].size(); i++)
        {


        	ans.add(list[0].get(i));
        	dfs(list,1);
        	ans.remove(list[0].get(i));

        }
        System.out.println(answer);
        return answer;
    }
    static void dfs(ArrayList<String>[] list,int cur)
    {

    	if(cur==list.length)
    	{
        	set = new HashSet<>();
        	for(int i=0; i<ans.size(); i++)
        		set.add(ans.get(i));
    		if(set.size()==list.length)
    		{
    			if(map.size()==0)
    			{
    				Map<String,Integer> temp = new HashMap<>();
    				Iterator ite = set.iterator();
    				while(ite.hasNext())
    				{
    					temp.put((String)ite.next(), 0);
    				}
    				map.add(temp);
    				answer++;	
    			}
    			else
    			{
    				boolean flag= false;
    				for(int i=0; i<map.size(); i++)
    				{
    					int cnt = 0;
    					Iterator ite = set.iterator();
    					while(ite.hasNext())
    					{
    						if(map.get(i).containsKey((String)ite.next()))
    							cnt++;
    					}
    					if(cnt==list.length)
    						flag = true;
    					
    				}
    				if(flag==false)
    				{
        				Map<String,Integer> temp = new HashMap<>();
        				Iterator ite = set.iterator();
        				while(ite.hasNext())
        				{
        					temp.put((String)ite.next(), 0);
        				}
        				map.add(temp);
        				answer++;	
    				}
    			}
    		}
    		
    		return;
    	}
    	for(int i=0; i<list[cur].size(); i++)
    	{
        	ans.add(list[cur].get(i));
    		dfs(list,cur+1);
    		ans.remove(list[cur].get(i));
    	}
    	
    }
}
class Node{
	int idx;
	char spel;
	public Node(int idx, char spel)
	{
		this.idx = idx;
		this.spel = spel;
	}
}