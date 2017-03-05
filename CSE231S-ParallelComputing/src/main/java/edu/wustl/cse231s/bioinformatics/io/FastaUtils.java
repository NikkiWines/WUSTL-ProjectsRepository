package edu.wustl.cse231s.bioinformatics.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum FastaUtils {
	;

	public static byte[] read(File file) throws IOException {
		GZIPInputStream is = new GZIPInputStream(new FileInputStream(file));
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (true) {
				String line = br.readLine();
				if (line != null) {
					byte[] bytes = line.getBytes();
					if (bytes[0] == (byte) '>') {
						// pass
					} else {
						baos.write(bytes);
					}
				} else {
					break;
				}
			}
			return baos.toByteArray();
		} finally {
			br.close();
		}
	}
}
