import re

val1 = "Programmers will often write hello world as their first project"

search1 = re.findall( r'hello world', val1)
if search1:
   print "Found --> ", search1
else:
    print "Not found :( !!"

val2 = "The gooey peanut butter and jelly sandwich was a beauty."

search2 = re.findall(r'([A-z]*[aeiou]{3}[A-z]*)', val2)
if search2:
   print "Found --> ", search2
else:
    print "Not found :( !!"

val3 = "AA312 AA1298 NW1234 US443 US31344"

search3 = re.findall(r'[A-Z]{2}\d{3,4}\b', val3)
if search3:
   print "Found --> ", search3
else:
   print "Not found :( !!"
