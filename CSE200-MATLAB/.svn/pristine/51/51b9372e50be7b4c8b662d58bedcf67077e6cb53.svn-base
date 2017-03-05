% Name: Nikki Wines
% Wustlkey: catherinewines

% Assignment: Homework 7
% Due Date: Nov 9th, 2015


%The answers to all problems should be placed in this file. If you are 
%asked to write a function, put the commands testing your function in this 
%file. Use comments to describe each Problem in one or two sentences. 
%Document your code!
%
%Do not forget to commit your work once you are finished!
addpath(genpath('.'))
%-------------------------------------------------------------------------
%Problem 1 %This probelm involves taking in 2 polynomial and adding them
%together.


polyadd([5 2 3], [4 9])

polyadd([1 -7 11 -4 -5 -2], [9 -10 6])

%Problem 2 %This problem involves taking in 2 polynomials and multiplying
%them together. 

polymult([5 2 3], [4 9])

polymult([1 -7 11 -4 -5 -2], [9 -10 6])

%Problem 3 %Takes in vectors as data sets and attempts to fix an 
%exponential curve to the data. 

p = exponentialFit([0:0.5:5], [6 4.83 3.7 3.15 2.41 1.83 1.49 1.21 0.96 0.73 0.64])

%Problem 4 %Takes in a vector and returns the a vector with the positions
%of the local maxima and a vector with the positions of the local minima

[max, min] = criticalPoints([-3 2 7])

[max, min] = criticalPoints([1 3 -11 -24 3 7])

[max, min] = criticalPoints([3 0 0 0]) 

%Problem 5 %Takes in a scalar balue and returns the values that would
%use the least amount of material to create a cup with the scalar volume

[r, h] = paperCup(250)

[r, h] = paperCup(100)

%Problem 6 %Takes in mass and height and ouputs the potential energy

P = potentialEnergy(25,100)

P = potentialEnergy(100, 750)

%Problem 7 %This problem involves finding the maximum and minimum of a
%function
clear
figure();
x=0:0.1:5; %spacing and bounds
y=(x-2)./((((x-2).^4)+2).^1.8); %equation from web page
plot(x,y); %plot data pts. 
minI=find(min(y)==y); %calc min
p7min=y(minI) %returns min index for y
maxI=find(max(y)==y); %calc max
p7max=y(maxI) %returns max index for y

%-------------------------------------------------------------------------
%Problem 8 %This problem involves fitting a polynomial and tehn a curve
%through 2 vectors
%The following vectors are to be used with problem 8:
clear
figure()
hold on

p8x = [-5 -3.4 -2 -0.8 0 1.2 2.5 4 5 7 8.5];
p8y = [4.4 4.5 4 2.6 2.9 3.8 3.5 2.5 1.2 0.5 -0.2];

plot(p8x,p8y,'p'); %plot data points
p8poly=polyfit(p8x,p8y,10) % fit polynomial
x2=-5:0.1:8.5; %new spacing
y2=polyval(p8poly,x2); %evaluate new polynomial
plot(x2,y2) %plot curve on top of data pts.
xlabel('Data for X values'); %labels
ylabel('Data for Y values');
title('Fitted Curve for X and Y values');

%-------------------------------------------------------------------------
%Problem 9 %This problem involes finding the distance traveled by a vehicle
%over time using the vectors provided
%The following vectors are to be used with problem 9:
clear
format short; % formatting for problem
p9t = 0:7;
p9v = [0 14 39 69 95 114 129 139];
p9distance=mean(p9v)*max(p9t) % mean velocity value * max time value

%Problem 10 %This problem involves solving equations involving a tumor
%growth equation. p10t, and p10A outputs are repressed because they are enormous
%column vectors. 

clear 
fun=@(t,A) (0.8*A*(1-(A/0.60)^0.25)); %create an anonymous fxn for formatting for ode45
[p10t,p10A]= ode45(fun,0:0.1:30,1); %spacing is 0 to 30 days spaced by 0.1