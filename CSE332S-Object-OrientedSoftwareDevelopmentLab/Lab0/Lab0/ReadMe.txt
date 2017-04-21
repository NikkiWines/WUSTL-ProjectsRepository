========================================================================
    CONSOLE APPLICATION : Lab0 Project Overview Nikki Wines 437324
========================================================================


Case 0-> Too Few Args
	
	Input: Lab0.exe 
	Output: Usage: lab0.exe input_file.txt
	Error level: 2

Case 1-> Too Many Args
	
	Input::Lab0.exe input_file.txt output_file.txt
	Output: Usage: lab0.exe input_file.txt
	Error level: 2

Case 2-> Input File Name does not exist (inpt_fle.txt vs. input_file.txt)
	
	Input: Lab0.exe inpt_fle.txt
	Output: Could not open file for reading
	Error level: 1

Case 3-> Correct Input!

	Input: Lab0.exe input_file.txt 
	Output: hello	
		world!
		Nikki
		Wines
		Lab0 
		4
		5
		34567
		1
	Error level: 0

Explanation-> 

input_file.txt: 	hello world!
			4
				5
			34567
			Nikki Wines
				Lab0
			1


The code first parses each word within the input file and pushes it into a vector of strings. 
This vector is then iterated across, and each word is checked to see if it is entirely
numeric. If so, the numeric strings are pushed into a vector of ints. The remaining non-numeric 
strings are printed to the console (hello, world!, Nikki, Wines, Lab0). Lastly, the numeric strings, 
which have been converted to ints by this point, are then printed to the console (4, 5, 34567, 1). The error level is 0, 
which is the code for success!
	

Errors/Warnings:

1. Ran into a error involving parsing each word of the input file. 
Was unable to parse more than one word, and eventually found that "while (ifs >> word_1) { }" 
resolved the issue, and allowed for each word to be parsed individually. 
Additionally, originally a string stream was also being used in the fileParser function. 
Wrapping the words in a string stream didn't cause additional errors at the time.
However, it also provided no benefit, so it was removed.

2. isdigit() caused some errors when attempting to check if an entire string was numeric.
Eventually found that each char of a string must be checked, rather than the string itself.
Solution was a double for-loop which iterated across each char of the current string.

3.For a while no values were being pushed into the int vector due to an error in initializing the 
isDigit boolean var. isDigit was not being reset for each iteration of the outer for loop. 
Solution was to initialize the varaible within the first for loop. 

4. 1>\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab0\lab0\lab0.cpp(51): warning C4018: '<': signed/unsigned mismatch
Fixed by changing "for (int i = 0; ...)" to "for (unsigned int i =0; ...)"

5. 1>\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab0\lab0\lab0.cpp(55): warning C4018: '<': signed/unsigned mismatch
Fixed by changing "for (int i = 0; ...)" to "for (unsigned int i =0; ...)"

6. 1>\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab0\lab0\lab0.cpp(71): warning C4018: '<': signed/unsigned mismatch
Fixed by changing "for (int i = 0; ...)" to "for (unsigned int i =0; ...)"

7.1>\\warehouse2.seasad.wustl.edu\home\catherinewines\my documents\visual studio 2015\projects\lab0\lab0\lab0.cpp(30): warning C4715: 'fileParser': not all control paths return a value
Fixed by adding "return success" to the end of the fileParser function.

Extra Credit: 

1. [catherinewines@shell ~]$
2. Errors and Warnings: 

Errors 4, 5, 6, and 7 from above were originally found during the extra credit portion of the lab and were fixed as explained above. 
Additionally the error "make: *** [Lab0.exe] Error 1" followed by "Undefined reference to 'main'" was found becuase Lab0.cpp was not listed as a source file. 

3.Running "make" after all errors have been resolved yeilds  "g++ -o Lab0.exe -DUNIX   -Wall -W -g -pedantic Lab0.cpp -lnsl"

4.
Case 0-> Too Few Args
	Input: ./Lab0.exe
	Ouput: Usage ./Lab0.exe input_file.txt 
Case 1-> Too Many Args
	Input: ./Lab0.exe input_file.txt output_file.txt
	Ouput: Usage ./Lab0.exe input_file.txt 
Case 2-> Input File Name does not exist (inpt_fle.txt vs. input_file.txt)
	
	Input: ./Lab0.exe inpt_fle.txt
	Output: Could not open file for reading

Case 3-> Correct Input!

	Input: ./Lab0.exe input_file.txt 
	Output: hello	
		world!
		Nikki
		Wines
		Lab0 
		4
		5
		34567
		1

