% Name: Nikki Wines
% Wustlkey: catherinewines

% Assignment: Homework 8
% Due Date: Nov 16th, 2015


%The answers to all problems should be placed in this file. If you are 
%asked to write a function, put the commands testing your function in this 
%file. Use comments to describe each Problem in one or two sentences. 
%Document your code!
%
%Do not forget to commit your work once you are finished!

%-------------------------------------------------------------------------
%Problem 1: involves solving an equation using ode45 and comparing it
%against the simulink model 

addpath(genpath('.'));
 
%run simulink model for problem 1
model = 'Problem1.mdl';
load_system(model);
sim(model);

%solve equation using ode45
fun=@(x,y) (sqrt(x*y)-(0.5*y*(exp(-0.1*x)))); %create an anonymous fxn for formatting for ode45
xvsyode45= ode45(fun,0:0.5:4,6.5);

%plot results
subplot(2, 1, 1)
plot (xvsyode45.x,xvsyode45.y);
title('x vs. y - ode45');
xlabel('x');
ylabel('y');

subplot(2, 1, 2)
plot(y);
title('x vs. y - simulink')
xlabel('x');
ylabel('y');
 
 
