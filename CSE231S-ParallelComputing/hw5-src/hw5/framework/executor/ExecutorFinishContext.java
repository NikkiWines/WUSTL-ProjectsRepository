package hw5.framework.executor;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;

/**
 * A context used by a finish's body to run tasks asynchronously. This class is
 * used by {@link ExecutorFramework} to manage the async tasks within a finish
 * block. Think of this as being like the area within the finish.
 * 
 * @author Finn Voichick
 */
final class ExecutorFinishContext implements FinishContext {

	/** The ExecutorService to which tasks are submitted. */
	private final ExecutorService executor;
	/** A collection of tasks running (or done) within this context. */
	private final Queue<Future<?>> tasks;

	/**
	 * Should create an ExecutorFinishContext. You will need to initialize the
	 * collection of tasks. Note that tasks is really a collection of futures,
	 * because that's how we access the running tasks.
	 * 
	 * @param executor
	 *            the executor used by this context to schedule tasks
	 * @see ConcurrentLinkedQueue
	 */
	ExecutorFinishContext(ExecutorService executor) {
		tasks = new ConcurrentLinkedQueue<Future<?>>();
		this.executor = executor;

	}

	/**
	 * Should run a task asynchronously by submitting it to an ExecutorService.
	 * 
	 * @param body
	 *            the body of this async
	 * @see ExecutorService
	 * @see Queue
	 */
	@Override
	public void async(Runnable body) {
		 tasks.add(executor.submit(body));
	}

	/**
	 * Should join all of the threads running in this FinishContext. This method
	 * will be called by {@link ExecutorFramework#finish(Consumer)}.
	 * 
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @throws ExecutionException
	 *             if the computation threw an exception
	 * @see Future
	 * @see Queue
	 */
	void getAll() throws InterruptedException, ExecutionException {
		Future<?> f;
		while ((f = tasks.poll()) != null ) { // while the head item of threads is not null
			f.get();
		}
	}

}
