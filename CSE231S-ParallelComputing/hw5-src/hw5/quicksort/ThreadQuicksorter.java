package hw5.quicksort;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.test.part2.BThreadQuicksortTest;
import student.studio.quicksort.PartitionLocation;
import student.studio.quicksort.PartitionUtils;

/**
 * A thread-based implementation of the quicksort algorithm. You can test this
 * class with {@link BThreadQuicksortTest}.
 * 
 * @author Finn Voichick
 */
public final class ThreadQuicksorter extends Quicksorter {

	/**
	 * Creates a ThreadQuicksorter.
	 */
	public ThreadQuicksorter() {
	}

	/**
	 * Should sort the given array in parallel using the quicksort algorithm.
	 * You should call
	 * {@link #parallelQuicksortKernel(int[], int, int, int, Queue)} to start
	 * off the recursion.
	 * <p>
	 * You should call {@link Thread#join()} in this method, rather in the
	 * recursive one. To accomplish this, you need to pass a collection of
	 * threads to the recursive method, and then join all of the threads added
	 * to the collection.
	 * 
	 * @param array
	 *            the array to sort
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @see ConcurrentLinkedQueue
	 * @see Thread
	 * @see ConcurrentLinkedQueue#ConcurrentLinkedQueue()
	 */
	public void parallelQuicksort(int[] array, int threshold) throws InterruptedException {
		Queue<Thread> queue = new ConcurrentLinkedQueue<Thread>();
		parallelQuicksortKernel(array, threshold, 0, array.length - 1, queue);
		
		for( Thread t: queue) { 
			t.join();
		}
	}

	/**
	 * Should sort the given range of the array in parallel using the quicksort
	 * algorithm. If the length is less than or equal to the threshold, it is
	 * not worth parallelizing, and this method should count sequentially using
	 * {@link #sequentialQuicksortKernel(int[], int, int)}. Otherwise, it should
	 * create two threads for the lower and upper halves of this range.
	 * <p>
	 * Note: This method should not call {@link Thread#join()}. This method
	 * starts the threads, and {@link #parallelQuicksort(int[], int)} joins
	 * them.
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
	 * @param threads
	 *            a thread-safe collection of threads that running threads can
	 *            be added to
	 * @see Thread
	 */
	private void parallelQuicksortKernel(int[] array, int threshold, int min, int max, Queue<Thread> threads) {
		//adapted from QuickSort studio- studio 1.
		int lengthOfSubArray = (max - min) + 1;
		if (lengthOfSubArray > 1) { 
			PartitionLocation partitionLocation = PartitionUtils.partitionSubArray(array, min, max);
			if (lengthOfSubArray > threshold) {
				Thread lowerhalf = new Thread(() -> {
					parallelQuicksortKernel(array,threshold, min, partitionLocation.getLeftSidesUpperInclusive(), threads);
				});
				Thread upperhalf = new Thread(() -> {
					parallelQuicksortKernel(array,threshold, partitionLocation.getRightSidesLowerInclusive(), max, threads);
				});
				lowerhalf.start();
				upperhalf.start();
				
				threads.add(lowerhalf);
				threads.add(upperhalf);
				
			} else {
				sequentialQuicksortKernel(array, min, max);

			}
		}
	}

}
