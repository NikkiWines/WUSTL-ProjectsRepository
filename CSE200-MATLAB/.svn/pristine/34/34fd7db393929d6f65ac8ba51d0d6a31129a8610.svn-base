function x= harmonicSum(n, p)

%This function takes in value p and n and creates the p series of p from 1
%to n as a vector and plotting the vector. 
%Inputs: 
% n-- scalar value 
% p-- scalar value 
%Outputs: 
%v-- vector value as a function of n and p 

sum=0; 
x= ones(1,n);

for i=1:n
    sum= sum+ (1/i^p);
    x(i)= sum; 
end 

xaxis= 1:n; 
plot(xaxis, x);

xlabel('n');
ylabel('series');

if p==1
    title('Harmonic Sum');
else
    title(['p-series with alpha=', ' ', num2str(p)]);
end 