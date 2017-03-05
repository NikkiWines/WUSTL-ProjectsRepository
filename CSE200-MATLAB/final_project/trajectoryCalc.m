function [xOut, yOut, tOut] = trajectoryCalc()
% This function calculates the trajectory of a projectile from its
% starting velocity, angle, and height.
% INPUTS:
% none - function gathers information from user
% OUTPUTS:
% x - x coordinates over time, vector
% y - y coordinates over time, vector
% t - time to hit the ground

goodData = 0;

while (~goodData)
    % get starting velocity (v >= 0)
    v = input('Enter a starting velocity greater than or equal to zero: ');
    while (v < 0)
        v = input('Enter a starting velocity greater than or equal to zero: ');
    end
    
    % get starting angle (-90 <= a <= 90)
    a = input('Enter a starting angle greater than or equal to -90 and less than or equal to 90 degrees: ');
    while (a > 90 || a < -90)
        a = input('Enter a starting angle greater than or equal to -90 and less than or equal to 90 degrees: ');
    end
    
    % get starting height (optional, h > 0)
    resp = input('Would you like to enter a starting height? yes/no: ', 's');
    if (strcmp(resp, 'yes'))
        h = input('Enter a starting height greater than 0: ');
        while (h <= 0)
            h = input('Enter a starting height greater than 0: ');
        end
    else % user elected not to input starting H
        h = -1;
    end
    
    % check to make sure data is good
    if (v == 0 && a == 0 && h == -1)
        disp('Please make sure that you enter good data. Try again.');
        goodData = 0;
    elseif (a < 0 && h == -1)
        disp('Please make sure that you enter good data. Try again.');
        goodData = 0;
    else
        goodData = 1;
    end
end

% data is good, move on to calculations

if (v == 0)
    xOut = [];
    yOut = [];
    tOut = 0;
else
    % find time in air
    if (h > 0)
        p = [-4.905, v*sind(a), h];
    else
        p = [-4.905, v*sind(a), 0];
    end
    
    r = roots(p);
    
    tOut = r(r>0);
    
    % create time vector
    tv = linspace(0, tOut, 100);
    
    % get x vector
    vix = v*cosd(a);
    xOut = vix.*tv;
    
    % get y vector
    viy = v*sind(a);
    if (h == -1)
        h = 0;
    end
    yOut = ((1/2)*-9.81).*(tv.^2) + viy.*tv + h;
end
end