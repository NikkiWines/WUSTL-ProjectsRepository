function p =exponentialFit(x, y)
%Function takes in vectors as a data set and fits an exponential curve to
%the data.
%Inputs:
% x-- first vector set
% y-- second vector set
%Outputs:
% p-- fitted vector returned as a function of a set1 and set2

p=fit(x',y','exp1');
 
plot(x,y, 'o');
hold on
plot(p, x, y);

title('Exponential Fit');
xlabel('Independent Variable');
ylabel('Dependent Variable');

