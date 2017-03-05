package edu.wustl.cse231s.sudoku.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import edu.wustl.cse231s.download.DownloadUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum PuzzlesResourceUtils {
	;
	public static List<String> readGivens( PuzzlesResource puzzlesResource ) throws IOException {
		System.out.print("get/download: \"" + puzzlesResource.getUrl() + "\"... ");
		File file = DownloadUtils.getDownloadedFile(puzzlesResource.getUrl(), puzzlesResource.getFileLength());
		System.out.println(" done.");
		return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
	}
}
