function [r, theta]= cartToPol(x, y)

%This function will change the cartesian coordinates [x] and [y] 
%to their respective polar coordinates [r] and [theta].
%INPUTS:
% x-- scalar input
% y-- scalar input
%OUTPUT: 
% r-- scalar output as a function of x and y 
% theta-- scalar output as a functio of x and y

r= sqrt((x^2) + y^2);
theta= atan2(y, x);
