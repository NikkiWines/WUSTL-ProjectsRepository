package hw5.test.part2;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.wustl.cse231s.timing.ImmutableTimer;
import hw5.quicksort.ExecutorQuicksorter;
import hw5.quicksort.ThreadQuicksorter;

/**
 * A unit test for {@link ExecutorQuicksorter}.
 * 
 * @author Finn Voichick
 */
public final class CExecutorTest {
	@Test
	public void test() throws InterruptedException, ExecutionException {

		int size = 1_000_000;
		int threshold = size / 11;
		Random random = new Random();
		int[] array = random.ints(size, 0, size).toArray();
		int[] sorted = Arrays.copyOf(array, size);
		Arrays.parallelSort(sorted);

		ImmutableTimer timer = new ImmutableTimer(String.format("parallelQuicksort(int[%,10d])", array.length));
		ExecutorQuicksorter sorter = new ExecutorQuicksorter();
		sorter.parallelQuicksort(array, threshold);
		Assert.assertArrayEquals(sorted, array);
		timer.markAndPrintResults();

	}
}
//@RunWith(Parameterized.class)
//public final class CExecutorTest {
//
//	private final ExecutorQuicksorter sorter;
//	private final int[] array;
//	private final int[] sorted;
//	private final int threshold;
//
//	public CExecutorTest(int size, int threshold) {
//
//		sorter = new ExecutorQuicksorter();
//
//		array = random.ints(size, 0, size).toArray();
//
//		sorted = Arrays.copyOf(array, size);
//		Arrays.parallelSort(sorted);
//
//		this.threshold = threshold;
//
//	}
//
//	@Parameters(name = "Test {index} | size: {0}, threshold: {1}")
//	public static Collection<Object[]> data() {
//
//		int processors = Runtime.getRuntime().availableProcessors();
//
//		Collection<Object[]> result = new LinkedList<>();
//
//		for (int size = 100_000; size < 100_000_000; size *= 10) {
//			int randomSize = random.nextInt(size);
//			for (int threshold = randomSize / processors; threshold > size / (1000 * processors); threshold /= 10)
//				result.add(new Object[] { randomSize, random.nextInt(threshold) });
//		}
//
//		return result;
//
//	}
//
//	@Test
//	public void test() throws InterruptedException, ExecutionException {
//
//		ImmutableTimer timer = new ImmutableTimer(
//				String.format("sequentialQuicksort(int[%,10d]         )", array.length));
//		sorter.sequentialQuicksort(array);
//		Assert.assertArrayEquals(sorted, array);
//		timer.markAndPrintResults();
//
//		String prefix = String.format("  parallelQuicksort(int[%,10d], %7d)", array.length, threshold);
//		timer = new ImmutableTimer(prefix);
//		sorter.parallelQuicksort(array, threshold);
//		Assert.assertArrayEquals(sorted, array);
//		timer.markAndPrintResults();
//
//	}
//
//	private final static Random random = new Random();
//
//}
