package kukens_tiarait_package;

/*
Objects represent the board fields
hold x, y and fieldValue
 */

public class BoardField {
    private int x;
    private int y;
    private int fieldValue; // 0 = empty; 1-4 = Player; 5 = wall

    public BoardField(int x, int y, int fieldValue){
        this.x = x;
        this.y = y;
        this.fieldValue = fieldValue;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFieldValue(int fieldValue) {
        this.fieldValue = fieldValue;
    }

    public int getFieldValue() {
        return fieldValue;
    }

    public boolean isSameAs(BoardField otherBoardFieldPlusCounter){
        if(this.getX() ==otherBoardFieldPlusCounter.getX()
                && this.getY() ==otherBoardFieldPlusCounter.getY()){
            return true;
        }
        return false;
    }

    public int distanceTo(BoardField otherBoardFieldPlusCounter){
        int distance = Math.abs(getX() - otherBoardFieldPlusCounter.getX())+ Math.abs(getY() - otherBoardFieldPlusCounter.getY());
        return distance;
    }

}
