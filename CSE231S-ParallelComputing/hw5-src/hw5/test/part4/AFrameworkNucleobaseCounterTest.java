package hw5.test.part4;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
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
import hw5.framework.Framework;
import hw5.nucleobase.FrameworkNucleobaseCounter;
import hw5.test.FrameworkType;

/**
 * A unit test for {@link FrameworkNucleobaseCounter}.
 * 
 * @author Finn Voichick
 */
@RunWith(Parameterized.class)
public class AFrameworkNucleobaseCounterTest {

	private final FrameworkType type;
	private final Framework framework;
	private static byte[] chromosome;
	private final Nucleobase nucleobase;
	private final int truthAndBeautyCount;
	private final FrameworkNucleobaseCounter counter;

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

	public AFrameworkNucleobaseCounterTest(FrameworkType type, Nucleobase nucleobase) {

		this.type = type;
		framework = type.toFramework();
		this.nucleobase = nucleobase;
		counter = new FrameworkNucleobaseCounter(framework);

		truthAndBeautyCount = IntStream.range(0, chromosome.length).map(index -> chromosome[index]).reduce(0,
				(a, b) -> nucleobase.toByte() == b ? a + 1 : a);

	}

	@Parameters(name = "Test {index} | framework: {0}, base: {1}")
	public static Collection<Object[]> data() {
		List<Object[]> result = new LinkedList<>();

		for (Nucleobase nucleobase : Nucleobase.values()) {
			for (FrameworkType type : FrameworkType.values())
				result.add(new Object[] { type, nucleobase });
		}
		return result;
	}

	@Before
	public void setUp() {
		char nucleobaseChar = nucleobase.toChar();
		System.out.println(String.format("Framework: %s, nucleobase: %c %s", type, nucleobaseChar, nucleobase));
	}

	@Test
	public void test2WaySplit() {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		timer.markAndPrintResults(" result:", count);

		timer = new ImmutableTimer("              Parallel2WaySplit(2)");
		count = counter.count2WaySplit(chromosome, nucleobase);
		Assert.assertEquals("Parallel2WaySplit(2) count does NOT match truth and beauty count", truthAndBeautyCount,
				count);

		timer.markAndPrintResults(" result:", count);

	}

	@Test
	public void testChunked() {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		timer.markAndPrintResults(" result:", count);

		timer = new ImmutableTimer("                   ParallelChunked");
		count = counter.countChunked(chromosome, nucleobase);
		Assert.assertEquals(String.format("ParallelChunked count does NOT match truth and beauty count"),
				truthAndBeautyCount, count);

		timer.markAndPrintResults(" result:", count);

	}

	@Test
	public void testDivideAndConquer() {

		if (type.equals(FrameworkType.EXECUTOR_WORK_STEALING))
			return;

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
		framework.close();
		System.out.println();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Done");
	}

}
