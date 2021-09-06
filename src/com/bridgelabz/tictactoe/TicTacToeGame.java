package com.bridgelabz.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	public static char[] creatBoard() {
		char[] board = new char[10];
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
		return board;
	}
	
	
	public static char selectLetter() {
		Scanner sc = new Scanner(System.in);
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
			if(i%3 == 0) System.out.println();
			System.out.print(board[i]);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to TIC-TAC-TOE game");
		char[] board = creatBoard();
		char playerLetter = selectLetter();
		char computerLetter = (playerLetter == 'X')?'O':'X';
		System.out.println("Player letter : "+playerLetter+"  Computer letter : "+ computerLetter);
		showBoard(board);
	}
}
