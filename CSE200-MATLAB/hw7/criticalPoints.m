function [max min]= criticalPoints(vec)
%Function takes in the vector and returns a vector containing the local
%maxima and a vector containing the local minima 
%Inputs:
% vec- input vector value (of a polynomial)
%Outputs:
% max-- vector of the local maxima of vec
% min-- vector of the local minima of vec

vec1=polyder(vec);
vec2=polyder(vec1);
vecroots= roots(vec1);
min= [];
max= []; 
for i= 1:length(vecroots) 
        vec3=polyval(vec2,vecroots(i));
        if vec3 > 0; %accounts for min
            min= [min vecroots(i)];
        elseif vec3 < 0; %accounts for no min or max so that both will be []
            max= [max vecroots(i)];
        end
end

end