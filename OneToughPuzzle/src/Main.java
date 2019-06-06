public class Main {

    PuzzlePiece[] pieces = new PuzzlePiece[9];

    PuzzlePiece[] solved = new PuzzlePiece[9];

    public Main(){
        pieces[0] = new PuzzlePiece("0", Shape.positiveSquare, Shape.negativeTwoTriangle, Shape.positiveTwoTriangle, Shape.negativeSquare);
        pieces[1] = new PuzzlePiece("1", Shape.positiveTriangle, Shape.negativeTriangle, Shape.positiveTriangle, Shape.negativeSquare);
        pieces[2] = new PuzzlePiece("2", Shape.positiveTriangle, Shape.negativeSquare, Shape.positiveSquare, Shape.negativeCircle);
        pieces[3] = new PuzzlePiece("3", Shape.positiveSquare, Shape.negativeCircle, Shape.positiveCircle, Shape.negativeTriangle);
        pieces[4] = new PuzzlePiece("4", Shape.positiveSquare, Shape.negativeTwoTriangle, Shape.positiveCircle, Shape.negativeSquare);
        pieces[5] = new PuzzlePiece("5", Shape.positiveTwoTriangle, Shape.negativeSquare, Shape.positiveSquare, Shape.negativeCircle);
        pieces[6] = new PuzzlePiece("6", Shape.positiveCircle, Shape.negativeTwoTriangle, Shape.positiveTriangle, Shape.negativeTwoTriangle);
        pieces[7] = new PuzzlePiece("7", Shape.positiveSquare, Shape.negativeCircle, Shape.positiveTriangle, Shape.negativeSquare);
        pieces[8] = new PuzzlePiece("8", Shape.positiveTwoTriangle, Shape.negativeTriangle, Shape.positiveCircle, Shape.negativeCircle);

        if (solveOTP()){
            System.out.println("Success");
            this.printAll();
        }
        else {
            System.out.println("Failure");
            this.printAll();
        }
    }

    void place(PuzzlePiece piece){
        for (int i = 0; i < 9; i++) {
            if (solved[i] == null){
                solved[i] = piece;
                piece.isUsed = true;
                break;
            }
        }
    }

    boolean fitsTop(PuzzlePiece piece, int index){
        if (index < 3){
            return true;
        }

        int topValue = piece.topEdge.getIntValue();
        int bottomValue = solved[index - 3].bottomEdge.getIntValue();

        if (topValue - bottomValue == 0){
            return true;
        }
        return false;
    }

    boolean fitsLeft(PuzzlePiece piece, int index){
        if (index < 1){
            return true;
        }

        if (piece.leftEdge.getIntValue() - solved[index - 1].rightEdge.getIntValue() == 0){
            return true;
        }
        return false;
    }

    boolean fits(PuzzlePiece piece){

        int index = -1;

        for (int i = 0; i < 9; i++) {
            if (solved[i] == null){
                index = i;
                break;
            }
        }

        if (!fitsTop(piece, index)){
            return false;
        }

        if (!fitsLeft(piece, index)){
            return false;
        }

        return true;
    }

    public Boolean solveOTP() {
        // Loop through all pieces
        for (int i = 0; i < 9; i++){
            // If piece is not being used
            if (!pieces[i].isUsed){
                // Check rotations
                for (int rotation = 0; rotation < 4; rotation++){
                    if (fits(pieces[i])){
                        place(pieces[i]);
                        if(solveOTP()){
                            return true;
                        }
                    } else {
                        pieces[i].rotateClockwise();
                    }
                }
            }
        }
        return false;
    }

    public void printAll(){

        System.out.println("              " + solved[0].topEdge + "                           " + solved[1].topEdge + "                       " + solved[2].topEdge);
        System.out.println(solved[0].leftEdge + "   0   " + solved[0].rightEdge + "   " + solved[1].leftEdge + "   1   " + solved[2].rightEdge + "   " + solved[2].leftEdge + "   2   " + solved[2].rightEdge);
        System.out.println("              " + solved[0].bottomEdge + "                              " + solved[1].bottomEdge + "                              " + solved[2].bottomEdge);
        System.out.println("              " + solved[3].topEdge + "                           " + solved[4].topEdge + "                       " + solved[5].topEdge);
        System.out.println(solved[3].leftEdge + "   3   " + solved[3].rightEdge + "   " + solved[4].leftEdge + "   4   " + solved[4].rightEdge + "   " + solved[5].leftEdge + "   5   " + solved[5].rightEdge);
        System.out.println("              " + solved[3].bottomEdge + "              " + solved[4].bottomEdge + "              " + solved[5].bottomEdge);
        System.out.println("              " + solved[6].topEdge + "                                 " + solved[7].topEdge + "                                 " + solved[8].topEdge);
        System.out.println(solved[6].leftEdge + "   6   " + solved[6].rightEdge + "   " + solved[7].leftEdge + "   7   " + solved[7].rightEdge + "   " + solved[8].leftEdge + "   8   " + solved[8].rightEdge);
        System.out.println("              " + solved[6].bottomEdge + "                             " + solved[7].bottomEdge + "                                   " + solved[8].bottomEdge);

        System.out.println();

    }

    public class PuzzlePiece {

        public String id;

        public boolean isUsed = false;

        public Shape topEdge;
        public Shape bottomEdge;
        public Shape rightEdge;
        public Shape leftEdge;

        public PuzzlePiece(String id, Shape topEdge, Shape bottomEdge, Shape rightEdge, Shape leftEdge){
            this.id = id;
            this.topEdge = topEdge;
            this.bottomEdge = bottomEdge;
            this.rightEdge = rightEdge;
            this.leftEdge = leftEdge;
        }

        public void rotateClockwise(){
            Shape newRight = this.topEdge;
            Shape newBottom = this.rightEdge;
            Shape newLeft = this.bottomEdge;
            Shape newTop = this.leftEdge;

            this.rightEdge = newRight;
            this.bottomEdge = newBottom;
            this.leftEdge = newLeft;
            this.topEdge = newTop;
        }
    }

    enum Shape {
        positiveSquare(1),
        negativeSquare(-1),
        positiveTriangle(2),
        negativeTriangle(-2),
        positiveCircle(3),
        negativeCircle(-3),
        positiveTwoTriangle(4),
        negativeTwoTriangle(-4);

        private int intValue;

        public int getIntValue(){
            return this.intValue;
        }

        private Shape(int intValue){
            this.intValue = intValue;
        }
    }




}
