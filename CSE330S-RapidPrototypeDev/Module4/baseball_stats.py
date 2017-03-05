import sys, os
from sys import argv
import re

# stackoverflow: http://stackoverflow.com/questions/983201/python-and-sys-argv
if len(sys.argv) != 2: # if 2 arguments aren't given
	sys.exit("Error: Unable to use '%s' without .txt file" % sys.argv[0]) # error if no .txt filename given

f = sys.argv[1] # set file to 2nd argument

if not os.path.exists(f):
	sys.exit("Error: File '%s' not found" % sys.argv[1]) # error if filename not found

txt = open(f)
data = txt.read()
lines = data.split('\n')

# create dictionary and sets
hits = {}
bats = {}
avg = []
players = set()

# read data line by line
for line in lines:

    if line == "": # continue if line is empty
        continue

    search= re.match(r'(\w+ \w+) batted (\d) times with (\d) hits', line)

    if search == None: # continue if no match is found
         continue
    playername = search.group(1) # set player names
    if playername in players:
        bats[playername] += float(search.group(2)) # add the bat value  to previous entry
        hits[playername] += float(search.group(3)) # add the hit value to previous entry
    else :
        players.add(playername) # add the player name to the set.
        bats[playername] = float(search.group(2)) # add player bat to dictionary
        hits[playername] = float(search.group(3)) # add player hit to dictionary

txt.close()


for player in players :
    avgVal = hits[player]/bats[player] # find averages
    avgVal = round(avgVal, 3) # round values
    avg.append((avgVal, player))

avg.sort(reverse=True)

averages = [x[0] for x in avg] # access first val of tuple
names = [x[1] for x in avg] # access second val of tuple

for i in range(0, len(averages)): # print line by line
    print "%s: %.3f" % (names[i], averages[i]) # formatting
