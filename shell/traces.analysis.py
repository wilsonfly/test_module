#!/usr/bin/python

import sys
import os
import re

def main():
    level = 0
    if (len(sys.argv) < 3):
        print('please enter trace file and symbols path!')
        print(sys.argv[0].split('/')[-1], '[trace] [symbols]')
        return
    elif (len(sys.argv) == 3):
        level = 0
    else:
        level = 1

    a2lCmd = ' '
    procNum = 0
    traceFile = open(sys.argv[1], 'r')
    for line in traceFile:
        print(line.strip('\n'))
        matchArch = re.match(r'ABI: ', line)
        if matchArch:
            procNum += 1
            if (procNum == 2 and level == 0):
                return

            if re.search('arm', line):
                a2lCmd = 'arm-linux-androideabi-addr2line'
            elif re.search('arm64', line):
                a2lCmd = 'aarch64-linux-android-addr2line'

        matchObj = re.search(r'#\d{2} pc (\w{8})  (/.*)', line)
        if matchObj:
            dumpAddr = matchObj.group(1)
            dumpLib = matchObj.group(2).split(' ')[0]
            dumpCmd = a2lCmd + ' -e ' + sys.argv[2] + dumpLib + ' ' + dumpAddr
            #os.system(dumpCmd)
            output = os.popen(dumpCmd)
            print(output.read())

    traceFile.close()

if __name__ == '__main__':
    main()