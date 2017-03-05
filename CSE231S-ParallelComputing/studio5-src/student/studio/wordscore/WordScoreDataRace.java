package student.studio.wordscore;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.io.FileUtils;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.download.DownloadUtils;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class WordScoreDataRace {
	public static Collection<String> toCleanedWords(Collection<String> sourceLines) throws SuspendableException {
		// TODO: for each sourceLine in parallel add a cleaned word to the return value.
		//       useful jdk class: ConcurrentLinkedQueue<>
		//       useful course method: WordScoreUtils.toCleanedWord() 
		Collection<String> result = new ConcurrentLinkedQueue<>();
		finish(() -> {
			for (String sourceLine: sourceLines) { 
				async(() -> {
					String cleanedWord = WordScoreUtils.toCleanedWord(sourceLine);
					result.add(cleanedWord);
				});
			}
		});
		return result;
	}

	public static List<String> toCleanedWordsViaArray(Collection<String> sourceLines) throws SuspendableException {
		// TODO: same as toCleanedWords except use an array to avoid the data race.
		//       useful course method: WordScoreUtils.toCleanedWord() 
		//       useful jdk method: Arrays.asList()
		String [] result = new String [sourceLines.size()];
		List<String> strings = null;
		finish(() -> {
			for (String sourceLine: sourceLines) { 
				async(() -> {
					String cleanedWord = WordScoreUtils.toCleanedWord(sourceLine);
					});
			}
		});
		//throw new NotYetImplementedException();
		return strings;
	}

	private static Map<String, Integer> createLetterToScoreMap(Collection<String> words,
			Map<Character, Integer> mapLetterToScore) throws SuspendableException {
		// TODO: for each word in parallel associate the word with its score in the map return value.
		//       useful jdk class: ConcurrentHashMap<>
		//       useful jdk method: Map.put()
		//       useful course method: WordScoreUtils.calculateScore() 
		Map<String, Integer> result = new HashMap<>();
			for (String word: words) { 
					int score = WordScoreUtils.calculateScore(word, mapLetterToScore);
					result.put(word, score);
			}
		return result;
		//throw new NotYetImplementedException();
	}

	private static Map<String, Integer> createLetterToScoreMapViaArray(List<String> words,
			Map<Character, Integer> mapLetterToScore) throws SuspendableException {
		// TODO: same as createLetterToScoreMap except use an array to avoid the data race.
		//       useful course method: EntryUtils.createEntryArray() 
		//       useful course method: WordScoreUtils.toCleanedWord() 
		//       useful course method: EntryUtils.createEntry() 
		//       useful course method: EntryUtils.createMapFromArrayOfEntries() 
		throw new NotYetImplementedException();
	}
		
	public static void main(String[] args) throws Exception {
		Map<Integer, String> scoreToLettersMap = WordScoreUtils.createScoreToLettersMap();
		Map<Character, Integer> letterToScoreMap = WordScoreUtils.createLetterToScoreMap(scoreToLettersMap);

		URL wordsUrl = new URL("https://users.cs.duke.edu/~ola/ap/linuxwords");
		System.out.print("get/download: \"" + wordsUrl + "\"... ");
		File file = DownloadUtils.getDownloadedFile(wordsUrl, 409048L);
		System.out.println(" done.");

		System.out.print("reading: " + file.getAbsolutePath() + "... ");
		List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
		System.out.println(" done.");

		System.out.print("toUnique: ");
		List<String> sourceLines = DataCleaningUtils.toUnique( lines );
		System.out.println(" done.");
		
		launchHabaneroApp(() -> {
			final int N_TEST_INTERATIONS = 10;
			for (int testIteration = 0; testIteration < N_TEST_INTERATIONS; testIteration++) {
				try {
					Collection<String> words = toCleanedWords(sourceLines);
					ResultsCheckingUtils.checkCollection(sourceLines, words);
					System.out.println("iteration: " + testIteration + "; words checks out.");
					try {
						Map<String,Integer> mapWordToScore = createLetterToScoreMap(words, letterToScoreMap);
						ResultsCheckingUtils.checkMap(sourceLines, mapWordToScore);
						System.out.println("iteration: " + testIteration + "; mapWordToScore checks out.");
					} catch( NotYetImplementedException nyie ) {
						System.out.println( "TODO: createLetterToScoreMap" );
					}
				} catch( NotYetImplementedException nyie ) {
					System.out.println( "TODO: toCleanedWords" );
				}
					
				
				try {
					List<String> wordsViaArray = toCleanedWordsViaArray(sourceLines);
					ResultsCheckingUtils.checkList(sourceLines, wordsViaArray);
					System.out.println("iteration: " + testIteration + "; words checks out (array).");
					try {
						Map<String,Integer> mapViaArray = createLetterToScoreMapViaArray(wordsViaArray, letterToScoreMap);
						ResultsCheckingUtils.checkMap(sourceLines, mapViaArray);
						System.out.println("iteration: " + testIteration + "; mapWordToScore checks out (array).");
					} catch( NotYetImplementedException nyie ) {
						System.out.println( "TODO: createLetterToScoreMapViaArray" );
					}
				} catch( NotYetImplementedException nyie ) {
					System.out.println( "TODO: toCleanedWordsViaArray" );
				}
					
				System.out.println();
			}
		});
	}
}
