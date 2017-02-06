package com.cicada.yuanxiaobao.attendance.view;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IChooseClass;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/6.
 * 特别关注
 */
public class SpecialFocusFragemnt extends BaseFragment implements IChooseClass {

    @Bind(R.id.date)
    MyFontTextView mDate;
    @Bind(R.id.date_layout)
    LinearLayout mDateLayout;

    private List<String> listString;
    private ClassModel mClassModel;
    private SpecialFocusLeftFragment leftFragment;
    private KidArchiveDetailFragment kidDetailFragment;
    private TeacherClassFragment mTeacherClassFragment;


    @Override
    protected int getLayoutResId() {
        return R.layout.special_focus_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }


    @Override
    protected void onInitPresenters() {
    }

    @Override
    public void initView() {
        listString = new ArrayList<>();
        mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(DateUtils.getDateNow()));
        leftFragment = (SpecialFocusLeftFragment) getChildFragmentManager().findFragmentById(R.id.special_focus_left_fragment);
        kidDetailFragment = (KidArchiveDetailFragment) getChildFragmentManager().findFragmentById(R.id.special_focus_right_fragment);
        mTeacherClassFragment = (TeacherClassFragment) getChildFragmentManager().findFragmentById(R.id.class_fragment);
        mTeacherClassFragment.setIChooseClass(this);
        kidDetailFragment.getView().setVisibility(View.INVISIBLE);

    }


    @OnClick({R.id.date_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_layout:
                AppTools.obtainData(mContext, mHandler, 0);
                break;
        }
    }

    public void setListKidModel(List<KidModel> list){
        if(list==null||list.size()==0){
            kidDetailFragment.getView().setVisibility(View.INVISIBLE);
        }
    }

    public void setKidModel(KidModel kidModel) {
        kidDetailFragment.getView().setVisibility(View.VISIBLE);
        kidDetailFragment.setKidModel(kidModel, false, DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(DateUtils.getStringToDate_YYYY_MM_DD_EN(mDate.getText().toString()))));
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Date date = (Date) msg.obj;
                    mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(date));
                    kidDetailFragment.getView().setVisibility(View.INVISIBLE);
                    leftFragment.setClassIdTime(mClassModel.getClassId(), DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(date)));
                    break;
            }
        }
    };


    @Override
    public void callBack(ClassModel classModel) {
        mClassModel = classModel;
        kidDetailFragment.getView().setVisibility(View.INVISIBLE);
        leftFragment.setClassIdTime(mClassModel.getClassId(), DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(DateUtils.getStringToDate_YYYY_MM_DD_EN(mDate.getText().toString()))));
    }
}
