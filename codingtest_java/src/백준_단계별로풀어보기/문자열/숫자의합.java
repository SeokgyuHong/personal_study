package 백준_단계별로풀어보기.문자열;

import java.util.Scanner;

public class 숫자의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String Buffer = sc.nextLine();

        long answer = 0;
        String str = sc.nextLine();
        String[] list = str.split("");
        for(int i=0; i<list.length; i++)
        {
            int temp = Integer.parseInt(list[i]);
            answer += temp;
        }
        System.out.println(answer);

    }
}
