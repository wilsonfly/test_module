#!/usr/bin/env python


def bisearch(seq,num,lower=0,upper=None):
    if upper is None:
        upper = len(seq)-1
    if lower == upper:
        assert num==seq[upper]
        return upper
    else:
        middle = (lower+upper)/2
        print 'seq[%d]' % middle,':',seq[middle]
        if (num>seq[middle]):
            return bisearch(seq,num,middle+1,upper)
        else:
            return bisearch(seq,num,0,middle)

seq=[2,3,8,22,9,99,45,23,0,11,77]
seq.sort()
print seq

ret=bisearch(seq,11)
print 'index of 11 is',ret
ret=bisearch(seq,0)
print 'index of 0 is',ret
ret=bisearch(seq,99)
print 'index of 99 is',ret
ret=bisearch(seq,3)
print 'index of 3 is',ret
