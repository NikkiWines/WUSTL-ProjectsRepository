%Matrix for Flow Rate 
%Problem 15: solve several equations using the diagram
%and the information avaliable via matrix operations.
%Equations: 
% 0.5(100)= 0.2(mt) +0.65(mb)
% x(100)= 0.35(mt) + 0.25(mb)
%(1- 0.5 -x)(100)= 0.45(mt) + 0.10(mb)

%calculating (in order by row) mt (where mt is m_top),
%mb (where mb is m_bottom)and x

coefficient= [0.2 0.65 0; 0.0035 0.0025 -1; 0.0045 0.001 1]
answer= [50 0 0.5]'

result= coefficient\answer

m_top= results(1,1)
m_bottom= results(2,1)