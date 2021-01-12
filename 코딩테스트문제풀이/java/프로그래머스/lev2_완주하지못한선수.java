//package coding_test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class lev2_완주하지못한선수 {
//
//	public static void main(String[] args) {
//		
//	Solution test = new Solution();
//	String[] participant = {"leo","kiki","eden"};
//	String[] completion = {"eden","kiki"};
//	System.out.println(test.solution(participant,completion));
//	}
//
//
//}
//
//class Solution {
//    public String solution(String[] participant, String[] completion) {
//        String answer = "";
//        Map<String,Integer> map = new HashMap<>();
//        for(String temp : participant)
//        {
//        	map.put(temp,map.getOrDefault(temp,0)+1);
//        	
//        }
//        for(String temp:completion)
//        {
//        	map.put(temp,map.get(temp)-1);
//        }
//        for(String key : map.keySet())
//        {
//        	if (map.get(key)!=0)
//        		{
//        			answer=key;
//        		}
//        }
//        return answer;
//    }
//}