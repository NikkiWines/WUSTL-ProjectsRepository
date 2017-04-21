package hw5.test.part4;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import edu.wustl.cse231s.timing.ImmutableTimer;
import hw5.framework.Framework;
import hw5.quicksort.FrameworkQuicksorter;
import hw5.test.FrameworkType;

/**
 * A unit test for {@link FrameworkQuicksorter}.
 * 
 * @author Finn Voichick
 */
public final class BFrameworkQuicksortTest {
	@Test
	public void test() throws InterruptedException, ExecutionException {
		int size = 1_000_000;
		int threshold = size / 11;
		Random random = new Random();
		int[] array = random.ints(size, 0, size).toArray();
		int[] sorted = Arrays.copyOf(array, size);
		Arrays.parallelSort(sorted);

		for (FrameworkType type : FrameworkType.values()) {
			ImmutableTimer timer = new ImmutableTimer(
					String.format("parallelQuicksort(int[%,10d]) %s", array.length, type.toString()));
			Framework framework = type.toFramework();
			FrameworkQuicksorter sorter = new FrameworkQuicksorter(framework);
			sorter.parallelQuicksort(array, threshold);
			Assert.assertArrayEquals(sorted, array);
			timer.markAndPrintResults();
			framework.close();
		}
	}
}
// @RunWith(Parameterized.class)
// public final class BFrameworkQuicksortTest {
//
// private final int[] array;
// private final int[] sorted;
// private final int threshold;
// private final FrameworkQuicksorter sorter;
//
// public BFrameworkQuicksortTest(int size, int threshold, FrameworkType type) {
//
// array = random.ints(size, 0, size).toArray();
//
// sorted = Arrays.copyOf(array, size);
// Arrays.parallelSort(sorted);
//
// this.threshold = threshold;
//
// sorter = new FrameworkQuicksorter(type.toFramework());
//
// }
//
// @Parameters(name = "Test {index} | size: {0}, threshold: {1}")
// public static Collection<Object[]> data() {
//
// int processors = Runtime.getRuntime().availableProcessors();
//
// Collection<Object[]> result = new LinkedList<>();
//
// for (int size = 100_000; size < 100_000_000; size *= 10) {
// int randomSize = random.nextInt(size);
// for (int threshold = randomSize / processors; threshold > size / (1000 *
// processors); threshold /= 10) {
// int randomThresh = random.nextInt(threshold);
// for (FrameworkType type : FrameworkType.values())
// result.add(new Object[] { randomSize, randomThresh, type });
// }
// }
//
// return result;
//
// }
//
// @Test
// public void test() throws InterruptedException, ExecutionException {
//
// ImmutableTimer timer = new ImmutableTimer(
// String.format("sequentialQuicksort(int[%,10d] )", array.length));
// sorter.sequentialQuicksort(array);
// Assert.assertArrayEquals("Failed sequential quicksort", sorted, array);
// timer.markAndPrintResults();
//
// String prefix = String.format(" parallelQuicksort(int[%,10d], %7d)",
// array.length, threshold);
// timer = new ImmutableTimer(prefix);
// sorter.parallelQuicksort(array, threshold);
// Assert.assertArrayEquals("Incorrect parallel quicksort", sorted, array);
// timer.markAndPrintResults();
//
// }
//
// private final static Random random = new Random();
//
// }
