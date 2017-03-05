function f = futureValueLoop(pv, i, n)
%This is a function that takes in an investment amount, interest rate, and
%number of periods, and returns the future value of the investment. 
%INPUTS:
%pv - investment amount
%i - interest rate as a percentage per period
%n - number of compounding periods
%OUTPUT:
%f - future value of investment as a vector for each period n
[X, Y] = meshgrid(pv, i);

f=zeros(1,n); % create a row vector of n slots with 0 as each value

for j = 1:n
f(j) = X .* (1 + Y).^j; %you put j so that your output is increasing from 1 to n.
end 