package com.cicada.yuanxiaobao.home.view;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseActivity;
import com.cicada.yuanxiaobao.common.BasePresenter;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/17.
 */
public class PropagandaActivity extends BaseActivity {


    @Bind(R.id.jump_bt)
    AppCompatButton mJumpBt;

    @Override
    protected int getLayoutResId() {
        return R.layout.propaganda_activity;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.jump_bt)
    public void onClick() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }


}
