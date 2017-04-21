//name: gamepiece.h
//purpose: declares gamepiece struct

#pragma once

#include <string>
using namespace std;

enum color { brown, no_color };

struct gamepiece
{
	color c;
	string d;
	bool isEmpty();
	void toEmpty();
};
