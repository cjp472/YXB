package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;
import android.widget.TextView;

import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;

import java.util.List;

/**
 * Created by tanglei on 16/7/26.
 */
public class SpinnerAdapter extends QuickAdapter<String> {
    public SpinnerAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, String item, int position) {
        ((TextView) helper.getView()).setText(item);
    }
}
