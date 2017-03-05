package student.studio.wordscore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/*package-private*/ enum DataCleaningUtils {
	;
	
	//sadly, in order to give the list to list conversion something to do
	//we need to get rid of any duplicates that would be lost in the map later
	/*package-private*/ static List<String> toUnique( List<String> sourceLines ) {
		Set<String> set = new HashSet<>();
		List<String> result = new ArrayList<>( sourceLines.size() );
		for( String sourceLine : sourceLines ) {
			String word = WordScoreUtils.toCleanedWord(sourceLine);
			if( set.contains(word) ) {
				//pass
			} else {
				set.add(word);
				result.add(sourceLine);
			}
		}
		return result;
	}
}
