
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_assignment_security_SecurityHelper_appId(JNIEnv *env, jobject instance) {
 return (*env)->  NewStringUTF(env, "60c6fbeb4b93ac653c492ba806fc346d");
}