package com.cicada.yuanxiaobao.utils;

import android.widget.Toast;

import com.cicada.yuanxiaobao.common.MyApplication;

/**
 * Toast 工具类
 */
public class ToastUtil {

    public static void showShort( String msg) {
        Toast.makeText(MyApplication.getInstance(),msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(MyApplication.getInstance(),msg, Toast.LENGTH_LONG).show();
    }
}
