package com.cicada.yuanxiaobao.attendance.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IFamilyMemberAction;
import com.cicada.yuanxiaobao.attendance.action.IRecordTrackAction;
import com.cicada.yuanxiaobao.attendance.adapter.ParentsPhoneAdapter;
import com.cicada.yuanxiaobao.attendance.adapter.SpinnerAdapter;
import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.attendance.model.RequestSickLeaveInfoModel;
import com.cicada.yuanxiaobao.attendance.model.SickItemModel;
import com.cicada.yuanxiaobao.attendance.presenter.KidArchiveDetailPresenter;
import com.cicada.yuanxiaobao.attendance.presenter.RecordTrackPresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontEditText;
import com.cicada.yuanxiaobao.common.font.MyFontRadioButton;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.common.widget.MyGridView;
import com.cicada.yuanxiaobao.examine.adapter.SymptomAdapter;
import com.cicada.yuanxiaobao.examine.model.ReceiveSymptomModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/13.
 */
public class RecordTrackDialog extends Dialog implements View.OnClickListener, IRecordTrackAction, IFamilyMemberAction, AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.title)
    MyFontTextView mTitle;
    @Bind(R.id.record_title)
    MyFontTextView mRecordTitle;
    @Bind(R.id.parent_name)
    MyFontTextView mParentName;
    @Bind(R.id.phone)
    MyFontTextView mPhone;
    @Bind(R.id.call)
    ImageView mCall;
    @Bind(R.id.message)
    ImageView mMessage;
    @Bind(R.id.down)
    ImageView mDown;
    @Bind(R.id.gridview)
    MyGridView mGridview;
    @Bind(R.id.hospital)
    MyFontEditText mHospital;
    @Bind(R.id.diagnose1)
    MyFontTextView mDiagnose1;
    @Bind(R.id.diagnose2)
    MyFontEditText mDiagnose2;
    @Bind(R.id.yes_rb)
    MyFontRadioButton mYesRb;
    @Bind(R.id.no_rb)
    MyFontRadioButton mNoRb;
    @Bind(R.id.contagion_group)
    RadioGroup mContagionGroup;
    @Bind(R.id.home_rb)
    MyFontRadioButton mHomeRb;
    @Bind(R.id.hospital_rb)
    MyFontRadioButton mHospitalRb;
    @Bind(R.id.hih_group)
    RadioGroup mHihGroup;
    @Bind(R.id.stable_rb)
    MyFontRadioButton mStableRb;
    @Bind(R.id.serious_rb)
    MyFontRadioButton mSeriousRb;
    @Bind(R.id.alleviate_rb)
    MyFontRadioButton mAlleviateRb;
    @Bind(R.id.recovery_rb)
    MyFontRadioButton mRecoveryRb;
    @Bind(R.id.current_status_group)
    RadioGroup mCurrentStatusGroup;
    @Bind(R.id.return_time)
    MyFontTextView mReturnTime;
    @Bind(R.id.remark)
    MyFontEditText mRemark;
    @Bind(R.id.save)
    MyFontTextView mSave;
    @Bind(R.id.cancel)
    MyFontTextView mCancel;
    @Bind(R.id.contact_layout)
    RelativeLayout mContactLayout;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    @Bind(R.id.diagnose1_down)
    ImageView mDiagnose1Down;

    private Window dialogWindow;
    private Context context;
    private ArrayList<ReceiveSymptomModel> data = new ArrayList<ReceiveSymptomModel>();
    private SymptomAdapter mSymptomAdapter;
    private PopupWindow popupWindow, diagnosePopupWindow;
    private boolean isShow, isDiagnoseShow;
    private RecordTrackPresenter mTrackPresenter;
    private KidArchiveDetailPresenter mPresenter;
    private SickItemModel sickItemModel;
    private List<ParentsModel> mParentsModelList = new ArrayList<>();
    private List<String> listDisease;
    private String phone;
    private ParentsPhoneAdapter adapter;
    private SpinnerAdapter mSpinnerAdapter;
    private int state, treatmentResult, isInfection = -1, medicalResult = 1;
    private Handler handler;
    private ArrayList<ReceiveSymptomModel> symptomData;
    private boolean isFrist = true;

    public RecordTrackDialog(Context context, Handler handler) {
        super(context, R.style.selection_dialog_theme);
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_track_dialog);
        ButterKnife.bind(this);
        dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(android.R.style.Animation_Dialog);
        init();
    }


    private void init() {
        mContagionGroup.setOnCheckedChangeListener(this);
        mHihGroup.setOnCheckedChangeListener(this);
        mCurrentStatusGroup.setOnCheckedChangeListener(this);
        String[] str = context.getResources().getStringArray(R.array.common_diseases);
        listDisease = Arrays.asList(str);
        mDiagnose1.setText(listDisease.get(0));
        mSpinnerAdapter = new SpinnerAdapter(context, R.layout.spinner_item, listDisease);
        adapter = new ParentsPhoneAdapter(context, R.layout.parents_phone_item, mParentsModelList);
        mReturnTime.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(DateUtils.getDateNow()));
        mTrackPresenter = new RecordTrackPresenter(this, context);
        mPresenter = new KidArchiveDetailPresenter(this, context);
        mGridview.setColumnWidth(DeviceUtils.getScreenWidth(context));
        mGridview.setOnItemClickListener(mTrackPresenter);
        mTrackPresenter.initLeftData();
        mPresenter.queryFamilyMember(sickItemModel.getChildId());
        String time = "";
        try {
            time = DateUtils.getTimeStampToYYYY_MM_DD_CN(sickItemModel.getLeaveTime()) + "请假";
        } catch (Exception e) {
            e.printStackTrace();
        }
        mRecordTitle.setText(sickItemModel.getChildName() + " " + sickItemModel.getChildSex() + " " + time);
    }


    @OnClick({R.id.call, R.id.message, R.id.down, R.id.diagnose1, R.id.save, R.id.cancel, R.id.return_time, R.id.diagnose1_down})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                AppTools.callPhone(context, phone);
                break;
            case R.id.message:
                AppTools.sendSMS(context, phone);
                break;
            case R.id.return_time:
                AppTools.obtainData(context, mReturnTime, DateUtils.format_yyyy_MM_dd_EN);
                break;
            case R.id.down:
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(context);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            isShow = false;
                            mHandler.sendEmptyMessageDelayed(0, 200);
                        }
                    });
                    if (!isShow) {
                        mTrackPresenter.showPopupWindow(popupWindow, mContactLayout, adapter);
                        isShow = true;
                    }
                } else {
                    popupWindow = null;
                }
                break;
            case R.id.diagnose1:
                break;
            case R.id.diagnose1_down:
                if (diagnosePopupWindow == null) {
                    diagnosePopupWindow = new PopupWindow(context);
                    diagnosePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            isDiagnoseShow = false;
                            mHandler.sendEmptyMessageDelayed(1, 200);
                        }
                    });
                    if (!isDiagnoseShow) {
                        mTrackPresenter.showPopupWindow(diagnosePopupWindow, mDiagnose1, mSpinnerAdapter);
                        isDiagnoseShow = true;
                    }
                } else {
                    diagnosePopupWindow = null;
                }
                break;
            case R.id.save:
                RequestSickLeaveInfoModel model = new RequestSickLeaveInfoModel();
                model.setAttach(mRemark.getText().toString());
                model.setHospital(mHospital.getText().toString());
                model.setLeaveTime(sickItemModel.getLeaveTime());
                model.setEndTime(DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(DateUtils.getStringToDate_YYYY_MM_DD_EN(mReturnTime.getText().toString()))));
                model.setState(state);
                model.setIsInfection(isInfection);
                model.setMedicalResult(medicalResult);
                model.setId(sickItemModel.getId());
                model.setTreatmentResult(treatmentResult);
                model.setResultInfo(mDiagnose2.getText().toString());
                ArrayList arrayList = mTrackPresenter.getTmp();
                if (arrayList.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayList.size(); i++) {
                        sb.append(arrayList.get(i) + ",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                    model.setSymptoms(sb.toString());
                }
                mTrackPresenter.updateSickLeaveInfo(model);
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    popupWindow = null;
                    break;
                case 1:
                    diagnosePopupWindow = null;
                    break;
            }
        }
    };


    @Override
    public void callBack() {
        dismiss();
        handler.sendEmptyMessage(0);
        ToastUtil.showShort("保存成功");
    }

    @Override
    public void setSymptomsAdapter(QuickAdapter adapter) {
        mSymptomAdapter = (SymptomAdapter) adapter;
        symptomData = (ArrayList<ReceiveSymptomModel>) adapter.getData();
        mGridview.setAdapter(adapter);
    }

    @Override
    public void setAdapter(QuickAdapter adapter, List<ParentsModel> data) {
        mParentsModelList.addAll(data);
        ParentsModel parentsModel = mParentsModelList.get(0);
        phone = parentsModel.getPhoneNum();
        mParentName.setText(parentsModel.getUserName());
        mPhone.setText(phone);

    }

    public void setSickItemModel(SickItemModel sickItemModel) {
        this.sickItemModel = sickItemModel;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (isShow) {
            ParentsModel parentsModel = mParentsModelList.get(i);
            phone = parentsModel.getPhoneNum();
            mParentName.setText(parentsModel.getUserName());
            mPhone.setText(phone);
            popupWindow.dismiss();
        }

        if (isDiagnoseShow) {
            medicalResult = i + 1;
            mDiagnose1.setText(listDisease.get(i));
            diagnosePopupWindow.dismiss();
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.yes_rb:
                isInfection = 1;
                break;
            case R.id.no_rb:
                isInfection = 0;
                break;
            case R.id.home_rb:
                treatmentResult = 2;
                break;
            case R.id.hospital_rb:
                treatmentResult = 1;
                break;
            case R.id.stable_rb:
                state = 1;
                break;
            case R.id.serious_rb:
                state = 2;
                break;
            case R.id.alleviate_rb:
                state = 3;
                break;
            case R.id.recovery_rb:
                state = 4;
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFrist) {
            setData();
            isFrist = false;
        }
    }

    private void setData() {
        String symptoms = sickItemModel.getSymptoms();
        if (!TextUtils.isEmpty(symptoms)) {
            if (symptoms.contains(",")) {
                String sym[] = symptoms.split(",");
                for (int i = 0; i < sym.length; i++) {
                    for (int j = 0; j < symptomData.size(); j++) {
                        if (sym[i].equals(symptomData.get(j).getName())) {
                            symptomData.get(j).setChoose(true);
                        }
                    }
                    mTrackPresenter.getTmp().add(sym[i]);
                }

            } else {
                for (int j = 0; j < symptomData.size(); j++) {
                    if (symptoms.equals(symptomData.get(j).getName())) {
                        symptomData.get(j).setChoose(true);
                        mTrackPresenter.getTmp().add(symptoms);
                    }
                }
            }
            mSymptomAdapter.notifyDataSetChanged();
        }
        mHospital.setText(sickItemModel.getHospital());

        mDiagnose2.setText(sickItemModel.getResultInfo());
        isInfection = sickItemModel.getIsInfection();
        switch (isInfection) {
            case 0:
                mNoRb.setChecked(true);
                break;
            case 1:
                mYesRb.setChecked(true);
                break;
        }

        treatmentResult = sickItemModel.getTreatmentResult();
        switch (treatmentResult) {
            case 1:
                mHospitalRb.setChecked(true);
                break;
            case 2:
                mHomeRb.setChecked(true);
                break;
        }

        state = sickItemModel.getState();
        switch (state) {
            case 1:
                mStableRb.setChecked(true);
                break;
            case 2:
                mSeriousRb.setChecked(true);
                break;
            case 3:
                mAlleviateRb.setChecked(true);
                break;
            case 4:
                mRecoveryRb.setChecked(true);
                break;
        }
        mRemark.setText(sickItemModel.getAttach());
        long endTime = sickItemModel.getEndTime();
        if (endTime > 0) {
            mReturnTime.setText(DateUtils.getTimeStampToYYYY_MM_DD_EN(endTime));
        }
        try {
            medicalResult = sickItemModel.getMedicalResult();
            mDiagnose1.setText(listDisease.get(medicalResult - 1));
        } catch (Exception e) {
            medicalResult=1;
            mDiagnose1.setText(listDisease.get(0));
        }
    }

}
