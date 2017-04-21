package hw5.test.part1;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import hw5.slice.Slice;

/**
 * A unit test for {@link Slice}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public class BSliceTest {

	private final int min;
	private final int max;
	private final int numSlices;

	public BSliceTest(int min, int max, int slices) {
		this.min = min;
		this.max = max;
		this.numSlices = slices;
	}

	@Parameters(name = "Test {index} | range: {0} - {1}, slices: {2}")
	public static Collection<Object[]> data() {

		Collection<Object[]> result = new LinkedList<>();

		int min = 1;
		for (int i = 0; i < 3; i++)
			result.add(new Object[] { min *= random.nextInt(1000), min * (random.nextInt(3) + 2),
					random.nextInt(20) + 10 });

		return result;

	}

	@Test
	public void test() {

		List<Slice> slices = Slice.createSlices(numSlices, min, max);

		long expectedSum = LongStream.range(min, max).sum();

		long sum = 0;
		for (Slice slice : slices)
			for (int i = slice.getMin(); i < slice.getMax(); i++)
				sum += i;    

		Assert.assertEquals("Incorrect slices", expectedSum, sum);

		long[] forEachSum = { 0 };
		for (Slice slice : slices)
			slice.forEach(i -> forEachSum[0] += i);

		Assert.assertEquals("Incorrect forEach behavior", expectedSum, forEachSum[0]);

	}

	private static final Random random = new Random();

}
