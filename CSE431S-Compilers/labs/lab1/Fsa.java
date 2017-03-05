package lab1;

import java.util.Enumeration;

public class Fsa {

	// states are the rows, tokens are columns
	static int GOTO[][] = { 
			/*     B    D    S    O    C    S    T    N    S    W    O */
			/*     l    e    e    r    o    t    e    o    t    i    t */
			/*     a    f    m         m    r    r    n    a    t    h */
			/*     n    i    i         m         m         r    h    e */
			/*     k    n              a         i         t         r */
			/*          e                        n                     */
			/*                                   a                     */
			/*                                   l                     */
			/*                                                         */
			{      0,   0,   0,   0,   0,   9,   2,   1,   6,   0,   0} ,
			{      1,   0,   0,   0,   0,   0,   2,   0,   0,   0,   0} ,
			{      2,   0,   0,   0,   0,   3,   0,   0,   0,   0,   0} ,
			{      3,   0,   0,   0,   0,   4,   0,   0,   0,   0,   0} ,
			{      4,   0,   0,   0,   5,   0,   0,   0,   0,   0,   0} ,
			{      5,   0,   0,   0,   0,   4,   0,   0,   0,   0,   0} ,
			{      6,   0,   0,   0,   0,   0,   0,   0,   0,   7,   0} ,
			{      7,   0,   0,   0,   0,   8,   0,   0,   0,   0,   0} ,
			{      8,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0} ,
			{      9,  10,   0,   0,   0,   0,   0,   0,   0,   0,   0} ,
			{     10,   0,   0,   0,   0,  11,   0,   0,   0,   0,   0} ,
			{     11,   0,   0,  10,   0,  12,   0,   0,   0,   0,   0} ,
			{     12,   0,   0,  10,   0,   0,   0,   0,   0,   0,   0} ,


	};
	// on the way of going from here to there do this -- what you're doing from the edge from
	static int ACTION[][] = { 
			/*     B    D    S    O    C    S    T    N    S    W    O */
			/*     l    e    e    r    o    t    e    o    t    i    t */
			/*     a    f    m         m    r    r    n    a    t    h */
			/*     n    i    i         m         m         r    h    e */
			/*     k    n              a         i         t         r */
			/*          e                        n                     */
			/*                                   a                     */
			/*                                   l                     */
			/*                                                         */
			{      1,   1,   1,   1,   1,   6,   1,   3,   1,   1,   1} , // 0
			{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1} , // 1	
			{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1} , // 2
			{      1,   1,   1,   1,   1,   5,  11,  11,  11,  11,   1} , // 3
			{      1,   1,   4,   1,   1,   1,   1,   1,   1,   1,   1} , // 4
			{      1,   1,   1,   1,   1,   5,  11,  11,   11, 11,   1} , // 5
			{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1} , // 6 
			{      1,   1,   1,   1,   1,   2,   1,   1,   1,   1,   1} , // 7
			{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1} , // 8
			{      1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1} , // 9
			{      1,   1,   1,   1,   1,   7,   1,   1,   1,   1,   1} , // 10 
			{      1,   1,  10,   9,   1,   8,   1,   1,   1,   1,   1} , // 11
			{      1,   1,  10,   9,   1,   1,   1,   1,   1,   1,   1} , // 12


	};

	/*
	 * Action cases:
	 * 1: do nothing
	 * 2: 
	 */

	public Fsa(Enumeration e) {
		// Uncomment the line below and each token processed will be echoed.
		//((TokenEnum)e).setDebug(true);

		SymbolTable symboltable = new SymbolTable();

		int state = 0;
		boolean isTerminal= true;
		String 
		lhs     = "", 
		term    = "", 
		nonterm = "$FINAL$";
		symboltable.enterNonTerminal(nonterm); // register $FINAL$ into symbol table

		while (e.hasMoreElements()) {
			Token t = (Token)e.nextElement();

//						System.out.println("   Read token type " + t.type() + ": " + t);

			int action = ACTION[state][t.type()];
			int newstate = GOTO[state][t.type()];

//						System.out.println("State " + state +
//								" Performing action " + action + " and going to " + newstate);

			switch (action) {
			case  1: /* do nothing */
				break;  
			case 2: // found "start with" print line to indicate the start state
				System.out.println("Start " + t.strValue());
				break;
			case 3: // found "non", keep track of str type
				isTerminal = false;
				break;
			case 4: // reset isTerminal boolean
				isTerminal = true;
				break;
			case 5: // register t.strValue in symbol table
				if (isTerminal) {
					symboltable.enterTerminal(t.strValue());
				}
				else { 
					symboltable.enterNonTerminal(t.strValue());
				}
				break;
			case 6: // left hand side check, always nonterminal, set lhs value
				if (symboltable.isTerminal(t.strValue())) { 
					oops("Expected nonterminal");
				}
				lhs = t.strValue();
				break;
			case 7: // right hand side set term value
				term = t.strValue();
				break;
			case 8: // right hand side set non-term value
				nonterm = t.strValue();

				break;
			case 9: // found or, right hand side check, terminal followed by at most one nonterminal. Reset term and nonterm
				if (symboltable.isTerminal(term) && !symboltable.isTerminal(nonterm)) { 
					System.out.println("Edge " + lhs + " " + nonterm + " " + term);
				}
				else if (!symboltable.isTerminal(term)){ 
					oops("Expected terminal: " + term);				
					}
				else { 
					oops("Expected nonterminal: " + nonterm);
				}
				term    = "";
				nonterm = "$FINAL$";
				break;
			case 10: // found semicolon, right hand side check, and reset lhs, term, and nonterm
				if (symboltable.isTerminal(term) && !symboltable.isTerminal(nonterm) || nonterm == "$FINAL") { 
					System.out.println("Edge " + lhs + " " + nonterm + " " + term);
				}
				else if (!symboltable.isTerminal(term)){ 
					oops("Expected terminal: " + term);				
					}
				else { 
					oops("Expected nonterminal: " + nonterm);
				}
				lhs     = "";
				term    = "";
				nonterm = "$FINAL$";
				break;
			case 11: // non string entered in value declaration
				oops("Type Error, Token <Str> Expected. " + t + " Given");
				break;
			}


			state = newstate;
		}
		if (state != 0) oops("End in bad state: " + state);
	}

	void oops(String s) {
		System.err.println("Error: " + s);
		System.out.println("ABORT");
		System.exit(-1);
	}
}
