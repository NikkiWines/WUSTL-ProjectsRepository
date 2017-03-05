function f= oddFactR(n)
%Function involves taking the factorial of only odd values from 1 to n
%Inputs:
%n-- scalar input value
%Outputs:
%ans-- scalar output as a function of n

if mod(n,2)==0 % if n is even it will return zero, n will be incremented to an odd number
    n=n-1;
end
if n<=1 % takes care of any 0's or 1's
    f= 1;
else
    f= n*oddFactR(n-2); % normal recursive factorial equation except n is incremented down by 2 instead of 1
end
end
 