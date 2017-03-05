package student.solution.mapreduce;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.numThreads;

import java.util.Map;

import edu.wustl.cse231s.mapreduce.MapReduceFramework;
import edu.wustl.cse231s.wordcount.WordCountUtils;
import edu.wustl.cse231s.wordcount.io.WordsResource;
import student.solution.mapreduce.apps.wordcount.WordCountMapper;
import student.solution.mapreduce.apps.wordcount.WordCountReducer;
import student.solution.mapreduce.matrix.MatrixMapReduceFramework;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class TestWordCountSolution {
	public static void main(String[] args) throws Exception {
		WordsResource wordsResource = WordsResource.TWAIN;
		String[][] linesOfWords = WordCountUtils.readAllLinesOfWords(wordsResource);

		launchHabaneroApp(() -> {
			int taskCountForMatrixFramework = numThreads();
			MapReduceFramework<String[], String, Integer> matrixFramework = new MatrixMapReduceFramework.Builder()
					.mapTaskCount(taskCountForMatrixFramework)
					.reduceTaskCount(taskCountForMatrixFramework)
					.build();

			WordCountMapper mapper = new WordCountMapper();
			WordCountReducer reducer = new WordCountReducer();

			int numIterations = 100;
			for (int i=0; i<numIterations; i++ )  {
				System.out.println(i);
				Map<String, Integer> mapWordToCount = matrixFramework.mapReduceAll(linesOfWords, mapper, reducer);
				WordCountUtils.checkWordCount(linesOfWords, mapWordToCount);
				if( i == numIterations-1 ) {
					WordCountUtils.printWordsToCountsSortedByCounts(mapWordToCount);
				}
			}
		});
	}
}
