package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IFamilyMemberAction;
import com.cicada.yuanxiaobao.attendance.adapter.ParentsPhoneAdapter;
import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.attendance.model.RequestFamilyMember;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/25.
 */
public class KidArchiveDetailPresenter extends BasePresenter<IFamilyMemberAction> {
    public KidArchiveDetailPresenter(IFamilyMemberAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYFAMILYMEMBER_URL:
                ParentsPhoneAdapter adapter;
                List<ParentsModel> data = gson.fromJson(objectString, new TypeToken<List<ParentsModel>>() {
                }.getType());
                if (data.size() > 2) {
                    List<ParentsModel> list = new ArrayList<>();
                    list.add(data.get(0));
                    list.add(data.get(1));
                    adapter = new ParentsPhoneAdapter(mContext, R.layout.parents_phone_item, list);
                } else {
                    adapter = new ParentsPhoneAdapter(mContext, R.layout.parents_phone_item, data);
                }
                view.setAdapter(adapter, data);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryFamilyMember(long childId) {
        RequestFamilyMember requestFamilyMember = new RequestFamilyMember();
        requestFamilyMember.setChildId(childId);
        ApiDataFactory.serviceAPI(YXBService.QUERYFAMILYMEMBER_URL, RequestDataUtils.getRequestModel(requestFamilyMember, true), this, true);
    }
}
