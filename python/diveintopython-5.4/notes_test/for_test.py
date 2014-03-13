
""" sample 1 """
li = ['a', 'b', 'cc']
for s in li:
	print s

print "\n".join(li)

""" sample 2 """
import os
for k, v in os.environ.items():
	print "%s=%s" % (k, v)

print "\n".join(["%s=%s" % (k, v)
	for k, v in os.environ.items()])
