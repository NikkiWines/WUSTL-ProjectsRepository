package hw5.framework.executor;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.test.part3.BExecutorFrameworkAsyncFinishTest;

/**
 * A framework for calling finish and async that is based on Java's Executors.
 * You can test this class with {@link BExecutorFrameworkAsyncFinishTest}.
 * 
 * @author Finn Voichick
 */
public final class ExecutorFramework implements Framework {

	/** The ExecutorService to which tasks are submitted. */
	private final ExecutorService executor;

	/**
	 * Should create an ExecutorFramework. You will need to initialize the
	 * executor.
	 * 
	 * @param executor
	 *            the executor that will run all of this framework's tasks
	 */
	public ExecutorFramework(ExecutorService executor) {
		this.executor = executor;
	}

	/**
	 * Should run the given body and wait for all asyncs to finish. This can be
	 * accomplished in the same three steps as the ThreadFramework.
	 * 
	 * @param body
	 *            the body of this finish
	 * @see ExecutorFinishContext
	 * @see ExecutorFinishContext#getAll()
	 */
	@Override
	public void finish(Consumer<FinishContext> body) {
		try {
			ExecutorFinishContext context = new ExecutorFinishContext(executor);
			body.accept(context);
			context.getAll();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Should shut down the executor.
	 */
	@Override
	public void close() {
		executor.shutdown();

	}

}
