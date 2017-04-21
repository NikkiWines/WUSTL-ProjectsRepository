// name: functions.cpp 
// purpose: defines usage function, called when wrong number of arguments is entered.


#include "stdafx.h"
#include "functions.h"
using namespace std;

int usage(char * program_name, string c)
{
	if (c == "MagicSquare") {
		cout << "usage: " << program_name << " -arguments needed: " << c << " (optional params int > 1 and int)" <<  endl;

	}
	else {
		cout << "usage: " << program_name << " -arguments needed: " << c << endl;
	}
	return failure_num_args;
}