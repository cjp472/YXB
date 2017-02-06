package com.cicada.yuanxiaobao.examine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.model.ReceiveSymptomModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/25.
 */
public class SymptomAdapter extends QuickAdapter<ReceiveSymptomModel> {

    int cols, rows;

    public SymptomAdapter(Context context, int layoutResId, List<ReceiveSymptomModel> data, int cols ) {
        super(context, layoutResId, data);
        this.cols=cols;
        rows = data.size() % cols == 0 ? data.size() / cols : data.size() / cols + 1;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ReceiveSymptomModel item, int position) {
        helper.setVisible(R.id.image,item.isChoose());
        helper.setText(R.id.name,item.getName());
        if (item.isChoose()) {
            helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context,R.color.colorYellowLight));
        } else {
            helper.setBackgroundColor(R.id.grid_item, Color.WHITE);}
        if (rows > 1) {
            if (position == 0) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_yellow_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_bg);
                }
            }
            if (position == cols - 1) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_yellow_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_bg);
                }
            }
            if (position == (rows - 1) * cols) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_yellow_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_bg);
                }
            }
            if (position == (rows * cols - 1)) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_yellow_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_bg);
                }
            }

        } else {


        }

    }


}
