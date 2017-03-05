package student.studio.count;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.numThreads;

import java.io.File;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import edu.wustl.cse231s.bioinformatics.io.ChromosomeResource;
import edu.wustl.cse231s.bioinformatics.io.FastaUtils;
import edu.wustl.cse231s.download.DownloadUtils;
import edu.wustl.cse231s.timing.ImmutableTimer;

/**
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 */
public class DivideAndConquerCount {
	private static final int NOT_READY_TO_TEST_VALUE = -1;

	private static int countSequentialKernel(byte[] chromosome, Nucleobase nucleobase, int min, int max) {
		int count = 0;
		for (int i=min; i<max; i++) {
			if (nucleobase.toByte() == chromosome[i]) {
				count++;
			}
		}
		return count;
	}
	public static int countSequential(byte[] chromosome, Nucleobase nucleobase) {
		return countSequentialKernel(chromosome, nucleobase, 0, chromosome.length);
	}

	private static int countParallelDivideAndConquerKernel(byte[] chromosome, Nucleobase nucleobase, int min, int max, int threshold) throws SuspendableException {
		final int mid = (max- min)/2;
		final int[] sum = new int[2];
		if (min*max > 1) { 
			if (min*max > threshold) { 
				finish(() -> {
					async(() -> {
						sum[0] =countParallelDivideAndConquerKernel(chromosome, nucleobase, min, mid, threshold);

					});
					sum[1] =countParallelDivideAndConquerKernel(chromosome, nucleobase, mid, max, threshold);

				});
			}
		}
		int summed = sum[0] + sum[1];
		if (summed == 0) { //catch all
			return -1;
		}
		return summed;
	}

	public static int countParallelDivideAndConquer(byte[] chromosome, Nucleobase nucleobase, int threshold) throws SuspendableException {
		return countParallelDivideAndConquerKernel( chromosome, nucleobase, 0, chromosome.length, threshold );
	}

	private static final void testSequential(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount) {
		ImmutableTimer timer = new ImmutableTimer("     Sequential(" + chromosome.length + ") Iteration #" + iteration);
		int count = countSequential(chromosome, nucleobase);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults(" result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("Sequential count does NOT match truth and beauty count: " + count + " "
						+ truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE:     Sequential count NOT checked: " + count + " " + truthAndBeautyCount);
		}
	}

	private static final void testParallelDivideAndConquer(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount, int threshold) throws SuspendableException {
		ImmutableTimer timer = new ImmutableTimer(
				"DivideAndConquer(" + String.format("%8d", threshold) + ") Iteration #" + iteration);
		int count = countParallelDivideAndConquer(chromosome, nucleobase, threshold);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults(" result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("DivideAndConquer(" + String.format("%8d", threshold)
				+ ") count does NOT match truth and beauty count: " + count + " " + truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE: DivideAndConquer(" + String.format("%8d", threshold) + ") count NOT checked: " + count
					+ " " + truthAndBeautyCount);
		}
	}

	public static void main(String[] args) throws Exception {
		ChromosomeResource chromosomeResource = ChromosomeResource.X;
		System.out.print("get/download: \"" + chromosomeResource.getUrl() + "\"... ");
		File file = DownloadUtils.getDownloadedFile(chromosomeResource.getUrl(), chromosomeResource.getFileLength());
		System.out.println(" done.");

		System.out.print("reading: " + file.getAbsolutePath() + "... ");
		byte[] chromosome = FastaUtils.read(file);
		System.out.println(" done.");

		Nucleobase nucleobase = Nucleobase.NONSPECIFIC;

		System.out.println("chromosome length: " + chromosome.length);
		System.out.println("nucleobase: " + nucleobase.toChar() + " " + nucleobase);

		int truthAndBeautyCount = countSequential(chromosome, nucleobase);

		System.out.println("truthAndBeautyCount: " + truthAndBeautyCount);
		launchHabaneroApp(() -> {
			int numThreads = numThreads();
			int basesPerThread = chromosome.length/numThreads;

			final int nIterations = 10;
			for (int iteration = 0; iteration < nIterations; iteration++) {
				System.out.println();
				testSequential(iteration, chromosome, nucleobase, truthAndBeautyCount);
				testParallelDivideAndConquer(iteration, chromosome, nucleobase, truthAndBeautyCount, basesPerThread);
				testParallelDivideAndConquer(iteration, chromosome, nucleobase, truthAndBeautyCount, basesPerThread/2);
				testParallelDivideAndConquer(iteration, chromosome, nucleobase, truthAndBeautyCount, basesPerThread/10);
				testParallelDivideAndConquer(iteration, chromosome, nucleobase, truthAndBeautyCount, basesPerThread/100);
				testParallelDivideAndConquer(iteration, chromosome, nucleobase, truthAndBeautyCount, basesPerThread/1000);
			}
			System.out.println();
			System.out.println("Done: " + nIterations + " iterations");
		});
	}
}
