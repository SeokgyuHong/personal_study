package 백준_단계별로풀어보기.문자열;

import java.util.Arrays;
import java.util.Scanner;

public class 문자열반복 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int r = sc.nextInt();
            String s = sc.next();
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<chars.length; j++)
            {
                for (int k = 0; k < r; k++)
                    sb.append(chars[j]);
            }
            System.out.println(sb);
        }
    }
}
