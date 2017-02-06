package com.cicada.yuanxiaobao.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.cicada.yuanxiaobao.common.MyApplication;

/**
 * Created by tanglei on 16/6/30.
 * 查数据缓存
 */
public class AppSelectSP {

    static SharedPreferences sp;

    static {
        sp = MyApplication.getInstance().getSharedPreferences("AppSelectSP", Context.MODE_PRIVATE);
    }

    public static void putString(String k, String v) {
        sp.edit().putString(k, v).commit();
    }

    public static String getString(String k) {
        String v = sp.getString(k, null);
        return v;
    }
}
