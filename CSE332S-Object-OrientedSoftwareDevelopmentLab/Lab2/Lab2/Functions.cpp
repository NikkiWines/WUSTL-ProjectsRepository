#include "stdafx.h"

#include "Functions.h"

int usage(char * program_name, string c) { // give args 
	cout << "Usage: " << program_name << " " << c << endl; // print out usage, program name, and direct them to input file
	return failureIncorrectArgs;
}
