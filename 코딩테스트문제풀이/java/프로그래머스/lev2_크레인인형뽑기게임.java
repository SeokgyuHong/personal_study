package coding_test;

import java.util.*;

public class lev2_크레인인형뽑기게임 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = new int[][] {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int[] moves = new int[] {1,5,3,5,1,2,1,4};
		Solution test = new Solution();
		System.out.println(test.solution(board, moves));
	}

}

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int cur =0;
        Deque<Integer>queue = new ArrayDeque<>();
        for(int i=0; i<moves.length; i++)
        {
         cur = moves[i]-1;
         int val=0;
         for(int j=0; j<board.length; j++)
         {
        	 if(board[j][cur]!=0)
        	 {
        		 val = board[j][cur];
        		 board[j][cur]=0;
        		 if(queue.peekLast()!=null)
        		 {
        			 int temp = queue.peekLast();
        			 if(temp == val)
        			 {
        				 queue.pollLast();
        				 answer+=1;
        			 }
        			 else
        			 {
        				 queue.offerLast(val); //마지막에삽입
        			 }
        		 }
        		 else
        		 {
        			 queue.offerLast(val);
        		 }
        		 break;
        	 }
         }
        }
        
        return answer;
    }
}