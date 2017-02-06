package com.cicada.yuanxiaobao.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cicada.yuanxiaobao.utils.Logger;

/**
 * Created by tanglei on 16/7/7.
 * 网络监听广播
 */
public class NetBroadcastReceiver extends BroadcastReceiver {

    private static boolean isNetworkAvailable = false;//标识变量，表示当前网络是否连接

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isAvailable()&&!isNetworkAvailable) {
            Intent intent1 = new Intent("com.cicada.yuanxiaobao.common.CommitDataService");
            intent1.setPackage(context.getPackageName());
            context.startService(intent1);
            isNetworkAvailable = true;
            Logger.i("broadcast "+activeInfo.getTypeName());
        } else isNetworkAvailable = false;

    }
}
