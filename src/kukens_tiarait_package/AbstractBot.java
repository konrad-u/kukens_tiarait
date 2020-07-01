package kukens_tiarait_package;

/*
AbstractBot determines everything that each bot needs to be able to do.

Each bot must be able to:
- determine a goal for movement
    - the goal is bot-dependent, as each bot has different skills which maximize my score

- calculate how to get there (most likely via an A* pathfinding algorithm)
-- this can return a direction object, which holds an xDir and yDir float

- give the NetworkClient the appropriate parameters for its
  nC.setMoveDirection(0, 0.1f, -0.8f);
  method (botNumber 0-2, xDirFloat, yDirFloat)

- scan the board, checking if goal is still in line with the current game setup

 */

import java.util.ArrayList;

public abstract class AbstractBot {
//0 = pyramid on head, eraser 110% speed
//1 = rectangle, colors 100% speed
//2 = pyramid, colors 2x 65% speed

    int playerNumber;
    int botNr;
    GameBoard gameBoard;
    BoardField botPosition;
    BoardField botGoal;
    BotDirection direction;
    boolean atGoal;


    public AbstractBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition){
        this.gameBoard = gameBoard;
        this.playerNumber = playerNumber;
        this.botNr = botNr;
        this.botPosition = botPosition;
        setBotGoal(gameBoard);
        direction = new BotDirection(gameBoard, botPosition, botGoal);
        atGoal = false;
    }

    public void checkAtGoal(){
        if(botPosition.equals(botGoal)){
            atGoal = true;
        }
        else{
            atGoal = false;
        }
    }

    //updates goal based on the current gameBoard
    public void setBotGoal(GameBoard currentBoard){
        /*
        switch (botNr){
            case(0):



                break;
            case(1):

                break;

            case(2):

                break;
        }

         */

        ArrayList<BoardField> immediateNeichbors = ArrayListImmediateNeighbors();
        botGoal = immediateNeichbors.get(0);
    }

    public void setBotPosition(int xPos, int yPos){
        botPosition = gameBoard.getBoardField((int)gameBoard.nC.getX(playerNumber, botNr),(int)gameBoard.nC.getY(playerNumber, botNr));
    }

    public void setDirection(GameBoard currentBoard) {
        this.direction = new BotDirection(currentBoard, botPosition, botGoal);
    }

    // class holds the two axis directional floats used in nc.setMoveDirection
    public class BotDirection {

        private float xDir;
        private float yDir;

        //where the pathfinding happens
        //currently just from one field to the next. To be overridden.
        public BotDirection(GameBoard gameBoard, BoardField botPosition, BoardField botGoal){
            float newXDir = (float) - (botPosition.getX() - botGoal.getX());
            float newYDir = (float) - (botPosition.getY() - botGoal.getY());
            setxDir(newXDir);
            setyDir(newYDir);
        };

        public float getxDir() {
            return xDir;
        }

        public void setxDir(float xDir) {
            this.xDir = xDir;
        }

        public float getyDir() {
            return yDir;
        }

        public void setyDir(float yDir) {
            this.yDir = yDir;
        }

    }


    // this method can be extended with conditionals to meet the strategies of each bot,
    // e.g. none of them should override their own color
    public ArrayList<BoardField> ArrayListImmediateNeighbors() {
        ArrayList<BoardField> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(gameBoard.boardFields[botPosition.getX() + i][botPosition.getY() + j].getFieldValue() !=5) {
                    neighbors.add(gameBoard.getBoardField((botPosition.getX() + i), (botPosition.getY() + j)));
                }
            }
        }
        return neighbors;
    }
}
