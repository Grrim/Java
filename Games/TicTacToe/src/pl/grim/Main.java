package pl.grim;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("");
        System.out.println("Welcome in Tic Tac Toe game! Have fun!");
        System.out.println("Set a game board size: ");
        Scanner game = new Scanner(System.in);
        int n = game.nextInt();
        while (n < 3) {
            System.out.println("The size of game board must be more than three!");
            System.out.println("Set a game board size: ");
            n = game.nextInt();
        }
        char[][] board = new char[n][n];
        char player1 = 'X';
        char player2 = 'O';
        gameMode(whoIsTurn(player1,player2), board, n);
    }

    public static void checkRow(char[][] board){
        for (int i = 0; i < board.length; i++){
            for (int j = 1; j < board.length - 1; j++) {
                if (board[i][j] == 'X' && board[i][j-1] == 'X' && board[i][j+1] == 'X'){
                    System.out.println("Player X has won");
                    System.exit(0);
                }
                else if (board[i][j] == 'O' && board[i][j-1] == 'O' && board[i][j+1] == 'O'){
                    System.out.println("Player O has won");
                    System.exit(0);
                }
            }
        }
    }

    public static void checkColumn(char[][] board){
        for (int i = 1; i < board.length-1; i++){
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'X' && board[i-1][j] == 'X' && board[i+1][j] == 'X'){
                    System.out.println("Player X has won");
                    System.exit(0);
                }
                else if (board[i][j] == 'O' && board[i-1][j] == 'O' && board[i+1][j] == 'O'){
                    System.out.println("Player O has won");
                    System.exit(0);
                }
            }
        }
    }

    public static void checkLeftDiagonal(char[][] board){
        for (int i = 1; i < board.length-1; i++){
            for (int j = 1; j < board.length-1; j++) {
                if (board[i][j] == 'X' && board[i-1][j-1] == 'X' && board[i+1][j+1] == 'X'){
                    System.out.println("Player X has won");
                    System.exit(0);
                }
                else if (board[i][j] == 'O' && board[i-1][j-1] == 'O' && board[i+1][j+1] == 'O'){
                    System.out.println("Player O has won");
                    System.exit(0);
                }
            }
        }
    }

    public static void checkRightDiagonal(char[][] board){
        for (int i = 1; i < board.length-1; i++){
            for (int j = 1; j < board.length-1; j++) {
                if (board[i][j] == 'X' && board[i+1][j+1] == 'X' && board[i-1][j-1] == 'X'){
                    System.out.println("Player X has won");
                    System.exit(0);
                }
                else if (board[i][j] == 'O' && board[i+1][j+1] == 'O' && board[i-1][j-1] == 'O'){
                    System.out.println("Player O has won");
                    System.exit(0);
                }
            }
        }
    }

    public static void gameMode(int whoIsTurn, char[][] board, int n){
        int i = 0;
        while(i < n*n) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choose the row: ");
            int row = scan.nextInt();
            System.out.println("Choose the column: ");
            int col = scan.nextInt();
            while (row > n-1 || col > n-1){
                System.out.println("Incorrect value!");
                System.out.println("Choose the row: ");
                row = scan.nextInt();
                System.out.println("Choose the column: ");
                col = scan.nextInt();
            }
            while (row < 0 || col < 0){
                System.out.println("Incorrect value!");
                System.out.println("Choose the row: ");
                row = scan.nextInt();
                System.out.println("Choose the column: ");
                col = scan.nextInt();
            }
            if (whoIsTurn == 0) {
                if (board[row][col] == 'X' || board[row][col] == 'O')
                {
                    System.out.println("This field is taken! Choose another");
                }
                else {
                    board[row][col] = 'X';
                    printBoard(board);
                    checkRow(board);
                    checkColumn(board);
                    checkLeftDiagonal(board);
                    checkRightDiagonal(board);
                    whoIsTurn = 1;
                    i = i + 1;
                }
            } else {
                if (board[row][col] == 'X' || board[row][col] == 'O')
                {
                    System.out.println("This field is taken! Choose another");
                }
                else {
                    board[row][col] = 'O';
                    printBoard(board);
                    checkRow(board);
                    checkColumn(board);
                    checkLeftDiagonal(board);
                    checkRightDiagonal(board);
                    whoIsTurn = 0;
                    i = i + 1;
                }
            }
        }
        System.out.println("Remis!");
    }

    public static int whoIsTurn(char player1, char player2){
        Random generator = new Random();
        int random = generator.nextInt(2);
        if (random == 0){
            System.out.println("Player X starts");
        }
        else {
            System.out.println("Player O starts");
        }
        return random;
    }

    public static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print("\t " + i + ":");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print(" " + i + ":\t|");
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 'X'){
                    board[i][j] = 'X';
                }
                else if (board[i][j] == 'O'){
                    board[i][j] = 'O';
                }
                else {
                    board[i][j] = ' ';
                }
                System.out.print(" " + board[i][j] + "\t|");;

            }
            System.out.println();
        }
    }
}
