package edu.wustl.cse231s.wordcount;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.wustl.cse231s.download.DownloadUtils;
import edu.wustl.cse231s.wordcount.io.WordsResource;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class WordCountUtils {
	private WordCountUtils() {
		throw new Error();
	}
	
	public static String[][] readAllLinesOfWords( WordsResource wordsResource ) throws IOException {
		System.out.print("get/download: \"" + wordsResource.getUrl() + "\"... ");
		File file = DownloadUtils.getDownloadedFile(wordsResource.getUrl(), wordsResource.getFileLength());
		System.out.println(" done.");
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

		String regex = "[^\\w']+";
		
		String[][] linesOfWords = new String[lines.size()][];
		int i = 0;
		for( String line : lines ) {
			linesOfWords[ i ] = line.split(regex);
			
//			for( int j=0; j<linesOfWords[i].length; j++ ) {
//				linesOfWords[i][j] = linesOfWords[i][j].toLowerCase();
//			}
			
			i++;
		}
		
		return linesOfWords;
	}
	
	public static void checkWordCount( String[][] linesOfWords, Map<String,Integer> mapWordToCount ) {
		Map<String, Integer> truthAndBeauty = new HashMap<>();
		for( String[] lineOfWords : linesOfWords ) {
			for( String word : lineOfWords ) {
				if( word.length() > 0 ) {
					truthAndBeauty.compute(word.toLowerCase(), (k,v)-> {
						return v!=null ? v+1 : 1;
					} );
				}
			}
		}
		for( Entry<String, Integer> entry : truthAndBeauty.entrySet() ) {
			String key = entry.getKey();
			int value = entry.getValue();
			Integer checkValue = mapWordToCount.get(key);
			if( checkValue != null ) {
				if( value != checkValue ) {
					throw new RuntimeException( entry.toString() + " " + checkValue );
				}
			} else {
				throw new RuntimeException( entry.toString() + " is null" );
			}
		}
	}

	public static void printWordsToCountsSortedByCounts(Map<String, Integer> mapWordToCount) {
		List<Entry<String, Integer>> list = new ArrayList<>(mapWordToCount.entrySet());
		list.sort((a, b) -> {
			return a.getValue() - b.getValue();
		});

		for (Entry<String, Integer> entry : list) {
			System.out.println(entry);
		}
	}
	
}
