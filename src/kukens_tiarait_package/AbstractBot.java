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
import java.util.Collections;
import java.util.HashMap;

public class AbstractBot {
//0 = pyramid on head, eraser 110% speed
//1 = rectangle, colors 100% speed
//2 = pyramid, colors 2x 65% speed

    private int playerNumber;
    private int botNr;
    private BoardField botPosition;
    private BoardField botGoal;
    private BotDirection direction;
    private boolean atGoal;
    private ArrayList<BoardField> visitedPath;


    public AbstractBot(GameBoard gameBoard, int playerNumber, int botNr, BoardField botPosition){
        this.playerNumber = playerNumber;
        this.botNr = botNr;
        this.botPosition = botPosition;
        //setBotGoal(gameBoard);
        //direction = new BotDirection(botPosition, botGoal);
        atGoal = false;
        visitedPath = new ArrayList<>();
    }

    //----------------BASIC SETTERS AND GETTERS-----------------------

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


    //method used in NTPlayer class to update gameBoard with bot's position
    public void setBotPosition(GameBoard gameBoard, int xPos, int yPos){
        botPosition = gameBoard.getBoardField(xPos,yPos);
    }

    public BoardField getBotGoal() {
        return botGoal;
    }

    public void setBotGoal(BoardField botGoal) {
        this.botGoal = botGoal;
    }

    //updates goal based on the current gameBoard

    public void setBotGoal(GameBoard gameBoard){

    }

    public ArrayList<BoardField> getVisitedPath() {
        return visitedPath;
    }

    public void setVisitedPath(ArrayList<BoardField> visitedPath) {
        this.visitedPath = visitedPath;
    }

    public BotDirection getDirection() {
        return direction;
    }

    public void setDirection(GameBoard gameBoard) {
        BoardField[] pathToGoal = createPath(gameBoard);

            for(int i = 0; i < pathToGoal.length; i++){
                if(pathToGoal[i] == null){

                }
                //else if(botPosition.distanceTo(botGoal) < pathToGoal[i].distanceTo(botGoal)){
                else if(visitedPath.contains(pathToGoal[i])){

                }
                else if(!visitedPath.contains(pathToGoal[i])){
                    this.direction = new BotDirection(botPosition, pathToGoal[i]);
                    break;
                }
            }
        // here we would redetermine a new goal
    }

    public boolean isAtGoal() {
        return atGoal;
    }

    /*
    public void setAtGoal(boolean atGoal) {
        this.atGoal = atGoal;
    }

     */

    public void checkAtGoal(){
        //if(botPosition.equals(botGoal)){
        if(botPosition.distanceTo(botGoal) < 2){
            atGoal = true;
        }
        else{
            atGoal = false;
        }
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


    public BoardField[] createPath(GameBoard gameBoard){

        HashMap<BoardField, Integer> allFieldsToPath = new HashMap<BoardField, Integer>();
        ArrayList<BoardField> bNeighborhood = new ArrayList<>();

    allFieldsToPath.put(botGoal, 0);

    //PART 1: searching until our ArrayList contains the starting point
        if(!allFieldsToPath.containsKey(botPosition)) {
            while (!allFieldsToPath.containsKey(botPosition)) {
                bNeighborhood.clear();
                int maxValueInPath = 0;
                for (BoardField b : allFieldsToPath.keySet()) {
                    ArrayList<BoardField> bNeighbors = getNeighbors(gameBoard, b, allFieldsToPath);

                    for (BoardField bN : bNeighbors) {
                        if (!bN.equals(b) && bN.distanceTo(botPosition) <= b.distanceTo(botPosition)) {
                            bNeighborhood.add(bN);
                        }
                    }
                }
                maxValueInPath = Collections.max(allFieldsToPath.values());
                for (BoardField bN : bNeighborhood) {
                    if (!allFieldsToPath.containsKey(bN)) {
                        allFieldsToPath.put(bN, maxValueInPath + 1);
                    }
                }
        /*
        for(BoardField b : allFieldsToPath.keySet()){
            gameBoard.getBoardField(b.getX(), b.getY()).setFieldValue(6);
        }

         */
            }
        }

    //PART 2: setting up the straight path between botPosition and botGoal
        int maxSteps = Collections.max(allFieldsToPath.values());
        BoardField[] shortestPath = new BoardField[maxSteps];
        if(shortestPath.length < 2){
            shortestPath[0] = botGoal;
        }
        else {
            shortestPath[shortestPath.length - 1] = botGoal;
        }

        for(int i = 1; i < maxSteps; i++){
            ArrayList<BoardField> inferiorSteps = new ArrayList<>();
            float lowestDistance = 999;
            for(BoardField bf : allFieldsToPath.keySet()){
                if(allFieldsToPath.get(bf).intValue() == (i)){
                    if(bf.distanceTo(botPosition) < lowestDistance
                            && bf.isNeighbor(shortestPath[shortestPath.length - i])){
                        shortestPath[shortestPath.length - (i+1)] = bf;
                        lowestDistance = bf.distanceTo(botPosition);
                    }
                    else{
                        inferiorSteps.add(bf);
                    }
                }
            }
            /*
            for(BoardField inferiorStep : inferiorSteps){
                allFieldsToPath.remove(inferiorStep);
            }

             */
        }

        /*
        System.out.print("The absolute path goes ");
        for(int j = 0; j < shortestPath.length; j++){
            if(shortestPath[j] != null) {
                System.out.print("[" + shortestPath[j].getX() + "][" + shortestPath[j].getY() + "] , ");
                //shortestPath[j].setFieldValue(7);
            }
            else{
                System.out.println("...");
            }
        }
        System.out.println();

         */

        //botPosition.setFieldValue(8);
        //botGoal.setFieldValue(9);
    return shortestPath;
    }


public ArrayList<BoardField> getNeighbors(GameBoard gameBoard, BoardField currentField, HashMap pathSoFar){

        ArrayList<BoardField> neighbors = new ArrayList<>();

    for (int i = -1; i < 2; i++) {
        for (int j = -1; j < 2; j++) {

            BoardField neighbor = gameBoard.getBoardField((
                    currentField.getX()+i)
                    , (currentField.getY() + j));
            if(neighbor.getFieldValue() != 5
                    //add. condition to eliminate diagonal walking. Must be better set up later.
                && ((Math.abs(i ) + Math.abs(j) < 2))
                //&& currentField.getFieldValue() != playerNumber
                && neighbor.distanceTo(botPosition) < currentField.distanceTo(botPosition)){
                neighbors.add(neighbor);
            }
        }
    }
      return neighbors;
}

    public ArrayList<BoardField> getNeighbors(GameBoard gameBoard, BoardField currentField){

        ArrayList<BoardField> neighbors = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {

                BoardField neighbor = gameBoard.getBoardField((
                                currentField.getX()+i)
                        , (currentField.getY() + j));
                if(neighbor.getFieldValue() != 5
                        //add. condition to eliminate diagonal walking. Must be better set up later.
                        && ((Math.abs(i ) + Math.abs(j) < 2))
                        //&& (neighbor.getFieldValue()-1 != playerNumber)
                ) {
                    neighbors.add(neighbor);
                }
            }
        }
        return neighbors;
    }
}
