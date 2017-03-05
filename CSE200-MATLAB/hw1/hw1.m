% Name: Wines
% Wustlkey: catherinewines

% Assignment: Homework 1
% Due Date: September 14th, 2015


%Problem 1: This problem involes computing an equation
%in radians, and storing the result. 

part1= tan(11*pi/4)^2;
part2= (0.5*sin(19*pi/6)) + 9.5;
part3= cos(3*pi/13)^3;

p1result= (part1/part2)- part3

%Problem 2: This problem involes verifying an identity,
%computing both sides of an equations, and using degrees,
%not radians for the equation. 

p2left= sind(84)^3
p2right= (0.25)*((3*sind(84))-sind(3*84))

%Problem 3: This problem involes using a logaraithm base
%change formula in order to compute 2 values. 

p3resulta= log10(14003)/log10(7)
p3resultb= log10(1.532)/log10(5)

%Problem 4: The problem asks that the user create a row
%vector whose first element is -3pi, the last element is
% 3pi and whose spacing as pi/8. 

p4result= [(-3*pi):(pi/8):(3*pi)]

%Problem 5: This problem asks the users to create a column
%vector of 21 evenly spaced eleemnts. The elements begin
% -6 and end in 11.3

result= linspace(-6,11.3, 21);
p5result= result'

%Problem 6: This problem involes creating a column vector with
%a specific starting point, ending point, and spacing. 

row= (-90.4:-0.2:-100.8);
p6result= row'

%Problem 7: This problem insvoles created a row vector with
% a specific starting point, ending point, and spacing. 

p7result= linspace(13.49,57.111,8)

%Problem 8: This problem involes creating matrices
%using both linspace and colon notation.

row1=linspace(19,4,6);
row2=linspace(33.57,48.12,6);
row3=(12:15:87);

p8result=[row1;row2;row3]

%Problem 9: This problem asks the user to manipulate
%the above problem by changing the rows and columns. 

newrow1= p8result(1:3,3)';
newrow2= p8result(1:3,4)';
newrow3= p8result(2, 1:3);
newrow4= row3([1 3 4]);

p9result= [newrow1;newrow2;newrow3;newrow4]

%Problem 10: This problem involves computing the value of y
%in an equation, based off of several x values.

x= [1:10];

a= x(:)+4;
b= ((x(:).^3)-9);
c=((x(:).^2)+11);
y= (a.*b)./c;
p10result= y


%Problem 11: This problem uses the ideal gas law
% equation to compute the temperature under different
% variables

a=5.536; 
b=0.03049;
R=0.08314472;
n=4;
P=500;
V=[1:2:15];


part1= ((n^2)*a);
part2= (V(:).^2);
part3= (V(:)-(n*b));
part4= (n*R);

p11result= ((P + (part1./part2)).*part3)/part4


%Problem 12: This problem asks the user to find the number
%of prime numbers between 2 values. 

prime40k= primes(40000);
over20k= find (prime40k > 20000);

p12result= length(over20k)


%Problem 13: This problem involves finding the max reading, 
%the min reading, and the time in seconds for the max readings to 
%occur for a matrix. 

%The following matrix is used for Problem 13. The first column represents
%time in seconds. The remaining 5 columns represent data collected from a
%set of sensors, one sensor per column.

sensorData = [0 70.6432 68.347 72.3469 67.6751 73.1764
    1 73.2823 65.7819 65.4822 71.8548 66.9929
    2 64.1609 72.4888 70.1794 73.6414 72.7559
    3 67.6970 77.4425 66.6823 80.5608 64.5008
    4 68.8678 67.2676 72.6770 63.2135 70.4300
    5 63.9342 65.7662 2.7644 64.8869 59.9772
    6 63.4028 68.7683 68.9815 75.1892 67.5346
    7 74.6561 73.3151 59.7284 68.0510 72.3102
    8 70.0562 65.7290 70.6628 63.0937 68.3950
    9 66.7743 63.9934 77.9647 71.5777 76.1828];

p13min= min(sensorData(1:10,2:6))
p13max= max(sensorData(1:10,2:6))
[Max Index] = max(sensorData(1:10,2:6));
p13maxtime= Index -1
p13avg= mean(sensorData(1:10,2:6))


%Problems 14, 15, and 16 are in their specific .m files
%Problem 14 is in the calorimeter.m file
%Problem 15 is in the flowRate.m file
%Problem 16 is in the testscores.m file


    