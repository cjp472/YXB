package com.cicada.yuanxiaobao.attendance.adapter;

import android.content.Context;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.model.SickItemModel;
import com.cicada.yuanxiaobao.common.BaseAdapterHelper;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.utils.DateUtils;

import java.util.List;

/**
 * Created by tanglei on 16/7/8.
 */
public class SickListAdapter extends QuickAdapter<SickItemModel> {
    String[] str;

    public SickListAdapter(Context context, int layoutResId, List<SickItemModel> data) {
        super(context, layoutResId, data);
        str = context.getResources().getStringArray(R.array.common_diseases);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, SickItemModel item, int position) {
        try {
            helper.setText(R.id.childName, item.getChildName());
            helper.setText(R.id.childSex, item.getChildSex());
            helper.setText(R.id.symptoms, item.getSymptoms());
            helper.setText(R.id.hospital, item.getHospital());
            helper.setText(R.id.treatmentResult, "");
            if (1 == item.getTreatmentResult()) helper.setText(R.id.treatmentResult, "住院");
            if (2 == item.getTreatmentResult()) helper.setText(R.id.treatmentResult, "居家");
            switch (item.getState()) {
                case 0:
                    helper.setText(R.id.state, "");
                    break;
                case 1:
                    helper.setText(R.id.state, "稳定");
                    break;
                case 2:
                    helper.setText(R.id.state, "加重");
                    break;
                case 3:
                    helper.setText(R.id.state, "减轻");
                    break;
                case 4:
                    helper.setText(R.id.state, "痊愈");
                    break;
            }
            helper.setText(R.id.leaveTime, "");
            helper.setText(R.id.endTime, "");
            if (item.getLeaveTime() > 0)
                helper.setText(R.id.leaveTime, DateUtils.getTimeStampToYYYY_MM_DD_EN(item.getLeaveTime()));
            if (item.getEndTime() > 0)
                helper.setText(R.id.endTime, DateUtils.getTimeStampToYYYY_MM_DD_EN(item.getEndTime()));
            helper.setText(R.id.attach, item.getAttach());
            helper.setText(R.id.resultInfo, "");
            helper.setText(R.id.resultInfo, str[item.getMedicalResult() - 1]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
