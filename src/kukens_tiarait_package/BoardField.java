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

    public boolean isSameAs(BoardField otherBoardField){
        if(this.getX() ==otherBoardField.getX()
                && this.getY() ==otherBoardField.getY()){
            return true;
        }
        return false;
    }

    public int distanceTo(BoardField otherBoardField){
        int distance = Math.abs(getX() - otherBoardField.getX())+ Math.abs(getY() - otherBoardField.getY());
        return distance;
    }

    public boolean isNeighbor(BoardField otherBoardField){
        if ((Math.abs(getX() - otherBoardField.getX()) > 1)
                || (Math.abs(getY() - otherBoardField.getY()) > 1)){
            return false;
        }
        return true;
    }

    public boolean isPlayerPainted(){
        if(fieldValue > 0 && fieldValue < 5){
            return true;
        }
        return false;
    }

    public boolean isEnemyPainted(int ownPlayerNumber){
        if(isPlayerPainted() && (fieldValue-1) != ownPlayerNumber){
            return true;
        }
        return false;
    }

}
