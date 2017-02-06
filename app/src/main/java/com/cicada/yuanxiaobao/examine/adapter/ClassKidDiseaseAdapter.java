package com.cicada.yuanxiaobao.examine.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;

import java.util.List;

/**
 * Created by tanglei on 16/6/25.
 */
public class ClassKidDiseaseAdapter extends QuickAdapter<KidModel> {

    int cols, rows, itemH;

    public ClassKidDiseaseAdapter(Context context, int layoutResId, List<KidModel> data, int cols, int itemH) {
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
            helper.setBackgroundDrawable(R.id.image, R.drawable.man);
        } else if ("女".equals(item.getChildSex())) {
            helper.setBackgroundDrawable(R.id.image, R.drawable.women);
        }
        String  order=item.getMedicalOrders();
        if(!TextUtils.isEmpty(order)){
            switch (order) {
                case "需要观察":
                    helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorBlueLight));
                    break;
                case "委托吃药":
                    helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorYellowLight));
                    break;
                case "传染病预警":
                    helper.setBackgroundColor(R.id.grid_item, ContextCompat.getColor(context, R.color.colorRedLight));
                    break;
            }
        }

        if (rows > 1) {
            if (position == 0) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_left_red_bg);
                        break;
                }
            }
            if (position == cols - 1) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.top_right_red_bg);
                        break;
                }
            }
            if (position == (rows - 1) * cols) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_left_red_bg);
                        break;
                }
            }
            if (position == (rows * cols - 1)) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.bottom_right_red_bg);
                        break;
                }
            }

        } else {
            if (position == 0) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.left_bottom_top_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.left_bottom_top_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.left_bottom_top_red_bg);
                        break;
                }

            }

            if (position == cols - 1) {
                switch (item.getMedicalOrders()) {
                    case "需要观察":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_blue_bg);
                        break;
                    case "委托吃药":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_yellow_bg);
                        break;
                    case "传染病预警":
                        helper.setBackgroundDrawable(R.id.grid_item, R.drawable.right_bottom_top_red_bg);
                        break;
                }
            }
        }

    }


}
