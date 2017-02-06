package com.cicada.yuanxiaobao.examine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.model.ClassModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/25.
 */
public class ClassAdapter extends QuickAdapter<ClassModel> {

    private int cols, rows, itemH;
    private boolean isMultiSelect = false;
    private boolean isTableTop=false;//表头是否可点击



    public ClassAdapter(Context context, int layoutResId, List<ClassModel> data, int cols, int itemH) {
        super(context, layoutResId, data);
        this.cols = cols;
        this.itemH = itemH;
    }

    @Override
    protected void convert(BaseAdapterHelper helper, ClassModel item, int position) {
        View convertView = helper.getView();
        convertView.getLayoutParams().height = itemH;
        rows = data.size() % cols == 0 ? data.size() / cols : data.size() / cols + 1;

        if (item == null) {
            ((TextView) convertView).setText(null);
            helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorbg));
            if (rows > 1 && position == (rows * cols - 1)) {
                helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_gray_bg);
            } else if (rows == 1 && position == (rows * cols - 1)) {
                helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_gray_bg);
            }
            return;
        }
        ((TextView) convertView).setText(item.getClassName());
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
                    if (isMultiSelect) {
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_bg);
                    } else {
                       if(!isTableTop) helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_gray_bg);
                    }
                }
            }
            if (position == cols - 1) {
                if (item.isChoose()) {
                    helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_blue_bg);
                } else {
                    if (isMultiSelect) {
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_bg);
                    } else {
                        if(!isTableTop) helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_gray_bg);
                    }
                }
            }

            if(!isTableTop&&!isMultiSelect&&position>0&&position<cols-1) helper.setBackgroundColor(R.id.grid_item,ContextCompat.getColor(context,R.color.colorbg) );

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

        }

    }

    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    public void setTableTop(boolean tableTop) {
        isTableTop = tableTop;
    }
}
