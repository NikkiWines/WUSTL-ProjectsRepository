function sum= polyadd(a, b)
%Function takes in the cooefficients of two different polynomails and adds
%them together to return the sum of the two polynomials 
%Inputs: 
% a-- vector input of the coefficients for polynomial a
% b-- vector input of the coefficients for polynomial b
%Outputs: 
% sum-- vector output as a function of a and b-- is the sum of the
% polynomials 

sum=0;
if numel(a) > numel(b) % if a is larger than b
  longerb= zeros(1, numel(a)-numel(b)); 
  longerb= horzcat(longerb, b); % adjust b to be equal length of a
  sum= a + longerb;
else % if b is longer than a
    longera= zeros(1, numel(b)-numel(a)); 
    longera= horzcat(longera, a); %adjust a to be equal length of b
    sum= b+ longera;
end 
end
   
    