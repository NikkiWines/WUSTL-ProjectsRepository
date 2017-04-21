package hw5.framework.thread;

import java.util.Collection;
import java.util.function.Consumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.test.part3.AThreadFrameworkAsyncFinishTest;

/**
 * A thread-based framework for calling finish and async.
 * <p>
 * This can be confusing, especially with the {@link Consumer}. To see how this
 * is used, take a look at
 * {@link AThreadFrameworkAsyncFinishTest#testMultipleAsyncs()}. See what
 * methods the {@link Framework} and {@link FinishContext} have. Remember that a
 * Consumer is exactly like a {@link Runnable}, except that its method takes a
 * single parameter.
 * <p>
 * You can test this class with {@link AThreadFrameworkAsyncFinishTest}.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
public final class ThreadFramework implements Framework {

	/**
	 * Constructs a ThreadFramework.
	 */
	public ThreadFramework() {
	}

	/**
	 * Should run the given body and wait for all asyncs to finish. This can be
	 * accomplished in three steps:
	 * <ol>
	 * <li>Create a context</li>
	 * <li>Run the body with the given context.</li>
	 * <li>Join all of the threads</li>
	 * </ol>
	 * The body is a Consumer. Think of it like a function (a lambda) that uses
	 * a FinishContext. It will likely use this FinishContext to spawn
	 * asynchronous tasks. However, at this point, we don't care what it does;
	 * it just needs to be run.
	 * 
	 * @param body
	 *            the body of this finish
	 * @see ThreadFinishContext
	 * @see ThreadFinishContext#joinAll()
	 */
	@Override
	public void finish(Consumer<FinishContext> body) {
		try {
			ThreadFinishContext context = new ThreadFinishContext();
			body.accept(context);
			context.joinAll();
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Closes this framework.
	 */
	@Override
	public void close() {
	}

}
