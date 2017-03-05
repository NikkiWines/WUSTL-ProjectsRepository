%Problem 16: This problem involves calculating the average
% of an array, computing the max and min valus of the 
% array and comparing two arraqys by forming a matrix. 

year1= [3 6 4 10; 5 8 6 10; 4 9 5 10; 6 4 7 9; 3 5 8 10];
year2= [2 7 3 10; 3 7 5 10; 4 5 5 10; 3 3 8 10; 3 5 2 10];
%creating 3D array testdata
testdata= year1; 
testdata(:,:, 2)= year2;
%calculating averages of each year. 
q1avgy1= mean(testdata (1:5, 1, 1));
q2avgy1= mean(testdata (1:5, 2, 1));
q3avgy1= mean(testdata (1:5, 3, 1));
q4avgy1= mean(testdata (1:5, 4, 1));

q1avgy2= mean(testdata (1:5, 1, 2));
q2avgy2= mean(testdata (1:5, 2, 2));
q3avgy2= mean(testdata (1:5, 3, 2));
q4avgy2= mean(testdata (1:5, 4, 2));

avg_scores= [q1avgy1 q2avgy1 q3avgy1 q4avgy1 ; q1avgy2 q2avgy2 q3avgy2 q4avgy2]

%computing which question had the highest average and the lowest average

questionscores= vertcat(testdata(:,:,1),testdata(:,:,2));

q1avg= mean(questionscores(1:10, 1));
q2avg= mean(questionscores(1:10, 2));
q3avg= mean(questionscores(1:10, 3));
q4avg= mean(questionscores(1:10, 4));

yearaverages= [q1avg, q2avg, q3avg, q4avg];

[M, I] = max(yearaverages);
avg_high= M
avg_high_index= I

[M, I] = min(yearaverages);
avg_low= M
avg_low_index= I

%computing change in scores between year 1 and year 2

sums1y1= sum(testdata(1,1:4, 1));
sums2y1= sum(testdata(2,1:4, 1));
sums3y1= sum(testdata(3,1:4, 1));
sums4y1= sum(testdata(4,1:4, 1));
sums5y1= sum(testdata(5,1:4, 1));

sums1y2= sum(testdata(1,1:4, 2));
sums2y2= sum(testdata(2,1:4, 2));
sums3y2= sum(testdata(3,1:4, 2));
sums4y2= sum(testdata(4,1:4, 2));
sums5y2= sum(testdata(5,1:4, 2));

changes1= sums1y2- sums1y1;
changes2= sums2y2- sums2y1;
changes3= sums3y2- sums3y1;
changes4= sums4y2- sums4y1;
changes5= sums5y2- sums5y1;

score_change= [changes1, changes2, changes3, changes4, changes5]

%creating a matrix comparing year 1 and year 2

q2_compare= horzcat(testdata(1:5, 2, 1), testdata(1:5, 2, 2))

