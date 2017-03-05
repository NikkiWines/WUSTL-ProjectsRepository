package edu.wustl.cse231s.sudoku.fx;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import edu.wustl.cse231s.download.DownloadUtils;
import edu.wustl.cse231s.sudoku.io.PuzzlesResource;
import edu.wustl.cse231s.sudoku.io.PuzzlesResourceUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public abstract class FxSudokuApp extends Application {

	public FxSudokuApp() {
		this.puzzle = new FxSudokuPuzzle();
	}
	protected abstract FxSudokuPane createMainPane();
	protected abstract Node createControls();

	@Override
	public void start(Stage primaryStage) throws Exception {
		List<String> givensList = PuzzlesResourceUtils.readGivens(PuzzlesResource.HARDEST);
		this.puzzle.setAllToGivens(givensList.get(0));

		BorderPane root = new BorderPane();

		Scene scene = new Scene(root);
		String packagePath = FxSudokuApp.class.getPackage().getName().replaceAll("\\.", "/");
		scene.getStylesheets().add(packagePath + "/sudoku.css");
		this.mainPane = this.createMainPane();
		this.mainPane.init();
		this.mainPane.setPuzzle(puzzle);

		root.setCenter(this.mainPane);
		
		Node controls = this.createControls();
		if( controls != null ) {
			root.setTop(controls);
		}

		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();

		// TODO: investigate
		Platform.runLater(() -> primaryStage.sizeToScene());

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}

	protected FxSudokuPuzzle getPuzzle() {
		return this.puzzle;
	}
	
	private final FxSudokuPuzzle puzzle;
	private FxSudokuPane mainPane;
}
