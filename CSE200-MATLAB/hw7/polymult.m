function mult= polymult(a, b)
%Function takes in the cooefficients of two different polynomails and
%multiplies them together to return the product of the two polynomials 
%Inputs: 
% a-- vector input of the coefficients for polynomial a
% b-- vector input of the coefficients for polynomial b
%Outputs: 
% sum-- vector output as a function of a and b-- is the sum of the
% polynomials 

mult=0;

for i=1:numel(a) % for loop from 1 to the length of a
    mult=polyadd(mult, [a(i) * b zeros(1,(numel(a)-i))]); % call polyadd 
end
end 
       