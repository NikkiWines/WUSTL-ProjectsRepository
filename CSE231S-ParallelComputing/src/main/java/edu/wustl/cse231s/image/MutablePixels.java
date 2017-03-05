package edu.wustl.cse231s.image;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

/**
 * This class represents an array of pixels which you will have to manipulate
 * for your flood fill algorithm.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class MutablePixels {
	public MutablePixels(Image image) {
		if (image instanceof WritableImage) {
			this.writableImage = (WritableImage) image;
		} else {
			this.writableImage = new WritableImage(image.getPixelReader(), (int) image.getWidth(),
					(int) image.getHeight());
		}
	}

	public int getWidth() {
		return (int) this.writableImage.getWidth();
	}

	public int getHeight() {
		return (int) this.writableImage.getHeight();
	}

	/**
	 * Gets the Color of the pixel at the given coordinates.
	 * 
	 * @param x
	 *            The x-coordinate of the desired pixel.
	 * @param y
	 *            The y-coordinate of the desired pixel.
	 * @return The color of the image at the given pixel.
	 */
	public Color getColor(int x, int y) {
		return this.writableImage.getPixelReader().getColor(x, y);
	}

	/**
	 * Sets the pixel at the given coordinates to a replacement color.
	 * 
	 * @param x
	 *            The x-coordinate of the desired pixel.
	 * @param y
	 *            The y-coordinate of the desired pixel.
	 * @param color
	 *            The new color for the pixel.
	 */
	public void setColor(int x, int y, Color color) {
		this.writableImage.getPixelWriter().setColor(x, y, color);
	}

	/**
	 * Sets the pixel at the given coordinates to a replacement color, then
	 * waits a bit before continuing. This method is similar in function to the
	 * setColor method, except that it waits afterwards. This is useful if you
	 * want to be able to visualize exactly what your flood fill algorithm is
	 * doing.
	 * 
	 * @param x
	 *            The x-coordinate of the desired pixel.
	 * @param y
	 *            The y-coordinate of the desired pixel.
	 * @param color
	 *            The new color for the pixel.
	 */
	public void setColorAndWait(int x, int y, Color color) {
		if (Platform.isFxApplicationThread()) {
			this.writableImage.getPixelWriter().setColor(x, y, color);
		} else {
			CountDownLatch countDownLatch = new CountDownLatch(1);
			Platform.runLater(() -> {
				this.writableImage.getPixelWriter().setColor(x, y, color);
				countDownLatch.countDown();
			});
			try {
				countDownLatch.await();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void copyAllColors(Image from, PixelWriter to) {
		final int bytesPerPixel = 4;
		int x = 0;
		int y = 0;
		int width = (int) from.getWidth();
		int height = (int) from.getHeight();
		byte[] buffer = new byte[width * height * bytesPerPixel];
		WritablePixelFormat<ByteBuffer> pixelformat = PixelFormat.getByteBgraInstance();
		int offset = 0;
		int scanlineStride = width * 4;
		from.getPixelReader().getPixels(x, y, width, height, pixelformat, buffer, offset, scanlineStride);
		to.setPixels(x, y, width, height, pixelformat, buffer, offset, scanlineStride);
	}

	public WritableImage createWritableImage() {
		return new WritableImage(this.writableImage.getPixelReader(), (int) this.writableImage.getWidth(),
				(int) this.writableImage.getHeight());
	}

	public void copyAllColorsTo(WritableImage other) {
		copyAllColors(this.writableImage, other.getPixelWriter());
	}

	public void setAllColorsFrom(Image image) {
		copyAllColors(image, this.writableImage.getPixelWriter());
	}

	/**
	 * Checks to see whether this image contains a pixel at the given
	 * coordinates.
	 * 
	 * @param x
	 *            The x-coordinate of the desired pixel.
	 * @param y
	 *            The y-coordinate of the desired pixel.
	 * @return true if the given pixel coordinates are within the bounds of the
	 *         image, otherwise false.
	 */
	public boolean isInBounds(int x, int y) {
		return x >= 0 && x < this.writableImage.getWidth() && y >= 0 && y < this.writableImage.getHeight();
	}

	private final WritableImage writableImage;
}
