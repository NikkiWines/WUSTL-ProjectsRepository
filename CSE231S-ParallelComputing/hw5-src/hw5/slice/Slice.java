package hw5.slice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;

import edu.wustl.cse231s.NotYetImplementedException;
import hw5.test.part1.BSliceTest;

/**
 * A utility for slicing a range of numbers (for example, array indices) into a
 * list of smaller "slices" of that range. You can test this class with
 * {@link BSliceTest}.
 * 
 * @author Dennis Cosgrove (http://www.cse.wustl.edu/~cosgroved/)
 * @author Finn Voichick
 */
public final class Slice {

	/**
	 * The ID of a single slice, indexed from zero. If a range were split into 8
	 * slices, the slices would each have a different id, ranging from 0
	 * (inclusive) to 8 (exclusive).
	 */
	private final int sliceID;
	/** The minimum value of the range of this slice, inclusive. */
	private final int min;
	/** The maximum value of the range of this slice, exclusive. */
	private final int max;

	/**
	 * Should construct a single slice with the given ID and range.
	 * 
	 * @param sliceID
	 *            the ID of this new slice, indexed from zero
	 * @param min
	 *            the minimum value of the range of this slice, inclusive
	 * @param max
	 *            the maximum value of the range of this slice, exclusive
	 */
	private Slice(int sliceID, int min, int max) {
		this.sliceID = sliceID;
		this.min = min;
		this.max = max;
	}

	/**
	 * Returns a string representation of a slice which contains the sliceID and
	 * the range.
	 * 
	 * @return a String representation of this slice
	 */
	@Override
	public String toString() {
		return String.format("%d: %d-%d", sliceID, min, max);
	}

	/**
	 * Should get the zero-indexed ID of this slice.
	 * 
	 * @return This slice's ID.
	 */
	public int getSliceID() {
		return sliceID;
	}

	/**
	 * Should get the minimum value of this slice's range of values, inclusive.
	 * 
	 * @return The minimum value of the range of this slice, inclusive.
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Should get the maximum value of this slice's range of values, exclusive.
	 * 
	 * @return The maximum value of the range of this slice, exclusive.
	 */
	public int getMax() {
		return max;
	}

	/**
	 * Should create a list of slices with the given maximum value. The range
	 * being sliced is from 0 (inclusive) to max (exclusive). This method should
	 * call {@link #createSlices(int, int, int)}.
	 * 
	 * @param numSlices
	 *            the number of slices to create
	 * @param max
	 *            the length of the range to slice
	 * @return a list of slices of the given range
	 */
	public static List<Slice> createSlices(int numSlices, int max) {
		return createSlices(numSlices, 0, max); // slice from 0 to max. 
		
	}

	/**
	 * Should create a list of slices with the given range. The number of slices
	 * in the returned list should be equal to numSlices, and each slice should
	 * be approximately the same length.
	 * <p>
	 * The sliceIDs of the slices should be zero-indexed; that is, the first
	 * slice should have an ID of zero, the second slice should have an ID of
	 * one, and so on.
	 * <p>
	 * The min value of the first slice should be equal to this method's min
	 * parameter, and the max value of the last slice should be equal to this
	 * method's max parameter.
	 * 
	 * @param numSlices
	 *            the number of slices to create of the given range
	 * @param min
	 *            the minimum value of the range to slice, inclusive
	 * @param max
	 *            the maximum value of the range to slice, exclusive
	 * @return a list of slices of the given range
	 */
	public static List<Slice> createSlices(int numSlices, int min, int max) {
		List<Slice> list = new ArrayList<Slice>(); 
		int length = (max - min) / numSlices;
		for (int i = 0; i < numSlices; i++) { 

			int sliceMin = min + (i*length);
			int sliceMax = max;
			if (numSlices - 1 != i) { 
				sliceMax = sliceMin + length;
			}
			Slice slice = new Slice(i,sliceMin, sliceMax); 
			list.add(slice);
		}
		return list;
	}

	/**
	 * Should iterate through the range of this slice, and run consumer on each 
	 * value within the range. This method should be sequential and should go in
	 * numerical order. To see how this method is used, take a look at
	 * {@link BSliceTest#test()}.
	 * 
	 * @param consumer
	 *            a function to run on each value in this slice's range
	 * @see IntConsumer
	 * @see IntConsumer#accept(int)
	 */
	public void forEach(IntConsumer consumer) {
		for (int i = min; i < max; i ++) { 
			consumer.accept(i);
		}

	}

}
