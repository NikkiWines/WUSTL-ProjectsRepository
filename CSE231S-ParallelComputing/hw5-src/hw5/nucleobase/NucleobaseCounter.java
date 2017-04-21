package hw5.nucleobase;

import edu.wustl.cse231s.NotYetImplementedException;
import edu.wustl.cse231s.bioinformatics.Nucleobase;
import hw5.test.part1.ASequentialNucleobaseCountTest;

/**
 * A sequential nucleobase counter. You can test this class with
 * {@link ASequentialNucleobaseCountTest}.
 * 
 * @author Finn Voichick
 *
 */
public class NucleobaseCounter {

	/**
	 * Creates a NucleobaseCounter.
	 */
	public NucleobaseCounter() {
	}

	/**
	 * Should sequentially count all of the instances of a specific nucleobase.
	 * No parallelization needed; nothing needs to run asynchronously. Just
	 * count the total number of bases in the sequence that are equal to the
	 * given base.
	 * <p>
	 * The chromosome is represented as an array of bytes. Each byte in the
	 * sequence represents one nucleobase: cytosine (C), guanine (G), adenine
	 * (A), thymine (T), or unknown (N). A byte is used (rather than char, int,
	 * String, or enum, for example) because a byte is a primitive data type
	 * that takes up very little memory.
	 * <p>
	 * The byte value representing this nucleobase occurs some number of times
	 * in the chromosome. In other words, if you call nucleobase.toByte(), it
	 * will be equal to some of the bytes in the chromosome array.
	 * <p>
	 * For this method, you may call
	 * {@link #countSequential(byte[], Nucleobase, int, int)}.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 */
	public int countSequential(byte[] chromosome, Nucleobase nucleobase) {
		int sum = 0; 
		for (int i=0; i < chromosome.length; i++ ) {
			if (chromosome[i] == nucleobase.toByte()) {  // from HW1
				sum++;
			}
		}
		return sum;
	}

	/**
	 * Should sequentially count all of the instances of a specific nucleobase
	 * that are within the given range. The range of numbers to search includes
	 * all indices in the array that are equal to or greater than the min index
	 * and less than the max index.
	 * 
	 * @param chromosome
	 *            the chromosome to examine, as an array of bytes
	 * @param nucleobase
	 *            the nucleobase to look for in the chromosome
	 * @param min
	 *            the lowest array index in the range to search, inclusive
	 * @param max
	 *            the highest array index in the range to search, exclusive
	 * @return the total number of times that the given nucleobase occurs in the
	 *         given chromosome
	 */
	protected int countSequential(byte[] chromosome, Nucleobase nucleobase, int min, int max) {
		int sum = 0; 
		for (int i= min; i < max; i++) { // from range min to max
			if (chromosome[i] == nucleobase.toByte()) {  
				sum++;
			}
		}
		return sum;
	}

}
