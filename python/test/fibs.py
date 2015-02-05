#!/usr/bin/env python

class Fibs:
    def __init__(self):
        self.a=0
        self.b=1
    def next(self):
        self.a,self.b=self.b, self.a+self.b
        return self.a
    def __iter__(self):
        return self

fibs = Fibs()
for x in fibs:
    if x>10:
        break
    else:
        print x,

print

fibs_2 = Fibs()
for y in range(0,6):
    print fibs_2.next(),

print

class FibsMax:
    def __init__(self,max):
        self.n, self.a, self.b = 0,0,1
        self.max = max
    def __iter__(self):
        return self
    def next(self):
        if self.n < self.max:
            self.a, self.b = self.b, self.a+self.b
            self.n = self.n+1
            return self.a
        raise

try:
    for x in FibsMax(6):
        print x,
except:pass

print

def fibs_fun(max):
    n,a,b = 0,0,1
    while n<max:
        print b,
        a,b = b, a+b
        n = n+1

fibs_fun(6)
print

def fibs_fun2(max):
    n,a,b = 0,0,1
    while n<max:
        yield b
        a,b = b, a+b
        n = n+1

for x in fibs_fun2(6):
    print x,
