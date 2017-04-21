package hw5.nucleobase;

import java.util.concurrent.atomic.AtomicInteger;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.framework.executor.ExecutorFramework;
import hw5.framework.thread.ThreadFramework;
import hw5.test.part4.AFrameworkNucleobaseCounterTest;

/**
 * A nucleobase counting implementation that uses the frameworks created in part
 * 3. It should be designed to use either a ThreadFramework or an
 * ExecutorFramework. Lucky for you, Framework and FinishContext are interfaces,
 * so you should use their methods. You can test this class with
 * {@link AFrameworkNucleobaseCounterTest}.
 * 
 * @author Finn Voichick
 */
public final class FrameworkNucleobaseCounter extends NucleobaseCounter {

	/** The framework providing the parallel constructs. */
	private final Framework framework;

	/**
	 * Should create a FrameworkNucleobaseCounter using the given framework.
	 * Remember that Framework is an interface, implemented by
	 * {@link ThreadFramework} and {@link ExecutorFramework}, so this the
	 * framework passed here will be one of those two.
	 * 
	 * @param framework
	 *            a parallel framework used for all of your parallel constructs
	 */
	public FrameworkNucleobaseCounter(Framework framework) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, spawning two tasks. The chromosome should be split into two
	 * halves, and the "upper" half should be counted at the same time
	 * (asynchronously) as the "lower" half.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @see FinishContext
	 * @see Framework
	 * @see FinishContext#async(Runnable)
	 * @see Framework#finish(java.util.function.Consumer)
	 */
	public int count2WaySplit(byte[] chromosome, Nucleobase nucleobase) {
		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should asynchronously count all of the instances of a specific
	 * nucleobase, chunking (or slicing) the nucleobase based on the number of
	 * available processors on your machine. In other words, if your machine has
	 * n available processors, you should submit n tasks, each of which counts
	 * 1/n of the chromosome. For example, if numTasks is 8, the chromosome
	 * should be divided into 8 pieces, and each of these pieces should be
	 * counted in a separate task. Note: if numTasks is 2, the behavior of this
	 * method will be the same as {@link #count2WaySplit(byte[], Nucleobase)}.
	 * <p>
	 * You can use an {@link AtomicInteger} for counting. We will cover
	 * AtomicInteger in more detail later, but for now, just treat it as an
	 * accumulator. You can increment it using
	 * {@link AtomicInteger#incrementAndGet()}, and you can get its value using
	 * {@link AtomicInteger#get()}.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, represented as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 * @see Framework
	 * @see Framework#forAllChunked(int, int, java.util.function.IntConsumer)
	 */
	public int countChunked(byte[] chromosome, Nucleobase nucleobase) {

		// TODO implement
		throw new NotYetImplementedException();

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

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should recursively use a divide-and-conquer approach to count all of the
	 * instances of a specific nucleobase in the given range. If the length is
	 * less than or equal to the threshold, it is not worth parallelizing, and
	 * this method should count sequentially using
	 * {@link #countSequential(byte[], Nucleobase, int, int)}. Otherwise, it
	 * should spawn two tasks for the lower and upper halves of this range.
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
	 * @see FinishContext
	 * @see Framework
	 */
	private int countDivideAndConquerKernel(byte[] chromosome, Nucleobase nucleobase, int threshold, int min, int max) {

		// TODO implement
		throw new NotYetImplementedException();

	}

}
