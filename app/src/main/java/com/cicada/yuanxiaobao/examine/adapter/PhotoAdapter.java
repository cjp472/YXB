package com.cicada.yuanxiaobao.examine.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.GlideRoundTransform;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.model.PhotoPathModel;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.List;

/**
 * Created by tanglei on 16/6/28.
 */
public class PhotoAdapter extends QuickAdapter<PhotoPathModel> {
    public PhotoAdapter(Context context, int layoutResId, List<PhotoPathModel> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, PhotoPathModel item, int position) {
        ImageView imageView = (ImageView) helper.getView(R.id.grid_item);
        if (!TextUtils.isEmpty(item.getPathString())) {
            Glide.with(context).load(item.getPathString()).centerCrop().error(ContextCompat.getDrawable(context,R.drawable.camera_bg)).override(DeviceUtils.dip2Px(context,80),DeviceUtils.dip2Px(context,80)).transform(new GlideRoundTransform(context,5)).into(imageView);
        }
    }
}
