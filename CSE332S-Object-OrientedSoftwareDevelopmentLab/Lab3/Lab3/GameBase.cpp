//name: GameBase.cpp
//purpose: Defines base class functions like check, prompt, and play

#include "stdafx.h"
#include "GameBase.h"
#include "NineAlmondsGame.h"
#include "MagicSquare.h"
#include "functions.h"

GameBase::GameBase(int hd, int vd) :
	horiz_dim(hd),
	vert_dim(vd)
{

}

shared_ptr<GameBase> GameBase::check(int argc, char * argv[])
{
	string correct_arg_nine = "NineAlmonds";
	string correct_arg_magic = "MagicSquare";
	if (argc == default_num_args) {
		if (argv[game_name] == correct_arg_nine) {
			try {
				NineAlmondsGame *game = new NineAlmondsGame;
				shared_ptr<NineAlmondsGame> return_game(game);
				return return_game;
			}
			catch (bad_alloc& ba)
			{
				cout << "bad_alloc caught: " << ba.what() << endl;
			}
		}
		if (argv[game_name] == correct_arg_magic) {
			try {
				int board_size = 3;
				int starting_piece = 1;
				MagicSquareGame *game = new MagicSquareGame(board_size, starting_piece);
				shared_ptr<MagicSquareGame> return_game(game);
				return return_game;
			}
			catch (bad_alloc& ba)
			{
				cout << "bad_alloc caught: " << ba.what() << endl;
			}
		}
		else { return nullptr; }
	}
	if (argc == three_args) { // 3 arguments only applicable for magicsquare
		if (argv[game_name] == correct_arg_magic) {
			istringstream iss(argv[additional_arg_1]);
			int argv2;
			if (iss >> argv2) {
				if (argv2 >= 1) { //boardsize is greater than or equal to 1
					try {
						int starting_piece = 1;
						MagicSquareGame *game = new MagicSquareGame(argv2, starting_piece);
						shared_ptr<MagicSquareGame> return_game(game);
	
						return return_game;
					}
					catch (bad_alloc& ba)
					{
						cout << "bad_alloc caught: " << ba.what() << endl;
					}
				}
				else { return nullptr; }
			}
			else { // value entered is not a number
				return nullptr;
			}
		}
		else { return nullptr; }
	}
	if (argc == four_args) { // 4 arguments only applicable for magicsquare
		if (argv[game_name] == correct_arg_magic) {
			istringstream iss(argv[additional_arg_1]);
			int argv2;
			if (iss >> argv2) {
				if (argv2 >= 1) { //boardsize is greater than or equal to 1
					istringstream iss2(argv[additional_arg_2]);
					int argv3;
					if (iss2 >> argv3)
					{
						try {
		
							MagicSquareGame *game = new MagicSquareGame(argv2, argv3);

							shared_ptr<MagicSquareGame> return_game(game);
							return return_game;
						}
						catch (bad_alloc& ba)
						{
							cout << "bad_alloc caught: " << ba.what() << endl;
						}
					}
					else { // not a number
						return nullptr;
					}
				}
				else { return nullptr; }
			}
			else { // value entered is not a number
				return nullptr;
			}
		}
		else { return nullptr; }
	}
	else {
		return nullptr;
	}
	return nullptr;
}

int GameBase::prompt(unsigned int & h, unsigned int & v)
{
	unsigned int dim = horiz_dim;
	bool valid_coord = false;
	while (valid_coord == false)
	{
		string s1;
		cout << "give me a comma separated pair of coordinates or type 'quit': ";
		getline(cin, s1);

		if (s1 == "quit")
		{
			valid_coord = true;
			return quit;
		}
		else
		{
			char s2 = ',';
			int valid_size = 3;
			int first_coord = 0;
			int second_coord = 2;
			int ascii_min = 48;
			int ascii_max = 53;
			unsigned int min_index = 0;
			if ((s1.find(s2) != string::npos) && (s1.size() == valid_size) && s1[1] == s2)
			{
				replace(s1.begin(), s1.end(), ',', ' ');
				istringstream iss(s1);
				int coord1, coord2;
				if (iss >> coord1)
				{
					if (iss >> coord2)
					{
						if ((coord1 >= 0 && coord1 < horiz_dim) && (coord2 >= 0 && coord2 < horiz_dim)) 
						{
							h = coord1;
							v = coord2;
							valid_coord = true;
							return succ_extract_coord;
						}
						else {
							cout << "Badly formed input. Please try again." << endl;
							return failure_extract_v;
						}
					}
					else
					{
						cout << "Extract failure" << endl;
						return failure_extract_v;
					}
				}
				else
				{
					cout << "Extract failure" << endl;
					return failure_extract_h;
				}
			}
			else {
				cout << "Input incorrect. Please try again. " << endl;
				return failure_extract_h;
			}
		}
	}
	return succ_extract_coord; // if exited while loop, validCoord = True
}

int GameBase::play()
{
	int turns = 0;
	while (true)
	{
		int call = turn();
		if (call == quit)
		{
			cout << "you have quit the game after the following number of turns: " << turns << endl;
			return quit;
		}
		else if (call == quit_after_continue)
		{
			turns++;
			cout << "you have quit the game after the following number of turns: " << turns << endl;
			return quit;
		}
		else if (call == failure_stalemate)
		{
			turns++;
			cout << "no valid moves remain, number of turns: " << turns << endl;
			return failure_stalemate;
		}
		else if (call == success)
		{
			turns++;
			cout << "congratulations! you won the game in " << turns << " turns" << endl;
			return success;
		}
		else
		{
			turns++;
		}
	}
}



