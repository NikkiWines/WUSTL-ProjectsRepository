package student.studio.pixelfilter;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.image.MutablePixels;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import student.studio.pixelfilter.PixelFilter;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class PixelFilterApp extends Application {
	private static enum Implementation {
		SEQUENTIAL_ITERATIVE, PARALLEL_ITERATIVE_PER_ROW, PARALLEL_DIVIDE_AND_CONQUER
	}

	private static void sequentialApplyPixelFilter(MutablePixels mutablePixels, PixelFilter pixelFilter, int xMin,
			int yMin, int xMax, int yMax) {
		for (int i=xMin; i < xMax; i++) { 
			for (int j=yMin; j < yMax; j++) { 
				Color color = pixelFilter.filter(mutablePixels.getColor(i, j));
				mutablePixels.setColor(i, j, color);	
			}
		}
	}

	private static void sequentialApplyPixelFilter(MutablePixels mutablePixels, PixelFilter pixelFilter) {
		sequentialApplyPixelFilter(mutablePixels, pixelFilter, 0, 0, mutablePixels.getWidth(),
				mutablePixels.getHeight());
	}

	private static void parallelPerRowApplyPixelFilter(MutablePixels mutablePixels, PixelFilter pixelFilter)
			throws SuspendableException {
		for (int i=0; i < mutablePixels.getWidth(); i++) {
			final int acs_i = i;
			async(() -> {
				for (int j=0; j < mutablePixels.getHeight(); j++) {
					Color color = pixelFilter.filter(mutablePixels.getColor(acs_i, j));
					mutablePixels.setColor(acs_i, j, color);
				}
			});

		}
	}

	private static void parallelDivideAndConquerApplyPixelFilterKernel(MutablePixels mutablePixels, PixelFilter pixelFilter,
			int threshold, int xMin, int yMin, int xMax, int yMax) throws SuspendableException {

		if ((mutablePixels.getHeight()*mutablePixels.getWidth()) > 1) { 
			if ((mutablePixels.getHeight()*mutablePixels.getWidth()) > threshold) { 
				async(() -> { 
					parallelPerRowApplyPixelFilter(mutablePixels,  pixelFilter);			
					});
			}
			else { 
				sequentialApplyPixelFilter(mutablePixels, pixelFilter);		
			}
		}
	}

	private static void parallelDivideAndConquerApplyPixelFilter(MutablePixels mutablePixels, PixelFilter pixelFilter,
			int threshold) throws SuspendableException {
		parallelDivideAndConquerApplyPixelFilterKernel(mutablePixels, pixelFilter,
				100, 0, 0, mutablePixels.getWidth(), mutablePixels.getHeight());
	}

	public static void applyPixelFilter(MutablePixels mutablePixels, PixelFilter pixelFilter)
			throws SuspendableException {
		Implementation implementation = Implementation.PARALLEL_DIVIDE_AND_CONQUER;
		switch (implementation) {
		case SEQUENTIAL_ITERATIVE:
			sequentialApplyPixelFilter(mutablePixels, pixelFilter);
			break;
		case PARALLEL_ITERATIVE_PER_ROW:
			parallelPerRowApplyPixelFilter(mutablePixels, pixelFilter);
			break;
		case PARALLEL_DIVIDE_AND_CONQUER:
			parallelDivideAndConquerApplyPixelFilter(mutablePixels, pixelFilter, 100);
			break;
		}
	}

	private static RadioButton createPixelFilterRadioButton( String text, ToggleGroup toggleGroup, PixelFilter pixelFilter ) {
		RadioButton result = new RadioButton( text );
		result.setToggleGroup(toggleGroup);
		result.setUserData(pixelFilter);
		return result;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		Label label = new Label("click on image to apply pixel filter");
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton grayscaleRadioButton = createPixelFilterRadioButton("grayscale", toggleGroup, (Color color) -> {
			return color.grayscale();
		});
		RadioButton brighterRadioButton = createPixelFilterRadioButton("brighter", toggleGroup, (Color color) -> {
			return color.brighter();
		});
		RadioButton darkerRadioButton = createPixelFilterRadioButton("darker", toggleGroup, (Color color) -> {
			return color.darker();
		});
		RadioButton allMagentaRadioButton = createPixelFilterRadioButton("all magenta", toggleGroup, (Color color) -> {
			return Color.MAGENTA;
		});

		grayscaleRadioButton.setSelected(true);

		Button resetButton = new Button("reset to original");

		VBox vBox = new VBox(4, label, grayscaleRadioButton, brighterRadioButton, darkerRadioButton, allMagentaRadioButton, resetButton);

		// source: http://www.cs.rice.edu/news/2015/sarkar-shares-most-influential-paper-prize/
		String url = "http://www.cs.rice.edu/uploadedImages/Computer_Science/News_Items/Sarkar.OOPSLA.Award.2015.jpeg";
		Image originalImage = new Image(url);
		MutablePixels mutablePixels = new MutablePixels(originalImage);

		WritableImage writableImage = mutablePixels.createWritableImage();
		ImageView imageView = new ImageView(writableImage);

		resetButton.setOnAction((ActionEvent e) -> {
			mutablePixels.setAllColorsFrom(originalImage);
			mutablePixels.copyAllColorsTo(writableImage);
		});
		root.setOnMousePressed((MouseEvent e) -> {
			Toggle toggle = toggleGroup.getSelectedToggle();
			PixelFilter pixelFilter = (PixelFilter) toggle.getUserData();
			launchHabaneroApp(() -> {
				applyPixelFilter(mutablePixels, pixelFilter);
			});
			mutablePixels.copyAllColorsTo(writableImage);
		});
		Scene scene = new Scene(root);
		root.setCenter(imageView);
		root.setRight(vBox);

		primaryStage.setTitle("Flood Fill");
		primaryStage.setScene(scene);
		primaryStage.show();

		Platform.runLater(() -> primaryStage.sizeToScene());

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
