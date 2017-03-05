package student.studio.wordscore;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/*package-private*/ enum ResultsCheckingUtils {
	;
	/*package-private*/ static void checkCollection(List<String> sourceLines, Collection<String> words) {
		if (sourceLines.size() != words.size()) {
			throw new RuntimeException(sourceLines.size() + " != " + words.size());
		}
	}
	/*package-private*/ static void checkList(List<String> sourceLines, List<String> words) {
		checkCollection(sourceLines, words);
		int i = 0;
		for (String word : words) {
			String sourceLine = sourceLines.get(i); 
			if (word.equals(WordScoreUtils.toCleanedWord(sourceLine))) {
				// pass
			} else {
				throw new RuntimeException(word + " _" + sourceLine + "_");
			}
			i++;
		}
	}
	/*package-private*/ static void checkMap(List<String> sourceLines, Map<String,Integer> mapWordToScore) {
		if (sourceLines.size() != mapWordToScore.size()) {
			throw new RuntimeException(sourceLines.size() + " != " + mapWordToScore.size());
		}
		for( String sourceLine : sourceLines ) {
			if( mapWordToScore.containsKey(WordScoreUtils.toCleanedWord(sourceLine))) {
				//pass
			} else {
				throw new RuntimeException( sourceLine );
			}
		}
		
		if( mapWordToScore.get("defenestration") != 18 ) {
			throw new RuntimeException();
		}
		if( mapWordToScore.get("quixotic") != 26 ) {
			throw new RuntimeException();
		}
	}
}
