

logfile = open('test.log', 'w')
logfile.write('test succeeded')
logfile.close()

logfile = open('test.log', 'r')
print logfile.tell()
print logfile.read(4)
print logfile.read(10)
logfile.seek(-14,1)
print logfile.read(10)
logfile.close()

logfile = open('test.log', 'a')
logfile.write('line 2')
"""another way to read """
print file('test.log').read()
logfile.write('\nline 3')
print file('test.log').read()

logfile.close()
"""until the file be close wc can read all the words in file"""
print file('test.log').read()
