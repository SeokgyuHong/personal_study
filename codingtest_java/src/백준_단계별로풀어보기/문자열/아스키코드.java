package 백준_단계별로풀어보기.문자열;

import java.util.Scanner;

public class 아스키코드 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char temp = s.charAt(0);
        System.out.println((int)temp);
    }

}

