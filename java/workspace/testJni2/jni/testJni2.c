#include <jni.h>
#include <String.h>
#include <android/log.h>

#define TAG "AccessNative"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

/*
 * Class:     com_wilsonflying_testjni2_AccessNative
 * Method:    readStringFromJNI
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wilsonflying_testjni2_AccessNative_readStringFromJNI
  (JNIEnv * env, jobject obj){
	return (*env)->NewStringUTF(env, "hello, message from native");
}

/*
 * Class:     com_wilsonflying_testjni2_AccessNative
 * Method:    putStringFromJNI
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_wilsonflying_testjni2_AccessNative_putStringFromJNI
  (JNIEnv * env, jobject obj, jstring str){
	jbyte* str_ok = "3Q,received msg:";
	jbyte* str_null = "ops,receive nothing";
	jbyte buf[64];

	jbyte* buf_get = (jbyte*)(*env)->GetStringUTFChars(env, str, NULL);
	if( buf != NULL){
		printf("get string from Activity:%s",buf);//logcat看不到
		__android_log_print(ANDROID_LOG_INFO, TAG, "收到 Activity 的消息:%s", buf);//乱码，不能%s打印jbyte
		LOGD("收到 Activity 的消息:%s", buf);//简洁、实用

		strncpy(buf, str_ok, sizeof(buf));
		strncat(buf, buf_get, sizeof(buf)-(strlen(str_ok)+1));
	}else{
		strcpy(buf, str_null);
	}


	(*env)->ReleaseStringUTFChars(env, str, buf_get);
	return (*env)->NewStringUTF(env, buf);
}

/*
 * Class:     com_wilsonflying_testjni2_AccessNative
 * Method:    getSum
 * Signature: (ID)D
 */
JNIEXPORT jdouble JNICALL Java_com_wilsonflying_testjni2_AccessNative_getSum
  (JNIEnv * env, jobject obj, jint x, jdouble y)
{
	LOGD("%d + %lf = %lf", x, y, x+y);
	return x+y;
}

/*
 * Class:     com_wilsonflying_testjni2_AccessNative
 * Method:    callBackVoid
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_wilsonflying_testjni2_AccessNative_callBackVoid
  (JNIEnv * env, jobject obj){
	jclass cls = (*env)->GetObjectClass(env, obj);
	jmethodID mid = (*env)->GetStaticMethodID(env, cls, "showToast", "()V");
	if( mid == NULL)
	{
		LOGD("methodid is null, do nothing");
		return;
	}

	(*env)->CallStaticVoidMethod(env, cls, mid);
	return;
}

/*
 * Class:     com_wilsonflying_testjni2_AccessNative
 * Method:    GetMemVar
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_wilsonflying_testjni2_AccessNative_GetMemVar
  (JNIEnv * env, jobject obj){
	jclass cls = (jclass)(*env)->GetObjectClass(env, obj);
	jfieldID id = (jfieldID)(*env)->GetStaticFieldID(env, cls, "age", "I");
	if(id == NULL)
	{
		LOGD("get filed id is null, do noting");
		return;
	}

	jint num = (jint)(*env)->GetStaticIntField(env, cls, id);

	LOGD("get member variable age:%d", num);

	return;
}
