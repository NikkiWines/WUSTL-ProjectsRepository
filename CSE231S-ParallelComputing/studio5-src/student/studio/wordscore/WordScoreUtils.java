package student.studio.wordscore;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/*package-private*/ enum WordScoreUtils {
	;
	/*package-private*/ static Map<Integer, String> createScoreToLettersMap() {
		Map<Integer, String> result = new HashMap<>();
		result.put(10, "qz");
		result.put(8, "jx");
		result.put(5, "k");
		result.put(4, "fhvwy");
		result.put(3, "bcmp");
		result.put(2, "dg");
		result.put(1, "eaionrtlsu");
		return result;
	}

	/*package-private*/ static Map<Character, Integer> createLetterToScoreMap(Map<Integer, String> scoreToLettersMap) {
		Map<Character, Integer> result = new HashMap<>();
		for (Entry<Integer, String> entry : scoreToLettersMap.entrySet()) {
			int score = entry.getKey();
			String letters = entry.getValue();
			for (char letter : letters.toCharArray()) {
				result.put(letter, score);
			}
		}
		return result;
	}

	/*package-private*/ static String toCleanedWord( String sourceLine ) {
		String word = sourceLine.toLowerCase();
		return word.replaceAll("[^a-z]", "");
		
	}

	/*package-private*/ static int calculateScore(String word, Map<Character, Integer> mapLetterToScore) {
		int sum = 0;
		for( char letter : word.toCharArray() ) {
			sum += mapLetterToScore.get(letter);
//			char lowerCaseLetter = Character.toLowerCase(letter);
//			Integer score = mapLetterToScore.get(lowerCaseLetter);
//			if( score != null ) {
//				sum += score;
//			} else {
//				//pass
//			}
		}
		return sum;
	}
}
