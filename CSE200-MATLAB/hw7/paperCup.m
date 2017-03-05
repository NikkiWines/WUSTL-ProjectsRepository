function [r h]= paperCup(V)
%Function takes in value V (volume) and returns the radius and height that
%would use the least material to create a cone of said volume 
%Inputs: 
% V-- scalar input value (volume)
%Ouputs:
% r-- scalar output as a function of V (radius)
% h-- scalar output as a function of V (height) 

format long; 
r= ((3*V)^2/(2*pi^2))^(1/6); % gives the radius
h= (3*V)/ (pi*r^2); %gets height from radius 