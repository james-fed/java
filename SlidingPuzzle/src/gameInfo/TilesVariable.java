package gameInfo;

public enum TilesVariable {


    NULL_SPACE_VALUE(0),
    UNDEFINED_TILE_VALUE(-1);

    private int value;

    private TilesVariable(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
