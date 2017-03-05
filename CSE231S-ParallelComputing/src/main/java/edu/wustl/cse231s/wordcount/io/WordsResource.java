package edu.wustl.cse231s.wordcount.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum WordsResource {
	SHAKESPEARE("https://ocw.mit.edu/ans7870/6/6.006/s08/lecturenotes/files/t8.shakespeare.txt", 5458199L),
	TWAIN("https://archive.org/stream/completeworksofm07twaiiala/completeworksofm07twaiiala_djvu.txt", 846080L);
	private WordsResource(String urlSpec, long fileLength) {
		this.urlSpec = urlSpec;
		this.fileLength = fileLength;
	}

	public long getFileLength() {
		return this.fileLength;
	}

	public URL getUrl() {
		try {
			return new URL(this.urlSpec);
		} catch (MalformedURLException murle) {
			throw new Error(murle);
		}
	}

	private final String urlSpec;
	private final long fileLength;
}
