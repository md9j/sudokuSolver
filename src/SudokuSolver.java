import java.util.Scanner;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        System.out.println("  Sudoku Solver Thingy!");
        System.out.println("  ---------------------\n");
        // far future update for input with user taken picture of game board
        int[][] board = new int[GRID_SIZE][GRID_SIZE]; // create blank game board
        
        enterPuzzleNumbersRowByRow(board);
        
        if(solveBoard(board)){
            System.out.println("\nVictory, the board has been solved!");
            printBoard(board);
        }  // end solveBoard
        
        else{
            System.out.println("Sad face, this board could not be solved.");
        }  // end else solveBoard
    }  // end main{}

    //*** functions ***//

    private static void printBoard(int[][] board){
        
        for(int row = 0; row < GRID_SIZE; row++){
            if(row % 3 ==0){
                System.out.println(" -------------------------");
            }
            for(int col = 0; col < GRID_SIZE; col++){
                if(col % 3 == 0){
                    System.out.print(" |");
                }  // end if
                System.out.print(" " + board[row][col]);
                if(col == GRID_SIZE - 1){
                    System.out.print(" |");
                }  // end if
            }  // end for col
            System.out.println();
        }  // end for row
        System.out.println(" -------------------------");

    }  // end printBoard()
    
    // test if number is already in current row, yes = true, no = false
    private static boolean isNumberInRow(int[][] board, int number, int row){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[row][i] == number){
                return true;
            }  // end if
        }  // end for i
        return false;
    }  // end isNumberInRow()
    
    
    // test if number is already in current column, yes = true, no = false
    private static boolean isNumberInColumn(int[][] board, int number, int col){
        for(int i = 0; i < GRID_SIZE; i++){
            if(board[i][col] == number){
                return true;
            }  // end if
        }  // end for
        return false;
    }  // end isNumberInColumn
    
    // test if number is already in current 3x3 box, yes = true, no = false
    private static boolean isNumberInBox(int[][] board, int number, int row, int col){
        // vars to identify the (0,0) space within individual 3x3 box
        int boxRow = row - (row % 3);  // locate first space in row of 3x3 box
        int boxCol = col -(col % 3);  // locate first space in column of 3x3 box
    
        //  iterate through 3x3 box spaces
        for(int i = boxRow; i < boxRow + 3; i++){
            for(int j = boxCol; j < boxCol + 3; j++){
                if(board[i][j] == number){
                    return true;
                }  // end if == number
            }  // end for boxCol
        }  // end for boxRow
        return false;
    }  // end isNumberInBox
    
    // single call function to execute all functions to attempt board solution
    // if all functions return false, this will return true signifying the placement is valid
    private static boolean isValidPlacement(int[][] board, int number, int row, int col){
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, col) &&
                !isNumberInBox(board, number, row, col);
    }  // end isValidPlacement
    
    
    // recursive function to attempt entire board solution
    private static boolean solveBoard(int[][] board){
        for(int row = 0; row < GRID_SIZE; row++){
            for(int col = 0; col < GRID_SIZE; col++){
                if(board[row][col] == 0){
                    for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
                        if(isValidPlacement(board, numberToTry, row, col)){
                            board[row][col] = numberToTry;
                            if(solveBoard(board)){
                                return true;
                            }  // end recursive solveBoard()
                            else{
                                board[row][col] = 0;
                            }  // end else, !isValidPlacement
                            
                        }  // end if isValidPlacement
                        // if a number was not valid in recursive sequence, restart the
                        // numberToTry sequence after returning false
                        
                    }  // end for numberToTry
                    return false;
                }  // end if == 0
            }  // end for col
        }  // end for row
        System.out.println("Board is solvable");
        return true;
    }  // end solveBoard
    
    private static void printBoardMap(){
        System.out.println("\n   Board Map\n---------------\n* Numbers will be entered by rows (format = row-column)" +
                ":\nr1-1, r1-2,..., r1-9," +
                " ... r9-1, r9-2, ... r9-9\n");
        System.out.println("     1 2 3   4 5 6   7 8 9");
        System.out.println("   -------------------------");
        for(int row =1; row <= GRID_SIZE; row++){
            System.out.println("r" + row + " | 0 0 0 | 0 0 0 | 0 0 0 |");
            if(row % 3 == 0){
                System.out.println("   -------------------------");
            }  // end if
        }  // end for row
    }  // end printBoardMap

    private static void enterPuzzleNumbersRowByRow(int[][] board){
        System.out.println("Enter the numbers for your puzzle board one by one. Numbers will be entered by rows. " +
                "Please use the following board map as reference for where your entered number will be placed.");
        
        printBoardMap();
        
        System.out.println("Please enter the numbers for your game board, remember \n" +
                "that '0' represents a blank space that is intended to be solved. \n" +
                "The board will be checked for a solution once you complete your entries. " +
                "\nIf you created a puzzle that this program is unable to solve, \n" +
                "you will be notified.");
        
        Scanner scanner = new Scanner(System.in);
         for(int row = 1; row <= GRID_SIZE; row++){
             for(int col = 1; col <= GRID_SIZE; col++){
                 System.out.println("Enter r" + row + "-" + col);
                 board[row-1][col-1] = scanner.nextInt();
             }  // end for col
         }  // end for row
        scanner.close();
        printBoard(board);
    }  // end
    
    private static void enterPuzzleNumbersBySpecificLocation(int[][] board){
    
    }  // end enterPuzzleNumbersBySpecificLocation


}  // end SudokuSolver{}