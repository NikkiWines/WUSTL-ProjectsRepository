//lab0.h 
//author: Nikki Wines nikki.wines@wustl.edu	
//
//purpose: 
//(1) read in and parse command line arguments, 
//(2) open a file and read in strings from it, 
//(3) differentiate numeric values from other input strings, and 
//(4) produce output based on the strings that it read in, to the standard output stream.

#ifndef LAB_0_H
#define LAB_0_H

//Enumeration Declaration 
enum labArray { programName, inputFile, numArgs };
enum returnCodes {success, failureRead, failureNumArgs };

#endif /* LAB_0_H */