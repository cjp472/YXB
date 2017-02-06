package com.cicada.yuanxiaobao.common;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cicada.yuanxiaobao.utils.FontManager;

import butterknife.ButterKnife;

/**
 * Created by tanglei on 16/6/16.
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity{

    /** * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     *需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract BasePresenter[] getPresenters();

    //初始化presenters，
    protected abstract void onInitPresenters();

    /**
     * 从intent中解析数据，具体子类来实现
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent){}

    public abstract  void initView();

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if(getIntent() != null){
            parseArgumentsFromIntent(getIntent());
        }
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        onInitPresenters();
        initView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BasePresenter[] basePresenters=getPresenters();
        if (basePresenters != null) {
            for (int i = 0; i <basePresenters.length ; i++) {
                basePresenters[i].onDetach();
            }
        }

    }
}
