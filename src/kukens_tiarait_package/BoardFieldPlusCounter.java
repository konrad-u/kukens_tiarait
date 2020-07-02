package kukens_tiarait_package;

// class which extends a BoardField it references with an integer counter, used for pathfinding
public class BoardFieldPlusCounter {

    private int counter;
    private BoardField boardField;
    private boolean isInList;

    public BoardFieldPlusCounter(BoardField boardField, int counter){
        this.boardField = boardField;
        this.counter = counter;
        isInList = false;
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

    public boolean isInList() {
        return isInList;
    }

    public void setInList(boolean inList) {
        isInList = inList;
    }
}
