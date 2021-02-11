package 백준_단계별로풀어보기.백트래킹;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class N과M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] arr = new boolean[n + 1];
        Arrays.fill(arr,false);
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=1; i<=n; i++)
        {
            if(arr[i]==false) {
                arr[i] = true;
                stack.push(i);
                backTracking(n,m,arr,stack);
                stack.pop();
                arr[i]=false;
            }
        }
    }
    public static void backTracking(int n,int m,boolean[] arr,Stack<Integer> stack){
        if(stack.size()==m)
        {
            Iterator ite = stack.iterator();
            while(ite.hasNext())
                System.out.print(ite.next()+" ");
            System.out.println();
        }
        for(int i=1; i<=n; i++)
        {
            if(arr[i]==false)
            {
                arr[i]=true;
                stack.push(i);
                backTracking(n,m,arr,stack);
                stack.pop();
                arr[i]=false;
            }

        }

    }




}
