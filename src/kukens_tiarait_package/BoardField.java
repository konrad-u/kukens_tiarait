package kukens_tiarait_package;

/*

 */

public class BoardField {
    private int x;
    private int y;
    private int fieldValue;

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

}
