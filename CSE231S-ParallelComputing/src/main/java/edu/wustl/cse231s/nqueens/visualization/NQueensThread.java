package edu.wustl.cse231s.nqueens.visualization;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import student.assignment.nqueens.NQueensUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class NQueensThread extends Thread {
	/*package-private*/ static boolean isThreatFreeWithQueensInPreviousRows(int[] queens, int row, int column) {
		for (int i = 0; i < row; i++) {
			// is in same column
			if (queens[i] == column) {
				return false;
			}
			// is in same diagonal A
			if (row - i == queens[i] - column) {
				return false;
			}
			// is in same diagonal B
			if (row - i == column - queens[i]) {
				return false;
			}
		}
		return true;
	}

	public NQueensThread(ChessboardPane pane, Button stepButton) {
		this.pane = pane;
		this.stepButton = stepButton;
	}

	private void updateQueens(int[] queens, int row) {
		Platform.runLater(new UpdateQueensRunnable(this.pane, queens, row + 1));
	}

	private void flashQueen(int row, int column, String otherColorName) {
		Platform.runLater(() -> {
			this.stepButton.disableProperty().set(true);
		});
		int nFlashes = 3;
		for (int i = 0; i < nFlashes * 2; i++) {
			boolean isOn = i % 2 == 1;
			Platform.runLater(() -> {
				StackPane squarePane = this.pane.getSquare(row, column);
				ChessboardPane.setSquareStyle(squarePane, row, column, otherColorName, isOn);
			});
			try {
				Thread.sleep((int) (200 / this.speed));
			} catch (InterruptedException ie) {
				throw new RuntimeException(ie);
			}
		}
		Platform.runLater(() -> {
			this.stepButton.disableProperty().set(false);
		});
	}

	private void handleThreatFree(int[] queens, int row, int column) {
		updateQueens(queens, row);
		if (this.isShowingThreatFreeOnly) {
			if (this.isAnimate) {
				try {
					Thread.sleep((int) (500 / this.speed));
				} catch (InterruptedException ie) {
					throw new RuntimeException(ie);
				}
			} else {
				try {
					queue.take();
				} catch (InterruptedException ie) {
					throw new RuntimeException(ie);
				}
			}
		} else {
			this.pane.updateStatusLabelLater(
					"action: " + (row == this.pane.getQueenCount() ? "solution found" : "recurse"));
			flashQueen(row, column, "blue");
			this.pane.updateStatusLabelLater("");
		}
	}

	private void handleThreat(int[] queens, int row) {
		if (this.isShowingThreatFreeOnly) {
			// pass
		} else {
			updateQueens(queens, row);
			if (this.isAnimate) {
				try {
					Thread.sleep((int) (500 / this.speed));
				} catch (InterruptedException ie) {
					throw new RuntimeException(ie);
				}
			} else {
				try {
					this.queue.take();
				} catch (InterruptedException ie) {
					throw new RuntimeException(ie);
				}
			}
		}
	}

	private void handleAboutToBacktrack(int[] queens, int row) {
		if (this.isShowingThreatFreeOnly) {
			// pass
		} else {
			String flashColorName = (row == queens.length - 1)
					&& isThreatFreeWithQueensInPreviousRows(queens, row, queens.length - 1) ? "white" : "#800000";
			this.pane.updateStatusLabelLater("action: backtrack");
			flashQueen(row, queens.length - 1, flashColorName);
			this.pane.updateStatusLabelLater("");
			updateQueens(queens, row - 1);
		}
	}

	private void placeQueenInRow(int[] at_count, int[] queens, int row) {
		for (int column = 0; column < queens.length; column++) {
			queens[row] = column;
			this.pane.updateQueenArrayLabelLater(queens, row);
			if (isThreatFreeWithQueensInPreviousRows(queens, row, column)) {
				handleThreatFree(queens, row, column);
				if (row == queens.length - 1) {
					// solution found
					at_count[0] += 1;
					this.pane.updateSolutionCountLabelLater(at_count[0]);
					NQueensUtils.printBoard(queens);
				} else {
					placeQueenInRow(at_count, queens, row + 1);
				}
			} else {
				handleThreat(queens, row);
			}
		}
		handleAboutToBacktrack(queens, row);
	}

	private void placeQueens(int size) {
		int[] at_count = { 0 };
		int[] queens = new int[size];
		placeQueenInRow(at_count, queens, 0);
		System.out.println("done");
		Platform.runLater(new UpdateQueensRunnable(pane, queens, 0));
		this.pane.updateStatusLabelLater("done");
	}

	@Override
	public void run() {
		try {
			this.queue.take();
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
		placeQueens(this.pane.getQueenCount());
	}

	public void step() {
		try {
			this.queue.put(1);
		} catch (InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}

	public void setShowingThreatFreeOnly(boolean isShowingThreatFreeOnly) {
		this.isShowingThreatFreeOnly = isShowingThreatFreeOnly;
		this.pane.updateStatusLabelLater("");
	}

	public void setAnimate(boolean isAnimate) {
		if (this.isAnimate != isAnimate) {
			this.isAnimate = isAnimate;
			if (this.isAnimate) {
				this.step();
			}
		}
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	private final ChessboardPane pane;
	private final Button stepButton;
	private double speed = 1.0;
	private boolean isShowingThreatFreeOnly = false;
	private boolean isAnimate = false;
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
}
