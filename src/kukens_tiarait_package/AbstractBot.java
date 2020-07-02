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

    private int playerNumber;
    private int botNr;
    private BoardField botPosition;
    private BoardField botGoal;
    private BotDirection direction;
    private boolean atGoal;


    public AbstractBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition){
        this.playerNumber = playerNumber;
        this.botNr = botNr;
        this.botPosition = botPosition;
        setBotGoal(gameBoard);
        direction = new BotDirection(botPosition, botGoal);
        atGoal = false;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getBotNr() {
        return botNr;
    }

    public void setBotNr(int botNr) {
        this.botNr = botNr;
    }

    public BoardField getBotPosition() {
        return botPosition;
    }

    public void setBotPosition(BoardField botPosition) {
        this.botPosition = botPosition;
    }

    public BoardField getBotGoal() {
        return botGoal;
    }

    public void setBotGoal(BoardField botGoal) {
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
        this.botGoal = botGoal;
    }

    public BotDirection getDirection() {
        return direction;
    }

    public void setDirection(BotDirection direction) {
        this.direction = direction;
    }

    public boolean isAtGoal() {
        return atGoal;
    }

    public void setAtGoal(boolean atGoal) {
        this.atGoal = atGoal;
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
    public void setBotGoal(GameBoard gameBoard){


        //ArrayList<BoardField> immediateNeichbors = BFSRecursiveNeighbors(gameBoard, 0);

        //botGoal = immediateNeichbors.get(0);
    }

    public void setBotPosition(GameBoard gameBoard, int xPos, int yPos){
        botPosition = gameBoard.getBoardField(xPos,yPos);
    }

    public void setDirection(GameBoard gameBoard) {
        this.direction = new BotDirection(botPosition, botGoal);
    }

    // class holds the two axis directional floats used in nc.setMoveDirection
    public class BotDirection {

        private float xDir;
        private float yDir;

        //where the pathfinding happens
        //currently just from one field to the next. To be overridden.
        public BotDirection(BoardField botPosition, BoardField botGoal){
            float newXDir = - (botPosition.getX() - botGoal.getX());
            float newYDir = - (botPosition.getY() - botGoal.getY());
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
    //made to scan all neighbors recursively
    public ArrayList<BoardFieldPlusCounter> BFSRecursiveNeighbors(BoardField parentField, GameBoard gameBoard, int parentDepth) {

        //parentField = gameBoard.boardFields[botPosition.getX()][botPosition.getY()];



        ArrayList<BoardFieldPlusCounter> neighbors = new ArrayList<>();


        while(parentField != botGoal) {

            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    //as long as not a wall AND not the already position of bot AND isn't already in neighbors
                    if (gameBoard.boardFields[botPosition.getX() + i][botPosition.getY() + j].getFieldValue() != 5
                            && gameBoard.boardFields[botPosition.getX() + i][botPosition.getY() + j] != parentField
                            && !neighbors.contains(gameBoard.boardFields[botPosition.getX() + i][botPosition.getY() + j])) {
                        BoardFieldPlusCounter weightedBoardField = new BoardFieldPlusCounter(gameBoard.boardFields[botPosition.getX() + i][botPosition.getY() + j], parentDepth);
                        neighbors.add(weightedBoardField);
                    }
                }
            }

        }
        System.out.println("our neighbors arrayList has length " + neighbors.size());
        return neighbors;
    }

}
