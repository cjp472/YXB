package com.cicada.yuanxiaobao.attendance.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.adapter.SpinnerAdapter;
import com.cicada.yuanxiaobao.attendance.model.ReceiveSpecialSituatModel;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontEditText;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/13.
 * 传染病记录
 */
public class ContagionTrackDialog extends Dialog implements AdapterView.OnItemClickListener {


    @Bind(R.id.record_title)
    MyFontTextView mRecordTitle;
    @Bind(R.id.hospital)
    MyFontEditText mHospital;
    @Bind(R.id.diagnosis_time)
    MyFontTextView mDiagnosisTime;
    @Bind(R.id.remark)
    MyFontEditText mRemark;
    @Bind(R.id.save)
    MyFontTextView mSave;
    @Bind(R.id.cancel)
    MyFontTextView mCancel;
    @Bind(R.id.sick)
    MyFontTextView mSick;
    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;

    private Window dialogWindow;
    private Context context;
    private Handler mHandler;
    private PopupWindow popupWindow;
    private List<String> listString;
    private SpinnerAdapter mSpinnerAdapter;
    private boolean isRecovered=true;


    public ContagionTrackDialog(Context context, Handler mHandler) {
        super(context, R.style.selection_dialog_theme);
        this.context = context;
        this.mHandler = mHandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contagion_track_dialog);
        ButterKnife.bind(this);
        dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(android.R.style.Animation_Dialog);
        init();
    }

    private void init() {
        popupWindow = new PopupWindow(context);
        String[] str = context.getResources().getStringArray(R.array.contagion);
        listString = Arrays.asList(str);
        mSpinnerAdapter = new SpinnerAdapter(context, R.layout.spinner_item, listString);
        mSick.setText(listString.get(0));
        mDiagnosisTime.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(DateUtils.getDateNow()));
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.yes:
                        isRecovered=true;
                        break;
                    case R.id.no:
                        isRecovered=false;
                        break;
                }
            }
        });
    }

    @OnClick({R.id.save, R.id.cancel, R.id.diagnosis_time, R.id.sick})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                ReceiveSpecialSituatModel.ContagionBean bean = new ReceiveSpecialSituatModel.ContagionBean();
                String DataString = mDiagnosisTime.getText().toString();
                Date date = DateUtils.getStringToDate_YYYY_MM_DD_EN(DataString);
                long inspectTime = DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(date));
                bean.setContagionName(mSick.getText().toString());
                bean.setDiagnosedDate(inspectTime);
                bean.setIsRecovered(isRecovered);
                bean.setHospital(mHospital.getText().toString());
                bean.setTreatment(mRemark.getText().toString());
                if(TextUtils.isEmpty(mHospital.getText())){
                    ToastUtil.showShort("请填写就医医院");
                    return;
                }
                mHandler.sendMessage(mHandler.obtainMessage(0, bean));
                dismiss();
                break;
            case R.id.cancel:
                dismiss();
                break;
            case R.id.diagnosis_time:
                AppTools.obtainData(context, mDiagnosisTime, DateUtils.format_yyyy_MM_dd_EN);
                break;
            case R.id.sick:
                showPopupWindow(popupWindow, mSick, mSpinnerAdapter);
                break;
        }
    }

    public void showPopupWindow(PopupWindow popupWindow, View view, QuickAdapter adapter) {
        int w = view.getWidth();
        ListView listView = (ListView) LayoutInflater.from(context).inflate(R.layout.parents_popup_window1, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        popupWindow.setContentView(listView);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(w);
        if (adapter.getData().size() > 5) {
            popupWindow.setHeight(DeviceUtils.dip2px(context, 200));
        } else popupWindow.setHeight(DeviceUtils.dip2px(context, 40 * adapter.getData().size()));
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        Drawable dw = ContextCompat.getDrawable(context, R.drawable.list_bg);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAsDropDown(view, 0, -1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mSick.setText(listString.get(i));
        popupWindow.dismiss();
    }
}
