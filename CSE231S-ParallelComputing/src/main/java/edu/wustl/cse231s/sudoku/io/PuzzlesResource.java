package edu.wustl.cse231s.sudoku.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum PuzzlesResource {
	HARDEST(902L), 
	TOP95(7790L);
	private PuzzlesResource(long fileLength) {
		this.fileLength = fileLength;
	}

	public long getFileLength() {
		return this.fileLength;
	}

	public URL getUrl() {
		try {
			return new URL("http://norvig.com/" + this.name().toLowerCase() + ".txt");
		} catch (MalformedURLException murle) {
			throw new Error(murle);
		}
	}

	private final long fileLength;
}
