name= {'Aluminum', 'Copper', 'Iron', 'Molybdenum', 'Colbalt', 'Vanadium'}';
symbol= char('Al', 'Cu', 'Fe', 'Mo', 'Co', 'V');
number= [13, 29, 26, 42, 27, 23]';
weight= [26.98, 63.55, 55.85, 95.94, 58.93, 50.94]';
density= [2.71, 8.94, 7.87, 10.22, 8.9, 6.0]';
structure=char('FCC', 'FCC', 'BCC', 'FBC', 'HCP', 'BCC');

% stores the above information in a single cell array
metals={name, symbol, number, weight, density, structure}

% contains the name, weight and structure of the 4th element on the list
name4=metals{1}{4};
weight4=metals{4}(4);
structure4=metals{6}(4,1:3);
element_info= {name4; weight4;structure4}

%Contains the average weight of all of the elements
avg_weight= (sum(metals{4}(1:end)))/6

%Contains the name of the element with the highest density
[M, I]= max(metals{5}(1:end));
highest_density=metals{1}{I}

%Contains the number of elements with the crystal structure BCC 
isSame=strmatch('BCC', metals{6});
bcc_count=numel(isSame)