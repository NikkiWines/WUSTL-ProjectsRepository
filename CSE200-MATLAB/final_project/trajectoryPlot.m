function [] = trajectoryPlot(x, y, t)
% This function plots the path of a projectile given its x coords, y
% coords, and the time it took to hit the ground.
% INPUTS:
% x - x values of projectile in vector
% y - y values of projectile in vector
% t - time for projectile to hit the ground
% OUTPUTS:
% none (function just creates a plot)

if (numel(x) == 0)
    disp('No velocity: projectile goes nowhere');
else
    % set up graph and axes
    tv = linspace(0, t, 100);
    xLim = max(x) + 0.1 * max(x);
    yLim = max(y) + 0.1 * max(y);
    
    % labels
    xlabel('Distance (m)');
    ylabel('Height (m)');
    
    % make sure figure doesn't re-draw
    hold on;

    % ball 
    ball = plot(x(1), y(1),'o','markersize', 10);

    % line that follows ball
    partialLine = plot(x(1), y(1), ':');
    
    % set axes' max to greater of the limits
    if yLim > xLim
        axis([0 yLim 0 yLim]);
    else
        axis([0 xLim 0 xLim]);
    end
    
    % set axes 1:1
    axis square;
    
    % update ball + partial line at every point in the time vector
    for k = 2:length(tv)
        titleString = sprintf('Distance vs. Height of Projectile at t = %0.5f s', tv(k));
        title(titleString);
        
        % update partial line
        set(partialLine, 'xdata', x(1:k), 'ydata', y(1:k));
        
        % update ball
        set(ball,'xdata', x(k),'ydata', y(k));
        
        drawnow    
    end
end



