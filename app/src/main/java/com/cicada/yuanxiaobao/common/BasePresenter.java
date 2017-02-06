package com.cicada.yuanxiaobao.common;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by tanglei on 16/6/16.
 * Presenter基类
 */
public abstract class BasePresenter<V> implements IPresenter {

    protected V view;
    public Context mContext;
    public static Gson gson = new GsonBuilder().serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    public BasePresenter(V view,Context mContext) {
        this.view = view;
        this.mContext=mContext;
    }

    public Context getContext(){
        return mContext;
    }

    //解绑view
    public void onDetach(){
        if (view != null) {
            view=null;
        }
    }
}
