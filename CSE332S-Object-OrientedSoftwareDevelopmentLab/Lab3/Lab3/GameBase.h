//name: GameBase.h
//purpose: declares a base class for NineAlmondsGame and MagicSquare
#pragma once
#include <iostream>
#include <cstring>
#include <string>
#include <vector>
#include <memory>
#include "gamepiece.h"
using namespace std;


class GameBase
{
protected:
	int horiz_dim;
	int vert_dim;
	int starting_piece = 1; // zero by default, set if 2 args
	unsigned int piece_length;
	vector<gamepiece> gameboard;
	virtual void print() = 0;
	virtual bool done() = 0;
	virtual bool stalemate() = 0;
	virtual int turn() = 0;
	virtual int prompt(unsigned int &h, unsigned int &v);
public: 
	int play();
	static shared_ptr<GameBase> check(int argc, char * argv[]);
	GameBase(int hd, int vd);
};