//LAB 2 NineAlmonds.h
//author: Nikki Wines nikki.wines@wustl.edu	

#ifndef NINEALMONDS_H
#define NINEALMONDS_H

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include "GamePiece.h"

using namespace std;

class NineAlmondsGame {
	friend	ostream &operator<<(ostream &out, const NineAlmondsGame &game_class);
public:
	NineAlmondsGame(); // default constructor
	NineAlmondsGame(game_piece almonds, vector<game_piece> game_board, int width, int height);
	
	//fxns
	bool done();
	bool stalemate();
	bool isValid(int i1, int j1, int i2, int j2);
	int prompt(unsigned int &coord1, unsigned int &coord2);
	int turn();
	void move_almond(int i1, int j1, int i2, int j2, string type);
	int play();

	//setters 
	void set_game_board(vector<game_piece> v);
	//getters
	game_piece get_game_piece();
	vector<game_piece> get_game_board();
	int get_width();
	int get_height();
private: 
	game_piece almonds; 
	vector<game_piece> game_board;
	int width = 5;
	int height = 5;
};

ostream &operator<<(ostream &out, const NineAlmondsGame &game_class);

vector<game_piece> game_setup(vector<game_piece> &, unsigned int x_loc);



#endif NINEALMONDS_H