package studio0.growinglist;

import timing.ExecuteAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class OurGrowth1 extends Rarrays {

	/**
	 * Try some function you think will work here.
	 */
	public int getNewSize() {
		int[] tenth= new int[(int)((array.length + (array.length))/10)];
		ticker.tick(tenth.length);
		for (int i= 0; i < array.length; i++) { 
			tenth[i]= array[i];
		}
		this.array = tenth;
		return tenth.length;
		
	}
	
	public String toString() { return "Grow by our own function (1)"; }

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 1000, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"growth1", 
				"studio0.growinglist.OurGrowth1", 
				new IntArrayGenerator(), 
				sizes
				);	
	}

}
