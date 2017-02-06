package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.attendance.action.IMornNoonExaminResultAction;
import com.cicada.yuanxiaobao.attendance.model.ReceiveMornNoonRecordModel;
import com.cicada.yuanxiaobao.attendance.model.RequestKidMornNoonRecord;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;

/**
 * Created by tanglei on 16/7/25.
 */
public class MornNoonExaminRecordPresenter extends BasePresenter<IMornNoonExaminResultAction> {
    public MornNoonExaminRecordPresenter(IMornNoonExaminResultAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
                    case YXBService.QUERYCHILDINSPECT_URL:
                        ReceiveMornNoonRecordModel recordModel=gson.fromJson(objectString,ReceiveMornNoonRecordModel.class);
                        view.callBack(recordModel);
                        break;
                    default:
                        break;
                }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryChildInspect(RequestKidMornNoonRecord model){
        ApiDataFactory.serviceAPI(YXBService.QUERYCHILDINSPECT_URL, RequestDataUtils.getRequestModel(model,true),this,true);
    }
}
