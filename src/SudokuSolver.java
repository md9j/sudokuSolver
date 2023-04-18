public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        System.out.println("  Sudoku Solver Thingy!");

        // fill with content for game board
        // later update for user entry when program run
        // far future update for input with user taken picture of game board
        int[][] board = {
                {7,0,2,0,5,0,6,0,0},
                {0,0,0,0,0,3,0,0,0},
                {1,0,0,0,0,9,5,0,0},
                {8,0,0,0,0,0,0,9,0},
                {0,4,3,0,0,0,7,5,0},
                {0,9,0,0,0,0,0,0,8},
                {0,0,9,7,0,0,0,0,5},
                {0,0,0,2,0,0,0,0,0},
                {0,0,7,0,4,0,2,0,3}
        }; //end entire game board
        
        System.out.println("Starting Game Board:");
        printBoard(board);  // print initial game board
        
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
        
        for(int row =0; row < GRID_SIZE; row++){
            if(row % 3 ==0){
                System.out.println(" ------------------------");
            }
            for(int col = 0; col < GRID_SIZE; col++){
                if(col % 3 == 0){
                    System.out.print(" |");
                }
                System.out.print(" " + board[row][col]);
            }  // end for col
            System.out.println();
        }  // end for row
        System.out.println(" ------------------------");

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
        return true;
    }  // end solveBoard


}  // end SudokuSolver{}