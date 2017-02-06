package com.cicada.yuanxiaobao.examine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/25.
 */
public class ClassKidAdapter extends QuickAdapter<KidModel> {

    int cols, rows, itemH;

    public ClassKidAdapter(Context context, int layoutResId, List<KidModel> data, int cols, int itemH) {
        super(context, layoutResId, data);
        this.cols = cols;
        this.itemH = itemH;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, KidModel item, int position) {
        View convertView = helper.getView();
        convertView.getLayoutParams().height = itemH;
        rows = data.size() % cols == 0 ? data.size() / cols : data.size() / cols + 1;
        if (item == null) {
            helper.setText(R.id.name, null);
            helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorbg));
            helper.setVisible(R.id.name, false);
            helper.setVisible(R.id.image, false);
            if (rows > 1 && position == (rows * cols - 1)) {
                helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_gray_bg);
            } else if (rows == 1 && position == (rows * cols - 1)) {
                helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_gray_bg);
            }
            return;
        }
        helper.setVisible(R.id.name, true);
        helper.setVisible(R.id.image, true);
        helper.setText(R.id.name, item.getChildName());
        if ("男".equals(item.getChildSex())) {
         helper.setBackgroundDrawable(R.id.image,R.drawable.man);
        } else if ("女".equals(item.getChildSex())) {
            helper.setBackgroundDrawable(R.id.image,R.drawable.women);
        }
        if (item.isChoose()) {
            helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorBlueLight));
        } else {
            helper.setBackgroundColor(R.id.grid_item, Color.WHITE);
        }

        if (rows > 1) {
            if (position == 0) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_bg);
                }
            }
            if (position == cols - 1) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_bg);
                }
            }
            if (position == (rows - 1) * cols) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_bg);
                }
            }
            if (position == (rows * cols - 1)) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_bg);
                }
            }

        } else {
            if (position == 0) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.left_bottom_top_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.left_bottom_top_bg);
                }
            }

            if (position == cols - 1) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_blue_bg);
                } else {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_bg);
                }
            }
        }

    }


}
