// name: Lab3.cpp : 
// purpose: Defines the entry point for the console application. Uses the functions defined in the added files to play a game.


#include "stdafx.h"
#include "functions.h"
#include "NineAlmondsGame.h"
#include "MagicSquare.h"
#include "GameBase.h"
#include <iostream>
#include <memory>

int main(int argc, char * argv[])
{
	string nine_almonds_start = "NineAlmonds";
	string magic_square_start = "MagicSquare"; 
	
	//use shared pointer instead of ptr 
	shared_ptr<GameBase> start_check = GameBase::check(argc, argv);
	if (start_check == nullptr) {
		usage(argv[program_name], nine_almonds_start);
		usage(argv[program_name], magic_square_start);
	}
	else { // non null ptr
		return start_check->play();
		
	}

}


