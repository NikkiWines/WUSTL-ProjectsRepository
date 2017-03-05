function [x, y] = myMin(v)

%This function takes in vector v and outputs the smallest value of the
%vector and the index of the first occurence of that vector. 
%Inputs: 
% v- vector input of any length 
%Outputs: 
%x- scalar input that is the smalled value of vector v 
%y- scalar input that is the index of the smallest value 

% for an empty vector 
if numel(v)==0; 
    x=[];
    y=[];
    return;
end 

% for a non epty vector
min= v(1); 
minIndex= 1; 

for i = 1:numel(v) % go through each value of v one by one 
    if min > v(i) 
        min = v(i); 
        minIndex= i; 
        
    end
end 

x= min;
y= minIndex;



