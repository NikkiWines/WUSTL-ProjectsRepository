//name: MagicSquare.h
//purpose: declares MagicSquare class and along with function for gameplay 

#pragma once

#include <iostream>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include "gamepiece.h"
#include "GameBase.h"
using namespace std;

class MagicSquareGame : public GameBase
{
	friend ostream &operator<< (ostream &out, const MagicSquareGame &game);
public:
	MagicSquareGame(int a, int b);
	vector<int> available_pieces;
	virtual bool done();
	virtual bool stalemate();
	virtual int turn();
	virtual void print();
	virtual void prompt(int &a);

};

ostream &operator<< (ostream &out, const NineAlmondsGame &game);

