package student.solution.mapreduce.apps.wordcount;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.mapreduce.MapContext;
import edu.wustl.cse231s.mapreduce.Mapper;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class WordCountMapper implements Mapper<String[], String, Integer> {
	@Override
	public void map(MapContext<String, Integer> context, String[] lineOfWords) {
		for (String word : lineOfWords) { 
			if (word.length() > 0) { 
				context.emit(word.toLowerCase(), 1); // from quiz 12
			}
		}		
	}
}
