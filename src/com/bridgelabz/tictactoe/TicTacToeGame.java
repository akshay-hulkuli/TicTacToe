package com.bridgelabz.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	static Scanner sc = new Scanner(System.in);
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
	
	public static void main(String[] args) {
		System.out.println("Welcome to TIC-TAC-TOE game");
		int currentUser = getToss();
		char[] board = creatBoard();
		char playerLetter = selectLetter();
		char computerLetter = (playerLetter == 'X')?'O':'X';
		System.out.println("Player letter : "+playerLetter+"  Computer letter : "+ computerLetter);
		showBoard(board);
		board = makeMove(board,playerLetter);
		showBoard(board);
	}
}
