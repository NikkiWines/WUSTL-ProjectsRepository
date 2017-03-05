package edu.wustl.cse231s.sudoku.fx.constraint;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.wustl.cse231s.sudoku.Square;
import edu.wustl.cse231s.sudoku.fx.FxSudokuPane;
import edu.wustl.cse231s.sudoku.fx.FxSudokuPuzzle;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FxSudokuConstraintPane extends FxSudokuPane {
	@Override
	protected Node createSquareNode(Square square) {
		ToggleButton toggleButton = new ToggleButton();
		toggleButton.setToggleGroup(this.toggleGroup);

		this.squareToToggleButtonMap.put(square, toggleButton);
		this.toggleToSquareMap.put(toggleButton, square);
		return toggleButton;
	}

	@Override
	public void init() {
		super.init();
		toggleGroup.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> observable, Toggle oldToggle, Toggle newToggle) -> {
					Square square = this.toggleToSquareMap.get(newToggle);
					Collection<Square> peers = square.getPeers();

					for (Square s : Square.values()) {
						ToggleButton toggleButton = this.squareToToggleButtonMap.get(s);
						String style;
						if (square == s) {
							style = "-fx-base: darkblue;";
						} else if (peers.contains(s)) {
							style = "-fx-base: slateblue;";
						} else {
							style = "-fx-base: lightgray;";
						}
						toggleButton.setStyle(style);
					}
				});
	}

	@Override
	protected void setSquare(FxSudokuPuzzle puzzle, Square square) {
		Collection<Integer> options = puzzle.getOptions(square);
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		for (int option : options) {
			sb.append(prefix);
			sb.append(option);
			prefix = ",";
		}
		ToggleButton toggleButton = this.squareToToggleButtonMap.get(square);
		toggleButton.setText(sb.toString());
		
		double expectedMax = 7.0;
		Color color = Color.GREEN.darker().darker();

		color = color.interpolate(Color.WHITE, options.size()/expectedMax);
		toggleButton.setStyle("-fx-base: " + String.format("#%02X%02X%02X", (int) (color.getRed() * 255),
				(int) (color.getGreen() * 255), (int) (color.getBlue() * 255)) + ";");
	}

	private final ToggleGroup toggleGroup = new ToggleGroup();
	private final Map<Square, ToggleButton> squareToToggleButtonMap = new HashMap<>();
	private final Map<Toggle, Square> toggleToSquareMap = new HashMap<>();
}
