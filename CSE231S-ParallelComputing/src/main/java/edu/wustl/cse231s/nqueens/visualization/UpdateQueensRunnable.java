package edu.wustl.cse231s.nqueens.visualization;

import java.io.IOException;

import edu.wustl.cse231s.fx.FxmlUtils;
import edu.wustl.cse231s.nqueens.visualization.resources.Resources;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import student.assignment.nqueens.NQueensUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class UpdateQueensRunnable implements Runnable {
	public UpdateQueensRunnable(ChessboardPane pane, int[] srcQueens, int depth) {
		this.pane = pane;
		this.queens = new int[depth];
		System.arraycopy(srcQueens, 0, this.queens, 0, depth);
		this.queenScaleGroups = new Group[srcQueens.length];
		for (int i = 0; i < this.queenScaleGroups.length; i++) {
			try {
				Group queen = FxmlUtils.loadGroup(Resources.QUEEN_FXML);
				queen.getTransforms().add(new Scale(0.09, 0.09));
				this.queenScaleGroups[i] = new Group();
				this.queenScaleGroups[i].getChildren().add(queen);
			} catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
	}

	private boolean isValid(int i, int row, int column) {
		if (this.queens[i] == column) {
			return false;
		}
		if (row - i == this.queens[i] - column) {
			return false;
		}
		if (row - i == column - this.queens[i]) {
			return false;
		}
		return true;
	}

	@Override
	public void run() {
		synchronized (this.pane) {
			for (int row = 0; row < this.pane.getQueenCount(); row++) {
				for (int column = 0; column < this.pane.getQueenCount(); column++) {
					StackPane squarePane = this.pane.getSquare(row, column);
					squarePane.getChildren().clear();
					ChessboardPane.setSquareStyleWithoutBorder(squarePane, row, column);
				}

				Label stackLabel = this.pane.getStackLabel(row);
				if (row < this.queens.length) {
					stackLabel.setText("placeQueenInRow( row=" + row + " )\n\tcolumn=" + this.queens[row]);
					stackLabel.setStyle("-fx-border-color: black; -fx-border-width: 1;");
				} else {
					stackLabel.setText("");
					stackLabel.setStyle("");
				}
			}

			if (this.queens.length > 0) {
				int _r = this.queens.length - 1;
				int _c = this.queens[_r];

				int r = 0;
				for (int c : this.queens) {
					StackPane squarePane = this.pane.getSquare(r, c);
					if (r == _r && c == _c) {
						ChessboardPane.setSquareStyleWithBorder(squarePane, r, c, "#8080FF");
					}
					squarePane.getChildren().add(this.queenScaleGroups[r]);

					Node queen = this.queenScaleGroups[r].getChildren().get(0);
					double opacity = 1;
					if (r < _r && isValid(r, _r, _c)) {
						// pass
					} else if (r == _r && NQueensThread.isThreatFreeWithQueensInPreviousRows(this.queens, _r, _c)) {
						// pass
					} else {
						squarePane.setStyle("-fx-background-color: red;");
						opacity = 0.5;
					}
					queen.setOpacity(opacity);
					r++;
				}
			}
		}
	}

	private final ChessboardPane pane;
	private final int[] queens;
	private final Group[] queenScaleGroups;
}
