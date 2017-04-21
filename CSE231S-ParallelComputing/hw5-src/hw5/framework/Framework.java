package hw5.framework;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.slice.Slice;
import hw5.test.part3.AThreadFrameworkAsyncFinishTest;
import hw5.test.part3.DFrameworkForAllTest;

/**
 * A framework for calling finish and async. Note that the async method isn't
 * contained here; that's at {@link FinishContext#async(Runnable)}.
 * <p>
 * To see how a framework is used, take a look at
 * {@link AThreadFrameworkAsyncFinishTest#testMultipleAsyncs()} and
 * {@link DFrameworkForAllTest#testForAll()}.
 * <p>
 * Like FinishContext, this is an interface, describing methods that several
 * classes have. You will be writing the default forAll and forAllChunked
 * methods here, but do not modify this file until you have gotten to Part 3D.
 * When you get here, you can test the methods with
 * {@link DFrameworkForAllTest}.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
public interface Framework extends AutoCloseable {

	/**
	 * Runs the given body and wait for all asyncs to finish. The body is a
	 * Consumer. Think of it like a function (a lambda) that uses a
	 * FinishContext.
	 * 
	 * @param body
	 *            the body of this finish
	 */
	public void finish(Consumer<FinishContext> body);

	/**
	 * Should spawn an asyncronous task for each element in iterable and wait
	 * for all of them to finish. Each of these tasks should run the body,
	 * passing it the element. In other words, you're iterating through the
	 * iterable and running the body on each element, but each iteration must
	 * run in parallel. This method is exactly like
	 * {@link FinishContext#forAsync(Iterable, Consumer)} except that it blocks
	 * until all iterations have finished. Your implementation should use the
	 * forAsync method.
	 * 
	 * @param <E>
	 *            the type of element used with each iteration
	 * @param iterable
	 *            the iterable with the values to go through
	 * @param body
	 *            the function to run on each iteration
	 */
	public default <E> void forAll(Iterable<E> iterable, Consumer<E> body) {
		//
		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should chunk the given range and run the body on each value. The number
	 * of chunks should be equal to the number of processors on your current
	 * machine. Each chunk (or {@link Slice}) should run in parallel, but within
	 * each chunk, the IntConsumer bodies should be run sequentially. Your
	 * implementation should make use of your
	 * {@link #forAll(Iterable, Consumer)} method.
	 * 
	 * @param min
	 *            the minimum value (inclusive) to run the body on
	 * @param max
	 *            the maximum value (exclusive) to run the body on
	 * @param body
	 *            the function to run on each value in the range
	 * @see Runtime
	 * @see Runtime#availableProcessors()
	 * @see Runtime#getRuntime()
	 */
	public default void forAllChunked(int min, int max, IntConsumer body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Closes this framework.
	 * 
	 * @see AutoCloseable
	 */
	@Override
	public void close();

}
