function w= windchill(T,v)
%This fucntion takes in vectors t and v and computes the resulting
%windchill vector and plot from the inputs.
%Inputs: 
% T-- (temperature, degrees F) vector value input
% v-- (wind speed, mi/h) vector value input
%Output:
% w-- (windchill) vector value output as a function of T and v
[X Y] = meshgrid(T, v);
w= 35.74 + 0.6215.*X - 35.75.*(Y.^0.16) + 0.4275.*X.*(Y.^0.16);

surf(X,Y,w);
title('Windchill as a Factor of Wind Velocity and Temperature');
xlabel('Temperature');
ylabel('Velocity of wind');
zlabel('Windchill');