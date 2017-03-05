function P= potentialEnergy(m, y)
%Function takes in mass of a satellite (m) and the height from the surface
%of the earth (y) and computes the potential energy P
%Inputs:
% m-- scalar input value (mass)
% y-- scalar input value (height) 
%Outputs:
% P-- scalar output value (potential energy) as a function of both m and y


fun= @(y) m.*(((6371000.^2).*9.81)./(6371000+y).^2); %anonymous func containing values and equation from webpage
P=integral(fun,0,y); %integral of anonymous function from 0 to y (height)

end