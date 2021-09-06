package com.bridgelabz.tictactoe;

public class TicTacToeGame {
	private char[] board;
	
	public TicTacToeGame() {
		board = new char[10];
		
	}
	public void creatBoard() {
		for(int i=0;i<10;i++) {
			board[i] = ' ';
		}
	}
	public static void main(String[] args) {
		System.out.println("Welcome to TIC-TAC-TOE game");
		TicTacToeGame myGame = new TicTacToeGame();
		myGame.creatBoard();
	}
}
