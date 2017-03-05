package edu.wustl.cse231s.sudoku.fx.constraint;

import edu.wustl.cse231s.sudoku.fx.FxSudokuApp;
import javafx.application.Application;
import javafx.scene.Node;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FxSudokuConstraintApp extends FxSudokuApp {
	@Override
	protected FxSudokuConstraintPane createMainPane() {
		return new FxSudokuConstraintPane();
	}

	@Override
	protected Node createControls() {
		return null;
	}

	public static void main(String[] args) {
		Application.launch(FxSudokuConstraintApp.class, args);
	}
}
