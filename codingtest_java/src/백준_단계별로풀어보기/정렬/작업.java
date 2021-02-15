package 백준_단계별로풀어보기.정렬;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 작업 {
    static class Node{
        int time;
        int idx;

        public Node(int time, int idx) {
            this.time = time;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        List<Node> arr = new ArrayList<>();
        arr.add(new Node(0, 0));
        int n = Integer.parseInt(br.readLine());
        int cur,next,temp=0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            cur = Integer.parseInt(st.nextToken());
            next = Integer.parseInt(st.nextToken());
            if(next!=0)
            {
                int temp_max=0;
                while (st.hasMoreTokens()) {
                    temp = Integer.parseInt(st.nextToken());
                    if(arr.get(temp).time>temp_max)
                        temp_max=arr.get(temp).time;
                }
                arr.add(new Node(cur+temp_max,i));
            }
            else
            {
                arr.add(new Node(cur, i));
            }
        }

        int max=0;
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).time>max)
                max=arr.get(i).time;
        }
        System.out.println(max);

    }
}
