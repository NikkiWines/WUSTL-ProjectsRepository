#include "stdafx.h"
#include "GamePiece.h"

game_piece::game_piece()
{
	name = "";
	display_type = "  ";
	color = " ";
}

game_piece::game_piece(string color_, string name_, string display_type_)
{
	name = name_;
	display_type = display_type_;
	color = color_;
}
