#!/usr/bin/env python

import re

m = re.match('www\.(.*)\..{3}','www.python.org')
print m.group(0)
print m.group(1)
print m.start(1)
print m.end(1)
print m.span(1)


#do not match *, not greedy
emphasis_pattern=r'\*([^\*]+)\*'
print 'no1:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')

#greedy
emphasis_pattern=r'\*(.+)\*'
print 'no2:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')

#not greedy
emphasis_pattern=r'\*(.+?)\*'
print 'no3:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')

#greedy
emphasis_pattern=r'\*(.*)\*'
print 'no4:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')

#greedy
emphasis_pattern=r'\*(.*?)\*'
print 'no5:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')

#greedy
emphasis_pattern=re.compile(r'''
    \*      #
    (       #
    .*?     #
    )       #
    \*      #
    ''', re.VERBOSE)
print 'no6:'+re.sub(emphasis_pattern,r'<em>\1</em>','*this* is *it*!')
