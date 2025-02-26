package com.better.aaudio;
/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class AAudioApi {



    // Load native library
    static {
        System.loadLibrary("check_aaudio");
    }


    // Native methods
    native  static  boolean init();

    static native void start();

    static native void stop();
    static native void delete();

    static native void writeBuffer(short[] buffer);
}
