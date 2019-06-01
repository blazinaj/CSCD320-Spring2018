public class Main {
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

    public void solvePuzzle(){
        PuzzlePiece[] pieces = new PuzzlePiece[9];

        PuzzlePiece[] solved = new PuzzlePiece[9];

        pieces[0] = new PuzzlePiece("0", Shape.positiveSquare, Shape.negativeTwoTriangle, Shape.positiveTwoTriangle, Shape.negativeSquare);
        pieces[1] = new PuzzlePiece("1", Shape.positiveTriangle, Shape.negativeTriangle, Shape.positiveTriangle, Shape.negativeSquare);
        pieces[2] = new PuzzlePiece("2", Shape.positiveTriangle, Shape.negativeSquare, Shape.positiveSquare, Shape.negativeCircle);
        pieces[3] = new PuzzlePiece("3", Shape.positiveSquare, Shape.negativeCircle, Shape.positiveCircle, Shape.negativeTriangle);
        pieces[4] = new PuzzlePiece("4", Shape.positiveSquare, Shape.negativeTwoTriangle, Shape.positiveCircle, Shape.negativeSquare);
        pieces[5] = new PuzzlePiece("5", Shape.positiveTwoTriangle, Shape.negativeSquare, Shape.positiveSquare, Shape.negativeCircle);
        pieces[6] = new PuzzlePiece("6", Shape.positiveCircle, Shape.negativeTwoTriangle, Shape.positiveTriangle, Shape.negativeTwoTriangle);
        pieces[7] = new PuzzlePiece("7", Shape.positiveSquare, Shape.negativeCircle, Shape.positiveTriangle, Shape.negativeSquare);
        pieces[8] = new PuzzlePiece("8", Shape.positiveTwoTriangle, Shape.negativeTriangle, Shape.positiveCircle, Shape.negativeCircle);


        while (true){
            if (solved)
        }

    }
}
