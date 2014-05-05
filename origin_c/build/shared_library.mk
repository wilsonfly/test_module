##############################################
# Author:	Wilson Sun                       #
# history:	2014-03-28 created               #
##############################################

include $(BUILD_PATH)/common.mk

TARGET = lib$(TARGET_MODULE).so
CFLAGS += -shared -fpic
shared_main:$(OBJ)
	$(CC) $(SRC_FILES) -o $(TARGET) $(CFLAGS) $(LINK_STATIC_LIBS)
	@chmod 777 $(TARGET)
	@echo "create $(TARGET)"

include $(BUILD_PATH)/final.mk
