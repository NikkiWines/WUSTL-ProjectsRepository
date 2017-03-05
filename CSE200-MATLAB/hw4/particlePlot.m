function [x y z]= particlePlot(t)
%Function takes in vector t and returns the positions of the particle
%(x y and z) with respect to time. 
%Input: 
%t-- (time) vector value input 
%Output: 
% x-- (position) vector value output as a function of t 
% y-- (position) vector value output as a function of t 
% z-- (position) vector value output as a function of t 

x=(4-(0.1.*t)).*sin(0.8.*t);
y=(4-(0.1.*t)).*cos(0.8.*t);
z=0.4.*t.^(3/2);

plot3(x,y,z);
grid on;
title('Particle position over time');
xlabel('(4-0.1t)sin(0.8t)');
ylabel('(4-0.1t)cos(0.8t)');
zlabel('0.4t^(3/2)');


