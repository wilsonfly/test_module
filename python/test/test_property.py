#!/usr/bin/env python

__metaclass__ = type
class Rectangle:
    def __init__(self):
        self.width=0
        self.height=0
    def setSize(self,size):
        self.width,self.height=size
    def getSize(self):
        return self.width,self.height
    size = property(getSize,setSize)

r=Rectangle()
r.width=10
r.height=20
print r.size
r.size=100,200
print r.width
print r.size
