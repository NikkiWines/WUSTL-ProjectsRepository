package hw5.quicksort;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.test.part4.BFrameworkQuicksortTest;

/**
 * An implementation of the quicksort algorithm using the frameworks created in
 * Part 3. You can test this class with {@link BFrameworkQuicksortTest}.
 * 
 * @author Finn Voichick
 */
public final class FrameworkQuicksorter extends Quicksorter {

	/** The framework providing the parallel constructs. */
	private final Framework framework;

	/**
	 * Should create a FrameworkNucleobaseCounter using the given framework.
	 * 
	 * @param framework
	 *            a parallel framework used for all of your parallel constructs
	 */
	public FrameworkQuicksorter(Framework framework) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should sort the given array in parallel using the quicksort algorithm.
	 * You should call
	 * {@link #parallelQuicksortKernel(int[], int, int, int, FinishContext)} to
	 * start off the recursion.
	 * <p>
	 * You should call {@link Framework#finish(java.util.function.Consumer)} in
	 * this method, rather in the recursive one. You will need to pass the
	 * FinishContext to all of the recursive calls, and then finish in this
	 * method.
	 * 
	 * @param array
	 *            the array to sortt
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 */
	public void parallelQuicksort(int[] array, int threshold) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should sort the given range of the array in parallel using the quicksort
	 * algorithm. If the length is less than or equal to the threshold, it is
	 * not worth parallelizing, and this method should count sequentially.
	 * Otherwise, it should submit two tasks to the executor for the lower and
	 * upper halves of this range.
	 * <p>
	 * Note: This method should not call
	 * {@link Framework#finish(java.util.function.Consumer)}. This method spawns
	 * the tasks, and {@link #parallelQuicksort(int[], int)} waits for them to
	 * complete.
	 * 
	 * @param array
	 *            the array to sort
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @param min
	 *            the minimum value (inclusive) of the range to sort
	 * @param max
	 *            the maximum value (inclusive) of the range to sort
	 * @param context
	 *            a context for calling async within the finish
	 */
	private void parallelQuicksortKernel(int[] array, int threshold, int min, int max, FinishContext context) {

		// TODO implement
		throw new NotYetImplementedException();

	}

}
