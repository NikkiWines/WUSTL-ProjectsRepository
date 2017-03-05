package heaps;

import java.util.Random;
import java.util.UUID;

import javax.swing.JOptionPane;

import heaps.util.HeapToStrings;
import heaps.validate.MinHeapValidator;
import timing.Ticker;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {

	private Decreaser<T>[] array;
	private int size;
	private final Ticker ticker;

	/**
	 * I've implemented this for you.  We create an array
	 *   with sufficient space to accommodate maxSize elements.
	 *   Remember that we are not using element 0, so the array has
	 *   to be one larger than usual.
	 * @param maxSize
	 */
	@SuppressWarnings("unchecked")
	public MinHeap(int maxSize, Ticker ticker) {
		this.array = new Decreaser[maxSize+1];
		this.size = 0;
		this.ticker = ticker;
	}

	//
	// Here begin the methods described in lecture
	// testing commit...again...

	/**
	 * Insert a new thing into the heap.  As discussed in lecture, it
	 *   belongs at the end of objects already in the array.  You can avoid
	 *   doing work in this method by observing, as in lecture, that
	 *   inserting into the heap is reducible to calling decrease on the
	 *   newly inserted element.
	 *
	 *   This method returns a Decreaser instance, which for the inserted
	 *   thing, tracks the thing itself, the location where the thing lives
	 *   in the heap array, and a reference back to MinHeap so it can call
	 *   decrease(int loc) when necessary.
	 */
	public Decreaser<T> insert(T thing) {
		//
		// Below we create the "handle" through which the value of
		//    the contained item can be decreased.
		// VERY IMPORTANT!
		//    The Decreaser object contains the current location
		//    of the item in the heap array.  Initially it's ++size,
		//    as shown below.  The size is increased by 1, and that's
		//    were you should store ans in the heap array.
		//
		//    If and when the element there changes location in the heap
		//    array, the .loc field of the Decreaser must change to reflect
		//    that.
		//
		Decreaser<T> ans = new Decreaser<T>(thing, this, ++size);
		//
		// You have to now put ans into the heap array
		//   Recall in class we reduced insert to decrease
		// location of ans will return ans


		// upon insertion value decreases from inf to inserted val
		// instert reduced to decrease.
		// test with parent to determine which is larger -- repeat.
		// 1) insert first then call decrease.
		// 2) call decrease
		//
		array[size] = ans; // size represents very end of array- insert ans into size location
		decrease(ans.loc); // calls decrease
		ticker.tick(3);
		return ans;
	}

	/**
	 * This method responds to an element in the heap decreasing in
	 * value.   As described in lecture, that element might have to swap
	 * its way up the tree so that the heap property is maintained.
	 *
	 * This method can be called from within this class, in response
	 *   to an insert.  Or it can be called from a Decreaser.
	 *   The information needed to call this method is the current location
	 *   of the heap element (index into the array) whose value has decreased.
	 *
	 * Really important!   If this method changes the location of elements in
	 *   the array, then the loc field within those elements must be modified
	 *   too.  For example, if a Decreaser d is currently at location 100,
	 *   then d.loc == 100.  If this method moves that element d to
	 *   location 50, then this method must set d.loc = 50.
	 *
	 * In my solution, I made sure the above happens by writing a method
	 *    moveItem(int from, int to)
	 * which moves the Decreaser from index "from" to index "to" and, when
	 * done, sets array[to].loc = to
	 *
	 * This method is missing the "public" keyword so that it
	 *   is only callable within this package.
	 * @param loc position in the array where the element has been
	 *     decreased in value
	 */
	void decrease(int loc) {
		//
		// As described in lecture
		//
		//when value is inserted call decrease to maintain heap property
		int c= loc; // child location
		int p= loc/2; //parent location

		if (p == 0) { // if parent val doesn't exist.
			ticker.tick(1);
			return;
		}
		T pval= array[p].getValue(); // parent value
		T cval= array[c].getValue(); // child value
		if (cval.compareTo(pval) < 0) { // if child is smaller than parent
			moveItem(c, p);
			decrease(p);
			ticker.tick(3);
		}

		//compares if parent is greater than child
		//and if parent  is grater than 0
		//and if child is smaller than parent
	}






	/**
	 * Described in lecture, this method will return a minimum element from
	 *    the heap.  The hole that is created is handled as described in
	 *    lecture.
	 *    This method should call heapify to make sure the heap property is
	 *    maintained at the root node (index 1 into the array).
	 */
	public T extractMin() { // calls heapify
		T ans = array[1].getValue();
		//
		// There is effectively a hole at the root, at location 1 now.
		//    Fix up the heap as described in lecture.
		//    Be sure to store null in an array slot if it is no longer
		//      part of the active heap
		// will pull smallest value from root array[1];
		// val should be replaced with inserted value -- heapify will then be called -recursively
		//		swap first and last element;

		array[1] = array[size]; // update first node and first node location
		array[1].loc = 1;
		ticker.tick(3);
		//		moveItem(1, size); //ans.loc == 1 equivalent to sort of saying moveItem(ans.loc, size)
		array[size] = null; // storing null since no logner active heap
		--size; // decrease size
		heapify(1); //heapify from root
		ticker.tick(4);
		return ans;
	}

	/**
	 * As described in lecture, this method looks at a parent and its two
	 *   children, imposing the heap property on them by perhaps swapping
	 *   the parent with the lesser of the two children.  The child thus
	 *   affected must be heapified itself by a recursive call.
	 * @param where the index into the array where the parent lives
	 */
	private void heapify(int where) {
		// does downward version of decrease
		// As described in lecture
		//  FIXME
		//return 12 -- creates hole at root, so take last element and return it to root.
		//test new root against it's children to make sure it's the smallest.
		//Do so with every subsequent child pair.
		// heapify will check each pair

		int cL = 2*where; // left child loc
		int cR = (2*where)+1; // right child loc
		int p = where; // parent loc


		//		T pval = array[p].getValue();  // these don't work for some reason (print statements and data types identical but it creates a nullpointer exception)
		//		T cLval= array[cL].getValue();
		//		T cRval= array[cR].getValue();
		//		System.out.println("this is a " + array[p].getValue());
		//		System.out.println("this is b " + pval);

//		Decreaser<T> pval = array[p]; // this works for some reason... using for cleaner code
//		Decreaser<T> cLval = array[cL]; // but it causes an out of bonds except for HeapTimer, so even though it passes the tests....
//		Decreaser<T> cRval[cR] = array[cR]; // i cant use it. :( 

		//possible comparisons:
		//1) cL > size (no child left exists)
		//2) cL == to size
		//2) cR does exists and cL does exist
		// test cLval against cRval --> if cLval < cRval
		// test cLval against pval --> heapify
		// test cLval against cRval --> if cRval < cLval
		// test cRval against pval --> heapify
		//3) cR doesn't exist, but cL does (mostly full heap - can still heapify)
		// test cLval against pval --> heapify

		if (cL > size) { // if left child loc is greater than end of array -- nothing to do, end.
			ticker.tick(1);
		}
		else if (cL == size) { // if left child is end of array...
			if(array[cL].getValue().compareTo(array[p].getValue()) < 0) { // if left child is less than  to parent
				moveItem(cL, p); // swap
				heapify(cL);
				ticker.tick(4);
			}
		}
		else if (cR <= size)  { // if right child loc is smaller than end of array. -- right child exists!
			if (array[cL].getValue().compareTo(array[cR].getValue()) <= 0) { // if left child is smaller than or equal to right child...
				if (array[cL].getValue().compareTo(array[p].getValue()) < 0) { // if left child is smaller than parent...
					moveItem(p, cL); // swap
					heapify(cL);  // and then heapify!
					ticker.tick(5);
				}
			}
			if (array[cR].getValue().compareTo(array[cL].getValue()) <= 0) { // if right child is smaller than or equal to left child...
				if (array[cR].getValue().compareTo(array[p].getValue()) < 0) { // if right child is smaller than parent...
					moveItem(p, cR);
					heapify(cR);
					ticker.tick(5);
				}
			}

		}
		else if (cR > size) { // if right child loc is greater than end of array -- right child doesn't exist, but left child can still exists- still work to be done.
			if (array[cL].getValue().compareTo(array[p].getValue()) < 0) { // so check to see if the left child is smaller than parent ....
				moveItem(p, cL);
				heapify(cL);
				ticker.tick(4);
			}
		}
	}

	// personal method
	// move item method:
	void moveItem(int from, int to) { // thanks to Prof Cytron for the idea, and the TA's for help with this!!
		Decreaser <T> temp = array[to]; // create new var "temp" equal value of "to"
		array[to]= array[from];
		array[from] = temp; //  array[from] = temp val of array[to]
		array[from].loc = from; //update location of from
		array[to].loc = to; // update location of to
		ticker.tick(6);

	}

	/**
	 * Does the heap contain anything currently?
	 * I implemented this for you.  Really, no need to thank me!
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	//
	// End of methods described in lecture
	//

	//
	// The methods that follow are necessary for the debugging
	//   infrastructure.
	//
	/**
	 * This method would normally not be present, but it allows
	 *   our consistency checkers to see if your heap is in good shape.
	 * @param loc the location
	 * @return the value currently stored at the location
	 */
	public T peek(int loc) {
		if (array[loc] == null)
			return null;
		else return array[loc].getValue();
	}

	/**
	 * Return the loc information from the Decreaser stored at loc.  They
	 *   should agree.  This method is used by the heap validator.
	 * @param loc
	 * @return the Decreaser's view of where it is stored
	 */
	public int getLoc(int loc) {
		return array[loc].loc;
	}

	public int size() {
		return this.size;
	}

	public int capacity() {
		return this.array.length-1;
	}


	/**
	 * The commented out code shows you the contents of the array,
	 *   but the call to HeapToStrings.toTree(this) makes a much nicer
	 *   output.
	 */
	public String toString() {
		//		String ans = "";
		//		for (int i=1; i <= size; ++i) {
		//			ans = ans + i + " " + array[i] + "\n";
		//		}
		//		return ans;
		return HeapToStrings.toTree(this);
	}

	/**
	 * This is not the unit test, but you can run this as a Java Application
	 * and it will insert and extract 100 elements into the heap, printing
	 * the heap each time it inserts.
	 * @param args
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "You are welcome to run this, but be sure also to run the TestMinHeap JUnit test");
		MinHeap<Integer> h = new MinHeap<Integer>(500, new Ticker());
		MinHeapValidator<Integer> v = new MinHeapValidator<Integer>(h);
		Random r = new Random();
		for (int i=0; i < 100; ++i) {
			v.check();
			h.insert(r.nextInt(1000));
			v.check();
			System.out.println(HeapToStrings.toTree(h));
			//System.out.println("heap is " + h);
		}
		while (!h.isEmpty()) {
			int next = h.extractMin();
			System.out.println("Got " + next);
		}
	}


}
