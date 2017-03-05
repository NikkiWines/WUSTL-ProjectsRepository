package student.studio.pixelfilter;

import javafx.scene.paint.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public interface PixelFilter {
	Color filter( Color in );
}
