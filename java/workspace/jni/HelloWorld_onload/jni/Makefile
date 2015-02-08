##############################################
# Author:	Wilson Sun                       #
# history:	2013-05-23 created               #
##############################################

LOCAL_PATH := $(shell pwd)
#BUILD_PATH := $(LOCAL_PATH)/../build/
BUILD_PATH := $(LOCAL_PATH)/../../../../../origin_c/build/
include $(BUILD_PATH)/config.mk


#include $(CLEAR_ENV)
#TARGET_MODULE = sample_test
#SRC_FILES = test.c
INCLUDE_DIR = 
#include $(BUILD_EXECUTABLE)


include $(CLEAR_ENV)
TARGET_MODULE = HelloWorld_jni
SRC_FILES = HelloWorld.c
INCLUDE_DIR := -I$(LOCAL_PATH)/../java \
	-I/usr/lib/jvm/jdk1.6.0_34/include/ \
	-I/usr/lib/jvm/jdk1.6.0_34/include/linux
include $(BUILD_SHARED_LIBRARY)

#include $(CLEAR_ENV)
#TARGET_MODULE = sample_test_shared
#SRC_FILES = test_shared.c
#LINK_SHARED_LIBS = \
	-L$(LOCAL_PATH)/ \
	-lfunc_shared
#include $(BUILD_EXECUTABLE)

#include $(CLEAR_ENV)
#TARGET_MODULE = func_static
#SRC_FILES = func_static.c
#include $(BUILD_STATIC_LIBRARY)

#include $(CLEAR_ENV)
#TARGET_MODULE = sample_test_static
#SRC_FILES = test_static.c
#LINK_STATIC_LIBS = \
	$(LOCAL_PATH)/libfunc_static.a
#include $(BUILD_EXECUTABLE)

