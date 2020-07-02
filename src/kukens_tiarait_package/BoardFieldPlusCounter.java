package kukens_tiarait_package;

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

}
