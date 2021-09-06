package com.bridgelabz.tictactoe;

import java.util.*;


public class TicTacToeGame {
	private static int currentPlayer;
	private static char computerLetter,playerLetter; 
	static Scanner sc = new Scanner(System.in);
	private static int positionsLeft = 9;
	private static int [][] winningPositions = {{1,2,3}, {4,5,6}, {7,8,9}, {1,4,7}, {2,5,8}, {3,6,9},{1,5,9}, {3,5,7}};
	public static char[] creatBoard() {
		char[] board = new char[10];
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
		return board;
	}
	
	
	public static char selectLetter() {
		
		System.out.println("select letter 1:X or 2:O");
		int option = sc.nextInt();
		if(option != 1 && option !=2) {
			System.out.println("invalid oprion");
			return ' ';
		}
		return (option == 1)?'X':'O';
		
	}
	
	public static void showBoard(char[] board) {
		System.out.println("Current status of board : ");
		for(int i=1;i<10;i++) {
			System.out.print(board[i]);
			if(i%3 == 0) {
				System.out.println();
				continue;
			}
			System.out.print(" | ");
			
		}
	}
	
	public static char[] makeMove(char[] board, char playerLetter) {
		int position;
		while(true) {
			System.out.println("Enter position(1-9) to make your move");
			position = sc.nextInt();
			if(board[position] != ' ') System.out.println("Position is not available");
			else break;
		}
		
		board[position] = playerLetter;
		positionsLeft--;
		return board;
	}
	
	public static int  getToss() {
		System.out.println("select  0:tail 1: head");
		int option = sc.nextInt();
		int tossResult = (int) (Math.floor(Math.random()*10)) %2;
		if(option == tossResult) {
			System.out.println("You won the toss. you will play first");
			return 1;
		}
		else{
			System.out.println("You have lost the toss.");
			return 0;
		}
	}
	
	public static void resultFinder(char[] board, char currentLetter) {
		if(positionsLeft == 0) {
			System.out.println("the game ended in a tie");
			showBoard(board);
			return;
		}
		
		for(int i=0;i<8;i++) {
			if(board[winningPositions[i][0]] == currentLetter && board[winningPositions[i][1]] == currentLetter && board[winningPositions[i][2]] == currentLetter) {
				System.out.println();
				System.out.println("****The "+(currentPlayer == 1?"User":"Computer")+" is the winner****");
				showBoard(board);
				return;
			}
		}
		
		currentPlayer = (currentPlayer==0)?1:0;
	}
	
	public static char[] computerMove(char[] board, char computerLetter) {
		if(findWinningMove(board)) return board;
		if(findBlockingMove(board)) return board;
		if(getCorner(board)) return board;
		
		return board;
		
		
	}
	
	public static boolean findWinningMove(char[] board) {
		int count = 0;
		for(int i=0; i<8;i++) {
			count = 0;
			
			for(int j=0;j<3;j++) {
				if(board[winningPositions[i][j]] == computerLetter) count++;
			}
			
			if(count == 2) {
				for(int j=0;j<3;j++) {
					if(board[winningPositions[i][j]]== ' ') {
						board[winningPositions[i][j]] = computerLetter;
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	public static boolean findBlockingMove(char[] board) {
		int count = 0;
		for(int i=0; i<8;i++) {
			count = 0;
			for(int j=0;j<3;j++) {
				if(board[winningPositions[i][j]] == playerLetter) count++;
			}
			
			if(count == 2) {
				for(int j=0;j<3;j++) {
					if(board[winningPositions[i][j]]==' ') {
						board[winningPositions[i][j]] = computerLetter ;
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	public static boolean getCorner(char[] board) {
		int[] corners = {1,3,7,9};
		for(int i=0;i<corners.length;i++) {
			if(board[corners[i]] == ' ') {
				board[corners[i]] = computerLetter;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to TIC-TAC-TOE game");
		currentPlayer = getToss();
		char[] board = creatBoard();
		playerLetter = selectLetter();
		computerLetter = (playerLetter == 'X')?'O':'X';
		System.out.println("Player letter : "+playerLetter+"  Computer letter : "+ computerLetter);
		showBoard(board);
		board = makeMove(board,playerLetter);
		showBoard(board);

		board = computerMove(board, computerLetter);
		showBoard(board);
		char currentLetter = (currentPlayer == 1)? playerLetter:computerLetter;
		resultFinder(board, currentLetter);
		
	}
}
