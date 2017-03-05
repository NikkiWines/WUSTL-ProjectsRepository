package student.studio.datarace.collection;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;

public class CollectionDataRaceAndDeterminism {
	private static Collection<Integer> generateTruthAndBeautyCollection(int length) {
		Collection<Integer> result = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			result.add(i);
		}
		return result;
	}

	private static Collection<Integer> generateDataRaceFreeCollection(int length) throws SuspendableException {
		// TODO: fix data race
		//       useful jdk class: ConcurrentLinkedQueue<> -- produces non functionally deterministic result
		Collection<Integer> result = new ConcurrentLinkedQueue<>();
		finish(() -> {
			for (int i = 0; i < length; i++) {
				final int accessible_i = i;
				async(() -> {
					result.add(accessible_i); // add method works, when you're done calling it the number will be in there but the order isnt defined. 
				});
			}
		});
		return result;
	}

	private static Collection<Integer> generateFunctionallyDeterministicCollection(int length) throws SuspendableException {
		// TODO: use an array to produce functionally deterministic collection 
		//       useful jdk method: Arrays.asList()
		Integer [] integers = new Integer [length];
		
		finish(() -> {
			for (int i = 0; i < length; i++) { 
				final int accessible_i = i;
				async(() -> {
					integers[accessible_i] = accessible_i; // add method works, when you're done calling it the number will be in there but the order isnt defined. 
					});
			}
		});
		//throw new NotYetImplementedException();
		return Arrays.asList(integers);
	}

	public static void main(String[] args) {
		final int N = 1_000;

		Collection<Integer> truthAndBeautyCollection = generateTruthAndBeautyCollection(N);
		CollectionResultsCheckingUtils.dataRaceCheck(truthAndBeautyCollection, N);
		CollectionResultsCheckingUtils.functionalDetermismCheck(truthAndBeautyCollection, N, NonFunctionallyDeterministicPolicy.THROW_EXCEPTION);

		System.out.println("                 truth and beauty: " + truthAndBeautyCollection);

		launchHabaneroApp(() -> {
			for (int testIteration = 0; testIteration < 10; testIteration++) {
				System.out.println();
				Collection<Integer> dataRaceFreeCollection = generateDataRaceFreeCollection(N);
				System.out.println("            data race free test " + testIteration + ": " + dataRaceFreeCollection);
				CollectionResultsCheckingUtils.dataRaceCheck(dataRaceFreeCollection, N);
				CollectionResultsCheckingUtils.functionalDetermismCheck(dataRaceFreeCollection, N, NonFunctionallyDeterministicPolicy.PRINT_NOTE);

				System.out.println();
				try {
					Collection<Integer> functionallyDeterministicCollection = generateFunctionallyDeterministicCollection(N);
					System.out.println("functionally deterministic test " + testIteration + ": " + functionallyDeterministicCollection);
					CollectionResultsCheckingUtils.dataRaceCheck(functionallyDeterministicCollection, N);
					CollectionResultsCheckingUtils.functionalDetermismCheck(functionallyDeterministicCollection, N, NonFunctionallyDeterministicPolicy.THROW_EXCEPTION);
				} catch( NotYetImplementedException nyie ) {
					System.out.println("NOT YET IMPLEMENTED: generateFunctionallyDeterministicCollection()");
				}
			}
		});
	}
}
