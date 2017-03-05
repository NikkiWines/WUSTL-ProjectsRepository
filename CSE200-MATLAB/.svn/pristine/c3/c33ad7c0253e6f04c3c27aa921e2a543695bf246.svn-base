function y= shipHull(x, z)
%Function takes in vectors x and z and computes y from those vectors and
%creates a 3-D plot of the 3 values. 
%Inputs:
% x-- (length) vector value input
% z-- (height) vector value input
%Output:
% y-- (width) vector value output as a function of x and z

[X Z]= meshgrid(x,z);

y= (1.2/2).*(1-((2.*X)/4).^2).*(1-((2.*Z)/0.5).^2);


figure;
subplot(1,2,1);
surf(X,y,Z);
hold on;
surf(X,-y,Z);
title('Ship Hull');
xlabel('x (m)');
ylabel('y (m)');
zlabel('z (m)');

subplot(1,2,2);
surf(X,y,Z);
hold on;
surf(X,-y,Z);
view(90, 0);
title('Ship Hull');
xlabel('x (m)');
ylabel('y (m)');
zlabel('z (m)');

