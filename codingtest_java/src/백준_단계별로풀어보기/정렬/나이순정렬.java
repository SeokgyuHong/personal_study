package 백준_단계별로풀어보기.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class 나이순정렬{
    static class Person implements Comparable<Person> {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        ArrayList<Person> arr = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr.add(new Person(age, name));
        }
        Collections.sort(arr);
        for(int i=0; i<arr.size(); i++)
            System.out.println(arr.get(i).age+" " +arr.get(i).name);
    }
}
