package student.assignment.floodfill;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import edu.rice.hj.api.HjSuspendable;
import edu.wustl.cse231s.color.ColorUtil;
import edu.wustl.cse231s.image.MutablePixels;
import edu.wustl.cse231s.rice.classic.contrib.consumer.HabaneroConsumerThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class FloodFillApp extends Application {

	/**
	 * This is the only method that you will need to implement for this
	 * assignment. This method should be recursive; that is, it should only set
	 * the color of a single pixel. When called by the floodFill method, your
	 * algorithm should change one connected group of pixels (of the same color)
	 * to another color.
	 * 
	 * @param mutablePixels
	 *            The set of pixels that you are supposed to manipulate. See the
	 *            MutablePixels class for information about the methods that you
	 *            have access to.
	 * @param targetColor
	 *            The color targeted for replacement. Remember that the flood
	 *            fill algorithm changes one color to another. It should only
	 *            change pixels of this target color.
	 * @param replacementColor
	 *            The new color to set pixels to. The targetColor parameter thus
	 *            finds which pixels to target, and the replacement color
	 *            describes how that color is replaced.
	 * @param x
	 *            The x-coordinate of the pixel to examine.
	 * @param y
	 *            The y-coordinate of the pixel to examine.
	 */
	private static void floodFillKernel(MutablePixels mutablePixels, Color targetColor, Color replacementColor, int x,
			int y) {
		//format from class pseudocode
		//  base case
		if ((mutablePixels.getColor(x, y).equals(replacementColor)) || (!mutablePixels.getColor(x, y).equals(targetColor))) { 
			return;
		}
		//recursive call
		mutablePixels.setColorAndWait(x, y, replacementColor); // set color of target pixel
		
		async(() -> { // so it works smoothly
			floodFillKernel(mutablePixels, targetColor, replacementColor, x + 1, y); // right
			floodFillKernel(mutablePixels, targetColor, replacementColor, x - 1, y); // left
			floodFillKernel(mutablePixels, targetColor, replacementColor, x, y + 1); // down
			floodFillKernel(mutablePixels, targetColor, replacementColor, x, y - 1); // up
		});
		return;
	}

	/**
	 * This is the method that will call floodFillKernel. It starts your
	 * recursive algorithm by finding the color of the original pixel and
	 * calling floodFillKernel on that pixel.
	 * 
	 * @param writableImage
	 *            The original image to manipulate for the flood fill.
	 * @param replacementColor
	 *            The new color to fill the area with.
	 * @param x
	 *            The x-coordinate of the flood fill starting point.
	 * @param y
	 *            The y-coordinate of the flood fill starting point.
	 */
	private static void floodFill(WritableImage writableImage, Color replacementColor, int x, int y) {
		MutablePixels mutablePixels = new MutablePixels(writableImage);
		Color targetColor = mutablePixels.getColor(x, y);
		if (replacementColor.equals(targetColor)) {
			// pass
		} else {
			floodFillKernel(mutablePixels, targetColor, replacementColor, x, y);
		}
	}

	private static void clampImageToBlackAndWhite(WritableImage image) {
		PixelReader pixelReader = image.getPixelReader();
		PixelWriter pixelWriter = image.getPixelWriter();
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				Color color = pixelReader.getColor(x, y);
				double opacity = color.getOpacity();
				pixelWriter.setColor(x, y, opacity < 0.5 ? Color.WHITE : Color.BLACK);
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();

		String url = "https://sites.wustl.edu/publicaffairs/files/2015/07/Washington_University_Monogram_Open1c200-01-17zuofc.png";
		Image image = new Image(url);
		WritableImage writableImage = new WritableImage(image.getPixelReader(), (int) image.getWidth(),
				(int) image.getHeight());

		clampImageToBlackAndWhite(writableImage);

		ImageView imageView = new ImageView(writableImage);

		Color[] colors = ColorUtil.getColorPalette();
		AtomicInteger atomicCount = new AtomicInteger(0);

		BlockingQueue<HjSuspendable> queue = new LinkedBlockingQueue<>();
		HabaneroConsumerThread habaneroConsumerThread = new HabaneroConsumerThread(queue);
		root.setOnMousePressed((MouseEvent e) -> {
			int count = atomicCount.getAndIncrement();
			int colorIndex = count % colors.length;
			Color color = colors[colorIndex];
			int x = (int) e.getX();
			int y = (int) e.getY();
			queue.offer(() -> {
				floodFill(writableImage, color, x, y);
			});
		});
		Scene scene = new Scene(root);
		root.setCenter(imageView);

		primaryStage.setTitle("Flood Fill");
		primaryStage.setScene(scene);
		primaryStage.show();

		Platform.runLater(() -> primaryStage.sizeToScene());
		habaneroConsumerThread.start();

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
