#include <jni.h>
#include <string>

#include <logging_macros.h>
#include "StreamEngine.h"

static StreamEngine *engine = nullptr;

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_better_aaudio_AAudioApi_init(JNIEnv *env, jclass clazz) {
    if (engine == nullptr) {
        engine = new StreamEngine();
    }

    return (engine != nullptr);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_better_aaudio_AAudioApi_start(JNIEnv *env, jclass clazz) {
    if (engine == nullptr) {
        LOGE("Engine is null, you must call createEngine before calling this method");
        return;
    }

    engine->start();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_better_aaudio_AAudioApi_stop(JNIEnv *env, jclass clazz) {
    if (engine == nullptr) {
        LOGE("Engine is null, you must call createEngine before calling this method");
        return;
    }

    engine->stop();
}
extern "C"
JNIEXPORT void JNICALL
Java_com_better_aaudio_AAudioApi_writeBuffer(JNIEnv *env, jclass clazz, jshortArray arr) {
    if (engine == nullptr) {
        LOGE("Engine is null, you must call createEngine before calling this method");
        return;
    }

    jshort *body = (env)->GetShortArrayElements(arr,JNI_FALSE);

    engine->writeBuffer(body);
}
extern "C"
JNIEXPORT void JNICALL
Java_com_better_aaudio_AAudioApi_delete(JNIEnv *env, jclass clazz) {
    if (engine){
        delete engine;
        engine = nullptr;

    }



}