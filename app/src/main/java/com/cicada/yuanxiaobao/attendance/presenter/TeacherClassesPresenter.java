package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.attendance.action.ITeacherClassesAction;
import com.cicada.yuanxiaobao.attendance.model.RequestTeacherClassModel;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by tanglei on 16/7/26.
 */
public class TeacherClassesPresenter extends BasePresenter<ITeacherClassesAction> {
    public TeacherClassesPresenter(ITeacherClassesAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        List<ClassModel> list=gson.fromJson(objectString,new TypeToken<List<ClassModel>>(){}.getType());
        view.setClass(list);
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void getTeacherClasses(){
        String str=AppSelectSP.getString("Login");
        ReceiveLoginModel loginModel = gson.fromJson(str,ReceiveLoginModel.class);
        RequestTeacherClassModel model=new RequestTeacherClassModel();
        model.setSchoolId(loginModel.getSchoolInfo().getSchoolId());
        ApiDataFactory.serviceAPI(YXBService.GETTEACHERCLASSES_URL, RequestDataUtils.getRequestModel(model,true),this,true);
    }
}
