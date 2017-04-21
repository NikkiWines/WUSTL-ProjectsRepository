package hw5.nucleobase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import hw5.slice.Slice;
import hw5.test.part1.DExecutorNucleobaseCountTest;

/**
 * A parallel nucleobase counter that uses Java's Executors to count different
 * sections of the chromosome in parallel. You can test this class with
 * {@link DExecutorNucleobaseCountTest}.
 * 
 * @author Finn Voichick
 */
public final class ExecutorNucleobaseCounter extends NucleobaseCounter implements AutoCloseable {

	/** An executor to which you can submit tasks. */
	private final ExecutorService executor;

	/**
	 * Should create an ExecutorNucleobaseCounter, initializing the executor.
	 * 
	 * @see Executors
	 * @see Executors#newCachedThreadPool()
	 */
	public ExecutorNucleobaseCounter() {
		executor = Executors.newCachedThreadPool();
		// TODO implement
		//throw new NotYetImplementedException();

	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, submitting two tasks. The chromosome should be split into two
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
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @see Callable
	 * @see ExecutorService
	 * @see Future
	 * @see ExecutorService#submit(Callable)
	 * @see Future#get()
	 */
	public int count2WaySplit(byte[] chromosome, Nucleobase nucleobase)
			throws InterruptedException, ExecutionException {
		final int[] sumArray = new int[2];

		Future<Integer> lowerFuture = executor.submit(() -> { 
			for (int i=0; i < chromosome.length/2; i++) { 
				if (chromosome[i] == nucleobase.toByte()) { 
					sumArray[0]++; 
				}
			}	
			return sumArray[0];
		});

		Future<Integer> upperFuture =  executor.submit(() -> { 
			for (int i = chromosome.length/2; i < chromosome.length; i++ ) { 
				if (chromosome[i] == nucleobase.toByte()) { 
					sumArray[1]++;
				}
			}
			return sumArray[1];
		});

		return lowerFuture.get() + upperFuture.get();

	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, creating the given number of tasks. In other words, you
	 * should submit n tasks, each of which counts 1/n of the chromosome. For
	 * example, if numTasks is 8, the chromosome should be divided into 8
	 * pieces, and each of these pieces should be counted in a separate task.
	 * You should use {@link Slice} for this slicing. You should not call
	 * {@link ExecutorService#submit(Callable)}. Note: if numTasks is 2, the
	 * behavior of this method will be the same as
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
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @see Callable
	 * @see ExecutorService
	 * @see Future
	 * @see Slice
	 * @see ExecutorService#invokeAll(Collection)
	 */
	public int countNWaySplit(byte[] chromosome, Nucleobase nucleobase, int numTasks)
			throws InterruptedException, ExecutionException {

		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		final int[] sumArray = new int[numTasks];
		int sum = 0;

		//list of slices from slice class
		//each thread operates on one slice.
		for(Slice s : Slice.createSlices(numTasks, chromosome.length)) { // for each slice in the chromosome.
			list.add(() -> {  // new callable for each slice.
				s.forEach(i -> { // for each slice 
					if (chromosome[i] == nucleobase.toByte()) {
						sumArray[s.getSliceID()] ++;
					}
				});
				return sumArray[0];
			});

		}
		List<Future<Integer>> futureList = executor.invokeAll(list); // returns a list of futures of type integer.

		for (Future<Integer> f: futureList) { 
			sum += f.get();
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
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 */
	public int countDivideAndConquer(byte[] chromosome, Nucleobase nucleobase, int threshold)
			throws InterruptedException, ExecutionException {
		return countDivideAndConquerKernel(chromosome, nucleobase, threshold, 0, chromosome.length);
	}

	/**
	 * Should recursively use a divide-and-conquer approach to count all of the
	 * instances of a specific nucleobase in the given range. If the length is
	 * less than or equal to the threshold, it is not worth parallelizing, and
	 * this method should count sequentially using
	 * {@link #countSequential(byte[], Nucleobase, int, int)}. Otherwise, it
	 * should create two tasks for the lower and upper halves of this range.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @param threshold
	 *            the threshold at which the work is no longer worth
	 *            parallelizing
	 * @param min
	 *            the lowest array index in the range to search, inclusive
	 * @param max
	 *            the highest array index in the range to search, exclusive
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @see Callable
	 * @see ExecutorService
	 * @see Future
	 */
	private int countDivideAndConquerKernel(byte[] chromosome, Nucleobase nucleobase, int threshold, int min, int max)
			throws InterruptedException, ExecutionException {
		int mid = (min + max)/2;

		if (min-max > threshold) { 
			Future<Integer> lowerFuture = executor.submit(() -> { 
				return 	countDivideAndConquerKernel(chromosome, nucleobase, threshold, min, mid);
			});

			Future<Integer> upperFuture =  executor.submit(() -> { 
				return countDivideAndConquerKernel(chromosome, nucleobase, threshold, mid, max);

			});
			return lowerFuture.get() + upperFuture.get();
		}
		else { 
			return countSequential(chromosome, nucleobase, min, max);
		}
	}

	/**
	 * Should shut down the executor.
	 * 
	 * @see ExecutorService
	 * @see ExecutorService#shutdown()
	 */
	@Override
	public void close() {
		executor.shutdown(); 
	}

}
