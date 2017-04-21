package hw5.nucleobase;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import edu.wustl.cse231s.sleep.SleepUtils;
import hw5.slice.Slice;
import hw5.test.part1.CThreadNucleobaseCountTest;

/**
 * A parallel nucleobase counter that uses threads to count different sections
 * of the chromosome in parallel. You can test this class with
 * {@link CThreadNucleobaseCountTest}.
 * 
 * @author Finn Voichick
 */
public final class ThreadNucleobaseCounter extends NucleobaseCounter {

	/**
	 * Creates a ThreadNucleobaseCounter.
	 */
	public ThreadNucleobaseCounter() {
	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, creating two threads. The chromosome should be split into two
	 * halves, and the "upper" half should be counted at the same time
	 * (asynchronously) as the "lower" half.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @see Thread
	 * @see Runnable
	 * @see Thread#Thread(Runnable)
	 * @see Thread#start()
	 * @see Thread#join()
	 */
	public int count2WaySplit(byte[] chromosome, Nucleobase nucleobase) throws InterruptedException {
		final int[] sumArray = new int[2];
		//lower half
		Thread lowerhalf = new Thread(() -> {
			for (int i=0; i < chromosome.length/2; i++) { 
				if (chromosome[i] == nucleobase.toByte()) { 
					sumArray[0]++; 
				}
			}
		});

		//upper half 
		Thread upperhalf = new Thread(() -> {
			for (int i = chromosome.length/2; i < chromosome.length; i++ ) { 
				if (chromosome[i] == nucleobase.toByte()) { 
					sumArray[1]++;
				}
			}
		});
		lowerhalf.start();
		upperhalf.start();

		lowerhalf.join();
		upperhalf.join();

		final int sum  = sumArray[0] + sumArray[1]; // sum separate arrays
		return sum;

	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, creating the given number of threads. In other words, you
	 * should spawn n threads, each of which counts 1/n of the chromosome. For
	 * example, if numTasks is 8, the chromosome should be divided into 8
	 * pieces, and each of these pieces should be counted in a separate thread.
	 * <p>
	 * You should use {@link Slice} for this slicing. Take advantage of the
	 * {@link Slice#forEach(java.util.function.IntConsumer)} method.
	 * <p>
	 * Note: if numTasks is 2, the behavior of this method will be the same as
	 * {@link #count2WaySplit(byte[], Nucleobase)}.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @param numTasks
	 *            the number of tasks to create
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @see Slice
	 * @see Thread
	 * @see Slice#createSlices(int, int)
	 * @see Slice#forEach(IntConsumer)
	 */
	public int countNWaySplit(byte[] chromosome, Nucleobase nucleobase, int numTasks) throws InterruptedException {

		List<Thread> threadsList = new ArrayList<Thread>();
		final int[] sumArray = new int[numTasks];
		int sum = 0;

		//list of slices from slice class
		//each thread operates on one slice.
		for(Slice s : Slice.createSlices(numTasks, chromosome.length)) { // for each slice in the chromosome.
			threadsList.add(new Thread(() -> {  // new thread for each slice.
				s.forEach(i -> { // for each slice 
					if (chromosome[i] == nucleobase.toByte()) {
						sumArray[s.getSliceID()] ++;
					}
				}); 
			}));
		}
		for(Thread t : threadsList) { 
			t.start();
			t.join();
		}
		for (int i=0; i < sumArray.length; i++) { 
			sum = sum + sumArray[i]; // sum array
		}

		return sum;	
	}

	/**
	 * Should use a divide-and-conquer approach to count all of the instances of
	 * a specific nucleobase. This method should call
	 * {@link #countDivideAndConquerKernel(byte[], Nucleobase, int, int, int)}
	 * to start off the recursion.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 */
	public int countDivideAndConquer(byte[] chromosome, Nucleobase nucleobase, int threshold) {
		return countDivideAndConquerKernel(chromosome, nucleobase, threshold, 0, chromosome.length);
	}

	/**
	 * Should recursively use a divide-and-conquer approach to count all of the
	 * instances of a specific nucleobase in the given range. If the length is
	 * less than or equal to the threshold, it is not worth parallelizing, and
	 * this method should count sequentially using
	 * {@link #countSequential(byte[], Nucleobase, int, int)}. Otherwise, it
	 * should create two threads for the lower and upper halves of this range.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @param min
	 *            the minimum array index in the range to search, inclusive
	 * @param max
	 *            the maximum array index in the range to search, exclusive
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @see Thread
	 */
	private int countDivideAndConquerKernel(byte[] chromosome, Nucleobase nucleobase, int threshold, int min, int max) {
		// adapted from studio 3.
		try {
			final int mid = (min + max)/2;
			final int[] sum = new int[2];


				if (min-max > threshold) { 
					Thread lowerhalf = new Thread(() -> {
						sum[0] =countDivideAndConquerKernel(chromosome, nucleobase, threshold, min, mid);
					});
					Thread upperhalf = new Thread(() -> {
						sum[1] = countDivideAndConquerKernel(chromosome, nucleobase, threshold, mid, max);
					});
					lowerhalf.start();
					upperhalf.start();

					lowerhalf.join();
					upperhalf.join();
				}
				else { // below the thresh.
					return countSequential(chromosome, nucleobase, min, max);
				}
			int summed = sum[0] + sum[1];
			return summed;	

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
