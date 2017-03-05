package student.studio.future.image;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.future;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import edu.rice.hj.api.HjFuture;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FutureImageViewApp extends Application {

	private static Image toImage(URL url) {
		try {
			byte[] buffer = IOUtils.toByteArray(url);
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			return new Image(bais);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	private static void setImageLater(Stage primaryStage, ImageView imageView, Image image) {
		Platform.runLater(() -> {
			imageView.setImage(image);
			primaryStage.sizeToScene();
		});
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/2/24/Red_White_Blood_cells.jpg");

		ImageView imageView = new ImageView();

		BorderPane pane = new BorderPane();
		pane.setCenter(imageView);
		pane.setBottom(new Label("red blood cell, platelet, T cell"));

		Scene scene = new Scene(pane);
		primaryStage.setTitle("ImageView: " + url.toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		// NOTE: non-Habanero support for concurrency in JavaFX
		// https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/package-summary.html
		// http://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm

		new Thread(() -> {
			launchHabaneroApp(() -> {
				//TODO:
				// create a future using toImage
				// get the value of that future and invoke setImageLater
			});
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
