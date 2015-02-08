#include <jni.h>
#include <stdio.h>
#include "HelloWorld.h"

/*
 * Class:     HelloWorld
 * Method:    print
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_HelloWorld_print
  (JNIEnv * env, jobject obj)
{
	printf("Hello World,I'm from jni_world !!!\n");
	return ;
}

/*
 * Class:     HelloWorld
 * Method:    lprint
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_HelloWorld_lprint
  (JNIEnv *env, jobject obj, jstring content)
{
	const jbyte *str = (const jbyte *)(*env)->GetStringUTFChars(env, content, JNI_FALSE);
	printf("Hello World, jni receive msg:%s \n", str);
	(*env)->ReleaseStringUTFChars(env, content, (const char*)str);
	return;
}


/*
 * Class:     HelloWorld
 * Method:    getLine
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_HelloWorld_getLine
  (JNIEnv *env, jobject obj, jstring content)
{
	char buf[64];
	const jbyte *str = (const jbyte *)(*env)->GetStringUTFChars(env, content, NULL/*JNI_FALSE*/);
	if( !str )
		return NULL;
	printf("Cmd from java_world:\n%s \n", str);
	(*env)->ReleaseStringUTFChars(env, content, str);
	fgets(buf, sizeof(buf), stdin);
	return (*env)->NewStringUTF(env, buf);
}


