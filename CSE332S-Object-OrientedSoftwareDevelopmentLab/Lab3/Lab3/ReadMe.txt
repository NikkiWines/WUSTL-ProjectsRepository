CSE 332 Lab 3: Multiple Games

Ivy Zhang (437706)
Nikki Wines (437324)
Tony De La Nuez (444735)

This program allows the user to select between two games, the "Nine Almonds Game" and the "Magic Square Game".

Usage: 
<ProgramName> NineAlmonds
<ProgramName> MagicSquare

Testing: 
Tried starting the game with badly formed input- 
Lab2.exe Nine -> leads to usage message
Lab2.exe -> leads to usage message
Lab2.exe NineAlmondsGame -> leads to usage message


If the user enters any string other than "NineAlmonds" or "MagicSquare", they will be given the usage message as stated above.

Warnings: 
Not all control paths return a value -

How to Play: 

Select the game by entering the program name and the appropriate game. 
When the game is loaded, the board for the game at game start will print and you will be prompted to enter a move. 

Magic Square Game: 
A correct input string is formatted as "x,y p" where x and y are integers which correspond to the x and y values of the place you'd like to put a piece. "p" is the value for an available piece. Once coordinates and available piece are selected, the board will refresh displaying the board after the piece was added. 

If the user enters invalid coordinates (either coordinates outside the dimensions of the game board or already occupied), they will be alerted that the coordinates are invalid and prompted for input again. 

If the user enters an invalid piece (not in the list of available pieceothey will be alerted that the piece selected was invalid and prompted for input again. 

Upon successful completion of the game, the game displays a success message and quits. 
If the board is filled but the game is not successfully completed (the sums do not match up), the user will be alerted that their "sums do not match up" and the game will end in a stalemate. 

Input: 
Nine Almonds Game: 
Coordinates-> #,# 
Continue Turn -> y
End Turn -> n
Quit Game-> quit

Magic Square Game: 
Coordinates-> #,# 
Piece -> #
Quit Game-> quit


Work Allocation: 
We decided to work on this all together; we didn't really split up much of the work. We used Ivy Zhang's implementation of the games and each did worked on some functionality (Nikki implemented the initial refactoring, Tony did the first stalemate and done methods, Ivy was helping convert the old code) then we met up and merged our parts and wrote the other functions. Most of our original (solo) implementations ended up changing due to unforeseen bugs. 


Bugfixes: 

* Fixed "not all control path return" issues

* Had a bug where input would automatically double submit (because of usage of basic cin extraction, fixed with getline())


***EXTRA CREDIT***
When making MagicSquare, we designed it with the extra credit in mind (using the variable horiz_dim wherever possible over hard coding). We augmented our check method to allow for multiple command line arguments for MagicSquare and used those inputs in a constructor that took in two ints (one for board size, one for starting piece value). We replaced the default constructor with this constructor. Lastly, we checked the selected piece against the first and last value of the available pieces vector instead of 0 and gameboard.size() to allow for negative piece values. 

For testing, we made sure that gameboards of size 0 (and non numeric values) called the usage function and gameboards greater than size 0 worked. We also tested negative piece values and double digit piece values and made sure that the gameboard adjusted its display spacing accordingly. For the negative piece values, we had to change our variables from unsigned ints to signed ints, so our overloaded prompt method now takes in ints rather than unsigned ints. This deviates from the instructions but was necessary to complete the extra credit. Lastly, we had to adjust our done() method to account for 1x1 boards. 
**note: 2x2 magic squares do not have solutions

Example: 
Input: lab3.exe MagicSquare 2 -1
Output: 
1
0
X 0 1
Available pieces: -1 0 1 2
give me a comma separated pair of coordinates or type 'quit':