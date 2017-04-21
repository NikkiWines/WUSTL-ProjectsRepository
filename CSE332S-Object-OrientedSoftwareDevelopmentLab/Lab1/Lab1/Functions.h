//LAB 1 Function.h
//author: Nikki Wines nikki.wines@wustl.edu	
//
//purpose: 
//declaring a struct to represent game pieces(e.g., for games like chess and checkers),
//using file streams and string streams to import information into a program,
//and subscripting(indexing) into a vector representing the positions of pieces on a game board

#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <iostream> 
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include <cstring>

using namespace std;
//Enumeration Declaration 
enum labArray { programName, inputFile, numArgs };
enum returnCodes { success, failureRead, failureNumArgs, failureGetLine, failureExtraction, improperForm };

// Function Declaration 
string toLower(string input);

int usage(char* programName, string inFile);

#endif /* FUNCTIONS_H */