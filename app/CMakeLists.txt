cmake_minimum_required(VERSION 3.4.1)

#配置工程路径
set(path_project C:/Users/joseph/Downloads/aip-ocr-android-sdk-1.3.2/aip-ocr-android-sdk-1.3.2/NdkDemo)

add_library( crypt
             SHARED
             src/main/cpp/crypt.c )

add_library( demo1
             SHARED
             src/main/cpp/demo1.cpp)

add_library( demo2
             SHARED
             src/main/cpp/demo2.cpp)

add_library(effect-lib
            SHARED
            src/main/cpp/effect_fix.cpp
            )


add_library( fmod
             SHARED
             IMPORTED
            )

add_library( libfmodL
             SHARED
             IMPORTED
            )


set_target_properties(  fmod
                        PROPERTIES IMPORTED_LOCATION
                         ${path_project}/app/src/main/jniLibs/${ANDROID_ABI}/libfmod.so
                      )

set_target_properties(
                        libfmodL
                        PROPERTIES IMPORTED_LOCATION
                         ${path_project}/app/src/main/jniLibs/${ANDROID_ABI}/libfmodL.so
                    )

find_library(
              log-lib
              log )

#将若干库文件链接到生成的目标effect-lib
target_link_libraries(
            effect-lib
            ${log-lib}
            fmod
            libfmodL
            )

#将若干库文件链接到生成的目标demo1
target_link_libraries(
            demo1
            ${log-lib}
            )