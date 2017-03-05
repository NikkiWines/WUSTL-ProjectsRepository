package student.studio.datarace.collection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
/*package-private*/ enum CollectionResultsCheckingUtils {
	;
	/*package-private*/ static void dataRaceCheck(Collection<Integer> collection, int n) {
		Set<Integer> set = new HashSet<>(collection);
		for (int i = 0; i < n; i++) {
			if (set.contains(i)) {
				// pass
			} else {
				throw new RuntimeException("does not contain " + i);
			}
		}
	}

	/*package-private*/ static void functionalDetermismCheck(Collection<Integer> collection, int n,	NonFunctionallyDeterministicPolicy policy) {
		int i = 0;
		for (int value : collection) {
			if (value == i) {
				// pass
			} else {
				String message = "functionally NON-deterministic: expected " + i + "; found " + value;
				if (policy == NonFunctionallyDeterministicPolicy.THROW_EXCEPTION) {
					throw new RuntimeException(message);
				} else {
					System.out.println("                             NOTE: " + message);
					break;
				}
			}
			i++;
		}
	}
}
