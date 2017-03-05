package edu.wustl.cse231s.sudoku.fx.solution;

import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.fx.FxSudokuApp;
import edu.wustl.cse231s.sudoku.fx.FxSudokuPuzzle;
import instructor.solution.sudoku.fx.FxSolution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FxSudokuSolutionApp extends FxSudokuApp {
	@Override
	protected FxSudokuSolutionPane createMainPane() {
		return new FxSudokuSolutionPane();
	}
	
	private static void resetPuzzle( FxSudokuPuzzle puzzle ) {
		for( Square square : Square.values() ) {
			if( puzzle.isGiven(square)) {
				//pass
			} else {
				puzzle.setValue(square, 0);
			}
		}
	}

	@Override
	protected Node createControls() {
		HBox result = new HBox(4.0);
		result.setAlignment(Pos.CENTER_LEFT);
		Button solveButton = new Button("solve puzzle");
		solveButton.setStyle("-fx-base: lightgreen;");
		CheckBox betterSearchOrderCheckBox = new CheckBox("better search order?");
		Button resetButton = new Button("reset puzzle");
		Button cancelButton = new Button("cancel");
		cancelButton.setDisable(true);
		
		boolean[] at_isRunning = { false };
		
		resetButton.setOnAction((event) -> {
			FxSudokuPuzzle puzzle = this.getPuzzle();
			resetPuzzle( puzzle );
		});

		
		solveButton.setOnAction((event) -> {
			FxSudokuPuzzle puzzle = this.getPuzzle();
			resetPuzzle( puzzle );
			
			Runnable runnable = () -> {
				solveButton.setDisable(true);
				resetButton.setDisable(true);
				cancelButton.setDisable(false);
				betterSearchOrderCheckBox.setDisable(true);
				at_isRunning[ 0 ] = true;
				FxSolution.solve(at_isRunning, puzzle, betterSearchOrderCheckBox.isSelected() );
				at_isRunning[ 0 ] = false;
				solveButton.setDisable(false);
				resetButton.setDisable(false);
				cancelButton.setDisable(true);
				betterSearchOrderCheckBox.setDisable(false);
			};
			final boolean IS_SEPARATE_THREAD_DESIRED = true;
			if (IS_SEPARATE_THREAD_DESIRED) {
				Thread sudokuSolutionThread = new Thread(runnable);
				// sudokuSolutionThread.setPriority(Thread.MIN_PRIORITY);
				sudokuSolutionThread.start();
			} else {
				Platform.runLater(runnable);
			}
		});
		
		cancelButton.setOnAction((event) -> {
			at_isRunning[ 0 ] = false;
		} );
		
		result.getChildren().addAll(solveButton, betterSearchOrderCheckBox, resetButton, cancelButton);
		return result;
	}

	public static void main(String[] args) {
		Application.launch(FxSudokuSolutionApp.class, args);
	}
}
