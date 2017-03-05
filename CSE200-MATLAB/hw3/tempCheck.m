function [n, i] = tempCheck(tempV, T) 

%This function takes in the vector tempV and the scalar value T and
%computes the the number of values greater than T; n, and the indices of
%these values 
%Inputs: 
% tempV- vector of a series of temperatures
% T- scalar value 
%Outputs: 
% n- scalar value as a function of tempV and T 
% i- scalar value as a function of tempV and T

[nValue, i]= find(tempV > T);
n= numel(nValue);

