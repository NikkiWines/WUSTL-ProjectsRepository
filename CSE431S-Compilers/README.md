CSE431 Nikki Wines - Spring Semester 2017

Lab 1: In this assignment I implemented a simple finite-state transducer, which uses the actions of a finite-state automaton to perform a simple language translation.

Lab 2: In this assignment I implemented a simple expression evaluator using recursive-descent parsing.

Lab 3: In this assignment I implemented a bottom-up parser and analyzed the structure of the Java programming language by constructing abstract syntax trees (WIP).

 /* Lab 4 Cancelled */ 

Lab 5: In this assignment, I implemented a JVM program, using Jasmine, that replicated a java recursive fibonacci program. 

Exam 1: In this take home assignment, I wrote a loost HTML context-free grammer and Java parser. We did not have to account for all HTML elements, just the ones included in the test file, however the recursive nature of the grammer should account for the majority of HTML tags. We were challenged to account for comments in the parser, rather than the scanner. The grammer looks like this: 

Terminals: 
terminal String   ltag_o;
terminal String   rtag;
terminal String   ltag_c;
terminal String   text;
terminal String   tag; 
terminal String   link;

Non terminals: 
non terminal Program
non terminal Body;

Program
::=  ltag_o tag rtag Body
;

Body 
::=  ltag_o tag rtag Body
|      ltag_o tag rtag
|      text Body
|      text
|      tag Body
|      tag
|      ltag_c tag rtag Body
|      ltag_c tag rtag
|      link Body
|      link
;

The tags used for parsing are the following: 

a) ltag_o: (<)
b) rtag: (>)
c) ltag_c: (<\/)
d) tag: ({ALPHA}+{DIGIT}*\"*\?*\'*\[*\]*\-*) Alpha characters,
digits and some extra character in case of overlap.
e) text: ([\w ]*[0-9:.,()\"\?\'\[\]\-]*) Any word, number or
character.
f) link: ((<a.*>(.|\n)*.*<\/a>)) Anything after <a and before >, as well as 
anything (including a newline) between <a.*> and </a>  c
g) comment: <!--.*--> (includes any text and the <!-- and --> tags)
h) pre: (<PRE>(.|\n)*<\/PRE>) Capture anything (.) and a new line
between <PRE> and </PRE> 

/* Note:  comments are simply consumed */

