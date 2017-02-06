package com.cicada.yuanxiaobao.attendance.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ICheckAction;
import com.cicada.yuanxiaobao.attendance.action.IChooseClass;
import com.cicada.yuanxiaobao.attendance.adapter.SpinnerAdapter;
import com.cicada.yuanxiaobao.attendance.presenter.CheckPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.widget.MyGridView;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/1.
 * 考勤
 */
public class CheckFragment extends BaseFragment implements ICheckAction, IChooseClass {

    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.date_layout)
    LinearLayout mDateLayout;
    @Bind(R.id.need_check_tv)
    TextView mNeedCheckTv;
    @Bind(R.id.need_check_gridview)
    MyGridView mNeedCheckGridview;
    @Bind(R.id.attendance_tv)
    TextView mAttendanceTv;
    @Bind(R.id.attendance_gridview)
    MyGridView mAttendanceGridview;
    @Bind(R.id.absenteeism_tv)
    TextView mAbsenteeismTv;
    @Bind(R.id.casual_leave_tv)
    TextView mCasualLeaveTv;
    @Bind(R.id.sick_leave_tv)
    TextView mSickLeaveTv;
    @Bind(R.id.absenteeism_total_tv)
    TextView mAbsenteeismTotalTv;
    @Bind(R.id.absenteeism_gridview)
    MyGridView mAbsenteeismGridview;
    @Bind(R.id.attendance)
    TextView mAttendance;
    @Bind(R.id.absenteeism)
    TextView mAbsenteeism;
    @Bind(R.id.casual_leave)
    TextView mCasualLeave;
    @Bind(R.id.sick_leave)
    TextView mSickLeave;
    @Bind(R.id.kid_details)
    TextView mKidDetails;
    @Bind(R.id.commit)
    TextView mCommit;
    @Bind(R.id.layout)
    LinearLayout mLayout;
    @Bind(R.id.absenteeism_layout)
    LinearLayout mAbsenteeismLayout;

    private CheckPresenter mCheckPresenter;
    private List<ClassModel> list;
    private List<String> listString;
    private ClassModel mClassModel;
    private PopupWindow popupWindow;
    private SpinnerAdapter mSpinnerAdapter;
    private TeacherClassFragment mTeacherClassFragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.check_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mCheckPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mCheckPresenter = new CheckPresenter(this, mContext);
    }

    @Override
    public void initView() {
        listString=new ArrayList<>();
        popupWindow=new PopupWindow(mContext);
        mSpinnerAdapter=new SpinnerAdapter(mContext, R.layout.spinner_item, listString);
        mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(DateUtils.getDateNow()));
        mTeacherClassFragment = (TeacherClassFragment) getChildFragmentManager().findFragmentById(R.id.class_fragment);
        mTeacherClassFragment.setIChooseClass(this);
    }


    @OnClick({R.id.date_layout, R.id.attendance, R.id.absenteeism, R.id.casual_leave, R.id.sick_leave, R.id.kid_details, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_layout:
                AppTools.obtainData(mContext, mHandler,0);
                break;
            case R.id.attendance:
                mCheckPresenter.attendance();
                break;
            case R.id.absenteeism:
                mCheckPresenter.absenteeism(2);
                break;
            case R.id.casual_leave:
                mCheckPresenter.absenteeism(5);
                break;
            case R.id.sick_leave:
                mCheckPresenter.absenteeism(6);
                break;
            case R.id.kid_details:

                break;
            case R.id.commit:
                mCheckPresenter.commit(DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(DateUtils.getStringToDate_YYYY_MM_DD_EN(mDate.getText().toString()))));
                break;
        }
    }


    @Override
    public void updateLayoutPadding(int dv) {
        if (dv > 0) mLayout.setPadding(0, 0, dv, 0);
    }

    @Override
    public void setNeedCheckGridviewAdapter(QuickAdapter adapter) {
        mNeedCheckGridview.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mNeedCheckGridview.setOnItemClickListener(mCheckPresenter);
        mNeedCheckGridview.setAdapter(adapter);
    }

    @Override
    public void setAttendanceGridviewAdapter(QuickAdapter adapter) {
        mAttendanceGridview.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mAttendanceGridview.setAdapter(adapter);
    }

    @Override
    public void setAbsenteeismGridviewAdapter(QuickAdapter adapter) {
        mAbsenteeismGridview.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mAbsenteeismGridview.setAdapter(adapter);
    }

    @Override
    public void updateNeedCheck(int size) {
        if (size > 0) {
            mNeedCheckTv.setVisibility(View.VISIBLE);
            mNeedCheckGridview.setVisibility(View.VISIBLE);
        } else {
            mNeedCheckTv.setVisibility(View.GONE);
            mNeedCheckGridview.setVisibility(View.GONE);
            return;
        }
        mNeedCheckTv.setText("待考勤: " + size + "人");
    }

    @Override
    public void updateAttendance(int size) {
        if (size > 0) {
            mAttendanceTv.setVisibility(View.VISIBLE);
            mAttendanceGridview.setVisibility(View.VISIBLE);
        } else {
            mAttendanceTv.setVisibility(View.GONE);
            mAttendanceGridview.setVisibility(View.GONE);
            return;
        }
        mAttendanceTv.setText("出勤: " + size + "人");
    }

    @Override
    public void updateAbsenteeism(int size, int absenteeism, int casual, int sick) {
        if (size > 0) {
            mAbsenteeismLayout.setVisibility(View.VISIBLE);
            mAbsenteeismGridview.setVisibility(View.VISIBLE);
            mAbsenteeismTv.setText("缺勤: " + absenteeism + "人");
            mCasualLeaveTv.setText("事假: " + casual + "人");
            mSickLeaveTv.setText("病假: " + sick + "人");
            mAbsenteeismTotalTv.setText("缺勤合计: " + size + "人");
        } else {
            mAbsenteeismLayout.setVisibility(View.GONE);
            mAbsenteeismGridview.setVisibility(View.GONE);
            return;
        }
    }

    @Override
    public void setButtonBg(boolean isEnable) {
     if(isEnable) {
         mCommit.setEnabled(true);
         mCommit.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bt_in_enable_bg));
     } else {
         mCommit.setEnabled(false);
         mCommit.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bt_in_enable1_bg));
     }
    }







    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                        case 0:
                            Date date= (Date) msg.obj;
                            mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(date));
                            mCheckPresenter.studentAttendance(mClassModel.getClassId(), DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(date)));
                            break;

                    }
        }
    };

    @Override
    public void callBack(ClassModel classModel) {
        mClassModel=classModel;
        mCheckPresenter.studentAttendance(mClassModel.getClassId(), DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(DateUtils.getStringToDate_YYYY_MM_DD_EN(mDate.getText().toString()))));
       }
}
