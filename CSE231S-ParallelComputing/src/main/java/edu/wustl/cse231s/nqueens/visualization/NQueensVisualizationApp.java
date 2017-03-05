package edu.wustl.cse231s.nqueens.visualization;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class NQueensVisualizationApp extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		ChessboardPane chessboardPane = new ChessboardPane(8);
		Slider slider = new Slider(1.0, 100.0, 1.0);
		CheckBox showingThreatFreeOnlyCheckBox = new CheckBox("show threat free only?");
		CheckBox animateCheckBox = new CheckBox("animate?");
		// animateCheckBox.setSelected(true);
		Button stepButton = new Button("step");
		stepButton.setStyle("-fx-font: 24 serif; -fx-base: #80FF80;");

		final int PADDING = 8;
		BorderPane root = new BorderPane();
		root.setCenter(chessboardPane);
		root.setPadding(new Insets(PADDING));

		HBox hBox = new HBox(PADDING);
		HBox.setHgrow(slider, Priority.ALWAYS);
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(slider, showingThreatFreeOnlyCheckBox, animateCheckBox, stepButton);
		root.setTop(hBox);

		Scene scene = new Scene(root);

		primaryStage.sizeToScene();
		primaryStage.setTitle("N-Queens");
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});

		NQueensThread thread = new NQueensThread(chessboardPane, stepButton);
		slider.valueProperty().addListener((ov, oldValue, newValue) -> {
			thread.setSpeed(newValue.doubleValue());
		});

		slider.valueChangingProperty().addListener((ov, oldValue, newValue) -> {
			if (newValue) {
				animateCheckBox.selectedProperty().set(true);
			} else {
				slider.setValue(1.0);
			}
		});

		showingThreatFreeOnlyCheckBox.selectedProperty().addListener((ov, oldValue, newValue) -> {
			thread.setShowingThreatFreeOnly(newValue);
		});

		animateCheckBox.selectedProperty().addListener((ov, oldValue, newValue) -> {
			thread.setAnimate(newValue);
		});

		stepButton.setOnAction(e -> {
			animateCheckBox.setSelected(false);
			thread.step();
		});

		thread.start();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
