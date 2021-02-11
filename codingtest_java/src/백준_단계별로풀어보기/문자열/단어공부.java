package 백준_단계별로풀어보기.문자열;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class 단어공부 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        temp = temp.toUpperCase();
        char[] arr = temp.toCharArray();
        int[] check = new int[26];
        for (int i = 0; i < arr.length; i++) {
            check[(int)arr[i]-65]+=1;
        }
        OptionalInt max = Arrays.stream(check).max();
        int cnt=0;
        int answer=0;
        for (int i = 0; i < check.length; i++) {
            if(check[i]==max.getAsInt())
            {
                cnt+=1;
                answer=i;

            }
        }
        if(cnt>1)
        {
            System.out.println("?");
        }
        else
        {
            char answer2 = (char)(answer+65);
            System.out.println(answer2);
        }

    }
}
