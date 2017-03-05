package studio0.growinglist;

import timing.ExecuteAlgorithm;
import timing.utils.GenSizes;
import timing.utils.IntArrayGenerator;

public class OurGrowth2 extends Rarrays {

	/**
	 * Try some function you think will work here.
	 */
	public int getNewSize() {
		int[] twenty= new int[(int)(array.length + 20)];
		ticker.tick(twenty.length);
		for (int i= 0; i < array.length; i++) { 
			twenty[i]= array[i];
		}
		this.array = twenty;
		return twenty.length;
		
	}
	
	public String toString() { return "Grow by multiplying by 10"; }

	public static void main(String[] args) {
		GenSizes sizes = GenSizes.arithmetic(1, 1000, 1);
		ExecuteAlgorithm.timeAlgorithm(
				"growth2", 
				"studio0.growinglist.OurGrowth2", 
				new IntArrayGenerator(), 
				sizes
				);	
	}
}
