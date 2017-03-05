function x=popGrowth(N0, Ninf, r, t)
%Function takes in scalars N0 and r, and vectors Ninf and t and computes the
%matrix N(t) which shows the population growth over time as a function of
%time and max population 
%Inputs: 
% N0--(initial population size) scalar value input 
% Ninf-- (maximum population size possible) vector value input
% r-- (rate constant) scalar value input
% t-- (time) vector value input 
%Output: 
% x-- (number of individuals in a population) matrix value output as a
% function of N0, Ninf, r, and t.
[X Y] = meshgrid(t, Ninf);
x = Y./(1+((Y./N0)-1).*exp(1).^(-r.*X));

mesh(X,Y,x);
title('Number of individuals in a population after t years with varying max populations');
xlabel('Time (years)');
ylabel('Max population');
zlabel('NUmber of individuals in a population');

