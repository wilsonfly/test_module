#!/usr/bin/env python
def showInfo(person):
    print 'will show the info of person:',person['age']
    print 'age:', person['age']
    try:
        print 'occupation:' + person['occupation']
    except KeyError:pass
    else:
        print 'all go well'
    finally:
        print '--------------------over'

def showInfo_new(person):
    print 'will show the info of person:',person['age']
    print 'age:', person['age']
    try:
        print 'occupation:',person['occupation'] #something will print befor exception
    except KeyError:pass
    else:
        print 'all go well'
    finally:
        print '--------------------over'

person1={'age':'33', 'occupation':'it'}
person2={'age':'22'}

showInfo(person1)
showInfo(person2)

showInfo_new(person1)
showInfo_new(person2)
