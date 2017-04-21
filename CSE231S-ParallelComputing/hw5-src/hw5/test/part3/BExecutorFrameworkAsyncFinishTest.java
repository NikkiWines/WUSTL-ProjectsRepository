package hw5.test.part3;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hw5.framework.Framework;
import hw5.framework.executor.ExecutorFramework;
import hw5.test.FrameworkType;

/**
 * A unit test for {@link ExecutorFramework}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public final class BExecutorFrameworkAsyncFinishTest {

	private final FrameworkType type;
	private final Framework framework;

	public BExecutorFrameworkAsyncFinishTest(FrameworkType type) {
		this.type = type;
		framework = type.toFramework();
	}

	@Parameters(name = "Test {index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays
				.asList(new Object[][] { { FrameworkType.EXECUTOR_CACHED }, { FrameworkType.EXECUTOR_WORK_STEALING } });
	}

	@Test
	public void testFinishIsRun() {

		int[] referenceArray = { 1 };
		int[] testArray = { 0 };

		framework.finish(context -> testArray[0] = 1);

		Assert.assertArrayEquals("Finish body is not run", referenceArray, testArray);

	}

	@Test
	public void testAsyncIsRun() {

		int[] referenceArray = { 1 };
		int[] testArray = { 0 };

		framework.finish(context -> context.async(() -> testArray[0] = 1));

		busyWait(1);
		Assert.assertArrayEquals("Async body is not run", referenceArray, testArray);

	}

	@Test
	public void testMultipleAsyncs() {

		int[] referenceArray = { 1, 2, 3 };
		int[] testArray = { 0, 0, 0 };

		framework.finish(context -> {
			context.async(() -> testArray[0] = 1);
			context.async(() -> testArray[1] = 2);
			context.async(() -> testArray[2] = 3);
		});

		busyWait(1);
		Assert.assertArrayEquals("Not all async bodies are run", referenceArray, testArray);

	}

	@Test
	public void testAsyncIsFinished() {

		int[] referenceArray = { 1 };
		int[] testArray = { 0 };

		framework.finish(context -> context.async(() -> {

			busyWait(1);
			testArray[0] = 1;

		}));

		Assert.assertArrayEquals("Async body is not finished", referenceArray, testArray);

	}

	@Test
	public void testParallelism() {

		List<Integer> referenceList = Arrays.asList(1, 2, 3);
		List<Integer> testList = new CopyOnWriteArrayList<>();

		framework.finish(context -> {

			context.async(() -> {
				busyWait(3);
				testList.add(3);
			});

			context.async(() -> {
				busyWait(2);
				testList.add(2);
			});

			context.async(() -> {
				busyWait(1);
				testList.add(1);
			});

		});

		Assert.assertEquals("Asyncs are not run in parallel", referenceList, testList);

	}

	@Test
	public void testContinuation() {

		List<Integer> referenceList = Arrays.asList(1, 2, 3);
		List<Integer> testList = new CopyOnWriteArrayList<>();

		framework.finish(context -> {

			testList.add(1);

			context.async(() -> {

				busyWait(1);
				testList.add(3);

			});

			testList.add(2);

		});

		Assert.assertEquals("Incorrect continuation behavior", referenceList, testList);

	}

	@Test
	public void testNestedAsync() {

		List<Integer> referenceList = Arrays.asList(1, 2, 3);
		List<Integer> testList = new CopyOnWriteArrayList<>();

		framework.finish(context -> {

			context.async(() -> {

				context.async(() -> {

					busyWait(2);
					testList.add(3);

				});

				busyWait(1);
				testList.add(2);

			});

			testList.add(1);

		});

		Assert.assertEquals("Incorrect behavior for nested asyncs", referenceList, testList);

	}

	@Test
	public void testNestedFinish() {

		List<Integer> referenceList = Arrays.asList(1, 2, 3);

		List<Integer> testList = new CopyOnWriteArrayList<>();
		framework.finish(context -> {

			context.async(() -> {
				busyWait(2);
				testList.add(3);
			});

			framework.finish((nestedContext) -> {
				busyWait(1);
				testList.add(1);
			});

			testList.add(2);

		});

		Assert.assertEquals("Incorrect behavior for nested finishes", referenceList, testList);

	}

	@Test
	public void testDivideAndConquer() {

		if (type.equals(FrameworkType.EXECUTOR_WORK_STEALING))
			return;

		int[] array;
		try (IntStream randomInts = random.ints(100, 0, 100)) {
			array = randomInts.toArray();
		}
		int truthAndBeautySum = Arrays.stream(array).sum();

		int sum = arraySumKernel(array, 0, array.length);
		Assert.assertEquals(truthAndBeautySum, sum);

	}

	private int arraySumKernel(int[] array, int min, int maxExclusive) {
		if (min == maxExclusive - 1) {
			return array[min];
		} else {
			int mid = (min + maxExclusive) / 2;
			int[] subSums = { 0, 0 };
			framework.finish(context -> {
				context.async(() -> {
					subSums[0] = arraySumKernel(array, min, mid);
				});
				subSums[1] = arraySumKernel(array, mid, maxExclusive);
			});

			return subSums[0] + subSums[1];
		}
	}

	static final void busyWait(int seconds) {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000L * seconds)
			ThreadLocalRandom.current().nextDouble();
	}

	@After
	public void tearDown() {
		framework.close();
	}

	private static final Random random = new Random();

}
