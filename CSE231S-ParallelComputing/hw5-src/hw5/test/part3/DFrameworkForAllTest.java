package hw5.test.part3;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hw5.framework.Framework;
import hw5.test.FrameworkType;

/**
 * A unit test for {@link Framework}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public class DFrameworkForAllTest {

	private final Framework framework;

	public DFrameworkForAllTest(FrameworkType type) {
		framework = type.toFramework();
	}

	@Parameters(name = "Test {index} | framework: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { FrameworkType.EXECUTOR_CACHED },
				{ FrameworkType.EXECUTOR_WORK_STEALING }, { FrameworkType.THREAD } });
	}

	@Test
	public void testForAll() throws ExecutionException {

		Set<Integer> originalSet = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
		Set<Integer> testSet = ConcurrentHashMap.newKeySet();

		framework.forAll(originalSet, i -> {
			BExecutorFrameworkAsyncFinishTest.busyWait(i);
			testSet.add(i * 2);
		});

		Assert.assertEquals("Not all tasks were run", new HashSet<>(Arrays.asList(2, 4, 6, 8, 10)), testSet);

	}

	@Test
	public void testForAllChunked() throws ExecutionException {

		double[] array = new double[10_000_000];

		framework.forAllChunked(0, array.length, i -> array[i] = Math.sin(i));

		Assert.assertEquals("forAll not implemented correctly for first task", 0.0, array[0], 0.0);

		Assert.assertEquals("forAll not implemented correctly for last task", Math.sin(array.length - 1),
				array[array.length - 1], 0.0);

		int randomIndex = random.nextInt(array.length);
		Assert.assertEquals("forAll not implemented correctly", Math.sin(randomIndex), array[randomIndex], 0.0);

	}

	@After
	public void tearDown() {
		framework.close();
	}

	private static final Random random = new Random();

}
