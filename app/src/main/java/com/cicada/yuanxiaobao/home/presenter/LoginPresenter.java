package com.cicada.yuanxiaobao.home.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.MyApplication;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.home.action.ILoginAction;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.home.model.RequestLoginModel;
import com.cicada.yuanxiaobao.home.model.RequestUserInfoModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.MD5;
import com.cicada.yuanxiaobao.utils.ToastUtil;

/**
 * Created by tanglei on 16/6/29.
 */
public class LoginPresenter extends BasePresenter<ILoginAction> {

    private String account, pwd;

    public LoginPresenter(ILoginAction view, Context context) {
        super(view, context);
    }


    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.LOGIN_URL:
                ReceiveLoginModel user = gson.fromJson(objectString,ReceiveLoginModel.class);
                String token = user.getToken();
                AppSelectSP.putString("token", token);
                AppSelectSP.putString("Login",objectString);
                MyApplication.account = account;
                AppSelectSP.putString(account, pwd);
                view.callBack();
//                queryUserInfoByUserId(user.getUserId());
                break;
            case YXBService.QUERYUSERINFOBYUSERID_URL:

                break;
        }
    }

    @Override
    public void onFail(String methodName, String rtnCodel) {
    }

    /**
     * 登录
     *
     * @param account
     * @param pwd
     */
    public void login(String account, String pwd) {
        if (TextUtils.isEmpty(account.trim())) {
            ToastUtil.showShort("手机号码不能为空!");
            return;
        }
        if (!AppTools.isPhone(account.trim())) {
            ToastUtil.showShort("手机号码不合法!");
            return;
        }
        if (TextUtils.isEmpty(pwd.trim())) {
            ToastUtil.showShort("密码不能为空!");
            return;
        }
        this.account = account.trim();
        this.pwd = pwd.trim();
        String pwdString = AppSelectSP.getString(account);
        if (pwd.equals(pwdString) || TextUtils.isEmpty(pwdString)) {
            RequestLoginModel loginModel = new RequestLoginModel();
            loginModel.setAccount(account);
            loginModel.setPwd(MD5.GetMD5Code(pwd));
            ApiDataFactory.serviceAPI(YXBService.LOGIN_URL, RequestDataUtils.getRequestModel(loginModel, true), this, false);
            return;
        }
        ToastUtil.showShort("密码错误");
    }


    public void queryUserInfoByUserId(int id) {
        RequestUserInfoModel model = new RequestUserInfoModel();
        model.setTargetUserId(id);
        ApiDataFactory.serviceAPI(YXBService.QUERYUSERINFOBYUSERID_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

}
