package kwaymergesort;

import timing.Ticker;

public class KWayMergeSort {

	/**
	 * 
	 * @param K some positive power of 2.
	 * @param input an array of unsorted integers.  Its size is either 1, or some other power of 2 that is at least K
	 * @param ticker call .tick() on this to account for the work you do
	 * @return
	 */
	public static Integer[] kwaymergesort(int K, Integer[] input, Ticker ticker) {
		int n = input.length; // given 
		ticker.tick();
		if (n > 1) { // array has enough elements to need sorting  
			Integer[][] pieces = new Integer[K][n / K]; // from Cytron class demonstration
			Integer[][] newArray = new Integer[K][]; // hold elements
			int count = 0;
			ticker.tick(3);
			for (int i = 0; i < K; i++) {
				for (int j = 0; j < n / K; j++) {
					pieces[i][j] = input[count]; // array within array implementation - set 
					count++;
					ticker.tick(2);
				}
			}
			int i =0; // for while loop
			ticker.tick();
			while (i < K) {
				newArray[i] = kwaymergesort(K, pieces[i], ticker); // recursively call kwaymergesort to sort individual portions of the array -- iterates over entire array
				i++;
				ticker.tick(2);
			}
			return mergeArray(newArray, ticker);
		}
		else { // length is 0 or 1 -- no sorting needed just return 
			ticker.tick();
			return input;
		}
	}
	// new method mergeArray to sort 2 individually sorted arrays together...
	private static Integer[] mergeArray(Integer[][] array, Ticker ticker) {
		Integer[] position = new Integer[array.length]; 
		Integer[] mergedArray = new Integer[array[0].length * array.length];
		ticker.tick(2);
		int k =0;
		while (k < array.length) { // iterate through array assigning 0 to all positions
			position[k] = 0;
			k++;
			ticker.tick(2);
		}
		for (int j=0; j < array.length * array[0].length; j++) { // iterate through length of n*n0
			int minv = Integer.MAX_VALUE; // originally set to infinity  integer.max_value == inf 
			int minp = -1;
			ticker.tick(2);
			for (int i = 0; i < array.length; i++) { 
				if (position[i] < array[0].length) { // if position is less than array[0].length
					ticker.tick(); // if statement ticker
					if (array[i][position[i]] < minv) { // anything is less than the min 
						minp = i; // set position for new min 
						minv = array[i][position[i]]; // set it equal to min 
						ticker.tick(3); // if stmt and minv and minp ticker
					}
				}
			}
			mergedArray[j] = minv; // add minv to mergedArray as new min val.
			position[minp]++; //increment position[minp]
			ticker.tick(2);
		}
		return mergedArray;
	}
}