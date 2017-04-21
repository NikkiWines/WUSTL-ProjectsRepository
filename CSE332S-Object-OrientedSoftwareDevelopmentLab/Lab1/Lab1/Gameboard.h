//LAB 1 Gameboard.h
//author: Nikki Wines nikki.wines@wustl.edu	

#ifndef GAMEBOARD_H
#define GAMEBOARD_H

#include <iostream> 
#include <string>
#include <sstream>
#include <fstream>
#include <vector>
#include <cstring>
#include "Gamepiece.h" // get all sorts of errors if i dont include this. 
using namespace std;

int read_board_dimensions(ifstream & ifs, unsigned int & x, unsigned int & y);
int read_game_pieces(ifstream & ifs, vector<game_piece> & pieces_vector, unsigned int x, unsigned int y);
int print_game(const vector<game_piece> &, unsigned int x_loc, unsigned int y_loc);
#endif /* GAMEBOARD_H */

//read a board from a file
//read a piece from a file 
//print board grid 
// extra credit function


