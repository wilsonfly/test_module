##############################################
# Author:	Wilson Sun                       #
# history:	2013-05-23 created               #
##############################################

BIN = a.out

SRC = test.c

#ifeq ($(SUPPORT_DEBUG),y)
CFLAGS = -DDEBUG
#endif

CFLAGS += -I.	\
		  -I../include

SUPPORT_DEBUG_LEVEL ?= 0
CFLAGS += -DDEBUG_LEVEL=$(SUPPORT_DEBUG_LEVEL)
CFLAGS += -W

main:
	gcc $(SRC) -o $(BIN) $(CFLAGS)
	@chmod 777 $(BIN)

clean:
	rm *.o $(BIN) -f

