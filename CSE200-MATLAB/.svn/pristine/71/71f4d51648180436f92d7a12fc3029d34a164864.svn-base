function gameMenu()
%Function has no inputs or outputs but pulls up a menu containing the games
%made in studio 6 and homework 5
%Inputs:
%none
%Outputs:
%none
finished=0;

%choosing the game the user wants
while finished==0
    disp('Welcome to the game menu!');
    disp('1.) Guessing game');
    disp('2.) Rock, Paper, Scissors');
    disp('3.) Hangman');
    disp('4.) Exit');
    choice= input('Please make a selection(1-4): ');
    if choice == 1
        guessingGame();
    elseif choice == 2
        rockPaperScissors();
    elseif choice == 3
        hangman();
    elseif choice == 4
        finished=1;
        disp('Goodbye!');
    else 
        disp('Selection not found, try again.')
    end
end

end