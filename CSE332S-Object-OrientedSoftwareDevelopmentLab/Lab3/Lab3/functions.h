// name: functions.h 
// purpose: declares usage function defined in functions.cpp, declares enums

#pragma once


#include "NineAlmondsGame.h"
#include "MagicSquare.h"
#include "GameBase.h"
#include <string>
#include <iostream>

using namespace std; 

int usage(char * input_file, string c);

enum return_values { quit = -1, success, succ_extract_coord, succ_turn,  quit_after_continue, failure_num_args, failure_invalid_move, failure_stalemate, failure_invalid_coord, failure_extract_h, failure_extract_v, failure_extract_i, failure_Yy_Nn, next_turn, failure_int_out_of_range, failure_mismatch };

enum usage_values { program_name, game_name , default_num_args, three_args, four_args};

enum argument_values { program, game, additional_arg_1, additional_arg_2};