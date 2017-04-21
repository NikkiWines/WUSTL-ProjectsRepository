package hw5.test.part1;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
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
import hw5.nucleobase.NucleobaseCounter;

/**
 * A unit test for {@link NucleobaseCounter}.
 * 
 * @author Finn Voichick
 *
 */
@RunWith(Parameterized.class)
public class ASequentialNucleobaseCountTest {

	private static byte[] chromosome;
	private final Nucleobase nucleobase;
	private final int truthAndBeautyCount;
	private final NucleobaseCounter counter;

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

	public ASequentialNucleobaseCountTest(Nucleobase nucleobase) {
		this.nucleobase = nucleobase;
		counter = new NucleobaseCounter();

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
	public void testSequential() {

		ImmutableTimer timer = new ImmutableTimer("                     Sequential(1)");
		int count = counter.countSequential(chromosome, nucleobase);
		Assert.assertEquals("Sequential count does NOT match truth and beauty count", truthAndBeautyCount, count);

		timer.markAndPrintResults(" result:", count);
	}

	@After
	public void tearDown() {
		System.out.println();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Done");
	}

}
