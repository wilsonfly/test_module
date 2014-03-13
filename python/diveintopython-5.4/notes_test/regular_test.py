
import re

s = '100 BROAD ROAD APT. 3'
a = re.sub('\bROAD\b','RD',s)
print a

"""\ in \b need to be transferred! what the fuck!"""
a = re.sub('\\bROAD\\b','RD',s)
print a

a = re.sub(r'\bROAD\b','RD',s)
print a
print


a = ()
phonePattern = re.compile(r'^\D*(\d{3})\D*(\d{3})\D*(\d{4})\D*(\d*)$')
"""ok"""
a =  phonePattern.search('(800)5551212 ext. 1234').groups()
print a

"""ok"""
a = phonePattern.search('800-555-1212').groups()
print a

"""fail"""
a = phonePattern.search('work 1-(800) 555.1212 #1234')
print a

"""Nome cannot use method groups"""
"""phonePattern.search('work 1-(800) 555.1212 #1234').groups()"""
print 


"""Final version,all pattern of phone-number are ok"""
phonePattern = re.compile(r'(\d{3})\D*(\d{3})\D*(\d{4})\D*(\d*)$')
a = phonePattern.search('work 1-(800) 555.1212 #1234').groups()
print a

a = phonePattern.search('80055512121234').groups()
print a

"""get empty element '' for no last four numbers"""
a = phonePattern.search('800-555-1212').groups()
print a

"""match info,get tuple when use groups"""
a = phonePattern.search('800-555-1212')
print a
print


"""re.VERBOSE main the not strict mode of regular"""
phonePattern = re.compile(r'''
		# don't match beginning of string, number can start anywhere
		(\d{3}) # area code is 3 digits (e.g. '800')
		\D* # optional separator is any number of non-digits
		(\d{3}) # trunk is 3 digits (e.g. '555')
		\D* # optional separator
		(\d{4}) # rest of number is 4 digits (e.g. '1212')
		\D* # optional separator
		(\d*) # extension is optional and can be any number of digits
		$ # end of string
		''', re.VERBOSE)
a = phonePattern.search('work 1-(800) 555.1212 #1234').groups()
print a
a = phonePattern.search('800-555-1212').groups()
print a
