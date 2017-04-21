package hw5.quicksort;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.test.part2.ASequentialQuicksortTest;
import student.studio.quicksort.PartitionLocation;
import student.studio.quicksort.PartitionUtils;

/**
 * A class used for implementing the quicksort algorithm. You can test this
 * class with {@link ASequentialQuicksortTest}.
 * 
 * @author Finn Voichick
 */
public class Quicksorter {

	/**
	 * Creates a Quicksorter.
	 */
	public Quicksorter() {
	}

	/**
	 * Should sequentially sort the given array using the quicksort algorithm.
	 * You should call {@link #sequentialQuicksortKernel(int[], int, int)} to
	 * start off the recursion.
	 * 
	 * @param array
	 *            the array to sort
	 */
	public void sequentialQuicksort(int[] array) {
		sequentialQuicksortKernel(array, 0, array.length - 1);

	}

	/**
	 * Should sequentially and recursively sort the given range of the array,
	 * from min (inclusive) to max (inclusive).
	 * 
	 * @param array
	 *            the array to sort
	 * @param min
	 *            the minimum value (inclusive) of the range to sort
	 * @param max
	 *            the maximum value (inclusive) of the range to sort
	 * @see PartitionLocation
	 * @see PartitionUtils
	 * @see PartitionLocation#getLeftSidesUpperInclusive()
	 * @see PartitionLocation#getRightSidesLowerInclusive()
	 * @see PartitionUtils#partitionSubArray(int[], int, int)
	 */
	protected static void sequentialQuicksortKernel(int[] array, int min, int max) {
		// from studio 1;
		int lengthOfSubArray = (max - min) + 1;
		if( lengthOfSubArray > 1 ) {
			PartitionLocation partitionLocation = PartitionUtils.partitionSubArray(array, min, max);
			
			sequentialQuicksortKernel(array, min, partitionLocation.getLeftSidesUpperInclusive());
			sequentialQuicksortKernel(array, partitionLocation.getRightSidesLowerInclusive(), max);

		}
	}

}
