package 분리집합;
import java.io.*;
import java.util.*;

public class 피리부는사나이 {
	static class Node{
		int x;
		int y;
		public Node(int x, int y)
		{
			this.x = x;
			this.y =y;
		}
	}
	static char[][] arr;
	static int[] parent;
	static char[] dir = {'U','R','D','L'};
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int start=1;

	public static void main(String[] args) throws IOException{

		// TODO Auto-generated method stub
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Node> queue = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		int n =Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();
		arr = new char[n][m];
		parent = new int[n*m];
		int cnt=0;
		for(int i=0; i<n; i++)
		{
			String temp = br.readLine();
			for(int j=0; j<m; j++)
			{
				arr[i][j] = temp.charAt(j);
				
			}
		}
		for(int i=0; i<n*m; i++)
			parent[i]=i;

		for(int i =0; i<n; i++)
		{
			for(int j=0; j<m; j++)
			{
				int idx = 0;
				for(int k=0; k<dir.length; k++)
				{
					if(arr[i][j]==dir[k])
					{
						idx=k;
						break;
					}
				}
				int dir_x = i+dx[idx];
				int dir_y = j+dy[idx];
				int a = find_parent(i*m+j);
				int b = find_parent(dir_x*m+dir_y);
				if(a<b)
					parent[b] =a;
				else
					parent[a] =b;

				
			}
	
		}
		for(int k=0; k<m*n; k++)
			set.add(find_parent(k));
		
		System.out.println(set.size());

	}
	static int find_parent(int x)
	{
		if(parent[x]!=x)
			return parent[x] = find_parent(parent[x]);
		else
			return parent[x];
			
	}


}
