// Lab0.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream> 
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include <cstring>
#include "Lab0.h"

using namespace std;

int fileParser(vector<string> & reference, char * filename) {

	ifstream ifs;
	string line_1, word_1;

	ifs.open(filename);
	if (ifs.is_open()) {
		while (ifs >> word_1) { // loop through until false
			reference.push_back(word_1);
		}
	}
	else {
		cout << "Could not open file for reading" << endl; 
		return failureRead;
	}
	return success;
}

int usage(char * program_name) {
	cout << "Usage: " << program_name << " input_file.txt" << endl; // print out usage, program name, and direct them to input file
	return failureNumArgs;
}
int main(int argc, char *argv[])
{

	if (argc != numArgs) {
		return usage(argv[programName]);
	}

	vector<string> vectorStrings;
	int result = fileParser(vectorStrings, argv[inputFile]); // call file parser

	if (result != 0) { // check to see error code of fileParser fxn--- POST SUBMISSION NOTE:  in future if (result != success)
		return result;
	}
	vector<int> vectorInts;

	for (unsigned int i = 0; i < vectorStrings.size(); ++i) { // iterate across strings in vector
		bool isDigit = true;
		string active = vectorStrings[i];

		for (unsigned int j = 0; j < active.length(); j++) { // iterate across chars in the current string
			if (!isdigit(active[j])) { // ex. active[j] == 'a'
				isDigit = false;
			}
		} 

		if (isDigit) { // string is entirely numeric
			istringstream iss(active); // wrap in string stream
			int i;
			iss >> i; 
			vectorInts.push_back(i); // convert to int and push
		}
		else {
			cout << active << endl; // print out non-numeric strings	
		}
	} 
	for (unsigned int i = 0; i < vectorInts.size(); ++i) { // iterate across vectorInts and print out vals 
		cout << vectorInts[i] << endl;
	}
	return success;

}




