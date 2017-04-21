//LAB 2 GamePiece.h
//author: Nikki Wines nikki.wines@wustl.edu	

#ifndef GAMEPIECE_H
#define GAMEPIECE_H

#include <iostream>
#include <string> 

using namespace std;

struct game_piece {
	game_piece();
	game_piece(string color, string name, string display_type);

	string color;
	string name;
	string display_type; 

};

#endif GAMEPIECE_H

