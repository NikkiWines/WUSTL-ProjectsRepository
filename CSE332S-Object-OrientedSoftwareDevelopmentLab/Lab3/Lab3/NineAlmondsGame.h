//name: NineAlmondsGame.h
//purpose: declares NineAlmondsGame class along with functions for gameplay
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


class NineAlmondsGame : public GameBase
{
	friend ostream &operator<< (ostream &out, const NineAlmondsGame &game);
public:
	NineAlmondsGame();
	virtual bool done();
	bool isValidMove(int h, int v);
	virtual bool stalemate();
	void move_action_1(unsigned int &h1, unsigned int &v1, unsigned int &h2, unsigned int &v2, bool &atLeastOneMove);
	int move_action_2(unsigned int &h1, unsigned int &v1, unsigned int &h2, unsigned int &v2, string &move, string &moves, bool &continued_turn);
	virtual int turn();
	virtual void print(); 
};

ostream &operator<< (ostream &out, const NineAlmondsGame &game);

