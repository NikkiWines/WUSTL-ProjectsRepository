// name: gamepiece.cpp
// purpose: defines functions isEmpty() and toEmpty()

#include "stdafx.h"
#include "gamepiece.h"

bool gamepiece::isEmpty()
{
	if (d == " " && c == no_color)
	{
		return true;
	}
	else
	{
		return false;
	}
}

void gamepiece::toEmpty()
{
	c = no_color;
	d = " ";
}
