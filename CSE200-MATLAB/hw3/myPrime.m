function primes= myPrime(x, y) 

%This function computes the prime numbers between x and y and puts them
%into the vector v 
%Inputs: 
%x- scalar input 
%y- scalar input 
%Outputs: 
%primes- vector output as a function of x and y 
index= 1; % your index counter
for current= x:y 
   isPrime= 1; % assume that (unless proven otherwise all values are prime)
    
    
   if current ==1  %special case-- if your current value is 1-- it isnt a prime
       isPrime= 0;
   end 
   
   for checkPrime= 2:(current-1) % check the division of the current value  from 2 up to current -1
        if mod(current, checkPrime) == 0; % if current%checkprime =0 (is not prime)
            isPrime=0;
        end 
   end 
   
   if isPrime == 1
       primes(index) = current; % if your current value is prime, put it in vector primes at ind index. 
       index= index +1; % increase your index by one after adding a value
   end 
end 


       
    
    
    
    