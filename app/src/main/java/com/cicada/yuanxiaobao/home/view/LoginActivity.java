package com.cicada.yuanxiaobao.home.view;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseActivity;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.home.action.ILoginAction;
import com.cicada.yuanxiaobao.home.presenter.LoginPresenter;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/24.
 * 登录
 */
public class LoginActivity extends BaseActivity implements ILoginAction {
    @Bind(R.id.phone)
    AppCompatEditText mPhone;
    @Bind(R.id.pwd)
    AppCompatEditText mPwd;
    @Bind(R.id.find_pwd)
    AppCompatTextView mFindPwd;
    @Bind(R.id.login)
    AppCompatTextView mLogin;
    @Bind(R.id.versionName)
    AppCompatTextView mVersionName;
    @Bind(R.id.service_phone)
    AppCompatTextView mServicePhone;

    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.login_activity;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mLoginPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mLoginPresenter = new LoginPresenter(this,this);
    }

    @Override
    public void initView() {
        mVersionName.setText("V " + DeviceUtils.getVersionName(this));
    }


    @OnClick({R.id.find_pwd, R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.find_pwd:
                break;
            case R.id.login:
                mLoginPresenter.login(mPhone.getText().toString(), mPwd.getText().toString());
//               callBack();
                break;
        }
    }

    @Override
    public void callBack() {
        finish();
        startActivity(new Intent(this, FragmentMainActivity.class));
    }


}
