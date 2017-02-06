package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ISpecialFocusAction;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidDiseaseAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveExaminResultModel;
import com.cicada.yuanxiaobao.examine.model.RequestExaminResultModel;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.List;

/**
 * Created by tanglei on 16/7/26.
 */
public class SpecialFousPresenter extends BasePresenter<ISpecialFocusAction> {
    private int columns, itemheight;
    private List<KidModel> enter, out;
    public SpecialFousPresenter(ISpecialFocusAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYCHILDBYCLASSIDS_URL:
                ReceiveExaminResultModel resultModel = gson.fromJson(objectString, ReceiveExaminResultModel.class);
                enter=resultModel.getEnter();
                itemheight = (DeviceUtils.getScreenWidth(mContext) - DeviceUtils.dip2Px(mContext, 160)) / 11;
                ClassKidDiseaseAdapter diseaseAdapter = new ClassKidDiseaseAdapter(mContext, R.layout.morn_noon_gridview_item, enter, 4, itemheight);
                view.setAdapter(diseaseAdapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryChildByClassIds(String classIds, long inspectTime, int inspectType) {
        RequestExaminResultModel model = new RequestExaminResultModel();
        model.setClassIds(classIds);
        model.setInspectTime(inspectTime);
        model.setInspectType(inspectType);
        ApiDataFactory.serviceAPI(YXBService.QUERYCHILDBYCLASSIDS_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }
}
