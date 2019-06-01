public class NMatt {
    int N;

    public NMatt(int numOfMatts){
        this.N = numOfMatts;
        if (this.N < 1){
            System.out.println("FUTILE");
            System.exit(0);
        }
    }

    void printSolution(int board[][]){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++)
                System.out.print("" + board[i][j]);
            System.out.println();
        }
    }

    boolean isSafe(int board[][], int row, int col){
        int i, j;

        // Check left side
        for (i = 0; i < col; i++){
            if (board[row][i] == 1){
                return false;
            }
        }

        // check upper left diag
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--){
            if (board[i][j] == 1) {
                return false;
            }
        }

        // check lower left diag
        for (i = row, j = col; j >= 0 && i < N; i++, j--){
            if (board[i][j] == 1){
                return false;
            }
        }

        return true;
    }

    boolean solve(int board[][], int col){
        // base case
        if (col >= N){
            return true;
        }

        // try matts in all rows 1 at a time
        for (int i = 0; i < N; i++) {
            // can matt be placed on board[i][col]?
            if (isSafe(board, i, col)){
                // place the matt
                board[i][col] = 1;

                if (solve(board, col + 1) == true){
                    return true;
                }

                // the backtrack
                board[i][col] = 0;
            }
        }

        // no matts could be placed
        return false;
    }

    boolean drive(int[][] board) {
        if (solve(board, 0) == false){
            System.out.println("FUTILE!");
            return false;
        }

        printSolution(board);
        return true;
    }
}
