import java.util.Scanner;

public class Main {

    public int sizeOfBoard;
    public String[][] board;
    int numberOccupied;

    public static void main(String[] args){
        // [row][col]
        Scanner input = new Scanner(System.in);
        int sizeOfBoard = input.nextInt();
        int numberOccupied = input.nextInt();
        String[][] board = new String[sizeOfBoard][sizeOfBoard];

        for (int num = 0; num < numberOccupied; num++){
            for (int col = 0; col < sizeOfBoard; col++){
                board[num][col] = input.next();
            }
        }

        for (int row = numberOccupied; row < sizeOfBoard; row++){
            for(int col = 0; col < sizeOfBoard; col++){
                board[row][col] = "*";
            }
        }

        Main main = new Main(board, sizeOfBoard, numberOccupied);

        //main.printBoard();

        boolean solution = main.solve();
        if (!solution){
            System.out.println("CANT! WONT!");
            main.printBoard();
        }else{
            System.out.println("Solution");
            main.printBoard();
        }
    }

    public Main(String[][] board, Integer sizeOfBoard, int numberOccupied){
        this.board = board;
        this.sizeOfBoard = sizeOfBoard;
        this.numberOccupied = numberOccupied;
    }

    Boolean checkAll(int rowToCheck, int colToCheck) {
        Boolean numberIsGood = checkRow(rowToCheck) && checkColumn(colToCheck) && checkDiagonal(rowToCheck, colToCheck);

        return numberIsGood;
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
//        for (int i = row; i < row + heightOfInnerBoard; i++) {
//            for (int j = col; j < col + widthOfInnerBoard; j++) {
//                if (board[i][j].equals(spot)) {
//                    return false;
//                }
//            }
//        }

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
                        else {
                            this.board[row][column] = "*";
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

}