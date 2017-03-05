function sqroot =sqrtTolerance(p, tolerance )
%This function takes in values p and tolerance (the allowed error in the
%computation) and computes the square root of p from the inputs. 
%Inputs: 
% p- scalar value
% tolerance-  scalar value
%Outputs: 
%sqroot- scalar value as a function of p and tolerance
format long 
sqroot= 1; %current best estimate of sqroot
E = 1; %current error value
while E >= tolerance %compute until E is no longer greater than tolerance
    pcurrent= sqroot; %previous guess value= current value
    sqroot = (sqroot + p/sqroot) /2; % from formula in problem 
    E= abs((sqroot-pcurrent)/pcurrent); %also from formula in problem 
end 


