package edu.wustl.cse231s.sudoku.fx;

import java.util.List;

import edu.wustl.cse231s.fx.FxUtils;
import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.SudokuPuzzle;
import instructor.solution.sudoku.fx.FxSolution;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FxSudokuPuzzle implements SudokuPuzzle {
	private String toString(int value) {
		return Math.abs(value) > 0 ? Integer.toString(value) : " ";
	}

	public FxSudokuPuzzle() {
		for (int row = 0; row < this.propertyMatrix.length; row++) {
			for (int column = 0; column < this.propertyMatrix[row].length; column++) {
				this.propertyMatrix[row][column] = new SimpleStringProperty();
			}
		}
	}

	public void setAllToGivens( String givens ) {
		if (givens != null) {
			int row = 0;
			int column = 0;
			for (char c : givens.toCharArray()) {
				int value;
				if (c == '.') {
					value = 0;
				} else {
					value = c - '0';
				}
				this.isGivenMatrix[row][column] = value > 0;
				this.setValue(row, column, value);

				column++;
				if (column == 9) {
					row++;
					column = 0;
				}
			}
		}
	}
	
	public boolean isGiven(Square square) {
		int row = square.getRow();
		int column = square.getColumn();
		return this.isGivenMatrix[row][column];
	}

	public List<Integer> getOptions(Square square) {
		return FxSolution.getOptions(this, square);
	}

	@Override
	public int getValue(Square square) {
		int row = square.getRow();
		int column = square.getColumn();
		return Math.abs(this.valueMatrix[row][column]);
	}

	private void setValue(int row, int column, int value) {
		this.valueMatrix[row][column] = value;
		Runnable runnable = ()->{
			this.propertyMatrix[row][column].set(toString(value));
		};
		if (Platform.isFxApplicationThread()) {
			runnable.run();
		} else {
			FxUtils.runAndWait(runnable);
		}
	}

	public void setValue(Square square, int value) {
		this.setValue(square.getRow(), square.getColumn(), value);
	}

	public StringProperty getProperty(Square square) {
		int row = square.getRow();
		int column = square.getColumn();
		return this.propertyMatrix[row][column];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < this.propertyMatrix.length; row++) {
			for (int column = 0; column < this.propertyMatrix[row].length; column++) {
				int value = this.valueMatrix[row][column];
				sb.append(toString(value));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private final int[][] valueMatrix = new int[9][9];
	private final boolean[][] isGivenMatrix = new boolean[9][9];
	private final StringProperty[][] propertyMatrix = new StringProperty[9][9];
}
