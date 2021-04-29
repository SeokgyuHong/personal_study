package 카카오2020인턴;


import java.io.*;
import java.util.*;

public class 경주로건설 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] array = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
		int temp =Solution3.solution(array);
		System.out.println(temp);
	}

	
}


class Solution3 {
	static int[][] dp;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	static boolean flag = false;
    public static int solution(int[][] board) {
    	dp = new int[board.length][board.length];
    	visited= new boolean[board.length][board.length];
    	Queue<Node> queue = new LinkedList<>();

    	for(int i=0; i<dp.length; i++)
    	{
    		for(int j=0; j<dp.length; j++)
    			dp[i][j]=Integer.MAX_VALUE;
    	}
    	
    	queue.offer(new Node(0,0,0,0,0));
    	dp[0][0] = 0;
    	int answer = Integer.MAX_VALUE;
    	while(!queue.isEmpty())
    	{
    		Node temp = queue.poll();
    		if(temp.cur_x ==board.length-1 && temp.cur_y == board.length-1)
    		{
    			if(answer>temp.weight)
    				answer=temp.weight;
    			continue;
    		}
    		for(int i=0; i<dx.length; i++)
    		{
    			int dir_x = temp.cur_x+dx[i];
    			int dir_y = temp.cur_y+dy[i];
    			if(0<=dir_x && dir_x<board.length && 0<=dir_y && dir_y<board.length)
    			{
    				if(board[dir_x][dir_y]!=1 && !(dir_x ==temp.bef_x && dir_y == temp.bef_y))
    				{
    					int weight = temp.weight;
    					if(temp.bef_x!=dir_x && temp.bef_y!=dir_y)
    					{
    						weight+=500;
    					}
    					weight+=100;
    					if(weight<= dp[dir_x][dir_y] || (temp.cur_x==0 && temp.cur_y==0))
    					{
    						dp[dir_x][dir_y]=weight;
    						queue.offer(new Node(temp.cur_x,temp.cur_y,dir_x,dir_y,weight));
    					}
    				}
    			}
    		}
    	}
    	
    	
    	return answer;
    	
    	
    }

}

class Node{
	int bef_x;
	int bef_y;
	int cur_x;
	int cur_y;
	int weight;
	public Node(int bef_x, int bef_y,int cur_x, int cur_y,int weight)
	{
		this.bef_x = bef_x;
		this.bef_y = bef_y;
		this.cur_x = cur_x;
		this.cur_y = cur_y;
		this.weight = weight;
		
	}
}

