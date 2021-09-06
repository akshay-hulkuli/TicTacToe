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
			System.out.println("\nEnter position(1-9) to make your move");
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
			System.out.println("You have lost the toss. Computer will play first");
			return 0;
		}
	}
	
	public static boolean resultFinder(char[] board, char currentLetter) {

		for(int i=0;i<8;i++) {
			if(board[winningPositions[i][0]] == currentLetter && board[winningPositions[i][1]] == currentLetter && board[winningPositions[i][2]] == currentLetter) {
				System.out.println();
				System.out.println("\n****The "+(currentPlayer == 1?"User":"Computer")+" is the winner****");
				return true;
			}
		}
		if(positionsLeft == 0) {
			System.out.println("\n*****the game ended in a tie******");
			return true;
		}
		currentPlayer = (currentPlayer==0)?1:0;
		return false;
	}
	
	
	public static char[] computerMove(char[] board, char computerLetter) {
		positionsLeft--;
		System.out.println("\nComputer has player its move");
		if(findWinningMove(board)) return board;
		if(findBlockingMove(board)) return board;
		if(getCorner(board)) return board;
		if(getCenter(board)) return board;
		getSides(board);
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
	
	public static boolean getCenter(char[] board) {
		int center = 5;
		if(board[center] == ' ') {
			board[center] = computerLetter;
			return true;
		}
		return false;
	}
	
	public static void getSides(char[] board) {
		int[] sides = {2,4,6,8};
		for(int i=0;i<sides.length;i++) {
			if(board[sides[i]] == ' ') {
				board[sides[i]] = computerLetter;
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to TIC-TAC-TOE game");
			
			char[] board = creatBoard();
			positionsLeft = 9;
			playerLetter = selectLetter();
			computerLetter = (playerLetter == 'X')?'O':'X';
			System.out.println("Player letter : "+playerLetter+"  Computer letter : "+ computerLetter);
			currentPlayer = getToss();
			showBoard(board);
			while(true) {
				if(currentPlayer == 1) makeMove(board, playerLetter);
				else computerMove(board, computerLetter);
				showBoard(board);
				char currentLetter = (currentPlayer == 1)? playerLetter:computerLetter;
				if(resultFinder(board, currentLetter)) break;
			}
			System.out.println();
			
	}
}
