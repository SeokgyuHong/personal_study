package 카카오2019겨울인턴;

import java.util.*;
public class 튜플 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
		Solution2.solution(s);

	}

}
class Solution2 {
    static public  int[] solution(String s) {
    	ArrayList<String> str = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		for(int i=1; i<s.length()-1; i++)
		{

			if(s.charAt(i)=='{')
			{
				sb = new StringBuilder();
				sb.append('{');
			}
			if(s.charAt(i)=='}')
			{
				sb.append('}');
				str.add(sb.toString());
				sb = new StringBuilder();
			}
			if(s.charAt(i)!='}' && s.charAt(i)!='{')
			{
				if(sb.length()!=0)
					sb.append(s.charAt(i));
			}
			
			
		}
		str.sort((a1,a2)->{return a1.length()-a2.length();});
		ArrayList<Integer>[] arr = new ArrayList[str.size()];
		for(int i=0; i<arr.length; i++)
			arr[i]= new ArrayList<>();
		for(int i=0; i<str.size(); i++)
		{
			StringBuilder temp =new StringBuilder();
			for(int j=1; j<str.get(i).length(); j++)
			{
				if(str.get(i).charAt(j)!=',' && str.get(i).charAt(j)!='}')
				{
					temp.append(str.get(i).charAt(j));
				}
				else
				{
					arr[i].add(Integer.parseInt(temp.toString()));
					temp = new StringBuilder();
				}
					
				
				
			}
		}

		Set<Integer> set = new HashSet<>();
		int[] answer = new int[arr.length];
		for(int i=0; i<arr.length; i++)
		{
			for(int j=0; j<arr[i].size(); j++)
			{
				if(!set.isEmpty())
				{
					if(set.contains(arr[i].get(j))==false)
						answer[i] = arr[i].get(j);
				}
				else
					answer[i] = arr[i].get(j);
			}
		}
		for(int i=0; i<str.size(); i++)
			System.out.println(str.get(i));

        return answer;
    }
}