package student.studio.quicksort;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;

import java.util.Random;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.timing.ImmutableTimer;

/**
 * modified from
 * http://www.cs.rice.edu/~vs3/hjlib/code/course-materials/demo-files/Quicksort.java
 * 
 * Parallel Quicksort program. partition() has not been parallelized.
 *
 * @author Vivek Sarkar (vsarkar@rice.edu)
 */
public class QuicksortStudio {
	private static void sequentialQuicksortKernel(int[] array, int lowInclusive, int highInclusive) {
		int lengthOfSubArray = (highInclusive - lowInclusive) + 1;
		if( lengthOfSubArray > 1 ) {
			PartitionLocation partitionLocation = PartitionUtils.partitionSubArray(array, lowInclusive, highInclusive);

			sequentialQuicksortKernel(array, lowInclusive, partitionLocation.getLeftSidesUpperInclusive());
			sequentialQuicksortKernel(array, partitionLocation.getRightSidesLowerInclusive(), highInclusive);

		}
	}

	private static void sequentialQuicksort(int[] array) {
		sequentialQuicksortKernel(array, 0, array.length - 1);
	}

	private static void parallelQuicksortKernel(int[] array, int lowInclusive, int highInclusive, int threshold) {
		int lengthOfSubArray = (highInclusive - lowInclusive) + 1;
		if (lengthOfSubArray > 1) { 
			PartitionLocation partitionLocation = PartitionUtils.partitionSubArray(array, lowInclusive, highInclusive);
			if (lengthOfSubArray > threshold) {
				async(() -> { 
					parallelQuicksortKernel(array, lowInclusive, partitionLocation.getLeftSidesUpperInclusive(), threshold);
				});
				async(() -> { 
					parallelQuicksortKernel(array, partitionLocation.getRightSidesLowerInclusive(), highInclusive, threshold);
				});
			} else {
				sequentialQuicksortKernel(array, lowInclusive, partitionLocation.getLeftSidesUpperInclusive());
				sequentialQuicksortKernel(array, partitionLocation.getRightSidesLowerInclusive(), highInclusive);

			}
		}
	}

	private static void parallelQuicksort(int[] array, int threshold) throws SuspendableException {
		finish(() -> { 
			parallelQuicksortKernel(array, 0, array.length-1, threshold);	
		});

	}
	
	private static boolean isValid(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static void testSequentialQuicksort(int iteration, int[] original) {
		final int[] array = new int[original.length];
		System.arraycopy(original, 0, array, 0, array.length);
		try {
			ImmutableTimer timer = new ImmutableTimer("   sequentialQuicksort() Iteration #" + iteration);
			sequentialQuicksort(array);
			timer.markAndPrintResults();
			if (isValid(array)) {
				//pass
			} else {
				throw new RuntimeException("sequentialQuicksort() is not valid" );
			}
		} catch( NotYetImplementedException nyie ) {
			System.out.println(nyie.getMessage());
		}
	}

	private static void testParallelQuicksort(int iteration, int[] original, int threshold) throws SuspendableException {
		final int[] array = new int[original.length];
		System.arraycopy(original, 0, array, 0, array.length);
		try {
			ImmutableTimer timer = new ImmutableTimer("parallelQuicksort(" + String.format("%5d", threshold) + ") Iteration #" + iteration);
			parallelQuicksort(array, threshold);
			timer.markAndPrintResults();
			if (isValid(array)) {
				//pass
			} else {
				throw new RuntimeException("parallelQuicksort(" + String.format("%5d", threshold) + ") is not valid" );
			}
		} catch( NotYetImplementedException nyie ) {
			System.out.println(nyie.getMessage());
		}
	}

	public static void main(String[] args) {
		Random rand = new Random(231L);
		int size = 10_000_000;
		final int[] original = new int[size];
		for (int i = 0; i < size; i++) {
			original[i] = rand.nextInt();
		}

		System.out.println("array length: " + original.length);

		launchHabaneroApp(() -> {
			final int nIterations = 10;
			for (int iteration = 0; iteration < nIterations; iteration++) {
				System.out.println();
				testSequentialQuicksort(iteration, original);
				//testParallelQuicksort(iteration, original, 1);
				testParallelQuicksort(iteration, original, 10);
				testParallelQuicksort(iteration, original, 100);
				testParallelQuicksort(iteration, original, 1_000);
				testParallelQuicksort(iteration, original, 10_000);
			}
			System.out.println();
			System.out.println("Done: " + nIterations + " iterations");
		});

	}

}
