package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.attendance.action.ISpecialSituationAction;
import com.cicada.yuanxiaobao.attendance.model.ReceiveSpecialSituatModel;
import com.cicada.yuanxiaobao.attendance.model.RequestFamilyMember;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

/**
 * Created by tanglei on 16/7/25.
 */
public class SpecialSituationPresenter extends BasePresenter<ISpecialSituationAction> {
    public SpecialSituationPresenter(ISpecialSituationAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYINSPECTSPECIAL_URL:
                ReceiveSpecialSituatModel model = gson.fromJson(objectString, ReceiveSpecialSituatModel.class);
                view.callBack(model);
                break;
            case YXBService.SAVEINSPECTSPECIAL:
                ToastUtil.showShort("保存成功!");
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryInspectSpecial(long childId) {
        RequestFamilyMember model = new RequestFamilyMember();
        model.setChildId(childId);
        ApiDataFactory.serviceAPI(YXBService.QUERYINSPECTSPECIAL_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

    public void saveInspectSpecial(ReceiveSpecialSituatModel situatModel) {
        ApiDataFactory.serviceAPI(YXBService.SAVEINSPECTSPECIAL, RequestDataUtils.getRequestModel(situatModel, true), this, true);

    }
}
