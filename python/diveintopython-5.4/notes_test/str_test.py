

def fun():print 2

fun()
"""wrong"""
fun().__doc__
fun().__doc__ == None

"""use print in *.py;"""
fun.__doc__
print fun.__doc__
print str(fun.__doc__)

"""human readable"""
print fun.__doc__ == None
print fun.__doc__ is None


if __name__ == "__main__":
	print "run this py directly rather than as a module"
