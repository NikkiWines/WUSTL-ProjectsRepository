package hw5.test;

import java.util.concurrent.Executors;

import hw5.framework.Framework;
import hw5.framework.executor.ExecutorFramework;
import hw5.framework.thread.ThreadFramework;

/**
 * A type of {@link Framework}. You do not have to alter this class at all; it
 * is used by the tests to create the various frameworks.
 * 
 * @author Finn Voichick
 */
public enum FrameworkType {

	EXECUTOR_CACHED, EXECUTOR_WORK_STEALING, THREAD;

	public Framework toFramework() {

		switch (this) {

		case EXECUTOR_CACHED:
			return new ExecutorFramework(Executors.newCachedThreadPool());

		case EXECUTOR_WORK_STEALING:
			return new ExecutorFramework(Executors.newWorkStealingPool());

		case THREAD:
			return new ThreadFramework();

		default:
			throw new IllegalStateException(String.valueOf(this));

		}

	}

}
