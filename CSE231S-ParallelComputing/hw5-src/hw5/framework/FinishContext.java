package hw5.framework;

import java.util.Queue;
import java.util.function.Consumer;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.rice.classic.HabaneroClassic;
import hw5.test.part3.CFrameworkForAsyncTest;

/**
 * A context used by a finish's body to run things asynchronously. This class is
 * used by {@link Framework} to manage the async tasks within a finish block.
 * Think of this as being like the area within the finish.
 * <p>
 * Note that this is an interface, not a class. Because of this, other classes
 * (namely, ThreadFramework.ThreadFinishContext and
 * ExecutorFramework.ExecutorFinishContext) can implement this interface. It
 * also means that those classes will have access to the default forAsync method
 * that you write here.
 * <p>
 * You should not modify this file until you get to Part 3C. When you get there,
 * you can test it with {@link CFrameworkForAsyncTest}.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
public interface FinishContext {

	/**
	 * Should run a task asynchronously. You'll want to run the task on a new
	 * {@link Thread}.
	 * 
	 * @param body
	 *            the body of this async
	 * @see Queue
	 * @see Thread
	 */
	public void async(Runnable body);

	/**
	 * Should spawn an asynchronous task for each element in iterable. Each of
	 * these tasks should run the body, passing it the element. In other words,
	 * you're iterating through the iterable and running the body on each
	 * element, but each iteration must run in parallel.
	 * 
	 * @param <E>
	 *            the type of element used with each iteration
	 * @param iterable
	 *            the iterable with the values to go through
	 * @param body
	 *            the function to run on each iteration
	 */
	public default <E> void forAsync(Iterable<E> iterable, Consumer<E> body) {
		this.async(() -> {
			for (E e: iterable) { 
				body.accept(e);
			}
		});


	}

}
