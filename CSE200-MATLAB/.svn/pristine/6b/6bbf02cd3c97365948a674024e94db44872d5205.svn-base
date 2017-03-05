function area=computePi(darts)

%This function takes the a) number of darts hit outside of a circle with radius
%1m and b) the number of darts hit inside of the circle, and computes pi by
%dividing the b) by a). 
%INPUTS:
%darts-- scalar input-- number of darts thrown 
%OUTPUTS: 
%area-- scalar output as a function of darts. 

matrix= rand(2,darts);
xdifferencesqrd = (0.5 - matrix(1, 1:darts)).^2;
ydifferencesqrd = (0.5 - matrix(2, 1:darts)).^2;

dist = sqrt(xdifferencesqrd + ydifferencesqrd);

didHitCircle = dist < 0.5;

ratio = sum(didHitCircle) / darts;

area = ratio * 4;



