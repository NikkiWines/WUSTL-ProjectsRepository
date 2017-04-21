Nikki Wines Lab 1 437324 

What does this lab do: parses input from a text file and creates either a chess or checker boards out of it. Uses ifstreamss and isstringstreams to compute the values. We also use vectors to hold our structs to place the individual pieces on the board

test cases: 
checkers and chess .txt 

 O O O O
O O O O
 O O O O

 X X X X
X X X X
 X X X X


chess-- 
passes test case as well 




8 8
block chicker X 0 0
blick checker X 9 2
black checker X 1 1
black checker X 2 9
BLACK checker X 2 2
black checker & 3 1
black checker X v 0
black checker X 4 2
black checker X 5 1
blAck checker X 6 0
black checker X 6 2
black checker X 9 9

red checker O 0 6
red checker O 1 5
red checker O 1 7
red checker O 2 6
red checker O 3 5
red checker O 3 7
red checker O 4 6
red checker O 5 5
red checker O 5 7
red checker O 6 6
red checker O 7 5
red checker O 7 7

output: 
Invalid Color: block 
Invalid Color: blick
 O O O O
O O O O
 O O O O

 X X X
X & X 
     X

Errors:
1) 
Error	C2084	function 'int usage(char *,std::string)' already has a body	Lab1	\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab1\lab1\functions.cpp	13	

— solved by deleting duplicate in gameboard.cpp 

2)
Error	C2923	'std::vector': 'game_piece' is not a valid template type argument for parameter '_Ty'	Lab1	\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab1\lab1\gameboard.h	17	

Error	C2065	'game_piece': undeclared identifier	Lab1	\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab1\lab1\gameboard.h	17	

Error	C3203	'allocator': unspecialized class template can't be used as a template argument for template parameter '_Alloc', expected a real type	Lab1	\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab1\lab1\gameboard.h	17	

— all solved by including gampiece.h in gameboard.h 

3)
ifs.open();
Error (active)	no instance of overloaded function "std::basic_ifstream<_Elem, _Traits>::open [with _Elem=char, _Traits=std::char_traits<char>]" matches the argument list	Lab1	\\warehouse2.seasad.wustl.edu\home\catherinewines\My Documents\Visual Studio 2015\Projects\Lab1\Lab1\Lab1.cpp	18	

-- solved by ifstream ifs(argv[inputFile]); instead of just ifstream ifs()