package hw5.quicksort;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.test.part2.CExecutorTest;
import student.studio.quicksort.PartitionLocation;
import student.studio.quicksort.PartitionUtils;

/**
 * An implementation of the quicksort algorithm that uses Java's Executors. You
 * can test this class with {@link CExecutorTest}.
 * 
 * @author Finn Voichick
 */
public final class ExecutorQuicksorter extends Quicksorter {

	/** The ExecutorService to which tasks are submitted. */
	private final ExecutorService executor;

	/**
	 * Should create an ExecutorQuicksorter. You will need to initialize the
	 * executor.
	 * 
	 * @see Executors
	 */
	public ExecutorQuicksorter() {
		executor = Executors.newCachedThreadPool();

	}

	/**
	 * Should sort the given array in parallel using the quicksort algorithm.
	 * You should call
	 * {@link #parallelQuicksortKernel(int[], int, int, int, Queue)} to start
	 * off the recursion.
	 * <p>
	 * You should call {@link Future#get()} in this method, rather in the
	 * recursive one. Like with {@link ThreadQuicksorter}, you need to pass a
	 * collection of futures to the recursive method, and then get all of the
	 * futures added to the collection.
	 * 
	 * @param array
	 *            the array to sort
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @see ConcurrentLinkedQueue
	 * @see Future
	 */
	public void parallelQuicksort(int[] array, int threshold) throws InterruptedException, ExecutionException {
		Queue<Future<?>> queue = new ConcurrentLinkedQueue<Future<?>>();
		parallelQuicksortKernel(array, threshold, 0, array.length - 1, queue);

		for( Future<?> f: queue) { 
			f.get();
		}
	}

	/**
	 * Should sort the given range of the array in parallel using the quicksort
	 * algorithm. If the length is less than or equal to the threshold, it is
	 * not worth parallelizing, and this method should count sequentially.
	 * Otherwise, it should submit two tasks to the executor for the lower and
	 * upper halves of this range.
	 * <p>
	 * Note: This method should not call {@link Future#get()}. This method
	 * submits the tasks, and {@link #parallelQuicksort(int[], int)} gets the
	 * futures.
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
	 * @param tasks
	 *            a thread-safe collection of futures to which newly-submitted
	 *            tasks can be added
	 * @see ExecutorService
	 * @see Runnable
	 * @see ExecutorService#submit(Runnable)
	 * @see Quicksorter#sequentialQuicksortKernel(int[], int, int)
	 */
	private void parallelQuicksortKernel(int[] array, int threshold, int min, int max, Queue<Future<?>> tasks) {
		//adapted from QuickSort studio- studio 1 and from ThreadQuicksorter
		int lengthOfSubArray = (max - min) + 1;
		if (lengthOfSubArray > 1) { 
			PartitionLocation partitionLocation = PartitionUtils.partitionSubArray(array, min, max);
			if (lengthOfSubArray > threshold) {
				Future<?> lowerFuture =  executor.submit(() -> { 
					parallelQuicksortKernel(array,threshold, min, partitionLocation.getLeftSidesUpperInclusive(), tasks);
				});
				Future<?> upperFuture =  executor.submit(() -> { 
					parallelQuicksortKernel(array,threshold, partitionLocation.getRightSidesLowerInclusive(), max, tasks);
				});;
				
				tasks.add(lowerFuture);
				tasks.add(upperFuture);
				
			} else {
				sequentialQuicksortKernel(array, min, max);
			}
		}

	}

}
