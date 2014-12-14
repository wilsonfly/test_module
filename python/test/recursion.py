#!/usr/bin/env python

def my_power(x,n):
    result = 1
    for i in range(n):
        result *= x
    return result

def my_power_2(x,n):
    result = 1
    for i in range(1,n):
        result *= x
    return result

def my_power_3(x,n):
    if n==0:
        return 1
    return x*my_power_3(x,n-1)

ret = my_power(2,3)
print ret
ret = my_power_2(2,3)
print ret
ret = my_power_3(2,3)
print ret
ret = my_power(2,0)
print ret
ret = my_power_2(2,0)
print ret
