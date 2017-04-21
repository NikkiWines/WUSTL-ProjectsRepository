package hw5.test.part2;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.wustl.cse231s.timing.ImmutableTimer;
import hw5.quicksort.Quicksorter;

/**
 * A unit test for {@link Quicksorter}
 * 
 * @author Finn Voichick
 */
public class ASequentialQuicksortTest {
	@Test
	public void test() {
		int size = 1_000_000;
		Random random = new Random();
		int[] array = random.ints(size, 0, size).toArray();
		int[] sorted = Arrays.copyOf(array, size);
		Arrays.parallelSort(sorted);

		ImmutableTimer timer = new ImmutableTimer(String.format("sequentialQuicksort(int[%,10d])", array.length));
		Quicksorter sorter = new Quicksorter();
		sorter.sequentialQuicksort(array);
			Assert.assertArrayEquals(sorted, array);
		timer.markAndPrintResults();

	}
}	

//@RunWith(Parameterized.class)
//public class ASequentialQuicksortTest {
//	private final Quicksorter sorter;
//	private final int[] array;
//	private final int[] sorted;
//
//	public ASequentialQuicksortTest(int size) {
//
//		sorter = new Quicksorter();
//
//		array = random.ints(size, 0, size).toArray();
//
//		sorted = Arrays.copyOf(array, size);
//		Arrays.parallelSort(sorted);
//
//	}
//
//	@Parameters(name = "Test {index} | size: {0}")
//	public static Collection<Object[]> data() {
//
//		Collection<Object[]> result = new LinkedList<>();
//
//		for (int size = 100_000; size < 100_000_000; size *= 10)
//			result.add(new Object[] { random.nextInt(size) });
//
//		return result;
//
//	}
//
//	@Test
//	public void test() {
//
//		ImmutableTimer timer = new ImmutableTimer(String.format("sequentialQuicksort(int[%,10d])", array.length));
//		sorter.sequentialQuicksort(array);
//		Assert.assertArrayEquals(sorted, array);
//		timer.markAndPrintResults();
//
//	}
//
//	private static final Random random = new Random();
//
//}
