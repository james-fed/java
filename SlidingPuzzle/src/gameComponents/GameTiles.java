package gameComponents;

import gameInfo.TilesVariable;

public class GameTiles {


    /**
     * store Tiles in the 2 dimension array
     */

    private Tile[][] tiles;

    public GameTiles(int size) {
        this.tiles = new Tile[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setRow(i);
                tiles[i][j].setColumn(j);
                tiles[i][j].setValue(TilesVariable.UNDEFINED_TILE_VALUE.getValue());
            }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public Tile getTile(int row, int column) {
        return this.tiles[row][column];
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void setTile(Tile tile) {
        this.tiles[tile.getRow()][tile.getColumn()] = tile;
    }

    public void setTile(int row, int column, int value) {

        this.tiles[row][column].setValue(value);
    }

}
