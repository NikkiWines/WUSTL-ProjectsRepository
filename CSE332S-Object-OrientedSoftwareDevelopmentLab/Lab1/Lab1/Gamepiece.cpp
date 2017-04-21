#include "stdafx.h"

#include "Gamepiece.h"
#include "Functions.h"	

game_piece::game_piece()
{
	name = "";
	display_type = " ";
	color = noColor;
}
game_piece::game_piece(piece_color color_, string name_, string display_type_){
	color = color_;
	name = name_;
	display_type = display_type_;

}

string colorEnum_toString(piece_color color) {
	if (color == red) {
		return "red";
	}
	else if (color == black) {
		return "black";
	}
	else if (color == white) {
		return "white";
	}
	else {
		return "invalid";
	}

}

piece_color string_toColorEnum(string color)
{
	color = toLower(color);

	if (color == "red") {
		return piece_color::red;
	}
	else if (color == "white") {
		return piece_color::white;
	}
	else if (color == "black") {
		return piece_color::black;
	}
	else if (color == "") {
		cout << "No Color" << endl;
		return piece_color::invalid;
	}
	else { // doesnt equal red, black, white 
		cout << "Invalid Color: " << color << endl;
		return piece_color::invalid;
	}
}
