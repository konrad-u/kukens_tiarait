package kukens_tiarait_package;

import java.lang.Math;

// class which extends a BoardField it references with an integer counter, used for pathfinding
public class BoardFieldPlusCounter {

    private int counter;
    private BoardField boardField;

    public BoardFieldPlusCounter(BoardField boardField, int counter){
        this.boardField = boardField;
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public BoardField getBoardField(){
        return boardField;
    }

    public void setBoardField(BoardField boardField){
        this.boardField = boardField;
    }

    public boolean isSameAs(BoardFieldPlusCounter otherBoardFieldPlusCounter){
        if(this.getBoardField().getX() ==otherBoardFieldPlusCounter.getBoardField().getX()
                && this.getBoardField().getY() ==otherBoardFieldPlusCounter.getBoardField().getY()){
            return true;
        }
        return false;
    }

    public int distanceTo(BoardFieldPlusCounter otherBoardFieldPlusCounter){
        int distance = Math.abs(boardField.getX() - otherBoardFieldPlusCounter.boardField.getX())+ Math.abs(boardField.getY() - otherBoardFieldPlusCounter.boardField.getY());
        return distance;
    }
}
