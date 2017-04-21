// Lab2.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "GamePiece.h"
#include "NineAlmonds.h"
#include "Functions.h"

int main(int argc, char* argv[])
{
	// make public getter method for NineAlmondsGame and then use those. 
	// std usage call
	if ((argc != numArgs) || (string(argv[nineAlmonds]) != "nineAlmonds") ) {
		cout << "Incorrect Number of Arguments" << endl;
		string msg = "nineAlmonds";
		return usage(argv[programName], msg);
	}
	NineAlmondsGame Game;
	game_piece gp = game_piece();
	vector<game_piece> pieces_vector;
	int board_width = 5;
	int board_height = 5;
	int buffer = 6;
	for (unsigned int i = 0; i < (board_width*board_height + buffer); i++) { // change to non-hard coded type but member variables are all private.
		pieces_vector.push_back(gp);

	}
	game_setup(pieces_vector, board_width); // again change to non-hard coded type but member variables are all private.
	Game.set_game_board(pieces_vector); // set game_board to the setup board

	Game.play();

}

