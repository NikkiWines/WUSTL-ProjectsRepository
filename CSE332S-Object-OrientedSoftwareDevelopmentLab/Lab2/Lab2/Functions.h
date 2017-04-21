//LAB 2 Functions.h
//author: Nikki Wines nikki.wines@wustl.edu	
//
//purpose: 


#ifndef FUNCTIONS_H
#define FUNCTIONS_H

#include <iostream> 
#include <string>
#include <vector>


using namespace std;
//Enumeration Declaration 
enum labArray { programName, nineAlmonds, numArgs};
enum returnCodes { success, failure, failureIncorrectArgs, improperForm, quit, stlmt, successExtract, failureExtract, endTurn };

//FXNS Declarations

int usage(char* programName, string inFile);

#endif FUNCTIONS_H