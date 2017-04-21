package hw5.test.part3;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hw5.framework.FinishContext;
import hw5.framework.Framework;
import hw5.test.FrameworkType;

/**
 * A unit test for {@link FinishContext}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public class CFrameworkForAsyncTest {

	private final Framework framework;

	public CFrameworkForAsyncTest(FrameworkType type) {
		framework = type.toFramework();
	}

	@Parameters(name = "Test {index} | framework: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { FrameworkType.EXECUTOR_CACHED },
				{ FrameworkType.EXECUTOR_WORK_STEALING }, { FrameworkType.THREAD } });
	}

	@Test
	public void testForAsyncRuns() {

		Set<Integer> originalSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
		Set<Integer> testSet = ConcurrentHashMap.newKeySet();

		framework.finish(context -> {

			context.forAsync(originalSet, i -> {

				BExecutorFrameworkAsyncFinishTest.busyWait(i);
				testSet.add(i * 2);

			});

		});

		Assert.assertEquals("Not all tasks were run", new HashSet<>(Arrays.asList(2, 4, 6, 8, 10)), testSet);

	}

	@Test
	public void testNestedForAsync() {

		Set<Integer> outerSet = new HashSet<>(Arrays.asList(1, 2, 3));
		Set<Double> innerSet = new HashSet<>(Arrays.asList(2.0, 3.0, 4.0));
		List<Double> testList = new CopyOnWriteArrayList<>();

		framework.finish(context -> {

			context.forAsync(outerSet, i -> {
				context.forAsync(innerSet, d -> {

					double product = i * d;
					BExecutorFrameworkAsyncFinishTest.busyWait(random.nextInt(5));
					testList.add(product);

				});
			});
		});

		Assert.assertEquals("Incorrect number of tasks for nested forAsync", 9, testList.size());
		Set<Double> expected = new HashSet<>(Arrays.asList(2.0, 3.0, 4.0, 6.0, 8.0, 9.0, 12.0));
		Assert.assertEquals("Problem with nested forAsync", expected, new HashSet<>(testList));

	}

	@After
	public void tearDown() {
		framework.close();
	}

	private static final Random random = new Random();

}
