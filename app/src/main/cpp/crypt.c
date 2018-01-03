//
// Created by joseph on 2017/11/9.
//

#include <stdio.h>
#include <string.h>
#include <jni.h>

//用于加密解密的密码
char password[] = "Li Jia Sheng";


JNIEXPORT int JNICALL Java_com_example_joseph_ndkdemo_Crytor_getMaxOrMin(JNIEnv *env,jobject object,jintArray array,jboolean max){
    jint * int_array = (*env)->GetIntArrayElements(env,array,NULL);
    int lenArray = (*env)->GetArrayLength(env,array);

    int tmp = int_array[0];
    for(int i = 1;i < lenArray;i++){
        if(max){
            if(tmp < int_array[i]){
                  tmp = int_array[i];
            }
        }else{
            if(tmp > int_array[i]){
                tmp = int_array[i];
            }
        }
    }

    (*env)->ReleaseIntArrayElements(env,array,int_array,0);

    return tmp;
}



JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Crytor_cryptFile(JNIEnv *env,
                                         jclass type,jstring src_,jstring dest_){

    const char *c_src = (*env)->GetStringUTFChars(env,src_,NULL);
    const char *c_dest = (*env)->GetStringUTFChars(env,dest_,NULL);

    FILE *f_read = fopen(c_src,"rb");
    FILE *f_write = fopen(c_dest,"wb");

    //判断文件是否正确打开
    if(f_read == NULL || f_write == NULL){
        printf("file open filed");
        return;
    }

    //一次读取一个字符
    int ch;
    int i = 0;
    int pwd_len = strlen(password);
    while ((ch = fgetc(f_read)) != EOF){
         //通过异或运算进行加密
        fputc(ch ^ password[i % pwd_len],f_write);

        fputc(ch ^ password[i % pwd_len],f_write);
        i++;
    }

    //关闭文件

    fclose(f_read);
    fclose(f_write);

    (*env)->ReleaseStringUTFChars(env,src_,c_src);
    (*env)->ReleaseStringUTFChars(env,dest_,c_dest);
}

JNIEXPORT void JNICALL Java_com_example_joseph_ndkdemo_Crytor_decryptFile(JNIEnv *env,
             jclass type,jstring src_,jstring dest_) {

    const char *c_src = (*env)->GetStringUTFChars(env,src_,NULL);
    const char *c_dest = (*env)->GetStringUTFChars(env,dest_,NULL);

    FILE *f_read = fopen(c_src,"rb");
    FILE *f_write = fopen(c_dest,"wb");

    //判断文件是否正确打开
    if(f_read == NULL || f_write == NULL){
        printf("file open filed");
        return;
    }

    //一次读取一个字符
    int ch;
    int i = 0;
    int pwd_len = strlen(password);
    while ((ch = fgetc(f_read)) != EOF){
        //通过异或运算进行加密
        fputc(ch ^ password[i % pwd_len],f_write);
        i++;
    }

    //关闭文件

    fclose(f_read);
    fclose(f_write);

    (*env)->ReleaseStringUTFChars(env,src_,c_src);
    (*env)->ReleaseStringUTFChars(env,dest_,c_dest);
}


JNIEXPORT jstring JNICALL
Java_com_example_joseph_ndkdemo_Crytor_getName(JNIEnv *env,jobject this){
    return (*env)->NewStringUTF(env,"!!阿胜!!");
}


JNIEXPORT void JNICALL
Java_com_example_joseph_ndkdemo_Crytor_testMethod1(JNIEnv *env, jclass type) {

}