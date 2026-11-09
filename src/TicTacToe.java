import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    
        String playAgain = "";

        do {
            
             playGame(scan);
            System.out.println("Play again? (y/n)");
            playAgain = scan.nextLine();
        } while (playAgain.equalsIgnoreCase("y"));

    }

    static char[][] createBoard (){
        char[][] board = new char[3][3];
        //rellenar el tablero 
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = '-';
            }
        }

        return board;
    } 

    static void playGame(Scanner scan ){
        char[][] board = createBoard();

        char currentPlayer = 'X';

        while (!isFull(board)) {

            printBoard(board);
            System.out.println("Player " +currentPlayer +"'s turn");
            int index = readMove(scan, board);
            int row = index / 3;
            int col = index % 3;
            board[row][col] = currentPlayer;

            
            if (hasWon(board, currentPlayer)) {
                
                break;
            }

            if (currentPlayer == 'X') {
                currentPlayer = 'O';
            }else{
                currentPlayer = 'X';
            }

            
        }

        printBoard(board);
        if (hasWon(board, currentPlayer)) {
            System.out.println("Player " + currentPlayer +" wins!");
        }else{
            System.out.println("It's a draw!");
        }
    }

    static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            // pinta la linea de casillas
            for (int col = 0; col < board[row].length; col++) {
                // System.out.println();
                System.out.print(" " + board[row][col] + " ");
                if (col < board[row].length - 1) {
                    System.out.print("|");

                }
            }

            System.out.println();
            // si no es la ultima fila, pinta un separador y termina
            if (row < board.length - 1) {
                System.out.println("---+---+---");
            }
        }
    }

    static int readMove(Scanner scan, char[][] board) {

        while (true) {
            System.out.println("Cell 1-9");
            String num = scan.nextLine();

            try {
                int col;
                int row;
                int index;
                int cell = Integer.parseInt(num);
                if (cell < 1 || cell > 9) {
                    System.out.println("Only numbers from 1 to 9.");
                    continue;
                }
                index = cell - 1;
                row = index / 3;
                col = index % 3;

                if (board[row][col] != '-') {
                    System.out.println("That cell is already taken");
                    continue;
                }
                return index;

            } catch (NumberFormatException e) {
                System.out.println("That's not a number.");
            }
        }
    }

    static boolean isFull(char[][] board) {

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean hasWon(char[][] board,char currentPlayer){
         for (int row = 0; row < board.length; row++) {
            if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
                return true;
            }
        }

        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
                return true;
            }
        }

        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer ) {
            return true;
        }

         if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer ) {
            return true;
        }
        return false;
    }
}
