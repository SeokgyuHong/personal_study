package 백준_단계별로풀어보기.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 수정렬하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(bf.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        List<Integer> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
            arr.add(Integer.parseInt(bf.readLine()));

        Collections.sort(arr);
        for(int i : arr)
            sb.append(i).append('\n');
        System.out.println(sb);
    }
}
