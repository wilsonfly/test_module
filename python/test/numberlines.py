#!/usr/bin/env python                              # 1 # 1 # 1
                                                   # 2 # 2 # 2
import sys                                         # 3 # 3 # 3
import fileinput                                   # 4 # 4 # 4
                                                   # 5 # 5 # 5
for line in fileinput.input(inplace=True):         # 6 # 6 # 6
    line = line.rstrip()                           # 7 # 7 # 7
    num = fileinput.lineno()                       # 8 # 8 # 8
    print '%-50s #%2i' % (line, num)               # 9 # 9 # 9
                                                   #10 #10 #10
                                                   #11 #11 #11
                                                   #12 #12 #12
                                                   #13 #13 #13
#print 'argc:%i' % sys.argv.count(sys.argv)        #14 #14 #14
#for i in range(0,sys.argv.count(sys.argv)):       #15 #15 #15
#    print '%s ' % sys.argv[i]                     #16 #16 #16
                                                   #17 #17 #17
for param in sys.argv:                             #18 #18 #18
    print param                                    #19 #19 #19
