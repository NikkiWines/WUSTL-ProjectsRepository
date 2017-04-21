//name: NineAlmonds.cpp
//purpose: Defines functions for NineAlmondsGame.

#include "stdafx.h"
#include "functions.h"
#include "NineAlmondsGame.h"
#include <iomanip>

NineAlmondsGame::NineAlmondsGame() :
	GameBase(5, 5)
{
	int bottom_row_start = 6;
	int bottom_row_end = 9;
	int middle_row_start = 11;
	int middle_row_end = 14;
	int upper_row_start = 16;
	int upper_row_end = 19;
	
	// initialize an almond gamepiece
	gamepiece a;
	a.c = brown;
	a.d = "A";

	// intialize an empty almond gamepiece
	gamepiece empty;
	empty.toEmpty();
	
	// change piece_length var if new max
	if (a.d.length() > piece_length) { 
		piece_length = a.d.length() + 1;
	}

	// fill gameboard with pieces
	for (int i = 0; i < horiz_dim*vert_dim; i++) {
		gameboard.push_back(empty);
	}
	for (int i = bottom_row_start; i < bottom_row_end; i++) {
		gameboard[i] = a;
	}
	for (int i = middle_row_start; i < middle_row_end; i++) {
		gameboard[i] = a;
	}
	for (int i = upper_row_start; i < upper_row_end; i++) {
		gameboard[i] = a;
	}
}

ostream &operator<< (ostream &cout, const NineAlmondsGame &game) {
	if ((game.horiz_dim)*(game.vert_dim) == game.gameboard.size())
	{
		// print out gameboard
		for (int i = game.vert_dim - 1; i >= 0; i--)
		{
			cout << i;
			
			for (int j = 0; j < game.horiz_dim; j++)
			{
				cout << setw(game.piece_length) << game.gameboard[game.horiz_dim*i + j].d;
			}
			cout << endl;
			cout << endl;
		}
		
		// print out the labels for the gameboard
		cout<< "X"
			<< setw(game.piece_length) << "0" 
			<< setw(game.piece_length) << "1"
			<< setw(game.piece_length) << "2" 
			<< setw(game.piece_length) << "3" 
			<< setw(game.piece_length) << "4" << endl;
	}
	else
	{
		cout << "vector size mismatch" << endl;
	}
	return cout;
}

bool NineAlmondsGame::done()
{
	int middle_piece = 12;
	if (gameboard[middle_piece].isEmpty() == false)
	{
		for (int i = 0; i < (horiz_dim)*(vert_dim); i++)
		{
			if (i != middle_piece && gameboard[i].isEmpty() == false) // if every piece that's not the middle piece is empty
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

bool NineAlmondsGame::isValidMove(int h, int v)
{
	int min_value = 0;
	// for a given piece, check to see if there is a valid move in any of the 8 directions
	if (gameboard[horiz_dim*v + h].isEmpty() == false)
	{
		// check to see if in the right direction, the piece adjacent is not empty and the piece 2 spots away is empty
		if ((h + 2) >= min_value && (h + 2) < horiz_dim && gameboard[(horiz_dim * v + (h + 1))].isEmpty() == false && gameboard[(horiz_dim * v + (h + 2))].isEmpty() == true)
		{
			return true;
		}
		else if ((h - 2) >= min_value && (h - 2) < horiz_dim && (gameboard[(horiz_dim * v + (h - 1))].isEmpty() == false) && gameboard[(horiz_dim * v + (h - 2))].isEmpty() == true)
		{
			return true;
		}
		else if ((v + 2) >= min_value && (v + 2) < horiz_dim && (gameboard[(horiz_dim * (v + 1) + h)].isEmpty() == false) && gameboard[(horiz_dim * (v + 2) + h)].isEmpty() == true)
		{
			return true;
		}
		else if ((v - 2) >= min_value && (v - 2) < horiz_dim && gameboard[(horiz_dim * (v - 1) + h)].isEmpty() == false && gameboard[(horiz_dim * (v - 2) + h)].isEmpty() == true)
		{
			return true;
		}
		else if ((h + 2) >= min_value && (h + 2) <= horiz_dim && (v + 2) >= min_value && (v + 2) < horiz_dim && gameboard[(horiz_dim * (v + 1) + (h + 1))].isEmpty() == false && gameboard[(horiz_dim * (v + 2) + (h + 2))].isEmpty() == true)
		{
			return true;
		}
		else if ((h + 2) >= min_value && (h + 2) <= horiz_dim && (v - 2) >= min_value && (v - 2) < horiz_dim && gameboard[(horiz_dim * (v - 1) + (h + 1))].isEmpty() == false && gameboard[(horiz_dim * (v - 2) + (h + 2))].isEmpty() == true)
		{
			return true;
		}
		else if ((h - 2) >= min_value && (h - 2) <= horiz_dim && (v + 2) >= min_value && (v + 2) < horiz_dim && gameboard[(horiz_dim * (v + 1) + (h - 1))].isEmpty() == false && gameboard[(horiz_dim * (v + 2) + (h - 2))].isEmpty() == true)
		{
			return true;
		}
		else if ((h - 2) >= min_value && (h - 2) <= horiz_dim && (v - 2) >= min_value && (v - 2) < horiz_dim && gameboard[(horiz_dim * (v - 1) + (h - 1))].isEmpty() == false && gameboard[(horiz_dim * (v - 2) + (h - 2))].isEmpty() == true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		return false;
	}
}

bool NineAlmondsGame::stalemate()
{
	//Go through the board, see if we still have any empty pieces. 
	//If there are any empty pieces, return false. 
	//If there are no empty pieces, and the game is not done, return true. 
	if (done() == false)
	{
		for (int i = 0; i < horiz_dim * vert_dim; i++)
		{
			int h = i % horiz_dim;
			int v = (i - h) / horiz_dim;
			if (isValidMove(h, v) == true)
			{
				return false;
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

// helper method 1 for turn method
void NineAlmondsGame::move_action_1(unsigned int &h1, unsigned int &v1, unsigned int &h2, unsigned int &v2, bool &atLeastOneMove)
{
	atLeastOneMove = true;
	int h1_to_right = h1 + 1;
	gameboard[v2 * horiz_dim + h2] = gameboard[v1 * horiz_dim + h1]; // move the piece to the spot that is 2 away 
	gameboard[v1 * horiz_dim + h1].toEmpty(); // make the spot that was jumped over empty
}

//helper method 2 for turn method
int NineAlmondsGame::move_action_2(unsigned int &h1, unsigned int &v1, unsigned int &h2, unsigned int &v2, string &move, string &moves, bool &continued_turn)
{
	// print the board
	operator<<(cout, *this);
	cout << endl;

	// keep track of the moves that have been made in the turn
	move = " to " + to_string(h2) + "," + to_string(v2); 
	moves = moves + move; 
	cout << moves << endl;
	string ans = "";
	if (done() == false)
	{
		if (stalemate() == false)
		{
			while (ans != "N" && ans != "n" && ans != "Y" && ans != "y")
			{
				cout << "Continue this turn (Yy/Nn)? ";
				getline(cin, ans);
				if (ans == "N" || ans == "n")
				{
					return succ_turn;
				}
				else if (ans == "Y" || ans == "y")
				{
					continued_turn = true;
					h1 = h2; //set the horizontal coord of the end piece as the horizontal coord of the start piece
					v1 = v2; // repeat for vertical coord
					cout << "You chose to continue your turn. Now enter coordinates to move to..." << endl;
					int call = prompt(h2, v2);
					if (call == quit)
					{
						return quit_after_continue;
					}
					else
					{
						return next_turn;
					}
				}
			}
		}
		else
		{
			return failure_stalemate;
		}
	}
	
	return success;
}



int NineAlmondsGame::turn()
{
	unsigned int h1, v1;
	unsigned int h2, v2;
	bool atLeastOneMove = false;
	while (atLeastOneMove == false) // make sure each turn has at least one valid move
	{
		print();
		int call1 = prompt(h1, v1); // prompt for start coordinate
		bool continued_turn = false;
		if (call1 == quit)
		{
			return quit;
		}
		else if (call1 == succ_extract_coord)
		{
			cout << "first coordinate input successful, enter place to move to" << endl;
			int call2 = prompt(h2, v2); // prompt for end coordinate
			if (call2 == quit)
			{
				return quit;
			}
			else if (call2 == succ_extract_coord)
			{
				string beg = to_string(h1) + "," + to_string(v1); // initialize string to keep track of moves for this turn
				string move;
				string moves = beg;
				while (done() == false)
				{
					if (stalemate() == false)
					{
						if (gameboard[v1*horiz_dim + h1].isEmpty() == false)
						{
							if (isValidMove(h1, v1))
							{
								if (gameboard[v2*horiz_dim + h2].isEmpty() == true)
								{
									// if the end coordinate is to the right of the start coordinate
									if ((h2 - h1) == 2 && v2 == v1 && gameboard[v1*horiz_dim + (h1 + 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove); 
										gameboard[(v1)* horiz_dim + (h1 + 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is to the left of the start coordinate
									else if ((h2 - h1) == -2 && v2 == v1 && gameboard[v1*horiz_dim + (h1 - 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1)* horiz_dim + (h1 - 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is above the start coordinate
									else if ((h2 == h1) && (v2 - v1) == 2 && gameboard[(v1 + 1)*horiz_dim + (h1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 + 1)* horiz_dim + (h1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is below the start coordinate
									else if ((h2 == h1) && (v2 - v1) == -2 && gameboard[(v1 - 1)*horiz_dim + (h1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 - 1)* horiz_dim + (h1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is diagonal (right and up) of the start coordinate
									else if ((h2 - h1) == 2 && (v2 - v1) == 2 && gameboard[(v1 + 1)*horiz_dim + (h1 + 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 + 1)* horiz_dim + (h1 + 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is diagonal (right and down) of the start coordinate
									else if ((h2 - h1) == 2 && (v2 - v1) == -2 && gameboard[(v1 - 1)*horiz_dim + (h1 + 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 - 1)* horiz_dim + (h1 + 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is diagonal (left and up) of the start coordinate
									else if ((h2 - h1) == -2 && (v2 - v1) == 2 && gameboard[(v1 + 1)*horiz_dim + (h1 - 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 + 1)* horiz_dim + (h1 - 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}

									// if the end coordinate is diagonal (left and down) of the start coordinate
									else if ((h2 - h1) == -2 && (v2 - v1) == -2 && gameboard[(v1 - 1)*horiz_dim + (h1 - 1)].isEmpty() == false)
									{
										move_action_1(h1, v1, h2, v2, atLeastOneMove);
										gameboard[(v1 - 1)* horiz_dim + (h1 - 1)].toEmpty();
										int call3 = move_action_2(h1, v1, h2, v2, move, moves, continued_turn);
										if (call3 == next_turn)
										{
											continue;
										}
										else
										{
											return call3;
										}
									}
									else
									{
										cout << "oops invalid end point for the given beginning point! try again" << endl;
										if (continued_turn == false) // if its not a continued turn, reprompt for both coords
										{
											break;
										}
										else
										{
											if (prompt(h2, v2) == quit) // if it is, reprompt for the end coord only
											{
												return quit;
											}
										}
									}
								}
								else
								{
									cout << "there already exists an almond at your end point! try again" << endl;
									if (continued_turn == false)
									{
										break;
									}
									else
									{
										if (prompt(h2, v2) == quit)
										{
											return quit;
										}
									}
								}
							}
							else
							{
								cout << "no valid moves for this beginning point! try again" << endl;
								if (continued_turn == false)
								{
									break;
								}
								else
								{
									if (prompt(h2, v2) == quit)
									{
										return quit;
									}
								}
							}
						}
						else
						{
							cout << "there is no almond at your beginning point! try again" << endl;
							if (continued_turn == false)
							{
								break;
							}
							else
							{
								if (prompt(h2, v2) == quit)
								{
									return quit;
								}
							}
						}
					}
					else
					{
						return failure_stalemate;
					}
				}
			}
		}
	}
	return success;
}

void NineAlmondsGame::print()
{
	operator<<(cout, *this);
}


