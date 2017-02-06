package com.cicada.yuanxiaobao.common;

import android.app.Application;

import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.Logger;

/**
 * Created by tanglei on 16/6/15.
 */
public class MyApplication extends Application {

    private static MyApplication sMyApplication = null;
    //帐号
    public static String account;



    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication = this;
        Logger.i("w= " + DeviceUtils.getScreenWidth(this) + " h=" + DeviceUtils.getScreenHeight(this) + " 密度  =" + DeviceUtils.getDensity(this)+"  dp2px= "+ DeviceUtils.dip2px(this,50));
    }

    public static MyApplication getInstance() {
        return sMyApplication;
    }

}
