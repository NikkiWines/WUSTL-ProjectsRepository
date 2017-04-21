package hw5.test.part1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import edu.wustl.cse231s.bioinformatics.Nucleobase;
import edu.wustl.cse231s.bioinformatics.io.ChromosomeResource;
import edu.wustl.cse231s.bioinformatics.io.FastaUtils;
import edu.wustl.cse231s.download.DownloadUtils;
import edu.wustl.cse231s.timing.ImmutableTimer;
import hw5.nucleobase.ExecutorNucleobaseCounter;

/**
 * A unit test for {@link ExecutorNucleobaseCounter}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public class DExecutorNucleobaseCountTest {

	private static byte[] chromosome;
	private final Nucleobase nucleobase;
	private final int truthAndBeautyCount;
	private final ExecutorNucleobaseCounter counter;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {

		ChromosomeResource chromosomeResource = ChromosomeResource.X;
		System.out.print("get/download: \"" + chromosomeResource.getUrl() + "\"... ");
		File file = DownloadUtils.getDownloadedFile(chromosomeResource.getUrl(), chromosomeResource.getFileLength());
		System.out.println(" done.");

		System.out.print("reading: " + file.getAbsolutePath() + "... ");
		chromosome = FastaUtils.read(file);
		System.out.println(" done.");

		System.out.println("chromosome length: " + chromosome.length);
		System.out.println();

	}

	public DExecutorNucleobaseCountTest(Nucleobase nucleobase) {
		this.nucleobase = nucleobase;
		counter = new ExecutorNucleobaseCounter();

		System.out.println("nucleobase: " + nucleobase.toChar() + " " + nucleobase);

		truthAndBeautyCount = IntStream.range(0, chromosome.length).map(index -> chromosome[index]).reduce(0,
				(a, b) -> nucleobase.toByte() == b ? a + 1 : a);

	}

	@Parameters(name = "Test {index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { Nucleobase.ADENINE }, { Nucleobase.THYMINE }, { Nucleobase.CYTOSINE },
				{ Nucleobase.GUANINE }, { Nucleobase.URACIL }, { Nucleobase.NONSPECIFIC } });
	}

	@Test
	public void test2WaySplit() throws InterruptedException, ExecutionException, TimeoutException {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		timer.markAndPrintResults(" result:", count);

		timer = new ImmutableTimer("              Parallel2WaySplit(2)");
		count = counter.count2WaySplit(chromosome, nucleobase);
		Assert.assertEquals("Parallel2WaySplit count does NOT match truth and beauty count", truthAndBeautyCount,
				count);

		timer.markAndPrintResults(" result:", count);

	}

	@Test
	public void testNWaySplit() throws InterruptedException, ExecutionException {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		timer.markAndPrintResults(" result:", count);

		int processors = Runtime.getRuntime().availableProcessors();

		for (int numTasks = processors; numTasks <= 100 * processors; numTasks *= 10) {

			timer = new ImmutableTimer(String.format("            ParallelNWaySplit(%3d)", numTasks));
			count = counter.countNWaySplit(chromosome, nucleobase, numTasks);
			Assert.assertEquals(
					String.format("ParallelNWaySplit(%d) count does NOT match truth and beauty count", numTasks),
					truthAndBeautyCount, count);

			timer.markAndPrintResults(" result:", count);

		}

	}

	@Test
	public void testDivideAndConquer() throws InterruptedException, ExecutionException {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		timer.markAndPrintResults(" result:", count);

		int basesPerProcessor = chromosome.length / Runtime.getRuntime().availableProcessors();

		for (int threshold = basesPerProcessor; threshold >= basesPerProcessor / 100; threshold /= 10) {

			timer = new ImmutableTimer(String.format("ParallelDivideAndConquer(%8d)", threshold));
			count = counter.countDivideAndConquer(chromosome, nucleobase, threshold);
			Assert.assertEquals(String
					.format("ParallelDivideAndConquer(%d) count does not match truth and beauty count", threshold),
					truthAndBeautyCount, count);

			timer.markAndPrintResults(" result:", count);

		}

	}

	@After
	public void tearDown() {
		counter.close();
		System.out.println();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Done");
	}

}