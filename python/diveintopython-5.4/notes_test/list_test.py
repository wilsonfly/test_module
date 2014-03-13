
li = ['aa',"bb",'cc']
print "\n".join(li)

print li
dir(li)

li.append('bb')
print li
li.append("bb")
print li
li.count('d')
print li
li.extend(['11','22'])
print li
li.index('22')
print li
i=li.index('bb')
print i
li.insert(1,'abc')
print li
li.pop()
print li
li.pop(2)
print li

"""remove element from list,the first one be found"""
"""li.remove(2)"""
li.remove('bb')
print li
li.reverse()
li.sort()

