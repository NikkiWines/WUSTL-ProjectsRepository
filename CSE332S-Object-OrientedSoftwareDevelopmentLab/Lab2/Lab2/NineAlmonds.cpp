#include "stdafx.h"
#include "NineAlmonds.h"
#include "Functions.h"

int numTurns = 1;

NineAlmondsGame::NineAlmondsGame()
{
}

NineAlmondsGame::NineAlmondsGame(game_piece almonds_, vector<game_piece> game_board_, int width_, int height_)
{
	almonds = almonds_;
	game_board = game_board_;
	width = width_;
	height = height_;
		
}
// setters 

void NineAlmondsGame::set_game_board(vector<game_piece> v) {
	game_board = v;
}

//getters 

game_piece NineAlmondsGame::get_game_piece() {
	return almonds;
}

vector<game_piece> NineAlmondsGame::get_game_board() {
	return game_board;
}

int NineAlmondsGame::get_width() {
	return width;
}

int NineAlmondsGame::get_height() {
	return width;
}
bool NineAlmondsGame::done()
{
	bool isEmpty = true;
	for (int j = height - 1; j >= 0; j--) {
		for (int i = 0; i < width; i++) {
			if ((j == 2) && (i == 2)) {
				if (game_board[width*j + i].display_type == " A") { // exception case -- we're looking at coordinate 2,2, shouldn't be empty
					isEmpty = true;
				}
				else { // it is empty but it shouldn't be!
					isEmpty = false;
				}
			}
			else {
				if (game_board[width*j + i].display_type != "  ") {// if we have an almond at this locations, change isEmpty to false.
					isEmpty = false;
				}
			}
		}
	}
	return isEmpty;
}

bool NineAlmondsGame::stalemate()
{
	bool isStalement = true;
	int almCount = 0;

	if (done() == true) { // check done
		isStalement = false;
	}
	for (int j = height - 1; j >= 0; j--) {
		for (int i = 0; i < width; i++) {
			if (game_board[width*j + i].display_type == " A") { // if no almonds except for 1 then stalemate
				almCount += 1;
			}
			if ((game_board[abs((int)(width*(j + 2) + i - 5))].display_type == " A") && (isValid(i, j, i, j - 2))) {
				isStalement = false;
			}
			else if ((game_board[abs((int)(width*(j -2) + i + 5))].display_type == " A") && (isValid(i, j, i, j - 2))) {  // if the jumped over location contains an almond
				isStalement = false;
			}
			else if ((game_board[abs((int)(width*j + (i + 2) - 1))].display_type == " A") && (isValid(i, j, i + 2, j))) {
				isStalement = false;
			}
			else if ((game_board[abs((int)(width*j + (i - 2) + 1))].display_type == " A") && (isValid(i, j, i - 2, j))) {
				isStalement = false;
			}

			else if ((game_board[abs((int)(width*(j + 2) + (i + 2) - 6))].display_type == " A") && (isValid(i, j, i + 2, j + 2))) { // up right diagonal 
				isStalement = false;

			}
			else if ((game_board[abs((int)(width*(j - 2) + (i - 2) + 6))].display_type == " A") && (isValid(i, j, i - 2, j - 2))) { // down left diagonal 
				isStalement = false;

			}
			else if ((game_board[abs((int)(width*(j + 2) + (i - 2) - 4))].display_type == " A") && (isValid(i, j, i - 2, j + 2))) { // up left diagonal 
				isStalement = false;

			}
			else if ((game_board[abs((int)(width*(j - 2) + (i + 2) - 4))].display_type == " A") && (isValid(i, j, i + 2, j - 2))) { // up left diagonal 
				isStalement = false;

			}
			else {

			}
		}
	}
	if (almCount == 1) {
		isStalement = true;
	}
	return isStalement;
}


bool NineAlmondsGame::isValid(int i1, int j1, int i2, int j2)
{
	bool isValid = false;
	if ((abs(i2 - i1) == 2) && (j1 == j2)) { // horizontal movement
		isValid = true;
	}
	if ((abs(j2 - j1) == 2) && (i1 == i2)) {  //vertical movement
		isValid = true;
	}
	if ((abs(i2 - i1) == 2) && (abs(j2 - j1) == 2)) { // diagonal movement 
		isValid = true;
	}
	return isValid;
}

int NineAlmondsGame::prompt(unsigned int &i, unsigned int &j)
{
	string input = "";
	cout << "Enter coordinates (ex. 0,0) or enter quit: ";
	cin >> input;
	if ((input.size() == 3) && (input.at(1) == ',') && (input.at(0) >= 48) && (input.at(0) < 53) && (input.at(2) >= 48) && (input.at(2) < 53)) {
		replace(input.begin(), input.end(), ',', ' ');
		istringstream iss(input);
		if (iss >> i >> j) {
			if ((i >= 0) && (i <= 4) && (j >= 0) && (j <= 4)) {
				return successExtract;
			}
		}
		else {
			return failureExtract;
		}
	}
	else if (input == "quit") {
		return quit;
	}
	else {
		cout << "Invalid Coordinate. Please re-enter a valid coordinate." << endl;
		prompt(i, j);
	}
}

int NineAlmondsGame::turn()
{	
	bool cont = true;
	vector<string> coords = {};
	bool midAlmond = false;
	int difference = 0;
	string input = "";
	unsigned int i1, j1;
	unsigned int i2, j2;
	unsigned int oi1 = 0;
	unsigned int oj1 = 0;

	//assign prompt return value
	// check return value
	int programcode = prompt(i1, j1);
	if (programcode == quit) {
		return quit;
	}
	while (!(game_board[width*j1 + i1].display_type == " A")) { // if the starting location doesn't have an almond
		cout << "Invalid 1st Coordinate. Please  re-enter a first coordinate." << endl;
		programcode = prompt(i1, j1);
		if (programcode == quit) {
			return quit;
		}
	}
	string coord = to_string(i1) + "," + to_string(j1);
	coords.push_back(coord);
	while (cont != false) {
		while (midAlmond == false) {
			programcode = prompt(i2, j2); // first coord is valid ,prompt for second
			if (programcode == quit) {
				return quit;
			}
			while ((!(isValid(i1, j1, i2, j2))) || (game_board[width*j2 + i2].display_type == " A") || ((oi1 == i2) && (oj1 == j2))) { // if the move from coordinate 1 - > 2 is not  valid  or if the destination coordinate contains an almond
				cout << "Invalid 2nd Coordinate. Please re-enter a second coordinate." << endl;
				programcode = prompt(i2, j2);
				if (programcode == quit) {
					return quit;
				}

			}
			// you've made a valid move that doesn't end on an almond... check for an almond inbetween the positions and adjust accordingly
			if ((i1 == i2) && ((game_board[abs((int)(width*j2 + i2) - 5)].display_type == " A") || (game_board[abs((int)(width*j2 + i2) + 5)].display_type == " A"))) {  // if the jumped over location contains an almond
				if (((int)j2 - (int)j1) > 0) { // vertical movement
					move_almond(i1, j1, i2, j2, "v+");
					midAlmond = true;
				}
				else {
					move_almond(i1, j1, i2, j2, "v-");
					midAlmond = true;
				}
			}
			else if ((j1 == j2) && ((game_board[abs((int)(width*j2 + i2) - 1)].display_type == " A") || (game_board[abs((int)(width*j2 + i2) + 1)].display_type == " A"))) {

				if (((int)i2 - (int)i1) > 0) { // horizontal movement
					move_almond(i1, j1, i2, j2, "h+");
					midAlmond = true;
				}
				else {
					move_almond(i1, j1, i2, j2, "h-");
					midAlmond = true;
				}
			}
			else if ((((int)i2 - (int)i1) > 0) && (((int)j2 - (int)j1) > 0) && (game_board[abs((int)(width*j2 + i2) - 6)].display_type == " A")) { // up right diagonal 
				move_almond(i1, j1, i2, j2, "d1+");
				midAlmond = true;
			}
			else if ((((int)i2 - (int)i1) < 0) && (((int)j2 - (int)j1) < 0) && (game_board[abs((int)(width*j2 + i2) + 6)].display_type == " A")) { // down left diagonal 
				move_almond(i1, j1, i2, j2, "d1-");
				midAlmond = true;
			}
			else if ((((int)i2 - (int)i1) < 0) && (((int)j2 - (int)j1) > 0) && (game_board[abs((int)(width*j2 + i2) - 4)].display_type == " A")) { // up left diagonal 
				move_almond(i1, j1, i2, j2, "d2+");
				midAlmond = true;
			}
			else if ((((int)i2 - (int)i1) > 0) && (((int)j2 - (int)j1) < 0) && (game_board[abs((int)(width*j2 + i2) + 4)].display_type == " A")) { // down right diagonal 
				move_almond(i1, j1, i2, j2, "d2-");
				midAlmond = true;
			}
			else {
				cout << "Did not jump over almond. Please re-enter a second coordinate." << endl;
				midAlmond = false;
			}
		}
		coord = to_string(i2) + "," + to_string(j2);
		coords.push_back(coord);
		cout << endl;
		for (unsigned int i = 0; i < coords.size()-1; i += 1) {
			cout << coords[i] << " to ";
		}
		cout << coords[coords.size() - 1] << endl;
		cout << endl;
		cout << "Continue this turn (YyNn)? ";
		cin >> input;
		while (!((input == "Y") || (input == "y") || (input == "N") || (input == "n"))) {
			cout << "Incorrect Input: Continue this turn(YyNn) ? ";
			cin >> input;
			cout << input;
		}
		if ((input == "N") || (input == "n")) {
			//cout << "You quit after " << numTurns << " turns." << endl;
			cont = false;
			numTurns++;
			return endTurn;
		}
		else if ((input == "Y") || (input == "y")) {
			midAlmond = false;
			oi1 = i1;
			oj1 = j1;
			i1 = i2;
			j1 = j2;
			cont = true;
		}
	}
}

void NineAlmondsGame::move_almond(int i1, int j1, int i2, int j2, string type)
{
	if (type == "v+") { // vertical moving up (eg. 2,2 -> 2,4
		game_board[width*j1 + i1].display_type = "  "; // remove almond from starting position
		game_board[width*j2 + i2].display_type = " A"; // add almond to new location
		game_board[(width*j1 + i1) + 5].display_type = "  "; // remove almond from jumped over location
	}
	if (type == "v-") { // vertical moving down (eg. 2,2 -> 2,0
		game_board[width*j1 + i1].display_type = "  "; 
		game_board[width*j2 + i2].display_type = " A"; 
		game_board[(width*j1 + i1) - 5].display_type = "  "; 
	}
	if (type == "h+") { //horizontal moving right (eg. 2,2 -> 4,2
		game_board[width*j1 + i1].display_type = "  "; 
		game_board[width*j2 + i2].display_type = " A"; 
		game_board[(width*j1 + i1) + 1].display_type = "  "; 
	}
	if (type == "h-") { //horizontal moving left (eg. 2,2 -> 0,2
		game_board[width*j1 + i1].display_type = "  ";
		game_board[width*j2 + i2].display_type = " A";
		game_board[(width*j1 + i1) - 1].display_type = "  ";
	}
	if (type == "d1+") { //diagonal moving right up (eg. 2,2 -> 4,4
		game_board[width*j1 + i1].display_type = "  ";
		game_board[width*j2 + i2].display_type = " A";
		game_board[(width*j1 + i1) + 6].display_type = "  ";
	}
	if (type == "d1-") { //diagonal moving left down (eg. 2,2 -> 0,0

		game_board[width*j1 + i1].display_type = "  ";
		game_board[width*j2 + i2].display_type = " A";
		game_board[(width*j1 + i1) - 6].display_type = "  ";
	}
	if (type == "d2+") { //diagonal moving right down (eg. 2,2 -> 4,0 
		game_board[width*j1 + i1].display_type = "  ";
		game_board[width*j2 + i2].display_type = " A";
		game_board[(width*j1 + i1) + 4].display_type = "  ";
	}
	if (type == "d2-") { //diagonal moving left up (eg. 2,2 -> 0,4
		game_board[width*j1 + i1].display_type = "  ";
		game_board[width*j2 + i2].display_type = " A";
		game_board[(width*j1 + i1) - 4].display_type = "  ";
	}
	operator<<(cout, *this); // this is a pointer to the gameboard
}

int NineAlmondsGame::play()
{
	operator<<(cout, *this); 
	while (!done() || !stalemate()) {
		int programcontrol = turn();
		bool isdone = done();
		bool isstlmt = stalemate();
		if (programcontrol == quit) {
			cout << "You quit after " << numTurns << " turns.";
			return quit;
		}
		else if (isdone) {
			cout << "Congrats! You took " << numTurns << " turns to complete the game!" << endl;
			return success;
		}
		else if (isstlmt) {
			cout << "No valid moves remaining :( You took " << numTurns << " turns to reach a stalemate." << endl;
			return stlmt;
		}
		else if (endTurn) {

		}
		else {
			return failure;
		}
	}
	
}



ostream & operator<<(ostream & out, const NineAlmondsGame & game_class)
{
	cout << endl;
	for (int j = game_class.height - 1; j >= 0; j--) {
		out << j;
		for (int i = 0; i < game_class.width; i++)
		{
			out << game_class.game_board[game_class.width * j + i].display_type;
		}
		out << endl;

	}
	cout << "X 0 1 2 3 4" << endl;
	
	return out;
}

vector<game_piece> game_setup(vector<game_piece>& pieces, unsigned int dimensions) // x and y vals 
{
	for (int j = 3; j >= 1; j--) {
		for (int i = 1; i <= 3; i++)
		{
			game_piece gp = game_piece("brown", "almond", " A");
			pieces[dimensions*j +i] = gp;
		}
	}
	return pieces;
}


