name= char('Aluminum', 'Copper', 'Iron', 'Molybdenum', 'Colbalt', 'Vanadium');
symbol= char('Al', 'Cu', 'Fe', 'Mo', 'Co', 'V');
number= [13, 29, 26, 42, 27, 23]';
weight= [26.98, 63.55, 55.85, 95.94, 58.93, 50.94]';
density= [2.71, 8.94, 7.87, 10.22, 8.9, 6.0]';
structure=char('FCC', 'FCC', 'BCC', 'FBC', 'HCP', 'BCC');

%stores the above information in one structure 

metals= struct('Name', name, 'Symbol', symbol, 'Weight', weight, 'Density', density, 'Structure', structure)

% contains the name, weight and structure of the 4th element on the list
name4=metals.Name(4, 1:end);
weight4=metals.Weight(4);
structure4=metals.Structure(4, 1:end);
element_info= struct('Name', name4, 'Weight', weight4, 'Structure', structure4)

%Contains the average weight of all of the elements
avg_weight= (sum(metals.Weight/6))
 
%Contains the name of the element with the highest density
[M, I]= max(metals.Density);
highest_density=metals.Name(I, 1:end)

%Contains the number of elements with the crystal structure BCC 
isSame=strmatch('BCC', metals.Structure);
bcc_count=numel(isSame)