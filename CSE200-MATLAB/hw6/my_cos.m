function value=my_cos(x, max_it, criterion)

%Function approximates cos(x) using the Maclaurin series, given the maximum
%number of iterations (total) and the criterion for convergence (criterion)
%Inputs:
% x-- scalar value
% total-- scalar value
% criterion-- scalar value
%Ouputs:
%value= approx. value of cos(x) -- scalar output value as a function of x,
%total and criterion
y(1) = 1;

%add term to result during each iteration
for i = 2:max_it
    new_term = ((-1)^(i + 1))/factorial(2 *i)*x^(2*i);
    y(i) = y(i-1) + new_term;
    
    % check termination criteria
    if abs(y(i)-y(i-1)) < criterion
        break
    end
end

%format result and return it
if i == max_it
    fprintf('Maximum number of iterations reached: %2.0f terms used\n',i)
else
    fprintf('This approximation requires %2.0f terms \n',i)
end
fprintf('Using our process cos(x) of %4.2f is %7.6f \n',x, -y(end))
fprintf('The value using the built-in cos function is %7.6f \n',cos(x))

y = -y(end);
end




