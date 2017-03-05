function f= fibR(N)
%Function takes in scalar value N and returns the Fibonacci sequence for 0 to N
%Inputs:
%N-- scalar input value
%Outputs:
%f-- vector output as a function of N
f=zeros(1,10) %fills vector with zeros
f(1)=0; %first condition stated by equation
f(2)=1; %second condition stated by equation
    for index= 3:N
      f(index)= f(index -1) + f(index-2); %psuedo recursive formula for any cases that arent 1 or 2
    end 
     
end