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

        if (solveOTP(0)){
            System.out.println("Success");
            for (int i = 0; i < 9; i++){
                solved[i].printPiece();
            }
        }
        else {
            System.out.println("Failure");
        }
    }

    Boolean CheckEdges(PuzzlePiece piece, int position){

    }

    public Boolean solveOTP() {
        for (int i = 0; i < 9; i++){
            if (!used(piece[i])){

            }
        }
    }

    Boolean comparePieceEdges(Shape edgeOne, Shape edgeTwo){
        if (edgeOne == Shape.positiveCircle && edgeTwo == Shape.negativeCircle)
            return true;
        if (edgeOne == Shape.negativeCircle && edgeTwo == Shape.positiveCircle)
            return true;
        if (edgeOne == Shape.positiveSquare && edgeTwo == Shape.negativeSquare)
            return true;
        if (edgeOne == Shape.negativeSquare && edgeTwo == Shape.positiveSquare)
            return true;
        if (edgeOne == Shape.positiveTriangle && edgeTwo == Shape.negativeTriangle)
            return true;
        if (edgeOne == Shape.negativeTriangle && edgeTwo == Shape.negativeTriangle)
            return true;
        if (edgeOne == Shape.positiveTwoTriangle && edgeTwo == Shape.negativeTwoTriangle)
            return true;
        if (edgeOne == Shape.negativeTwoTriangle && edgeTwo == Shape.positiveTriangle)
            return true;
        return false;
    }

    public class PuzzlePiece {

        public String id;

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
            Shape newTop = this.topEdge;

            this.rightEdge = newRight;
            this.bottomEdge = newBottom;
            this.leftEdge = newLeft;
            this.topEdge = newTop;
        }

        public void printPiece(){
            System.out.println("ID: " + id + " top is: " +topEdge);
        }


    }

    enum Shape {
        positiveSquare,
        negativeSquare,
        positiveTriangle,
        negativeTriangle,
        positiveCircle,
        negativeCircle,
        positiveTwoTriangle,
        negativeTwoTriangle
    }




}
