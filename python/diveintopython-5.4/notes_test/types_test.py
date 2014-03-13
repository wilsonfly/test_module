
"""dictionary"""
d = {"aa":"bb","cc":"dd"}
print d
print d["aa"]
d["cc"]="ee"
print d
d["Cc"]="ff"
print d
d["gg"]=3
print d
d[4]="gg"
print d
del d[4]
print d
d.clear()
print d
print dir(d)
print

"""list"""
li = ["a","b","c","d"]
print li[0]
print li[-1]
print li[0:2] #[0],[1] and without[2]
print li[:2]
print li[0:-2]
print li[:2]
print li[2:]
print li[:]
li.append("e")
print li
li.insert(2,"new")
print li
li.extend(["f","g"])
print li
print len(li)
print li[-1]
li.append(["aa","bb"]) #the para of append be treated as one element
print len(li)
print li[-1]
print li.index("f")
print "xyz" in li
li.remove("f")  #remove the first one
print li
print li.pop()
print li
li = li+["aa","bb"]
print li
li += ["cc"]
print li
li = [1,2]*3
print li
print dir(d)
print

"""tuple"""
t = ("a","b","c")
print t[0]
print t[0:2]
#t.extend("d") #no such method for tuple
#t.append("d") #no such method for tuple
print t.index("c")
print "a" in t
