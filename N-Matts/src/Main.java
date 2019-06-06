import java.util.Scanner;

public class Main {

    public int sizeOfBoard;
    public String[][] board;
    int numberOccupied;

    public static void main(String[] args){
        // [row][col]
        Scanner input = new Scanner(System.in);

        while(true) {

            int sizeOfBoard = input.nextInt();

            if (sizeOfBoard == 0) {
                break;
            }

            int numberOccupied = input.nextInt();
            String[][] board = new String[sizeOfBoard][sizeOfBoard];

            for (int num = 0; num < numberOccupied; num++) {
                for (int col = 0; col < sizeOfBoard; col++) {
                    board[num][col] = input.next();
                }
            }

            for (int row = numberOccupied; row < sizeOfBoard; row++) {
                for (int col = 0; col < sizeOfBoard; col++) {
                    board[row][col] = "*";
                }
            }


            Main main = new Main(board, sizeOfBoard, numberOccupied);

            //main.printBoard();

            boolean solution = main.solve();
            if (!solution) {
                System.out.println("FUTILE!");
            } else {
                main.printBoard();
            }
        }
    }

    public Main(String[][] board, Integer sizeOfBoard, int numberOccupied){
        this.board = board;
        this.sizeOfBoard = sizeOfBoard;
        this.numberOccupied = numberOccupied;
    }

    Boolean checkAll(int rowToCheck, int colToCheck) {
        if (!checkRow(rowToCheck))
            return false;
        if (!checkColumn(colToCheck))
            return false;
        if (checkDiagonal(rowToCheck, colToCheck))
            return false;

        return true;
    }

    Boolean checkRow(int rowToCheck) {
        for (int cellInThatRow = 0; cellInThatRow < sizeOfBoard; cellInThatRow++) {
            if (!board[rowToCheck][cellInThatRow].equals("*")) {
                return false;
            }
        }

        return true;
    }

    Boolean checkColumn(int columnToCheck) {
        for (int cellInThatColumn = 0; cellInThatColumn < sizeOfBoard; cellInThatColumn++) {
            if (!board[cellInThatColumn][columnToCheck].equals("*")) {
                return false;
            }
        }

        return true;
    }

    Boolean checkDiagonal(int rowToCheck, int columnToCheck) {

        // check up left
        for (int row = rowToCheck; row < sizeOfBoard; row++){
            for (int col = columnToCheck; col < sizeOfBoard; col++) {
                if (row - 1 >= 0 && col -1 >= 0){
                    if (!board[row - 1][col - 1].equals("*")){
                        return false;
                    }
                }
            }
        }

        // check up right
        for (int row = rowToCheck; row < sizeOfBoard; row++){
            for (int col = columnToCheck; col < sizeOfBoard; col++) {
                if (row - 1 >= 0 && col + 1 < sizeOfBoard){
                    if (!board[row - 1][col + 1].equals("*")){
                        return false;
                    }
                }
            }
        }

        // check down left
        for (int row = rowToCheck; row < sizeOfBoard; row++){
            for (int col = columnToCheck; col < sizeOfBoard; col++) {
                if (row + 1 < sizeOfBoard && col - 1 >= 0){
                    if (!board[row + 1][col - 1].equals("*")){
                        return false;
                    }
                }
            }
        }

        // check down right
        for (int row = rowToCheck; row < sizeOfBoard; row++){
            for (int col = columnToCheck; col < sizeOfBoard; col++) {
                if (row + 1 < sizeOfBoard && col + 1 < sizeOfBoard){
                    if (!board[row + 1][col + 1].equals("*")){
                        return false;
                    }
                }
            }
        }
        return true;
    }



    public void printBoard(){
        for (int row = 0; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                System.out.print(board[row][col]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    Boolean solve() {

        for (int row = numberOccupied; row < this.sizeOfBoard; row++) {
            for (int column = 0; column < this.sizeOfBoard; column++) {
                if (this.board[row][column].equals("*")) {
                    if (checkAll(row, column)) {
                        this.board[row][column] = "M";
                        if (solve()) {
                            return true;
                        }
                        this.board[row][column] = "*";
                        return false;
                    }
                  //return false;
                }
            }
        }

        return true;
    }

}