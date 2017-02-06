package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.utils.AppTools;

import java.util.List;

/**
 * Created by tanglei on 16/7/12.
 */
public class ParentsPhoneAdapter extends QuickAdapter<ParentsModel> implements View.OnClickListener {
    public ParentsPhoneAdapter(Context context, int layoutResId, List<ParentsModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ParentsModel item, int position) {

        helper.setText(R.id.user_name,item.getUserName());
        helper.setText(R.id.relation,"("+item.getRelation()+")");
        helper.setText(R.id.phone,item.getPhoneNum());
        View call=helper.getView(R.id.call);
        View message=helper.getView(R.id.message);
        call.setTag(item.getPhoneNum());
        message.setTag(item.getPhoneNum());
        call.setOnClickListener(this);
        message.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String phone= (String) view.getTag();
        if (TextUtils.isEmpty(phone)){
           return;
        }
        switch (view.getId()) {
            case R.id.call:
                AppTools.callPhone(context,phone);
                break;
            case R.id.message:
                AppTools.sendSMS(context,phone);
                break;
        }
    }
}
