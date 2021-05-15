package main;

import gameComponents.GameData;
import gameComponents.Tile;
import gameInfo.TilesVariable;
import gameInfo.KeyDirectionTag;
import gameComponents.GameTiles;

import java.util.Random;

/**
 * GameHandler class get direction of key then process on it all data that is
 * game dependent will be change in this class. game contain set of tiles that
 * standing together like a matrix therefore store data in GameTiles that
 * contain set of Tiles then change data in this class. (for more information
 * about Tiles and GameTiles refer to the GameComponent package).
 */

public class GameHandler {

	private GameTiles gameTiles;
	private int gameSize;
	private int movementCounts;

	public GameHandler(int gameSize) {
		this.gameSize = gameSize;
		this.movementCounts = 0;
		gameTiles = new GameTiles(gameSize);
		do
			setRandomValueForTiles(gameTiles);
		while (isWin());
	}

	GameTiles getTiles() {
		return this.gameTiles;
	}

	/**
	 * if directionKeys is valid, game data(Tiles & movementsCount) is changing
	 * according on direction and then return gameTiles gameTiles is a matrix that
	 * store Tiles (for more information refer to the gameComponent package).
	 */
	public GameTiles updateGameData(KeyDirectionTag directionKeys) {

		Tile nullSpaceTile = getNullSpace();
		int rowOfNullSpace = nullSpaceTile.getRow();
		int columnOfNullSpace = nullSpaceTile.getColumn();
		switch (directionKeys) {
		case UP:
			if (rowOfNullSpace != gameSize - 1) {
				swapTiles(nullSpaceTile, gameTiles.getTile(rowOfNullSpace + 1, columnOfNullSpace));
				movementCounts++;
			}
			break;

		case DOWN:
			if (rowOfNullSpace != 0) {
				swapTiles(nullSpaceTile, gameTiles.getTile(rowOfNullSpace - 1, columnOfNullSpace));
				movementCounts++;
			}
			break;

		case LEFT:
			if (columnOfNullSpace != gameSize - 1) {
				swapTiles(nullSpaceTile, gameTiles.getTile(rowOfNullSpace, columnOfNullSpace + 1));
				movementCounts++;
			}

			break;
		case RIGHT:
			if (columnOfNullSpace != 0) {
				swapTiles(nullSpaceTile, gameTiles.getTile(rowOfNullSpace, columnOfNullSpace - 1));
				movementCounts++;
			}
			break;
		case NO_DIRECTION:
		default:
		}
		GameData.setMoveCount(movementCounts);
		return gameTiles;
	}


	public boolean isWin() {

		if (this.gameTiles.getTile(gameSize - 1, gameSize - 1).getValue() != TilesVariable.NULL_SPACE_VALUE.getValue())
			return false;

		for (int i = 0; i < Math.pow(gameSize, 2) - 2; i++) {
			Tile currentTile, nextTile;
			currentTile = this.gameTiles.getTile(i / gameSize, i % gameSize);
			nextTile = this.gameTiles.getTile((i + 1) / gameSize, (i + 1) % gameSize);

			if (currentTile.getValue() > nextTile.getValue())
				return false;
		}

		return true;
	}


	/**
	 * Search for finding tile with value and then return tile otherwise return null
	 */
	private Tile getTileByValue(int value) {
		for (int i = 0; i < gameSize; i++)
			for (int j = 0; j < gameSize; j++)
				if (gameTiles.getTile(i, j).getValue() == value)
					return gameTiles.getTile(i, j);

		return null;
	}

	private Tile getNullSpace() {
		return getTileByValue(TilesVariable.NULL_SPACE_VALUE.getValue());
	}

	/**
	 * this method just get two tile and swap the value of this two tiles
	 */
	private void swapTiles(Tile tile1, Tile tile2) {

		int temp = tile2.getValue();
		tile2.setValue(tile1.getValue());
		tile1.setValue(temp);
	}

	/**
	 * generate random position and define each file that not defined if the random
	 * position is defined, then iterate in gameTiles to find a undefined tile to
	 * define it.
	 */
	private void setRandomValueForTiles(GameTiles tiles) {
		int tileCounts = gameSize * gameSize;
		Random random = new Random();
		int randomRow, randomColumn;
		int value = 0;
		do {
			randomRow = random.nextInt(gameSize);
			randomColumn = random.nextInt(gameSize);

			// if the random tile detected already defined then iterating to find undefined
			// tile to define it
			while (tiles.getTile(randomRow, randomColumn).getValue() != TilesVariable.UNDEFINED_TILE_VALUE.getValue())
				if (++randomColumn >= gameSize) {
					randomColumn = 0;
					if (++randomRow >= gameSize)
						randomRow = 0;
				}

			tiles.getTile(randomRow, randomColumn).setValue(value);
		} while (++value < tileCounts);
	}

	// redundant function
	public GameTiles winTilesState(GameTiles tiles) {
		int value = 2;
		boolean evenNumber = true;

		tiles.getTile(gameSize - 1, gameSize - 1).setValue(0);
		for (int i = 0; i < gameSize; i++)
			for (int j = 0; j < gameSize; j++) {

				tiles.getTile(i, j).setValue(value);
				if (evenNumber) {
					if (value + 2 < gameSize * gameSize)
						value += 2;
					else {
						value = 1;
						evenNumber = false;
					}
				} else if (value + 2 < gameSize * gameSize)
					value += 2;
				else
					break;

			}
		return tiles;
	}

}
