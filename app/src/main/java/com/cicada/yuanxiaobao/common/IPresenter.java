package com.cicada.yuanxiaobao.common;

import android.content.Context;

/**
 * Created by tanglei on 16/6/16.
 */
public interface IPresenter {

    /* 成功*/
    public void onSuccess(String url, String objectString);

    /*失败*/
    public void onFail(String url,String rtnCodel);

    public Context getContext();
}
