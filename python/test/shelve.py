#!/usr/bin/env python

import sys
import shelve

def store_persion(db):
    pid = raw_input("Enter unique ID:")
    persion={}
    persion['name']=raw_input("Enter the name:")
    persion['age']=raw_input("Enter the age:")
    persion['phone']=raw_input("Enter the phone number:")
    db[pid]=persion

def lookup_persion(db):
    pid = raw_input("Enter unique ID:")
    field = raw_input("What do you want to know?(name,age,phone)")
    field= field.strip().lower()
    print field.capitalize() + ":" + db[pid][field]

def enter_command():
    cmd = raw_input("Enter the command:")
    return cmd.strip().lower()

def show_usage():
    print "Usage:"
    print "store    :Store the new information about a persion"
    print "lookup   :Lookup a persion through the ID"
    print "quit     :Exit the programe"
    print "?        :Show the Usage"

def main():
    #database = shelve.open('C:\\dbByShelve.db')
    database = shelve.open('dbByShelve.db')
    #database = shelve.open('./dbByShelve.db','c',None,False,False)
    try:
        cmd = enter_command()
        if cmd == 'store':
            store_persion(database)
        elif cmd == 'lookup':
            lookup_persion(database)
        elif cmd == 'quit':
            return
        elif cmd == '?':
            show_usage()
        else:
            print "Invalid cmd!"

    finally:
         database.close()

if __name__ == '__main__':
    main()

