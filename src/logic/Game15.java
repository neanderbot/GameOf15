package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game15 {
	private int[] tiles;
	private int[] endResult;
	private static final int dimension = 4;
	private static final int numberOfTiles = dimension * dimension;
	private int blankIndex;

	public Game15() {
		tiles = new int[numberOfTiles];
		tiles = iniTiles(tiles);
		endResult = tiles.clone();
		shuffle();
	}

	private int[] iniTiles(int[] array) {
		int[] tempArray = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			tempArray[i] = (i + 1) % array.length;
		}
		setBlankIndex();
		return tempArray;
	}

	public void move(int from) {
		if (isValidMove(from)) {
			int temp = tiles[from];
			tiles[from] = tiles[blankIndex];
			tiles[blankIndex] = temp;
			setBlankIndex();
		}
	}

	public boolean isValidMove(int from) {
		Position ini = getPosition(from);
		Position blank = getPosition(blankIndex);

		List<Position> allowed = allowedPositions();
		for (Position p : allowed) {
			if (p.equals(ini)) {
				return true;
			}
		}

		return false;
	}

	public List<Position> allowedPositions() {
		Position[] allowedPositions1 = new Position[4];
		Position blank = getPosition(blankIndex);
		allowedPositions1[0] = Position.of(blank.getRow() - 1, blank.getColumn());
		allowedPositions1[1] = Position.of(blank.getRow() + 1, blank.getColumn());
		allowedPositions1[2] = Position.of(blank.getRow(), blank.getColumn() - 1);
		allowedPositions1[3] = Position.of(blank.getRow(), blank.getColumn() + 1);

		List<Position> allowedPositions = new ArrayList<>();
		if (!((blank.getRow() - 1) < 0)) {
			allowedPositions.add(Position.of(blank.getRow() - 1, blank.getColumn()));
		}
		if ((blank.getRow() + 1) >= 0) {
			allowedPositions.add(Position.of(blank.getRow() + 1, blank.getColumn()));
		}
		if (!((blank.getColumn() - 1) < 0)) {
			allowedPositions.add(Position.of(blank.getRow(), blank.getColumn() - 1));
		}
		if ((blank.getColumn() + 1) >= 0) {
			allowedPositions.add(Position.of(blank.getRow(), blank.getColumn() + 1));
		}

		return allowedPositions;
	}

	public Position getPosition(int index) {
		int column = index % dimension;
		int row = index / dimension;
		return Position.of(row, column);
	}

	public void shuffle() {
		Integer[] shuffled = new Integer[tiles.length];

		Random random = new Random();

		for (int i = 0; i < tiles.length; i++) {
			int randomPosition = random.nextInt(shuffled.length);
			boolean wasntNull = true;
			do {
				if (shuffled[randomPosition] == null) {
					wasntNull = false;
					shuffled[randomPosition] = tiles[i];
					break;
				} else {
					wasntNull = true;
					randomPosition = random.nextInt(shuffled.length);
				}
			} while (wasntNull);
		}

		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = shuffled[i];
		}
		setBlankIndex();
		if(!isSolvable()) {
			shuffle();
		}
	}

	public boolean isSolvable() {
		boolean isSolvable = false;
		
		// get number of inversions
		int numberOfInversions = 0;
		for (int i = 0; i < tiles.length - 1; i++) {
			for (int j = i + 1; j < tiles.length; j++) {
				if (tiles[j] == 0 || tiles[i] == 0) {
					continue;
				}
				numberOfInversions = tiles[i] < tiles[j] ? numberOfInversions : ++numberOfInversions;
			}
		}
		
		// get row of blankIndex
		int blankIndexRow = blankIndex / dimension;
		
		if(blankIndexRow % 2 == 0 && numberOfInversions % 2 != 0) {
			isSolvable = true;
		}
		
		if(blankIndexRow % 2 != 0 && numberOfInversions % 2 == 0) {
			isSolvable = true;
		}
		
		System.out.println("number of inversions: " + numberOfInversions);
		return isSolvable;
	}

	public boolean isSolved() {
		return Arrays.equals(tiles, endResult);
	}

	public void printBoard() {
		int column = 1;
		for (int i : tiles) {
			System.out.printf("%3d ", i);

			if (column % dimension == 0) {
				System.out.println();
			}
			column++;

		}
		System.out.println();
	}

//	public static Game15 newGame() {
//		Game15 game = new Game15();
//		
//		return game;
//
//	}

	public int[] getTiles() {
		return tiles;
	}

	public int getBlankIndex() {
		return blankIndex;
	}

	private void setBlankIndex() {
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] == 0) {
				blankIndex = i;
			}
		}
	}

	public static int getDimension() {
		return dimension;
	}

}
