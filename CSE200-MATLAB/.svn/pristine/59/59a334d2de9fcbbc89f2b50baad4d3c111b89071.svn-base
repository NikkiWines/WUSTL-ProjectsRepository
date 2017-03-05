function z= rockPaperScissors()
%Function has no inputs and outputs the number of wins, losses, and ties
%that the user had.
%Inputs:
%none
%Outputs:
%z-- vector ouput
rounds= input('How many games would you like to play?: '); % user input for number of games
% users choice for game
userWins=0; % win counter
userLoses=0; %lose counter
tied= 0;  % tie counter

% this transfers the user's choice into a numeric value to be compared to
% the computer's random output.


for index = 1:rounds
    choice= input('Rock, paper, or scissors?: ', 's');
    
    while((~strcmpi(choice, 'rock') && ~strcmpi(choice,'paper') && ~strcmpi(choice, 'scissors')))
        choice= input('Error: Rock, paper, or scissors?: ', 's');
        
    end
    
    if strcmpi(choice,'rock')
        user=0;
    elseif strcmpi(choice,'paper')
        user=1;
    elseif strcmpi(choice,'scissors')
        user=2;
    end
    
    playGame= int8(2*rand()); %rock = 0, paper = 1, scissors = 2
    if playGame==0
        disp('The computer played: rock');
    elseif playGame==1
        disp('The computer played: paper');
    else
        disp('The computer played: scissors');
    end
    if (playGame==0 & user==2) | (playGame==1 & user==0) | (playGame==2 & user==1)
        userLoses=userLoses+1;
        disp('Computer wins!')
        
    elseif (playGame==0 & user==1) | (playGame==1 & user==2) | (playGame==2 & user==0)
        userWins=userWins+1;
        disp('Player wins!')
    else
        tied=tied+1;
        disp('Its a tie!')
    end
end

disp(['The player won ', num2str(userWins), ' time(s), the computer won ', num2str(userLoses),' time(s), and there were ', num2str(tied), ' tie(s).'])
z=[userWins, userLoses, tied];

end
