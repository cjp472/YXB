package com.cicada.yuanxiaobao.home.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseActivity;
import com.cicada.yuanxiaobao.common.BasePresenter;

/**
 * Created by tanglei on 16/6/17.
 */
public class LauncherActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.launcher_activity;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {}

    @Override
    public void initView() {

      mHandler.sendEmptyMessageDelayed(0,2000);
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            finish();
//            startActivity(new Intent(LauncherActivity.this,PropagandaActivity.class));
            startActivity(new Intent(LauncherActivity.this,LoginActivity.class));

        }
    };
}
