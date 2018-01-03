//
// Created by joseph on 2018/1/2.
//

#include <jni.h>
#include <iostream>

using namespace std;

extern "C"{
JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Demo2_getNUM(JNIEnv *env, jclass type_, jstring s){
     env->GetStringUTFChars(s, false);
}


//   1.jni 调用c++时,c++方法要加上 extern"C"{} ,否则报错java.lang.UnsatisfiedLinkError: No implementation found
//
//   2.  no "I" field "name" in class "Ljava/lang/Class
//   当java 中的native 方法为 static 的时候  jobject demo2 代表的是 Demo2.class 对象;
//   java native方法非静态时 jobject demo2 即为 jobject;    tip:jclass jobject 在jni中类型一致,所以未报错
JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Demo2_sayHello(JNIEnv *env,jobject demo2){
     jclass clazz = env->GetObjectClass(demo2);

     jfieldID id_num = env->GetFieldID(clazz,"num","I");
     jint num = env->GetIntField(demo2,id_num);
     cout << num <<endl;
     env -> SetIntField(demo2,id_num,100L);
}


}
