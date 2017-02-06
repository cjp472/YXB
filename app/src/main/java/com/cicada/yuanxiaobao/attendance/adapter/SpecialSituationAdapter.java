package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.model.ReceiveSpecialSituatModel;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.utils.DateUtils;

import java.util.List;

/**
 * Created by tanglei on 16/7/25.
 */
public class SpecialSituationAdapter extends QuickAdapter<ReceiveSpecialSituatModel.ContagionBean> {
    public SpecialSituationAdapter(Context context, int layoutResId, List<ReceiveSpecialSituatModel.ContagionBean> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ReceiveSpecialSituatModel.ContagionBean item, int position) {
        helper.setText(R.id.allergen,item.getContagionName()+" "+ DateUtils.getTimeStampToYYYY_MM_DD_EN(item.getDiagnosedDate())+" "+(item.isIsRecovered()?"已痊愈":"未痊愈"));
        helper.setText(R.id.hospital,item.getHospital());
        helper.setText(R.id.treatment,item.getTreatment());
    }
}
