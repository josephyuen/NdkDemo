//
// Created by joseph on 2017/12/26.
//

#include <jni.h>
#include <iostream>
#include <android/log.h>

//日志输出宏定义
#define LOGI(FORMAT, ...) __android_log_print(ANDROID_LOG_INFO,"jason",FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT, ...) __android_log_print(ANDROID_LOG_ERROR,"jason",FORMAT,##__VA_ARGS__);

using namespace std;
extern "C" {

JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Demo1_doit(JNIEnv *env,jclass type){
    return;
}


JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Demo1_outPut(JNIEnv *env,jclass type) {
    string str;
    cin >> str;
    cout << str << endl;
    LOGI("c++输出:%s", str.c_str());
    return ;
}



 int sum();

int sum(){
    int i, sum=0;
    for(i=1; i<=100; i++){
        sum+=i;
    }
    return sum;
}

}