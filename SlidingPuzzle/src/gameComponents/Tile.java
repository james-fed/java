package gameComponents;

public class Tile {

    private int value;
    private int row;
    private int column;

    public int getValue() {
        return this.value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
