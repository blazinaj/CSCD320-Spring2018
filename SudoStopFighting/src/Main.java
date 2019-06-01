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

        boolean solution = SolveSoduku(0, 0);

        if (!solution){
            System.out.println("CANT! WONT!");
            return;
        }else{
            System.out.println("Solution");
            printBoard();
        }
    }

    public static Boolean SolveSoduku(int currentRow, int currentCol){

        int row = currentRow;
        int col = currentCol;

        if ( ++col >= sizeOfBoard){
            col = 0;
            row++;
        }

        if (row >= sizeOfBoard){
            solutionIsFound = true;
        }

        if (solutionIsFound){
            return true;
        }

        if (board[currentRow][currentCol] != 0) {
            return SolveSoduku(++currentRow, ++currentCol);
        }

        boolean numberMightWork = false;

        for (int i = 1; i <= sizeOfBoard; i++){
            numberMightWork = checkRow(currentRow, i);

            if (numberMightWork)
                numberMightWork = checkColumn(currentCol, i);

            if (numberMightWork)
                numberMightWork = checkInnerSquare(currentRow, currentCol, i);

            if (!numberMightWork){
                continue;
            }

            board[currentRow][currentCol] = i;

            boolean areWeDone = SolveSoduku(currentRow++, currentCol++);

            if (areWeDone) {
                return true;
            }
            else {
                board[currentRow][currentCol] = 0;
            }
        }
        return false;
    }


    public static Boolean checkRow(int rowToCheck, int numberToCheck){
        for (int columnPosition = 0; columnPosition < sizeOfBoard; columnPosition++){
            if (board[rowToCheck][columnPosition] == numberToCheck){
                return false;
            }
        }
        return true;
    }

    public static Boolean checkColumn(int colToCheck, int numberToCheck){
        for (int rowPosition = 0; rowPosition < sizeOfBoard; rowPosition++){
            if (board[colToCheck][rowPosition] == numberToCheck){
                return false;
            }
        }
        return true;
    }

    public static Boolean checkInnerSquare(int rowPosition, int colPosition, int numberToCheck){
        int startingCol = colPosition / widthOfInnerBoard * widthOfInnerBoard;
        int startingRow = rowPosition / heightOfInnerBoard * heightOfInnerBoard;

        for (int i = 0; i < widthOfInnerBoard; i++){
            for (int j = 0; j < heightOfInnerBoard; j++){
                if (board[startingRow + i][startingCol + j] == numberToCheck){
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(){
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
