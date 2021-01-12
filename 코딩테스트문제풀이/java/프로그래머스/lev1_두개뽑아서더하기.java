//package coding_test;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//public class lev1_두개뽑아서더하기 {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	System.out.println("123");
//	}
//
//}
//
//class Solution {
//    public List<Integer> solution(int[] numbers) {
//        List<Integer> answer = new ArrayList<>();
//        Set<Integer> set = new HashSet<>();
//        for(int i=0; i<numbers.length; i++)
//        {
//            for(int j=i+1; j<numbers.length; j++)
//            {
//                set.add(numbers[i]+numbers[j]);
//            }
//        }
//        Iterator<Integer> ite = set.iterator();
//        
//        while(ite.hasNext())
//        {
//        	answer.add(ite.next());
//        }
//        Collections.sort(answer);
//        System.out.println(answer);
//        return answer;
//    }
//}