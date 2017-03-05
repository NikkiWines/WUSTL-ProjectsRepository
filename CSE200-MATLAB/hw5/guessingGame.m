function guesses= guessingGame()
%Function takes in no inputs but outputs the number of guesses it takes
%for the user to guess the corr ect value
%Inputs:
% none
%Outputs:
% guesses- scalar output value of the number of guesses it takes to be
% correct.

value= randi([1 10],1,1); % creates a random value inclusive of 1 and 10
numberOfGuesses=1; % guessing counter.
initialGuess= input('Please guess a number between 1 and 10: '); %asks the user for a value between 1 and 10


% if the initial guess doesnt equal the value, add the number of guesses to
% the number of incorrect guesses
if initialGuess ~= value
    numberOfGuesses= numberOfGuesses + incorrectGuesses(value);
end

% returns a statement about how many guesses you took
if numberOfGuesses == 1
disp(['It took you ', num2str(numberOfGuesses), ' guess!']);
else 
    disp(['It took you ', num2str(numberOfGuesses), ' guesses!']); 
guesses= numberOfGuesses;
end 
end 

    function g= incorrectGuesses(value)
    % Function takes in the value that the user guesses and returns whether or
    % not it is correct
    % the user guesses a random value between 1 and 10.
    %Inputs:
    % value-- scalar input between 1 and 10
    %Outputs:
    % g: scalar ouput value of 1- the number guesses it takes the user to guess the value

    valueGuessed= input('Incorrect! Please guess a number between 1 and 10: '); % asks the user to input a value between 1 and 10 after they are incorrect

    % if the value guessed is equal to the value return 1, else add one to
    % the number of guesses. 
    if valueGuessed== value
        g= 1;
    else

        g= 1+incorrectGuesses(value);
    end
        
        
    end
