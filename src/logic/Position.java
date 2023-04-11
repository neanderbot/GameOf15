package logic;

import java.util.HashMap;
import java.util.Map;

public class Position {
	Map<String, Integer> position;

	private Position(int row, int column) {
		position = new HashMap<>();
		position.put("row", row);
		position.put("column", column);
	}

	public static Position of(int row, int column) {
		if (row >= 0 && column >= 0) {
			return new Position(row, column);
		} else {
			return null;
		}
	}

	public int getRow() {
		return position.get("row");
	}

	public int getColumn() {
		return position.get("column");
	}
	
	

	

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Position)) {
			return false;
		} else {
			Position otherPosition = (Position) other;
			return (this.getRow() == otherPosition.getRow()) && (this.getColumn() == otherPosition.getColumn());
		}
	}

	@Override
	public String toString() {
		return String.format("Position: row %d - column %d\n", position.get("row"), position.get("column"));
	}

}
