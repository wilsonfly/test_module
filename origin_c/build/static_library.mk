##############################################
# Author:	Wilson Sun                       #
# history:	2014-03-28 created               #
##############################################

include $(BUILD_PATH)/common.mk

TARGET = lib$(TARGET_MODULE).a
static_main:$(OBJ)
	$(AR) rc $(TARGET) $(OBJ)
	@chmod 777 $(TARGET)
	@echo "create $(TARGET)"

include $(BUILD_PATH)/final.mk
