package lab2;
import lab2.Scanner;
import lab2.autogen.*;
import java_cup.runtime.*;
import common.Listing;

/**
 ********** Answer to question 1: *************
Program
	::= File
	;

File   
	::= Lists
	;

Lists
	::= List L'
	;

L' (Lprime)
	::= List L'
	| 	lamba 
	;

List
	::= lparen Expression rparen
	;

Expression
	::= plus    Operand Operand
	|   minus   Operand Operand
	|   times   Operand Operand
	|   negate  Operand
	|   sum     Operands
	|   product Operands
	|   mean    Operands
	;

Operand
	::= Atom
	;

Operands
	::= Operand O'
	;

O' (Oprime)
	::= Operand O'
	|	lambda 
	;

Atom
	::= List
	|   number
	;

 ******** Answer to question 2: **************

Program: 
	First(Program) = lparen 
	Follow(Program) = $

File: 	
	First(File) = lparen 
	Follow(File) = $ 

Lists: 	
	First(Lists) = lparen 
	Follow(Lists) = $ 

L' (Lprime): 	
	First(Lprime) = lparen 
		|		  =  {}
	Follow(lprime) = $
		|		   = $ 

List:	
	First(List) = lparen 
	Follow(List) = lparen, $ 

Expression: 
	First(Expression) = plus 
		|			  = minus 
		|			  = times 
		|			  = negate 
		|			  = sum 
		|			  = product
		| 			  = mean 
	Follow(Expression) = rparen
		|			   = rparen
		|			   = rparen 
		|			   = rparen 
		|			   = rparen 
		|			   = rparen
		| 			   = rparen 

Operand: 
	First(Operand) = lparen 
	Follow(Operand) = number, lparen, rparen

Operands: 
	First(Operands) = lparen 
	Follow(Operands) = rparen 

O' (Oprime): 
	First(Oprime) = lparen 
		|		  = rparen
	Follow(Oprime) = rparen
		| 		  = $

Atom: 
	First(Atom) = lparen 
		| 		= number 
	Follow(Atom) = number, lparen, rparen
		|		 = {} 

 ************* Answer to question 4: *************

	Intended to implement with an overall mean counter that counts vals within the mean call instead of just counting the number of #'s passed over. 
	
 */

public class RecursiveDescent {

	public RecursiveDescent() {
		Scanner.init();

		//
		// FIXME:  Eliminate the loop below and instead call
		//   the method associated with your grammar's goal symbol
		//
		//		//  So, eliminate from here....
		//		Symbol t = Scanner.peek();
		//		while (t.sym != sym.EOF) {
		//			Listing.get().EmitMessage("Next token will be " + t);
		//			Scanner.advance();
		//			Listing.get().EmitMessage("Next2 token will be " + t);
		//			t = Scanner.peek();
		//		}
		//		//  ... to here
		Program();
	}
	int numParens = 0;
	int count = 0;

	//procedure methods
	void Program() {
		File();
	}
	void File() { 
		Lists();
	}
	void Lists() { 
		List();
		Lprime();
	}
	void Lprime() { 
		Symbol t = Scanner.peek();
		switch (t.sym) { 
		case sym.lparen:
			List();
			Lprime();
			break;
		default: // input lambda
			break;
		}
	}
	Integer List() { //  
		expect(sym.lparen);
		numParens++; 
		Integer ans = Expression();
		expect(sym.rparen); 
		numParens--;
		if (numParens == 0)
		System.out.print(" Answer: " + ans + " ");
		return ans;
	}

	Integer Expression() { 
		Symbol t = Scanner.peek();

		int ans = 0;
		switch (t.sym) { 
		case sym.plus:
			expect(sym.plus);
			ans = Operand() + Operand();
			return ans;
		case sym.minus: 
			expect(sym.minus);

			ans = Operand() - Operand();
			return ans;
		case sym.times: 
			expect(sym.times);

			ans = Operand() * Operand();
			return ans;
		case sym.negate:
			expect(sym.negate);
			ans = -Operand();
			return ans;
		case sym.sum: 
			expect(sym.sum);
			ans = Operands(0);
			return ans;
		case sym.product: 
			expect(sym.product);
			Operands(1);
			break;
		case sym.mean: 
			expect(sym.mean);
			Operands(2);
			break;	
		}
		return ans;
		
	}

	Integer Operand() {
		return Atom();
	}

	Integer Operands(int operation) { // takes in sum, product, or mean
		Symbol t = Scanner.peek();
		Integer operand1 = 0;
		Integer operand2 = 0;

		switch (operation) { // sum
		case 0: 
			operand1 = Operand();
			operand2 = 0;
			t = Scanner.peek();
			if (t.sym != sym.number) {
				operand2 = OprimeSum();
			} else {
				operand2 = Operands(0); // continue calling Operands to extend the call chain appropriately
			}
			return operand1 + operand2;
		case 1: // product
			operand1 = Operand();
			operand2 = 0;
			t = Scanner.peek();
			if (t.sym != sym.number) {
				operand2 = OprimeMult();
			} else {
				operand2 = Operands(0); // continue calling Operands to extend the call chain appropriately
			}
			return operand1 * operand2;
		case 2: // mean
			// didn't get around to implementing. 
			//need to keep track of mean count rather than individual number count 
			// this avoids over counting the numbers you intend to average out
			
			return 0;
		}
		oops("expected call to sum, product, or mean");
		return 0;
	}

	Integer OprimeSum() { 
		Symbol t = Scanner.peek();
		switch (t.sym) { 
		case sym.lparen:
			return Operand() + OprimeSum();
		case sym.rparen: // input lambda
			return 0;
		}
		return 0; 
	}
	
	Integer OprimeMult() {
		Symbol t = Scanner.peek();
		switch (t.sym) { 
		case sym.lparen:
			return Operand() * OprimeMult();
		case sym.rparen: // input lambda
			return 0;
		}
		return 0;
	}

	Integer Atom() { 
		Symbol t = Scanner.peek();
		switch (t.sym) { 
		case sym.lparen:
			return List();
		case sym.number: 
			int number = (int) t.value;
			expect(sym.number);
			count ++;
			return number;
		}
		return 0;
	}


	// methods from Prof. Cytron
	void oops(String s) {
		Listing.get().EmitMessage("Error: " + s);
		System.err.println("Error: " + s);
		System.err.println("Stack trace at error:");
		Error e = new Error();
		e.printStackTrace(System.err);
		System.exit(-1);
	}

	/**
	 * Symbol id of the next symbol, without advancing input
	 * @return
	 */
	protected int peek() {
		return Scanner.peek().sym;
	}

	/**
	 * When we expect a number, let's expect it but
	 * also return its integer value
	 * @return the value of the next symbol if it is a number
	 */
	protected int expectNum() {
		if (Scanner.peek().sym != sym.number) {
			oops("expected number but saw symbol " + Scanner.peek().sym);
			return -1; // doesn't matter
		}
		else {
			//
			// Capture the integer value and then
			//   advance the input through expect
			//
			int ans = (Integer) Scanner.peek().value;
			expect(sym.number);
			return ans;
		}
	}

	protected void expect(int symval) {
		expect(symval, "Expected symbol #" + symval + " (as defined in sym.java)");
	}

	protected void expect(int symval, String message) {
		if (Scanner.peek().sym != symval) oops(message);
		else Scanner.advance(); 
	}
}
