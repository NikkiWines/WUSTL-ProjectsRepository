package edu.wustl.cse231s.color;

import javafx.scene.paint.Color;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum ColorUtil {
	;
	
	public static Color[] getColorPalette() {
		// chosen from conservative 7-color palette: http://mkweb.bcgsc.ca/colorblind/
		return new Color[] { /*Color.BLACK, */Color.rgb(230, 159, 0), Color.rgb(86, 180, 233), Color.rgb(0, 158, 115), Color.rgb(240, 228, 66), Color.rgb(0, 114, 178), Color.rgb(213, 94, 0), Color.rgb(204, 121, 167) };
	}
}
