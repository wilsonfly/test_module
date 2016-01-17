#!/bin/sh

find . -type f | xargs grep "tombstones"
find . -type f | xargs grep "ANR "
find . -type f | xargs grep "am_anr"
find . -type f | xargs grep "WATCHDOG"
find . -type f | xargs grep "FATAL"
find . -type f | xargs grep " backtrace"
find . -type f | xargs grep "am_crash"
find . -type f | xargs grep "DEATH"
find . -type f | xargs grep "ServiceManager: service "

#find . -type f | xargs grep "Long monitor contention event with owner"

#cat logcat.log.3|grep -E "SignalSourceManager: MainActivity – onResume|ANR|am_anr|DEBUG|died"
#find . -type f |cat *| grep -E "SignalSourceManager: MainActivity – onResume|ANR|am_anr|DEBUG|died"
