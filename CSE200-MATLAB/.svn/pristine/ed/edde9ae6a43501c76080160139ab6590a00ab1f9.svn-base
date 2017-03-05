function x= containsR(v,n)
%Function takes in vector v and scalar n and recursively returns true if n is a value
%contained inside of vector v
%Inputs:
%n--scalar input value
%v-- vector input value
%Outputs:
%x-- boolean output value (1 or 0) as a function of v and n

if numel(v) == 0
    x = 0; % check to see if there are any elements in vector v
elseif v(numel(v)) == n %check to see if any of the elements in v are equal to n
    x = 1; % if they are, return true
    return;
else
    new = v(1:(length(v) - 1)); % create a new vector with the last element deleted.
    x = containsR(new, n); %call the function again on the new vector.
end

end


