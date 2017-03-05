package student.studio.accumulator;

import static edu.wustl.cse231s.rice.classic.HabaneroClassic.async;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.finish;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.launchHabaneroApp;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.newIntegerFinishAccumulator;
import static edu.wustl.cse231s.rice.classic.HabaneroClassic.register;

import java.io.File;

import edu.rice.hj.api.SuspendableException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import edu.wustl.cse231s.bioinformatics.io.ChromosomeResource;
import edu.wustl.cse231s.bioinformatics.io.FastaUtils;
import edu.wustl.cse231s.download.DownloadUtils;
import edu.wustl.cse231s.rice.classic.contrib.api.FinishAccumulator;
import edu.wustl.cse231s.rice.classic.contrib.api.NumberReductionOperator;
import edu.wustl.cse231s.timing.ImmutableTimer;

public class NucleobaseAccumulating {
	private static final int NOT_READY_TO_TEST_VALUE = -1;

	/**
	 * This method should sequentially count all of the instances of a specific
	 * nucleobase. No parallelization needed; nothing needs to run
	 * asynchronously. Without using async or finish, just count the total
	 * number of bases in the sequence that are equal to the given base.
	 * 
	 * @param chromosome
	 *            The chromosome to examine, represented as an array of bytes.
	 *            Each byte in the sequence represents one nucleobase--that is,
	 *            cytosine (C), guanine (G), adenine (A), thymine (T), or
	 *            unknown (N). A byte is used (rather than char, int, String, or
	 *            enum, for example) because a byte is a primitive data type
	 *            that takes up very little memory.
	 * @param nucleobase
	 *            The nucleobase to look for in the chromosome. The byte value
	 *            representing this nucleobase occurs some number of times in
	 *            the chromosome. In other words, if you call
	 *            nucleobase.toByte(), it will be equal to of the bytes in the
	 *            chromosome array.
	 * @return The total number of times that the given nucleobase occurs in the
	 *         given chromosome, or -1 if it hasn't been implemented yet.
	 */
	private static int countSequential(byte[] chromosome, Nucleobase nucleobase) {
		int count = 0;
		for (byte b : chromosome) {
			if (nucleobase.toByte() == b) {
				count++;
			}
		}
		return count;
	}

	/**
	 * This method should asynchronously count all of the instances of a
	 * specific nucleobase, creating two tasks. The chromosome should be split
	 * into two halves, and the "upper" half should be counted at the same time
	 * (asynchronously) as the "lower" half. You will need async and finish for
	 * this method.
	 * 
	 * @param chromosome
	 *            The chromosome to examine, represented as an array of bytes.
	 * @param nucleobase
	 *            The nucleobase to look for in the chromosome.
	 * @return The total number of times that the given nucleobase occurs in the
	 *         given chromosome, or -1 if it hasn't been implemented yet.
	 */
	private static int countParallelUpperLowerSplit(byte[] chromosome, Nucleobase nucleobase)
		throws SuspendableException {
		FinishAccumulator<Integer> acc= newIntegerFinishAccumulator(NumberReductionOperator.SUM);
		
			final int[] sumArray = new int[2];
			
			finish(register(acc), () -> {
				// lower half 
				async(()-> {
					for (int i=0; i < chromosome.length/2; i++) { 
						if (chromosome[i] == nucleobase.toByte()) { 
							sumArray[0] ++;
							acc.put(sumArray[0]);
						}
					}
				}); // end async
				//upper half 
				for (int i = chromosome.length/2; i < chromosome.length; i++ ) { 
					if (chromosome[i] == nucleobase.toByte()) { 
						sumArray[1] ++;
						acc.put(sumArray[1]);
					}
				}
			}); //end finish
			final int sum  = sumArray[0] + sumArray[1]; // sum separate arrays
			if (sum == 0) { //catch all
				return -1;
			}
			return acc.get();
		//return NOT_READY_TO_TEST_VALUE;
	}

	private static final void testSequential(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount) {
		ImmutableTimer timer = new ImmutableTimer("    Sequential(1) Iteration #" + iteration);
		int count = countSequential(chromosome, nucleobase);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults(" result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("Sequential(1) count does NOT match truth and beauty count: " + count + " "
						+ truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE:     Sequential(1) count NOT checked: " + count + " " + truthAndBeautyCount);
		}
	}

	private static final void testParallelUpperLowerSplit(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount) throws SuspendableException {
		ImmutableTimer timer = new ImmutableTimer(" ParallelUpLow(2) Iteration #" + iteration);
		int count = countParallelUpperLowerSplit(chromosome, nucleobase);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults(" result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("ParallelUpLow(2) count does NOT match truth and beauty count: " + count
						+ " " + truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE:  ParallelUpLow(2) count NOT checked: " + count + " " + truthAndBeautyCount);
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
			final int nIterations = 10;
			for (int iteration = 0; iteration < nIterations; iteration++) {
				System.out.println();
				testSequential(iteration, chromosome, nucleobase, truthAndBeautyCount);
				testParallelUpperLowerSplit(iteration, chromosome, nucleobase, truthAndBeautyCount);
			}
			System.out.println();
			System.out.println("Done: " + nIterations + " iterations");
		});
	}
}
