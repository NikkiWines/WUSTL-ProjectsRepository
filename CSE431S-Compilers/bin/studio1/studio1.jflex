/*
 *  $Id: studio3.jflex 18 2010-05-08 13:33:52Z cytron $
 *
 */

/* This section has code that goes at the top of the generated parser
 *    Things like package declaration and imports
 *    You should need nothing here for this exercise
 */
 
package studio1.autogen;
 
%%

/* This section has declarations and options settings
 *
 * First, some handy declarations
 *   Be sure you understand the syntax before moving on
 */
 
%public

LineTerminator = \r | \n | \r\n
InputCharacter = [^\r\n]	
WhiteSpace     = {LineTerminator} | [ \t\f]		/* The blank after the bracket is significant */
Slash = [/]
Minus = [-]
NotSlashMinus = [^/-]

/* Now we tell JFlex we are not part of CUP, just standalone
 */

%standalone

/* Tell JFlex to output the DFA as a table (the default is
 * a packed string, which is harder for humans to read */

%table

/* The following code is emitted in the generated class
 *   You should use it when you find something interesting
 */
 
%{
   /*  Call me to say what you found */
   private int count = 0; 
   
   public void found(String str) {
   	  ++ count; 
      System.out.println();  System.out.flush();  /* flush std out */
      System.err.println(count + ": " + "Found |" + str + "| from text -->" + yytext() + "<--");
   }
%}

%%

/* Finally, patterns of interest and what to
 *   upon finding them
 */

{Slash}{Minus}(.|{LineTerminator})*{Minus}{Slash}	{ found ("a comment"); }
/* [a-z]([a-z]|[0-9])*	{ found("identifier"); }  */
/* "if"			{ found("IF"); } */
/* "then"			{ found("THEN"); } */
"hello"			{ found("greeting"); }
([0|1])*"0"		{ found("even binary integer"); } 
("0"|"1")+		{ found("binary integer"); }
"goodbye"		{ System.exit(0); } 
"Nikki"			{ found("person 1"); }
"Kai"			{ found("person 2"); } 

