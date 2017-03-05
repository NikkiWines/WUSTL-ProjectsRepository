package edu.wustl.cse231s.fx;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum FxmlUtils {
	;
	public static Group loadGroup(URL url) throws IOException {
		FXMLLoader loader = new FXMLLoader(url);
		return loader.load();
	}
}
