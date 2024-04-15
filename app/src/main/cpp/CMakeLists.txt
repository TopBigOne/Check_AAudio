# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.22.1)

# Declares and names the project.

project("check_aaudio")

# Debug utilities
set(DEBUG_UTILS_PATH "../debug-utils")
set(DEBUG_UTILS_SOURCES ${DEBUG_UTILS_PATH}/trace.cpp)

# Code shared between AAudio samples
set(AAUDIO_COMMON_PATH "../common")
set(AAUDIO_COMMON_SOURCES ${AAUDIO_COMMON_PATH}/audio_common.cpp)


add_library( # Sets the name of the library.
        ${PROJECT_NAME}

        # Sets the library as a shared library.
        SHARED

        StreamEngine.cpp
        ${DEBUG_UTILS_SOURCES}
        ${AAUDIO_COMMON_SOURCES}
        jni_bridge.cpp)


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_include_directories(
        ${PROJECT_NAME}
        PRIVATE
        ${AAUDIO_COMMON_PATH}
        ${DEBUG_UTILS_PATH})

target_link_libraries( # Specifies the target library.
        ${PROJECT_NAME}

        atomic
        aaudio
        ${log-lib})