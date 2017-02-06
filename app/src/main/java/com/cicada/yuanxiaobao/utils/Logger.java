package com.cicada.yuanxiaobao.utils;

import android.util.Log;

import com.cicada.yuanxiaobao.BuildConfig;

/**
 * 日志
 */
public class Logger {

    public static boolean isDebug = BuildConfig.DEBUG;
    public static final String TAG = "HOT_TAG";

    public static void e(String msg) {
        if (isDebug)
            Log.d(TAG, msg);

    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }
}
