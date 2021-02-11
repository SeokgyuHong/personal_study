package 백준_단계별로풀어보기.문자열;

import java.util.Arrays;
import java.util.Scanner;

public class 알파벳찾기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] aplha = new int[300];
        Arrays.fill(aplha,-2);
        String temp = sc.nextLine();
        String[] temp2 = temp.split("");
        for(int i=97; i<=122; i++)
        {
            boolean flag = false;
            for (int j = 0; j < temp.length(); j++) {
                char a = temp2[j].charAt(0);
                if((int)a ==i){
                    flag=true;
                    if(aplha[i]==-2)
                    {
                        aplha[i]=j;
                    }
                }
            }
            if(flag==false)
            {
               if(aplha[i]==-2){
                   aplha[i]=-1;
               }
            }
        }
        for(int i=97; i<=122; i++)
        {
            System.out.print(aplha[i]+" ");
        }
    }
}
