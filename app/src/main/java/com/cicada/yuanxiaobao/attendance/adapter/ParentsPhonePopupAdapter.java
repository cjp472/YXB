package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;

import java.util.List;

/**
 * Created by tanglei on 16/7/12.
 */
public class ParentsPhonePopupAdapter extends QuickAdapter<ParentsModel> implements View.OnClickListener {
    public ParentsPhonePopupAdapter(Context context, int layoutResId, List<ParentsModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ParentsModel item, int position) {
        View call=helper.getView(R.id.call);
        View message=helper.getView(R.id.message);
        call.setOnClickListener(this);
        message.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                break;
            case R.id.message:
                break;
        }
    }
}
