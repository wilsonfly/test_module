##############################################
# Author:	Wilson Sun                       #
# history:	2014-03-28 created               #
##############################################

CC = gcc
CXX = g++
AR = ar
CCSUFIX =cpp

ifeq ($(SUPPORT_DEBUG),n)
CFLAGS = 
else
CFLAGS = -DDEBUG
endif

INCLUDE_DIR += -I$(LOCAL_PATH)	\
				-I$(LOCAL_PATH)/../include

CFLAGS += $(INCLUDE_DIR)

SUPPORT_DEBUG_LEVEL ?= 0
CFLAGS += -DDEBUG_LEVEL=$(SUPPORT_DEBUG_LEVEL)

CFLAGS += -W
BUILD_OUT_PATH = $(LOCAL_PATH)/../out


OBJ = $(patsubst %.c,$(BUILD_OUT_PATH)/%.o,$(patsubst %.$(CCSUFIX),$(BUILD_OUT_PATH)/%.o,$(SRC_FILES))) 

#$(BUILD_OUT_PATH)/%.o: %.c
$(BUILD_OUT_PATH)/%.o: $(SRC_FILES)
	@mkdir -p $(dir $@)
	@${CC} $(CFLAGS) -c $< -o $@

$(BUILD_OUT_PATH)/%.o: %.$(CCSUFIX)
	@mkdir -p $(dir $@)
	@${CXX} $(CXXFLAGS) -c $< -o $@

