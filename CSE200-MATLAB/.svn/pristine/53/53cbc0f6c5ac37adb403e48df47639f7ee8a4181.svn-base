function pascal= pascalMatrix(n)
%Function: creates a pascal matrix from value n
%Input:
%n-- scalar input
%Output:
%pascal-- matrix output as a function of n 

pascal= ones(n); %Pre-fill matrix with the value 1.

for r= 1:n %% same as in javas for (int r=0; r <n; r++) 
    for c= 1:n
        pascal(r, c)= ((factorial(r +c -2))/((factorial(r-1))*(factorial(c-1))));
    end 
end 


