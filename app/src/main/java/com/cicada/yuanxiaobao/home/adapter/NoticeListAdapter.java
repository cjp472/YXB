package com.cicada.yuanxiaobao.home.adapter;

import android.content.Context;

import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.home.model.NoticeModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/22.
 */
public class NoticeListAdapter extends QuickAdapter<NoticeModel> {

    public NoticeListAdapter(Context context, int layoutResId, List<NoticeModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NoticeModel item, int position) {

    }
}
