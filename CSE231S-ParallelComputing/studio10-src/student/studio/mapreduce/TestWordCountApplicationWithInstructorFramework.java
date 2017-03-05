package student.studio.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.wordcount.WordCountUtils;
import edu.wustl.cse231s.wordcount.io.WordsResource;
import instructor.solution.mapreduce.InstructorUtils;
import student.solution.mapreduce.apps.wordcount.WordCountMapper;
import student.solution.mapreduce.apps.wordcount.WordCountReducer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestWordCountApplicationWithInstructorFramework {
	public static void main(String[] args) throws Exception {
		WordsResource wordsResource = WordsResource.TWAIN;
		String[][] linesOfWords = WordCountUtils.readAllLinesOfWords(wordsResource);

		launchHabaneroApp(() -> {
			WordCountMapper mapper = new WordCountMapper();
			WordCountReducer reducer = new WordCountReducer();
			MapReduceFramework<String[], String, Integer> mapReduceFramework = InstructorUtils.newMatrixMapReduceFramework();
			Map<String, Integer> mapWordToCount = mapReduceFramework.mapReduceAll(linesOfWords, mapper, reducer);
			WordCountUtils.checkWordCount(linesOfWords, mapWordToCount);
			WordCountUtils.printWordsToCountsSortedByCounts(mapWordToCount);
		});
	}
}
