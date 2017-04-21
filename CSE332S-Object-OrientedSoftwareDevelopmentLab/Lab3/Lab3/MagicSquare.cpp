//name: MagicSquare.cpp
//purpose: Defines functions for MagicSquare 

#include "stdafx.h"
#include "functions.h"
#include "MagicSquare.h"
#include <algorithm>
#include <string>
#include <iomanip>

//Iterator stuff
#include <iterator> 
#include <iostream>

using namespace std; 
MagicSquareGame::MagicSquareGame(int board_size, int starting_piece)
	:GameBase(board_size, board_size)
{
	gamepiece empty;
	empty.toEmpty();
	for (int i = 0; i < board_size * board_size; ++i)
	{
		gameboard.push_back(empty);
		available_pieces.push_back(starting_piece + i);

		if (to_string(starting_piece + i).length() > piece_length) {
			piece_length = to_string(starting_piece + i).length();
		}
	}
	if (piece_length == 1) { piece_length += 1; } // default set board display to equal widths
	int bottomleft = 0;
}


void MagicSquareGame::prompt(int & a)
{
	string s1; // input string 
	int piece; // piece input val 
	cout << "give me a int value from the available pieces or type 'quit': ";
	getline(cin, s1);
	if (s1 == "quit") {
		throw (int) quit;
	}
	else {
		istringstream iss(s1);
		if (iss >> piece) {
			if (piece >= available_pieces[0] && piece <= available_pieces.back()) { // is one of the available pieces
				a = piece;
			}
		}
	}
}

ostream &operator<< (ostream &cout, const MagicSquareGame &game) {
	if ((game.horiz_dim)*(game.vert_dim) == game.gameboard.size())
	{
		// print out the gameboard pieces
		for (int i= game.vert_dim - 1; i >= 0; i--)
		{
			cout << i;
		
			for (int j = 0; j < game.horiz_dim; j++)
			{
				cout << setw(game.piece_length) << game.gameboard[game.horiz_dim*i + j].d;
			}
			cout << endl;
			cout << endl;
		}
		// print out the gameboard labels
		cout << "X";
		for (int i = 0; i < game.horiz_dim; i++) {
			cout << setw(game.piece_length) << i;
		}
		cout << endl;
	}
	else
	{
		cout << "vector size mismatch" << endl;
	}
	// print out the available pieces
	cout << "Available pieces : ";
	ostream_iterator<int> out_it(std::cout, " ");
	copy(game.available_pieces.begin(), game.available_pieces.end(), out_it);
	cout << endl;
	return cout;
}
//Part 22, override the base class print() method and define it simply inserting *this into an ostream 
void MagicSquareGame::print() {
	operator<<(cout, *this);
}


bool MagicSquareGame::done()
{
	for (int i = 0; i < (horiz_dim)*(vert_dim); i++)
	{
		if (gameboard[i].isEmpty() == true)
		{
			return false;
		}
	}

	if (horiz_dim == 1 && !gameboard[0].isEmpty()) { // default: any correct entry in a 1x1 square results in success
		return true;
	}

	// sums for rows
	vector<int> sums;
	int sum_of_row = 0;
	int sum_of_col = 0;
	int sum_of_diag = 0;
	for (int j = 0; j < horiz_dim*vert_dim; j += horiz_dim) { // not done with checking to make sure sums are different
		for (int i = j; i < j + horiz_dim; i++) {
			sum_of_row += stoi(gameboard[i].d);
		}
		sums.push_back(sum_of_row);
		sum_of_row = 0;
	}

	// sums for cols
	for (int j = 0; j < horiz_dim; j ++) { // not done with checking to make sure sums are different
		for (int i = j; i < horiz_dim*(vert_dim); i += horiz_dim) {
			sum_of_col += stoi(gameboard[i].d);
		}
		sums.push_back(sum_of_col);
		sum_of_col = 0;
	}
	// sums for diags
	for (int i = 0; i < horiz_dim*vert_dim; i = i + horiz_dim + 1) {
		sum_of_diag += stoi(gameboard[i].d);
	}
	sums.push_back(sum_of_diag);
	
	sum_of_diag = 0;
	for (unsigned int i = horiz_dim-1; i <= gameboard.size() - horiz_dim; i = i + horiz_dim - 1) {
		sum_of_diag += stoi(gameboard[i].d);
	}
	sums.push_back(sum_of_diag);

	// check to see if the sums in the vector are all the same
	int comp_val = sums[0];
	bool isDone = true;
	for (unsigned int i = 0; i < sums.size(); i++) {
		if (sums[i] != comp_val) {
			isDone = false; // if any value is not equal , set to false
		}
	}
	return isDone;
}

bool MagicSquareGame::stalemate()
{
	//Go through the board, see if we still have any empty pieces. 
	//If there are any empty pieces, return false. 
	//If there are no empty pieces, and the game is not done, return true. 

	for (gamepiece p : gameboard)
	{
		if (p.isEmpty())
		{
			return false;
		}
	}
	if (done()) {
		return false; 
	} else {
		return true;
	}
}


//This method repeatedly calls the overloaded prompt() method to obtain coordinates for an empty square on the board
// and the value of an available piece to play on that square. 
int MagicSquareGame::turn()
{
	bool finished = false;
	bool moveChoiceMade = false;
	string pieceSelectedString;
	int pieceSelected;
	//Available piece on board
	unsigned int availablePiece = 0; 
	//Coodinates for empty square
	unsigned int xCoord = 0; 
	unsigned int yCoord = 0; 
		
	xCoord = 0; 
	yCoord = 0;
	int call;
	while (true) {
		if (!done()) {
			if (!stalemate()) {
				print();
				call = GameBase::prompt(xCoord, yCoord); 
				//Check if the first coordinate was extracted successfully before trying to subsequent coordinates. 
				while (call != succ_extract_coord && call != quit) {
					call = GameBase::prompt(xCoord, yCoord);
				}
				if (call == quit) {
					return quit;
				}
				//pieceSelected = 0; // reset pieceSelected
				try {
					prompt(pieceSelected);

				}
				catch (int i){
					i = quit;
					return i;
				}
				// put prompt in try and catch from there. 
				cout << endl;
				if (gameboard[yCoord*horiz_dim + xCoord].isEmpty()) { // if we can place a value in that location			
					if (find(available_pieces.begin(), available_pieces.end(), pieceSelected) != available_pieces.end()) { // check to see if available pieces contains the selected number. 
						//available pieces contains piece selected -- we can remove it
						available_pieces.erase(remove(available_pieces.begin(), available_pieces.end(), pieceSelected), available_pieces.end()); // remove available pieces from array 
						//convert input to string
						stringstream ss;
						ss << pieceSelected;
						string str = ss.str();
						gameboard[yCoord*horiz_dim + xCoord].d = str;
					}
					else {
						// available pieces does NOT contain piece selected 
						cout << "Sorry, that's not an available piece" << endl;
						return failure_invalid_move;
					}
				}
				else {
					cout << "Sorry, that's not an available coordinate" << endl;
					return failure_invalid_move;
				}
			}
			else {
				print();
				return failure_stalemate;
			}
		}
		else {
			print();
			return success;
		}
	}
}