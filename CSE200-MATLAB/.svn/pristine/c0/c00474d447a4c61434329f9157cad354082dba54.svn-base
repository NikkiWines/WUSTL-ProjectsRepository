function [x v a] = straightParticle(t)
%This function computes the position, velocity, and acceleration as a
%function of vector t. 
%Inputs: 
%t-- vector input
%Outputs: 
%x-- (position) vector output as a function of t 
%v-- (velocity) vector output as a function of t 
%a-- (acceleration) vector output as a function of t

x= 0.41.*(t.^4) - 10.8.*(t.^3) + 64.*(t.^2) - 8.2.*t + 4.4;
v= (41.*(t.^3)- 810.*(t.^2) +3200.*t -205)./25;
a= (123.*(t.^2) - 1620.*t +3200)/25;

subplot(1,3,1) 
plot(t,x);
title('Position over time'); 
xlabel('Time (s)');
ylabel('Position (m)');

subplot(1,3,2)
plot(t,v);
title('Velocity over time');
xlabel('Time (s)');
ylabel('Velocity (m/s)');

subplot(1,3,3)
plot(t,a);
title('Acceleration over time');
xlabel('Time (s)');
ylabel('Acceleration (m/s^2)');
