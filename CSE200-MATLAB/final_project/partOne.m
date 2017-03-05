%% CSE200 Final Project
% Part I of the project is to be completed in the template below.

%% Group Information
% Number in Group: 1
%
% Member 1 (use THIS repository):
% Name: Kai Banks
% WUSTL key: kai.banks
%
% Member 2:
% Name: Nikki Wines
% WUSTL key: catherinewines
%
%
% Project Choice: Mechanical Engineering

%% Overview:
% Please provide a brief description of both the intended project timeline
% and division of work.

% We will aim to submit our completed mechanical engineering model by
% Monday, November 30th. The work will be divided as follows: 
% Kai: First two required functions + end-game troubleshooting
% Nikki: Third required function and implementation


%% Project Specific Material:
% Complete any Part I questions from the project instructions below.

% set: This function is used to set the value of a particular property of a
% graphical object. It will be useful because it allows us to change the
% color of any of our UI elements at will. 

% drawnow: This function causes the graphical interface to re-draw itself,
% which is useful because it'll let us show the path of the projectile in
% real-time. It's a "refresh" function for whatever is displayed
% graphically.

% nargin: This syntax can be used in a "switch" block to figure out how
% many elements were passed as inputs to the function. This will be very
% useful for us because a lot of our project involves optional data;
% instead of writing a function for each possible combination of inputs, we
% can condense those functions into one that checks to see how many/which
% parameters were inputted. 

% exist: This function checks to see whether a variable exists in the
% workspace or is of a particular type. It will be useful for
% bulletproofing our code because it will allow us to make sure that the
% variables we are manipulating actually exist so that errors do not occur.

% pause: This function stops all Matlab processes until the user presses a
% key. This will be useful for our project because it will allow us to stop
% the drawing animation at any time and resume it at will.

% sprintf: This function formats data into strings. It will allow us to
% display all the data about the simulation as it occurs on-screen to the
% correct degree of accuracy/sig-figs. 