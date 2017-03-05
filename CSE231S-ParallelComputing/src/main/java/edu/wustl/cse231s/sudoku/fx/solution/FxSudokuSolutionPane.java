package edu.wustl.cse231s.sudoku.fx.solution;

import java.util.HashMap;
import java.util.Map;

import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.fx.FxSudokuPane;
import edu.wustl.cse231s.sudoku.fx.FxSudokuPuzzle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FxSudokuSolutionPane extends FxSudokuPane {
	@Override
	protected Node createSquareNode(Square square) {
		Label label = new Label();
		label.setId("celltext");
		this.squareToLabelMap.put(square, label);
		return label;
	}
	
	@Override
	protected void setSquare(FxSudokuPuzzle puzzle, Square square) {
		Label label = this.squareToLabelMap.get(square);
		label.textProperty().unbind();
		label.textProperty().bind(puzzle.getProperty(square));
		label.setTextFill(puzzle.isGiven(square) ? Color.BLACK : Color.DARKGRAY);
	}
	
	private final Map<Square, Label> squareToLabelMap = new HashMap<>();
}
