#include "stdafx.h"

#include "Gameboard.h" 
#include "Functions.h"
#include "Gamepiece.h"

int read_board_dimensions(ifstream & ifs, unsigned int & width, unsigned int & height) {
	string  word;
		if (getline(ifs, word)) { // check if you can get line
			if (istringstream(word) >> width >> height ) { // check if you can extract word 1 and 2 // use x and y or width and height	
					return success;
			}
			else {
				cout << "Could not extract value " << endl;
				return failureExtraction;
			}
		}
		else {
			cout << "Could not get line" << endl;
			return failureGetLine;
		}
}

int read_game_pieces(ifstream & ifs, vector<game_piece>& pieces_vector, unsigned int width, unsigned int height)
{
	string  word; // word from file
	string color;
	string name;
	string display;
	unsigned int x;
	unsigned int y;
	bool All = true;
	while (getline(ifs, word)) { // check if you can get line
		istringstream iss(word);
		if (iss >> color >> name >> display >> x >> y) {
			piece_color pc = string_toColorEnum(color); // check string to enum color func  game_piece_color
			// check gamepiece x and y against board_dimensions height and width
			if ((x < height) && (y < width) && (pc != piece_color::invalid)) { // values are within params and piece color is not invalid
			 //create game piece;
				game_piece piece;
				//set vars
				piece.color = pc;
				piece.display_type = display;
				piece.name = name;
				unsigned int index = (width*x) + y;
				//push piece onto vector at location index.
				pieces_vector[index] = piece;
				//return success;
				All = false; 
			}
			// if not- just skip dont error out.
		}
	}
	if (All == true){ // we were unable to extract all 
		cout << "Could not extract value" << endl;
			return failureExtraction;
		}
		else { // able to extract at least 1
			return success;
		}

}



int print_game(const vector<game_piece>& pieces, unsigned int width, unsigned int height) // x and y vals 
{
	for ( int j = height - 1; j >= 0; j--){
		for ( int i = 0; i < width; i++) 
			{
				cout << pieces[width*i + j].display_type; // accessing individual pieces 
			}
		cout << endl;
	}
	return success;
}

