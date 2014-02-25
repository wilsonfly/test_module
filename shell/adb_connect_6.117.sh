#!/bin/sh
set -v
adb disconnect
adb connect 172.16.6.117:5555
adb remount
