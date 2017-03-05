function P= computeInterest(P0, r, t)
%This function takes in scalar values iB, gR and t and computes the
%compound continuous interest as a function of these values over time 
%Inputs:
%P0-- (initial balance) scalar value 
%r-- (growth rate) scalar value
%t-- (time) scalar value
%Output: 
%P- (current balance) vector output as a function of iB, gR and t
time= 0:t;
P=(P0).*exp(1).^(r.*time);

subplot (2,2,1)
plot(time,P);
title('Compound Interest of Principle Balance');
xlabel('Times (years)');
ylabel('Balance (dollars)');

subplot (2,2,2)
semilogx(time,P);
title('Compound Interest of Principle Balance');
xlabel('Times (years-logathrimic)');
ylabel('Balance (dollars)');

subplot (2,2,3)
semilogy(time,P);
title('Compound Interest of Principle Balance');
xlabel('Times (years)');
ylabel('Balance (dollars-logarithmic)');

subplot(2,2,4)
loglog(time,P);
title('Compound Interest of Principle Balance');
xlabel('Times (years-logathrimic)');
ylabel('Balance (dollars-logarithmic)');




