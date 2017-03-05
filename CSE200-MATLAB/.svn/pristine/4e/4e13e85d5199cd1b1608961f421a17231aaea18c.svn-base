% Name: Nikki Wines
% Wustlkey: catherinewines

% Assignment: Homework 4
% Due Date: October 5th, 2015


%The answers to all problems should be placed in this file. If you are 
%asked to write a function, put the commands testing your function in this 
%file. Use comments to describe each Problem in one or two sentences. 
%Document your code!
%
%Do not forget to commit your work once you are finished!
addpath(genpath('./hws/hw4'));
%-------------------------------------------------------------------------
%Problem 1: This problem involves taking in 2 values and computing the
%p-series of those values as a vector 

x = harmonicSum(7,1)
x = harmonicSum(17,2)

%Problem 2: This problem takes in a vector t for time, and outputs the
%position, velocity, and acceleration with respect to time. 

[x v a] = straightParticle(1:0.5:10)

%Problem 3: This problem takes in 3 scalar values (initial balance, growth 
%rate and time) and outputs the vector value of compound continuous 
%interest for 0 to time 

P = computeInterest(2000,0.2,20)

P = computeInterest(10000,0.1,40)

%normal plot is best for both because it shows how the interest increases
%over time, and is the most to scale.

%Problem 4: This problem takes in a vector for time, and returns the
%positions of the particle as vectors x, y, and z.

[x y z] = particlePlot(0:0.5:10)


%Problem 5: This probelm takes in a tmperature (T) and a windspeed (v) and
%computes the resulting windchill as a vector and a plot 

w = windchill(0:100, 0:100);

w = windchill(0:50, 50:100);

%Problem 6: This problem involves taking in 2 scalars (initial population
%(N0) and the rate constant (r)) and 2 vectors (maximum population size
%(Ninf) and time (t)) and computing a matrix of them that shows the population growth 
%over time. 

x = popGrowth(10, 100:1000, 0.1, 0:100);

x = popGrowth(5, 1000:10000, 0.2, 0:100);

%Problem 7: This problem takes in 2 vectors (length and height) and
%computes the width (y) from those inputs and then creates a 3-D plot of
%the values. 

y = shipHull(-2:0.5:2, -0.25:0.05:0)


% testing problems from practice exam:
% 
%   x = 1:.1:20;
%   
%   y=@(x) x.^2 +2;
%   z=@(x)(x+5).^(1/2);
% %    
%   subplot(2,1,1)
%   fplot(y,[1:20])
% % 
%   subplot(2,1,2)
%   fplot(z,[1:20])


%  x=[-1,2,3,-2];
%  y=[0.2,3.1,0,-3];
%  z=[3,0,1,0.1];
% % 
%  x+~y>z
% 
% nums=rand(5)

% x=[0:2:8; 11 -2 -14 15 16; -3:-4:-19]
% 
% z=[-5:-3:-17;x(3,:)]
