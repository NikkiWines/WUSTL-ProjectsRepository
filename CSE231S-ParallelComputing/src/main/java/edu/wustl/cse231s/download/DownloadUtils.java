package edu.wustl.cse231s.download;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public enum DownloadUtils {
	;
	private static final long DO_NOT_CHECK_FILE_LENGTH = -1;
	public static File getDownloadsDirectory() {
		File homeDirectory = new File(System.getProperty("user.home"));
		File downloadsDirectory = new File( homeDirectory, "Downloads" );
		if( downloadsDirectory.exists() ) {
			if( downloadsDirectory.isDirectory() ) {
				return downloadsDirectory;
			} else {
				throw new RuntimeException( downloadsDirectory.getAbsolutePath() + " is not a directory." );
			}
		} else {
			throw new RuntimeException( downloadsDirectory.getAbsolutePath() + " does not exist." );
		}
	}
	public static File getDownloadedFile( URL url, long fileLengthCheck ) throws IOException {
		String name = FilenameUtils.getName(url.getFile());
		File file = new File( getDownloadsDirectory(), name );
		boolean isAlreadyDownloaded = false;
		if( file.exists() ) {
			if( fileLengthCheck != DO_NOT_CHECK_FILE_LENGTH ) {
				long fileLength = file.length();
				if( fileLength == fileLengthCheck ) {
					isAlreadyDownloaded = true;
				} else {
					System.err.println();
					System.err.println();
					System.err.println();
					System.err.println( file + " file length check failed.  expecting: " + fileLengthCheck + "; found: " + fileLength + ".  Re-downloading." );
					System.err.println();
					System.err.println();
					System.err.println();
				}
			} else {
				isAlreadyDownloaded = true;
			}
		}
		
		if( isAlreadyDownloaded ) {
			//pas
		} else {
			FileUtils.copyURLToFile(url, file);
			if( fileLengthCheck != DO_NOT_CHECK_FILE_LENGTH ) {
				long fileLength = file.length();
				if( fileLength == fileLengthCheck ) {
					//pass 
				} else {
					throw new IOException( file + " file length check failed.  expecting: " + fileLengthCheck + "; found: " + fileLength );
				}
			}
		}
		return file;
	}
	public static File getDownloadedFile( URL url ) throws IOException {
		return getDownloadedFile(url, DO_NOT_CHECK_FILE_LENGTH);
	}
}
