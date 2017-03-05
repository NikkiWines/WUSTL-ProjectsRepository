package rabinkarp;

import timing.Ticker;

public class RK {

	//
	// Be sure to look at the write up for this assignment
	//  so that you get full credit by satisfying all
	//  of its requirements
	//
	int RKhash; // hash value 
	char [] currentChars; // circular buffer -- from class
	int m; // size of window 
	int start; //starting index
	Ticker ticker;
	/**
	 * Rabin-Karp string matching for a window of the specified size
	 * @param m size of the window
	 */
	public RK(int m) {
		this.ticker = new Ticker();
		this.m = m; 
		this.RKhash = 0;
		this.start = 0;
		this.currentChars = new char [this.m]; // new circular buffer of specified (m) size. 
	}
	/**
	 * Compute the rolling hash for the previous m-1 characters with d appended.
	 * @param d the next character in the target string
	 * @return
	 */
	public int nextCh(char d) {
		int c = currentChars[start%this.m];
		int one = (RKhash*31)%511;
		int two = (pow(this.m, c));
		int three = d%511;
		ticker.tick(4);
		if (start < m) { 
			RKhash = one + three;
			RKhash = RKhash%511; // calculate second part of hash
			ticker.tick(2);
		}
		else { 
			RKhash = one - two + three + 511; // calculate first part of hash. add 511 to offset any negative vals
			RKhash = RKhash%511; // calculate second part of hash 
			ticker.tick(2);
		}
		currentChars[start%this.m] = d; // add d to buffer (overridden pos) and keep start index in bounds
		start ++; // increment starting position (c in the website alg );
		ticker.tick(2);
		return RKhash;
	}
	// create pow method// manually calculates 31^m
	public int pow(int m, int c) {
		int ans = 1;
		for (int i = 0; i < this.m; i ++) { 
			ans = ((31%511)*(ans%511))%511; // gives you (31^m)%511
			ticker.tick();
		}
		ticker.tick(2);
		ans = (ans*(c%511))%511; // ((31^m)%511)*(c%511))%511 = (31^m*c)%511
		ticker.tick();
		return ans;

	}
}

