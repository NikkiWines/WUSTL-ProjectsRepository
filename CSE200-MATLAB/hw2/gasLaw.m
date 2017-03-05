function T = gasLaw(P, V, n)
%Function that calculates pressure of ideal gas when given
%number of moles, volume (as vector), and temperature of said gas
%INPUTS: 
%P - pressure of gas
%V - volume of gas as vector
%n - number of moles of gas
%OUTPUTS:
%T - volume of gas

R = 0.08314472;

T= (P*V) / (n*R); 