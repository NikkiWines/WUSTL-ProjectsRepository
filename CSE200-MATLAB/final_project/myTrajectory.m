function [] = myTrajectory()
% Wrapper function for mechanical engineering final project.
% INPUTS:
% none - this function is just a UI loop
% OUTPUTS:
% none - just a UI loop

addpath(genpath('.')); % make sure all other functions are imported

finished = 0; % keeps track of whether input loop is over
x = [];
y = [];
t = -1;

% UI loop
while ~finished
    disp('Press the number of the corresponding command to execute it.');
    disp('1. Input data');
    disp('2. Show max height');
    disp('3. Show max distance');
    disp('4. Show time to ground');
    disp('5. Show trajectory graph');
    disp('6. Exit');
    
    in = input('Input here: ');
    
    while (t == -1 && (in ~= 1 && in ~= 6))
        in = input('No data yet. Input here: ');
    end
    
    while (in < 0 || in > 6) % sentinel to check that input is valid
        in = input('Input here: ');
    end
   
    % got a viable input by this point
    
    if (in == 1) % call trajectoryCalc
        [x, y, t] = trajectoryCalc();
    elseif (in == 6) % quit loop
        disp('Goodbye!');
        return;
    end
    
    % if data is present
    if (in == 2) % display max height
        maxH = max(y);
        if (numel(y) == 0)
            maxH = 0;
        end
        hString = sprintf('\nMax height: %0.4f m\n', maxH);
        disp(hString);
    elseif (in == 3) % display max distance (R)
        maxD = max(x);
        if (numel(x) == 0)
            maxD = 0;
        end
        dString = sprintf('\nMax distance: %0.4f m\n', maxD);
        disp(dString);
    elseif (in == 4) % display time from launch to ground
        tString = sprintf('\nTime to ground: %0.4f s\n', t);
        disp(tString);
    elseif (in == 5) % plot trajectory
        close all;
        trajectoryPlot(x, y, t);
    end
end


