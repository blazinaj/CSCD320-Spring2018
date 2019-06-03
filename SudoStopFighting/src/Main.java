import java.util.Scanner;

public class Main {

    public static Integer[][] board;
    public static Boolean solutionIsFound = false;
    public static int sizeOfBoard;
    public static int widthOfInnerBoard;
    public static int heightOfInnerBoard;
    public static void main(String[] args){
        // [row][col]
        Scanner input = new Scanner(System.in);
        sizeOfBoard = Integer.parseInt(input.next());
        widthOfInnerBoard = Integer.parseInt(input.next());
        heightOfInnerBoard = sizeOfBoard / widthOfInnerBoard;

        board = new Integer[sizeOfBoard][sizeOfBoard];

        for (int row = 0; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                board[row][col] = Integer.parseInt(input.next(), 36);
            }
        }

        boolean solution = SolveSoduku(board);
        printBoard(board);
        if (!solution){
            System.out.println("CANT! WONT!");
            return;
        }else{
            System.out.println("Solution");
            printBoard(board);
        }
    }
    
    public static boolean solve() {
    	return SolveSoduku(board);
    }

    public static Boolean SolveSoduku(Integer[][] solveBoard){
    	
    	Integer[] nextEmpty = findNextEmpty(solveBoard);
    	
    	if (nextEmpty == null) {
    		printBoard(solveBoard);
    		return true;
    	}
    	
    	for (int i = 1; i < sizeOfBoard; i++) {
    		if (isSafe(solveBoard, nextEmpty, i)) {
    			solveBoard[nextEmpty[0]][nextEmpty[1]] = i;
    			if (SolveSoduku(solveBoard)) {
    				printBoard(solveBoard);
    				System.out.println();
    				return true;
    			}
    			else {
    				solveBoard[nextEmpty[0]][nextEmpty[1]] = 0;
    				printBoard(solveBoard);
    				System.out.println();
    			}
    		}
    	}
    	return false;
    	
    }
    
    static Integer[] findNextEmpty(Integer[][] solveBoard) {
    	Integer[] res = new Integer[2];
    	for (int i = 0; i < solveBoard.length; i++) {
    		for (int j = 0; j < solveBoard.length; j++) {
    			if (solveBoard[i][j] == 0) {
    				res[0] = i;
    				res[1] = j;
    				return res;
    			}
    		}
    	}
    	return null;
    }

    
    static Boolean isSafe(Integer[][] solveBoard, Integer[] nextEmpty, int numberToCheck) {
    	int row = nextEmpty[0];
    	int col = nextEmpty[1];
    	
    	for (int i = 0; i < solveBoard.length; i++) {
    		if (solveBoard[row][i] == numberToCheck || solveBoard[i][col] == numberToCheck)
    			return false;
    	}
    	
        int startingCol = col / widthOfInnerBoard;
        int startingRow = row / heightOfInnerBoard;

        for (int i = heightOfInnerBoard * startingRow; i < heightOfInnerBoard; i++){
            for (int j = widthOfInnerBoard * startingCol; j < widthOfInnerBoard; j++){
                if (solveBoard[i][j] == numberToCheck){
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(Integer[][] board){
        for (int row = 0; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                    System.out.print(Integer.toString(board[row][col], 36).toUpperCase());
//                        if (col % widthOfInnerBoard == 0){
//                            System.out.print(" ");
//                        }

            }
            System.out.println();
        }
    }

}
