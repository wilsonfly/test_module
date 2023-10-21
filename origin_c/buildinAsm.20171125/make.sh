gcc -c -fno-builtin test.c
ld -static -e myMain -o test test.o
