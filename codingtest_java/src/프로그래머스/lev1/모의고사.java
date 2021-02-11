package 프로그래머스.lev1;
import java.util.*;
public class 모의고사 {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sol.solution(arr));
    }
}
class Solution {
    public List<Integer> solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] a = {1,2,3,4,5};
        int[] b= {2,1,2,3,2,4,2,5};
        int[] c= {3,3,1,1,2,2,4,4,5,5};
        int a_c=0;
        int b_c=0;
        int c_c=0;
        for(int i=0; i<answers.length; i++)
        {
            if(a[i%a.length]==answers[i])
                a_c+=1;
            if(b[i%b.length]==answers[i])
                b_c+=1;
            if(c[i%c.length]==answers[i])
                c_c+=1;
        }
        int max = Math.max(a_c,Math.max(b_c,c_c));
        if(max==a_c)
            answer.add(1);
        if(max==b_c)
            answer.add(2);
        if(max==c_c)
            answer.add(3);
        Collections.sort(answer);

        return answer;
    }
}