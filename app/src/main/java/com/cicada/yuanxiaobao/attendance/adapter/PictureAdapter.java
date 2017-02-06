package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.MyApplication;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import java.util.List;

/**
 * Created by tanglei on 16/7/25.
 */
public class PictureAdapter extends QuickAdapter<String> {
    public PictureAdapter(Context context, int layoutResId, List<String> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, String item, int position) {
        Glide.with(MyApplication.getInstance()).load(item).into((ImageView) helper.getView(R.id.image));
    }
}
