#include <jni.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>
/* Header for class net_sunniwell_HelloWorld_jni_test_HelloWorld */

#ifdef __cplusplus
extern "C" {
#endif

//#define mClassName "net/sunniwell/HelloWorld_jni_test/HelloWorld"
#define mClassName "net/wilson/HelloWorld_jni_test/HelloWorld"

/*
 * Class:     net_sunniwell_HelloWorld_jni_test_HelloWorld
 * Method:    print
 * Signature: ()V
 */
//JNIEXPORT void JNICALL Java_net_sunniwell_HelloWorld_1jni_1test_HelloWorld_print
//  (JNIEnv * env, jobject obj)
JNIEXPORT void JNICALL native_print
  (JNIEnv *env, jclass clazz)
{
	printf("Hello World,I'm from jni_world !!!\n");
	return ;
}

JNIEXPORT void JNICALL native_lprint
  (JNIEnv *env, jclass clazz, jstring input)
{
	const jbyte *str = (const jbyte*)(*env)->GetStringUTFChars(env, input, NULL);
	printf("Hello World,I'm from jni_world !!!,receive msg:%s\n", str);
	(*env)->ReleaseStringUTFChars(env, input, str);
	return ;
}

JNIEXPORT jstring JNICALL native_getline
  (JNIEnv *env, jclass clazz, jstring input)
{
	char buf[64];
	const jbyte *str = (const jbyte*)(*env)->GetStringUTFChars(env, input, NULL);
	printf("Hello World,I'm from jni_world !!!,receive msg:%s\n", str);
	(*env)->ReleaseStringUTFChars(env, input, str);
	
	memset(buf,0,sizeof(buf));
	fgets(buf,sizeof(buf),stdin);
	return (*env)->NewStringUTF(env, buf);
}

static const JNINativeMethod gMethods[] =
{
	{ "print",		"()V",										(void*)native_print		},
	{ "lprint",		"(Ljava/lang/String;)V",					(void*)native_lprint	},
	{ "getLine",	"(Ljava/lang/String;)Ljava/lang/String;",	(void*)native_getline	},
};

static int registerMethods(JNIEnv* env)
{
	jclass clazz;

	clazz = (*env)->FindClass(env,mClassName);
	if(clazz == NULL)
	{
		printf("[%s,%d] not find class:%s\n",__FUNCTION__,__LINE__, mClassName);
		return -1;
	}
	if((*env)->RegisterNatives(env,clazz,gMethods,sizeof(gMethods)/sizeof(gMethods[0])) != JNI_OK)
	{
		printf("[%s,%d] registerNativeMethods failed\n",__FUNCTION__,__LINE__);
		return -1;
	}
	return 0;
}

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
	JNIEnv* env = NULL;
	jint result = -1;

	printf("[%s,%d] begin\n",__FUNCTION__,__LINE__);
	if((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_4) != JNI_OK)
	{
		printf("[%s,%d] GetEnv failed\n",__FUNCTION__,__LINE__);
		goto FAIL;
	}
	
	assert(env != NULL);
	if(registerMethods(env) != 0)
	{
		printf("[%s,%d] registerMethods failed\n",__FUNCTION__,__LINE__);
		goto FAIL;
	}
	result = JNI_VERSION_1_4;
	printf("[%s,%d] ok\n",__FUNCTION__,__LINE__);
FAIL:
	return result;
}
#ifdef __cplusplus
}
#endif
