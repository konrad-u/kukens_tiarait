package kukens_tiarait_package;

import java.util.ArrayList;

public class BigPainterBot extends AbstractBot{

    public BigPainterBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition) {
        super(gameBoard, playerNumber, botNr, botPosition);
    }


    public void setBotGoal(GameBoard gameBoard){

        /*
        go in circle through arena, trying to paint as largely as possible
        do this by picking 4 points on radius that are not walls, and iteratively going to each of them.
         */

            BoardField[] cornerPoints = getcornerPoints(gameBoard);

            for (int i = 0; i < cornerPoints.length; i++) {

                if (getBotPosition().distanceTo(cornerPoints[0]) < 3) {
                    setBotGoal(cornerPoints[1]);
                } else if (getBotPosition().distanceTo(cornerPoints[1]) < 3) {
                    setBotGoal(cornerPoints[2]);
                } else if (getBotPosition().distanceTo(cornerPoints[2]) < 3) {
                    setBotGoal(cornerPoints[3]);
                } else if (getBotPosition().distanceTo(cornerPoints[3]) < 3) {
                    setBotGoal(cornerPoints[0]);
                } else {
                    setBotGoal(cornerPoints[0]);
                }
            }
            setDirection(gameBoard);
    }

    public BoardField[] getcornerPoints(GameBoard gameBoard){
        BoardField[] cornerPoints = new BoardField[4];

        cornerPoints[0] = gameBoard.getBoardField(10,10);
        cornerPoints[1] = gameBoard.getBoardField(10,22);
        cornerPoints[3] = gameBoard.getBoardField(22,22);
        cornerPoints[2] = gameBoard.getBoardField(22,10);


        for(int i = 0; i < cornerPoints.length; i++){

            if(cornerPoints[i].getFieldValue() == 5){
                ArrayList<BoardField> nonWallNeighbors = getNeighbors(gameBoard, cornerPoints[i]);
                cornerPoints[i] = nonWallNeighbors.get(0);
            }

        }

        return cornerPoints;
    }


}
