
BIN = test_llog

SRC = test_llog.c

ifeq ($(SUPPORT_DEBUG),y)
CFLAGS = -DDEBUG
endif

CFLAGS += -I.	\
		  -I./include

SUPPORT_DEBUG_LEVEL ?= 99
CFLAGS += -DDEBUG_LEVEL=$(SUPPORT_DEBUG_LEVEL)

main:
	gcc $(SRC) -o $(BIN) $(CFLAGS)
	@chmod 777 $(BIN)

clean:
	rm *.o $(BIN) -f

