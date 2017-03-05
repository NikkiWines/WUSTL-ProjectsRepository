package edu.wustl.cse231s.sudoku.fx;

import edu.wustl.cse231s.sudoku.Square;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public abstract class FxSudokuPane extends GridPane {
	public void init() {
		for (int rowMacro = 0; rowMacro < 3; rowMacro++) {
			if (rowMacro > 0) {
				Separator separator = new Separator(Orientation.HORIZONTAL);
				GridPane.setColumnSpan(separator, 9 + 2);
				this.add(separator, 1, rowMacro * 4);
			}
			for (int colMacro = 0; colMacro < 3; colMacro++) {
				if (colMacro > 0) {
					Separator colSeparator = new Separator(Orientation.VERTICAL);
					GridPane.setRowSpan(colSeparator, 9 + 2);
					this.add(colSeparator, colMacro * 4, 1);
				}
				for (int r = 0; r < 3; r++) {
					int row = rowMacro*3+r;
					for (int c = 0; c < 3; c++) {
						int column = colMacro*3+c;
						Square square = Square.valueOf(row,column);
						Node squareNode = this.createSquareNode(square);
						this.add(squareNode, c + colMacro * 4 + 1, r + rowMacro * 4 + 1);
					}
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			Label label = new Label(Character.toString((char) ('A' + i)));
			label.setId("headingtext");
			GridPane.setHalignment(label, HPos.CENTER);
			this.add(label, 0, 1 + i + i / 3);
		}

		for (int i = 0; i < 9; i++) {
			Label label = new Label(Integer.toString(i+1));
			label.setId("headingtext");
			this.add(label, 1 + i + i / 3, 0);
		}

		this.getStyleClass().add("sudoku-grid");
	}
	
	protected abstract void setSquare( FxSudokuPuzzle puzzle, Square square );
	public void setPuzzle( FxSudokuPuzzle puzzle ) {
		for( Square square : Square.values()) {
			this.setSquare(puzzle, square);
		}
	}
	
	protected abstract Node createSquareNode( Square square );
}
