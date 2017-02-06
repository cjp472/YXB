package com.cicada.yuanxiaobao.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.home.model.NavigationListItemModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/17.
 */
public class NavigatiionListAdapter extends QuickAdapter<NavigationListItemModel> {

    public NavigatiionListAdapter(Context context, int layoutResId, List<NavigationListItemModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, NavigationListItemModel item, int position) {
        helper.setText(R.id.title, item.getTitle());
        helper.setTextColor(R.id.title, Color.WHITE);
        helper.setImageDrawable(R.id.image, ContextCompat.getDrawable(context, item.getDrawableId1()));

        if (item.getSelect()) {
            helper.setBackgroundColor(R.id.layout, ContextCompat.getColor(context, R.color.colorSelect));
        } else {
            helper.setBackgroundColor(R.id.layout, Color.TRANSPARENT);
        }
    }


}
