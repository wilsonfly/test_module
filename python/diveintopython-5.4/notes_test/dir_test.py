
import os
dirname = "/home/sunhuasheng/"
dirname = os.path.expanduser("~")

os.listdir(dirname)

list_1 = [f for f in os.listdir(dirname)
		if os.path.isfile(os.path.join(dirname, f))]
print list_1
print 

list_1_full = [ os.path.join(dirname, f) for f in list_1]
print list_1_full
print 

list_2 = [f for f in os.listdir(dirname)
		if os.path.isdir(os.path.join(dirname, f))]
print list_2
print


filename = "test.c"
li = []
li = os.path.splitext(filename)
print li
[k,v] = os.path.splitext(filename)
print k
print v
a = os.path.splitext(filename)[0]
print a
print a.upper()
a = os.path.splitext(filename)[1]
print a
print
				  

import glob
print glob.glob("/home/sunhuasheng/tools/*.sh")
