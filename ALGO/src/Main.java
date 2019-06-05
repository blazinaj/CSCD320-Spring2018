import java.util.Scanner;

public class Main {
    public Integer[][] board;
    public Boolean solutionIsFound = false;
    public int sizeOfBoard;
    public int widthOfInnerBoard;
    public int heightOfInnerBoard;
    public static void main(String[] args){
        // [row][col]
        Scanner input = new Scanner(System.in);
        Integer sizeOfBoard = input.nextInt(36);
        Integer widthOfInnerBoard = input.nextInt(36);
        Integer heightOfInnerBoard = sizeOfBoard / widthOfInnerBoard;
        Integer[][] board = new Integer[sizeOfBoard][sizeOfBoard];


        for (int row = 0; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                Integer number = input.nextInt(36);
                board[row][col] = number;
            }
        }

        Main main = new Main(board, sizeOfBoard, widthOfInnerBoard, heightOfInnerBoard);

        boolean solution = main.solveSudoku();
        if (!solution){
            System.out.println("CANT! WONT!");
            main.printBoard();
        }else{
            System.out.println("Solution");
            main.printBoard();
        }
    }

    public Main(Integer[][] board, Integer sizeOfBoard, Integer widthOfInnerBoard, Integer heightOfInnerBoard){
        this.board = board;
        this.sizeOfBoard = sizeOfBoard;
        this.widthOfInnerBoard = widthOfInnerBoard;
        this.heightOfInnerBoard = heightOfInnerBoard;
    }

    Boolean checkAll(int rowToCheck, int colToCheck, int number) {
        Boolean numberIsGood = checkRow(rowToCheck, number) && checkColumn(colToCheck, number) && checkInnerBox(rowToCheck, colToCheck, number);

        return numberIsGood;
    }

    Boolean checkRow(int rowToCheck, int number) {
        for (int cellInThatRow = 0; cellInThatRow < sizeOfBoard; cellInThatRow++) {
            if (board[rowToCheck][cellInThatRow] == number) {
                return false;
            }
        }

        return true;
    }

    Boolean checkColumn(int columnToCheck, int number) {
        for (int cellInThatColumn = 0; cellInThatColumn < sizeOfBoard; cellInThatColumn++) {
            if (board[cellInThatColumn][columnToCheck] == number) {
                return false;
            }
        }

        return true;
    }

    Boolean checkInnerBox(int rowToCheck, int columnToCheck, int number) {
        int row = rowToCheck - rowToCheck % heightOfInnerBoard;
        int col = columnToCheck - columnToCheck % widthOfInnerBoard;

        for (int i = row; i < row + heightOfInnerBoard; i++) {
            for (int j = col; j < col + widthOfInnerBoard; j++) {
                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }



    public void printBoard(){
        for (int row = 0; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                System.out.print(Integer.toString(board[row][col], 36).toUpperCase());
                System.out.print(" ");
                if (col== sizeOfBoard / heightOfInnerBoard - 1){
                    System.out.print(" ");
                }
            }
            System.out.println();
            if (row  == sizeOfBoard / widthOfInnerBoard - 1){
                System.out.println();
            }
        }
    }

    Boolean solveSudoku() {

        for (int row = 0; row < this.sizeOfBoard; row++) {
            for (int column = 0; column < this.sizeOfBoard; column++) {
                if (this.board[row][column] == 0) {
                    for (int numberToCheck = 1; numberToCheck <= this.sizeOfBoard; numberToCheck++) {
                        if (checkAll(row, column, numberToCheck)) {
                            this.board[row][column] = numberToCheck;

                            if (solveSudoku()) {
                                return true;
                            }
                            else {
                                this.board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }

}