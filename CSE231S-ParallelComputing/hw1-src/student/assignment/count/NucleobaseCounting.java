package student.assignment.count;

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

public class NucleobaseCounting {
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
		int sum = 0; 
		for (int i=0; i < chromosome.length; i++ ) {
			if (chromosome[i] == nucleobase.toByte()) {  // sum if same
				sum++;
			}
		}
		if (sum == 0) { // catch all
			return -1;
		} 
		return sum;
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
		final int[] sumArray = new int[2];
		finish(() -> {
			// lower half 
			async(()-> {
				for (int i=0; i < chromosome.length/2; i++) { 
					if (chromosome[i] == nucleobase.toByte()) { 
						sumArray[0]++; 
					}
				}
			}); // end async
			//upper half 
			for (int i = chromosome.length/2; i < chromosome.length; i++ ) { 
				if (chromosome[i] == nucleobase.toByte()) { 
					sumArray[1]++;
				}
			}
		}); //end finish
		final int sum  = sumArray[0] + sumArray[1]; // sum separate arrays
		if (sum == 0) { //catch all
			return -1;
		}
		return sum;
	}

	/**
	 * This method should asynchronously count all of the instances of a
	 * specific nucleobase, creating the given number of tasks. In other words,
	 * you should spawn n tasks, each of which counts for 1/n of the chromosome.
	 * For example, if numTasks is 8, the chromosome should be divided into 8
	 * pieces, and each of these pieces should be counted in a separate
	 * asynchronous task. You should enclose each of these tasks in an async
	 * block, so that each task can run in parallel. Note: if numTasks is 2, the
	 * behavior of this method will be the same as countParallelUpperLowerSplit.
	 * 
	 * @param chromosome
	 *            The chromosome to examine, represented as an array of bytes.
	 * @param nucleobase
	 *            The nucleobase to look for in the chromosome.
	 * @param numTasks
	 *            The number of tasks to create.
	 * @return The total number of times that the given nucleobase occurs in the
	 *         given chromosome, or -1 if it hasn't been implemented yet..
	 */
	private static int countParallelNWaySplit(byte[] chromosome, Nucleobase nucleobase, int numTasks)
			throws SuspendableException {			
		int sum = 0;
		final int[] sumArray = new int[numTasks];
		final int increment = chromosome.length/ numTasks + 1;
		finish(() -> {
			for (int i = 0; i < numTasks; i ++) {
				final int accessible_i = i;
				async(() -> {
					int start = 0 + accessible_i * increment;
					int end = start + increment;
					
					if (end > chromosome.length) {
						end = chromosome.length;
					}
					for (int j = start; j < end; j ++) {
						if (chromosome[j] == nucleobase.toByte()) {
							sumArray[accessible_i] ++;
						}
					}
				});
			}
		});

		for (int i=0; i < sumArray.length; i++) { 
			sum = sum + sumArray[i]; // sum array
		}
		if (sum == 0) { //catch all
			return -1;
		}
		return sum;
	}

	private static final void testSequential(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount) {
		ImmutableTimer timer = new ImmutableTimer("    Sequential(1) Iteration #" + iteration + ": time = ");
		int count = countSequential(chromosome, nucleobase);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults("nsec;  result:", count);
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
		ImmutableTimer timer = new ImmutableTimer(" ParallelUpLow(2) Iteration #" + iteration + ": time = ");
		int count = countParallelUpperLowerSplit(chromosome, nucleobase);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults("nsec;  result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("ParallelUpLow(2) count does NOT match truth and beauty count: " + count
						+ " " + truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE:  ParallelUpLow(2) count NOT checked: " + count + " " + truthAndBeautyCount);
		}
	}

	private static final void testParallelNWaySplit(int iteration, byte[] chromosome, Nucleobase nucleobase,
			int truthAndBeautyCount, int numTasks) throws SuspendableException {
		ImmutableTimer timer = new ImmutableTimer(
				"ParallelNWay(" + String.format("%3d", numTasks) + ") Iteration #" + iteration + ": time = ");
		int count = countParallelNWaySplit(chromosome, nucleobase, numTasks);
		if (count != NOT_READY_TO_TEST_VALUE) {
			timer.markAndPrintResults("nsec;  result:", count);
			if (count != truthAndBeautyCount) {
				throw new RuntimeException("ParallelNWay(" + String.format("%3d", numTasks)
				+ ") count does NOT match truth and beauty count: " + count + " " + truthAndBeautyCount);
			}
		} else {
			System.out.println("NOTE: ParallelNWay(" + String.format("%3d", numTasks) + ") count NOT checked: " + count
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

			final int nIterations = 10;
			for (int iteration = 0; iteration < nIterations; iteration++) {
				System.out.println();
				testSequential(nIterations, chromosome, nucleobase, truthAndBeautyCount);
				testParallelUpperLowerSplit(nIterations, chromosome, nucleobase, truthAndBeautyCount);
				testParallelNWaySplit(nIterations, chromosome, nucleobase, truthAndBeautyCount, numThreads);
				testParallelNWaySplit(nIterations, chromosome, nucleobase, truthAndBeautyCount, numThreads * 2);
				testParallelNWaySplit(nIterations, chromosome, nucleobase, truthAndBeautyCount, numThreads * 10);
				testParallelNWaySplit(nIterations, chromosome, nucleobase, truthAndBeautyCount, numThreads * 100);
			}
			System.out.println();
			System.out.println("Done: " + nIterations + " iterations");
		});
	}
}
