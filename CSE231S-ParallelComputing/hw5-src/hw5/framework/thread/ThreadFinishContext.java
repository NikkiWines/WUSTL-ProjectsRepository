package hw5.framework.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.framework.FinishContext;

/**
 * A context used by a finish's body to run things asynchronously. This class is
 * used by {@link ThreadFramework} to manage the async tasks within a finish
 * block. Think of this as being like the area within the finish.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
final class ThreadFinishContext implements FinishContext {

	/** A collection of threads running (or done) within this context. */
	private final Queue<Thread> threads;

	/**
	 * Should create a ThreadFinishContext. You will need to initialize the
	 * collection of threads.
	 * 
	 * @see ConcurrentLinkedQueue
	 */
	ThreadFinishContext() {
		threads = new ConcurrentLinkedQueue<Thread>(); 
	}

	/**
	 * Should run a task asynchronously. You'll want to run the task on a new
	 * {@link Thread}.
	 * 
	 * @param body
	 *            the body of this async
	 * @see Queue
	 * @see Thread
	 */
	@Override
	public void async(Runnable body) {
	 Thread thread = new Thread(body);
	 thread.start();
	 threads.add(thread);
	}

	/**
	 * Should join all of the threads running in this FinishContext. This method
	 * will be called by {@link ThreadFramework#finish(Consumer)}.
	 * 
	 * @throws InterruptedException
	 *             if the current thread was interrupted while waiting
	 * @see Queue
	 * @see Thread
	 */
	void joinAll() throws InterruptedException {
		Thread t = new Thread();
		while ((t = threads.poll()) != null ) { // while the head item of threads is not null
			t.join();
		}
	}

}
