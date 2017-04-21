//LAB 1 Gamepiece.h
//author: Nikki Wines nikki.wines@wustl.edu	

#ifndef GAMEPIECE_H
#define GAMEPIECE_H

#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include <cstring>

using namespace std;

enum piece_color { red, black, white, invalid, noColor };

struct game_piece {
	game_piece();
	game_piece(piece_color color, string name, string display_type);

	enum piece_color color;
	string name;
	string display_type;

};



string colorEnum_toString(piece_color);

piece_color string_toColorEnum(string color);

#endif /* GAMEPIECE_H */