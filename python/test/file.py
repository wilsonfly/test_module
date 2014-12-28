#!/usr/bin/env python

#with this import is also ok
#from __future__ import with_statement

import sys
import pprint
import fileinput

filename="file_generated.txt"
with open(filename,'w+') as fd:
    fd.write("hello,\nabc\ndefg\n")
    #fd.flush()
    fd.seek(-3,2)
    text=fd.read(3)
    print text

    fd.seek(0,0)
    text=fd.read(3)
    print text
    
    fd.seek(0,0)
    for i in range(3):
        print str(i)+":"+fd.readline()

    fd.seek(0,0)
    pprint.pprint(fd.readlines())

def process(param):
    print "in process,text:"+param

fd=open(filename)
while True:
    line = fd.readline()
    if not line:break
    process(line)
fd.close()

for line in fileinput.input(filename):
    process(line)

#iteration
for line in open(filename):
    process(line)
fd.close()

#iteration
def test_stdin():
    print "Enter something,ctrl+d finish it!"
    for line in sys.stdin:
        process(line)

if __name__ == '__main__':
    test_stdin()
