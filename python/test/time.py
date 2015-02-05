#!/usr/bin/env python

import time
import random

date1=(2014,12,28,10,14,30,6,300,0)
print date1

time_sec=time.mktime(date1)
print time_sec

time_local=time.localtime(time_sec)
print time_local

time_asc=time.asctime(time_local)
print time_asc

time_sec2=time.mktime(time_local)
print time_sec2

random_time=random.uniform(time_sec,time_sec+1000)
print time.asctime(time.localtime(random_time))
