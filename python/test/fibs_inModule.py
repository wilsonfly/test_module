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

def test_Fibs():
    fibs = Fibs()
    for x in fibs:
        if x>10:
            break
        else:
            print x,
    print

def test_Fibs_no2():
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

def test_FibsMax():
    try:
        for x in FibsMax(6):
            print x,
    except:pass
    print

def FibsMax_fun(max):
    n,a,b = 0,0,1
    while n<max:
        print b,
        a,b = b, a+b
        n = n+1

def test_FibsMax_fun():
    FibsMax_fun(6)
    print

def FibsMax_fun2(max):
    n,a,b = 0,0,1
    while n<max:
        yield b
        a,b = b, a+b
        n = n+1

def test_FibsMax_fun2():
    for x in FibsMax_fun2(6):
        print x,
    print

if __name__=='__main__':
    test_Fibs()
    test_Fibs_no2()
    test_FibsMax()
    test_FibsMax_fun()
    test_FibsMax_fun2()
