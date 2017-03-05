package student.studio.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.wustl.cse231s.wordcount.WordCountUtils;
import edu.wustl.cse231s.wordcount.io.WordsResource;
import student.solution.mapreduce.apps.wordcount.WordCountMapper;
import student.solution.mapreduce.apps.wordcount.WordCountReducer;
import student.studio.mapreduce.concrete.wordcount.WordCountConcreteStaticMapReduce;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestWordCountConcreteStaticFramework {
	
	// question for students: why do we need to remove the duplicates for this case?
	private static void removeDuplicateWordsFromLines( String[][] linesOfWords ) {
		int i = 0;
		for( String[] lineOfWords : linesOfWords ) {
			Set<String> uniqueWordSet = new HashSet<>();
			for( String word : lineOfWords ) {
				uniqueWordSet.add(word.toLowerCase());
			}
			String[] uniqueWordArray = new String[ uniqueWordSet.size() ];
			linesOfWords[i] = uniqueWordSet.toArray(uniqueWordArray);
			i++;
		}
	}
	public static void main(String[] args) throws Exception {
		WordsResource wordsResource = WordsResource.TWAIN;
		String[][] linesOfWords = WordCountUtils.readAllLinesOfWords(wordsResource);

		removeDuplicateWordsFromLines( linesOfWords );
		
		launchHabaneroApp(() -> {
			WordCountMapper mapper = new WordCountMapper();
			WordCountReducer reducer = new WordCountReducer();
			Map<String, Integer> mapWordToCount = WordCountConcreteStaticMapReduce.mapReduceAll(linesOfWords, mapper, reducer);
			WordCountUtils.checkWordCount(linesOfWords, mapWordToCount);
			WordCountUtils.printWordsToCountsSortedByCounts(mapWordToCount);
			
			System.out.println("NOTE: duplicate words removed");
		});
	}
}
