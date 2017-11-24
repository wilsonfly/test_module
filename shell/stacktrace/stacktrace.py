#!/usr/bin/env python
#python script for android tombstone file parser

#todo: stack parser
#todo: remote symbols download auto
#todo: unwind art backtrace

import getopt
import os
import re
import string
import sys
import getpass
import urllib
import shlex,subprocess

ADDR2LINE32 = 'arm-linux-androideabi-addr2line -Cife'
ADDR2LINE64 = 'aarch64-linux-android-addr2line -Cife'

def execute_blocked(cmd):
    proc = subprocess.Popen(shlex.split(cmd),stdout=subprocess.PIPE,stderr=subprocess.PIPE)
    out,err = proc.communicate()
    if proc.returncode == 0:
        return out
    else:
        return err

def addr2line(path,lib,addr):
    if lib != "":
        if len(addr) == 8:
            addr2l = ADDR2LINE32
        else:
            addr2l = ADDR2LINE64
        cmd =  addr2l+ path + lib + " 0x" + addr
        out = execute_blocked(cmd)
        return out
    else:
        return "Not lib defined"

def unwind_backtrace(path,backtrace,outputfile):
    outputfile.write("\n    <================= stacktrace begin =================>    \n\n")
    for line,addr,lib in backtrace:
        out = addr2line(path,lib,addr)
        outputfile.write(line)
        outputfile.write(out)

    outputfile.write("\n     <================= stacktrace end =================>    \n")

def parser_file(inputfile,outputfile,symbol_path):

    BACKTRACE_LINE = re.compile(r'^.+?\s+([0-9a-fA-F]{8,16})[ ]+(/.+?)[\n| ]')
    bt = []
    found_backtrace = False
    for line in inputfile.readlines():
        if BACKTRACE_LINE.search(line):
            found_backtrace = True
            match = BACKTRACE_LINE.match(line)
            groups = match.groups()
            bt.append((line,groups[0],groups[1]))
        else:
            if found_backtrace == True:
                break

    unwind_backtrace(symbol_path,bt,outputfile)

def usage():
    print
    print "  Usage: " + sys.argv[0] + " [options] [FILE]"
    print
    print "  -s|--symbols=path"
    print "       default=pwd"
    print "       the path to a symbols dir, such as out/target/product/dream/symbols"
    print
    print "  -o|--output=filename"
    print "       default=stdout"
    print "       the filename for analyze result out put"
    print
    print "  FILE should contain a stack trace in it somewhere"
    print "       the tool will find the stack and translate address to:"
    print "           function"
    print "           file:line"
    print "       function, source files and line numbers. "  
    print "       If you don't pass FILE, or if file is -, it reads from stdin,"
    print "       and end with ctrl+d"
    print "       If there are a few of callstacks, first one is the target."
    print
    sys.exit(1)

def main():
    try:
        options, arguments = getopt.getopt(sys.argv[1:], "s:o:h",
                             ["symbols=","output=","help"])
    except getopt.GetoptError, error:
        usage()
    symbol = "."
    output = ""
    for option, value in options:
        if option in ("-h", "--help"):
            usage()
        elif option in ("-s", "--symbols"):
            symbol = value
        elif option in ("-o", "--output"):
            output = value

    if len(arguments) > 1:
        usage()

    if len(arguments) == 0 or arguments[0] == "-":
        print "Please input native crash log:  (eof = Ctrl+d)"
        inputfile = sys.stdin
    else:
        print "Searching for native crashes in: %s" % arguments[0]
        inputfile = open(arguments[0], "r")

    outputfile = sys.stdout
    if output != "":
        outputfile = open(output,"a+")
    
    parser_file(inputfile,outputfile,symbol)

    if output != "":
        print "Wrote stacktrace to file: %s" % output
    inputfile.close()
    outputfile.close()
    sys.exit(0)

if __name__ == '__main__':
    main()
