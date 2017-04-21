Nikki Wines Lab 2: 

Overview: 

This lab executes the Nine Almonds game, where the user inputs coordinates on a 4x4 table to move their almond "A" across others. 
The goal is the end in the center position (2,2) with no other almonds around. You can switch your selected almond by ending your turn and beginning a new one. 

Test Cases: 

Incorrect Starting Input- only opens to Lab2.exe nineAlmonds else gives error and returns a failureNumArgs 

Incorrect coords- doesn't accept in a form other than #,#. If a non-valid starting value is given then
	reprompts. 
Quit/Continue: if quit is called program ends and gives number of turns, else prompts for new coords 

Yy/Nn: if Y or y or N or n is ticked then appropriate continuing/non continuing of turns is executed. else reprompts for Yy/nn 

Valid moves are checked after every 2 coordinate pairs are entered, if they arent a valid pair of coordinates then you are reprompted for the second coordinate. 


Errors: 

1>\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab2\lab2\ninealmonds.cpp(178): warning C4804: '>': unsafe use of type 'bool' in operation

 Solved by casting unsigned int to int. 

Error (active)		"cout" is ambiguous	Lab2	\\warehouse2.seasad.wustl.edu\home\catherinewines\My Documents\Visual Studio 2015\Projects\Lab2\Lab2\NineAlmonds.cpp	117	

 Did not solve but mysteriously dissapeared when closing and reopening VS15.


