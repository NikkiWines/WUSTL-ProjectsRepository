package hw5.jalapeno;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.test.part5.AJalapenoTest;

/**
 * A Habanero-like parallel framework, where everything is a static method. In
 * many ways, this class acts like a wrapper for {@link Framework}, but the
 * difficulty is in how to organize the {@link FinishContext}s. Both the finish
 * and async methods here take a Runnable as a parameter, meaning that spawned
 * asynchronous tasks don't necessarily know which context to use.
 * <p>
 * This problem is solved by using a thread-local stack of FinishContexts. Every
 * time you call the finish method, a FinishContext is pushed onto the stack,
 * and once all of the tasks associated with that FinishContext have completed,
 * the FinishContext is popped from the stack. The async method then has to peek
 * at the FinishContext at the top of the stack.
 * <p>
 * The stack has to be thread-local, meaning that each thread has its own stack.
 * This is what allows you to have multiple finishes running in parallel, and it
 * provides a threadsafe way to find the relevant FinishContext.
 * <p>
 * Note that this "stack" is not an instance of Java's {@link Stack} class; it
 * uses Java's {@link Deque} interface.
 * <p>
 * You can test this class with {@link AJalapenoTest}.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
public class Jalapeno {

	/** The wrapped framework providing the parallel constructs. */
	private static Framework framework;
	/** The thread-local stack used to keep track of the FinishContexts. */
	private static final ThreadLocal<Deque<FinishContext>> contextStack = ThreadLocal.withInitial(() -> {
		return new LinkedList<>();
	});

	private Jalapeno() {
		throw new AssertionError("This class cannot be instantiated");
	}

	/**
	 * Should launch Jalapeno with the given framework. This method does not
	 * have to be thread-safe; that is, it is okay if it fails when run in
	 * parallel with itself, because it can be assumed that only one Jalapeno
	 * will be launched at any given time. Note that any asynchronous tasks
	 * spawned within the body should be finished before this method returns.
	 * 
	 * @param framework
	 *            the framework providing the finish and async implementations
	 * @param body
	 *            the Runnable to run, which will likely contain calls to finish
	 *            and async
	 * @see #finish(Runnable)
	 */
	public static void launch(Framework framework, Runnable body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should execute the body, waiting for all asynchronous tasks spawned in
	 * the body to complete before returning. This should call the framework's
	 * finish method, pushing the FinishContext onto the stack, and popping it
	 * once finished.
	 * 
	 * @param body
	 *            the body of this finish statement, which likely contains calls
	 *            to {@link #async(Runnable)}
	 * @see Deque
	 * @see ThreadLocal
	 * @see Deque#pop()
	 * @see Deque#push(Object)
	 * @see ThreadLocal#get()
	 */
	public static void finish(Runnable body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should execute the body asynchronously. This should get the immediately
	 * enclosing finish from the stack and call the
	 * {@link FinishContext#async(Runnable)} method.
	 * <p>
	 * Note that for nested asyncs to work, you will need to push the
	 * FinishContext onto the thread-local stack of the newly-spawned task.
	 * 
	 * @param body
	 *            the body of this async statement, which should be run
	 *            asynchronously
	 * @see Deque
	 * @see ThreadLocal
	 * @see Deque#peek()
	 */
	public static void async(Runnable body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should spawn an asynchronous task for each element in iterable. You can
	 * (and should) use your {@link FinishContext#forAsync(Iterable, Consumer)}
	 * method here.
	 * 
	 * @param <E>
	 *            the type of element used with each iteration
	 * @param iterable
	 *            the iterable with the values to go through
	 * @param body
	 *            the function to run on each iteration
	 * @see Consumer
	 * @see Deque
	 */
	public <E> void forAsync(Iterable<E> iterable, Consumer<E> body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should spawn an asyncronous task for each element in iterable and wait
	 * for all of them to finish. You can (and should) use your
	 * {@link Framework#forAll(Iterable, Consumer)} method here.
	 * 
	 * @param <E>
	 *            the type of element used with each iteration
	 * @param iterable
	 *            the iterable with the values to go through
	 * @param body
	 *            the function to run on each iteration
	 */
	public <E> void forAll(Iterable<E> iterable, Consumer<E> body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

	/**
	 * Should chunk the given range and run the body on each value. You can (and
	 * should) use your {@link Framework#forAllChunked(int, int, IntConsumer)}
	 * method here.
	 * 
	 * @param min
	 *            the minimum value (inclusive) to run the body on
	 * @param max
	 *            the maximum value (exclusive) to run the body on
	 * @param body
	 *            the function to run on each value in the range
	 */
	public void forAllChunked(int min, int max, IntConsumer body) {

		// TODO implement
		throw new NotYetImplementedException();

	}

}
