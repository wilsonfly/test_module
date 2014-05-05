##############################################
# Author:	Wilson Sun                       #
# history:	2014-03-28 created               #
##############################################

include $(BUILD_PATH)/common.mk

TARGET = $(TARGET_MODULE)

exe_main:
	$(CC) $(SRC_FILES) -o $(TARGET) $(CFLAGS) $(LINK_SHARED_LIBS) $(LINK_STATIC_LIBS)
	@chmod 777 $(TARGET)
	@echo "create $(TARGET)"

include $(BUILD_PATH)/final.mk
