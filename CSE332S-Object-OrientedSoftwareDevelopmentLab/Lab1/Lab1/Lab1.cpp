// Lab1.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "Gameboard.h"
#include "Functions.h"
#include "Gamepiece.h"


int main(int argc, char* argv[])
{
	if (argc != numArgs) {
		//cout << "Incorrect Number of Arguments" << endl;
		string msg = "chess.txt or checkers.txt";
		return usage(argv[programName], msg);
	}
	unsigned int width;
	unsigned int height;

	ifstream ifs(argv[inputFile]);

	if (!ifs.is_open()) {
		cout << "Could not read file" << endl;
		return failureRead;
	}
	read_board_dimensions(ifs, width, height);
	game_piece gp = game_piece();
	vector<game_piece> pieces_vector;
	
	for (unsigned int i = 0; i < (width*height); i++) {
		
		pieces_vector.push_back(gp);
	}

	//read_game_pieces(ifs, pieces_vector, width, height);
	

	int res = read_game_pieces(ifs, pieces_vector, width, height);

	if (res != 0) { // if not success from read game pieces then we weren't able to extract everything/
		return failureExtraction; 
	}
	//ifs.close();
	return print_game(pieces_vector, width, height);
}

