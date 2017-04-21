// to lowercase 
// enums for program name and err codes 


#include "stdafx.h"

#include "Functions.h"



// only called when you have the incorrect number of args 
// c = "game.txt" -- checkers or chess
int usage(char * program_name, string c) { // give args 
	cout << "Usage: " << program_name << " " << c << endl; // print out usage, program name, and direct them to input file
	return failureNumArgs;
}
	//define to lowercase in here 

string toLower(string input) {
	//check if in range 
	// check if theyre all chars not other 
	char c;
	for (unsigned int i = 0; i < input.size(); ++i) { // iterate across string
		c = input[i];
		if ((c > 64) && (c < 91)) {
			c = c + 32;
		}
		input[i] = c;
	}
	return input;
}

