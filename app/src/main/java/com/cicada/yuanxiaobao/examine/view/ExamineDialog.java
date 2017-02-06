package com.cicada.yuanxiaobao.examine.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.UploadFilePresenter;
import com.cicada.yuanxiaobao.examine.action.IExamineDialogAction;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveSymptomModel;
import com.cicada.yuanxiaobao.examine.model.RequestExamineModel;
import com.cicada.yuanxiaobao.examine.presenter.ExamineDialogPresenter;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/27.
 */
public class ExamineDialog extends Dialog implements IExamineDialogAction {

    @Bind(R.id.title)
    AppCompatTextView mTitle;
    @Bind(R.id.close)
    ImageView mClose;
    @Bind(R.id.left_grid)
    GridView mLeftGrid;
    @Bind(R.id.right_grid)
    GridView mRightGrid;
    @Bind(R.id.observe)
    Button mObserve;
    @Bind(R.id.medicine)
    Button mMedicine;
    @Bind(R.id.alert)
    Button mAlert;
    @Bind(R.id.remark)
    AppCompatEditText mRemark;
    @Bind(R.id.in_preschool)
    Button mInPreschool;
    @Bind(R.id.out_preschool)
    Button mOutPreschool;
    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;

    private Context mContext;
    private Window dialogWindow;
    private String flag;
    private Fragment mFragment;
    private UploadFilePresenter mUploadPresenter;
    private ExamineDialogPresenter mDialogPresenter;
    private RequestExamineModel mRequestExamineModel = new RequestExamineModel();
    private int inspectType=1;
    private ClassModel classModel;
    private KidModel kidModel;
    private  View mView;

    public ExamineDialog(Context context, Fragment fragment) {
        super(context, R.style.selection_dialog_theme);
        mContext = context;
        this.mFragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.examine_popup_window);
        ButterKnife.bind(this);
        dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setWindowAnimations(android.R.style.Animation_Dialog);
        init();
    }

    private void init() {
        AppTools.expandViewTouchDelegate(mClose,50,50,50,50);
        String title=classModel.getClassName()+"/"+kidModel.getChildName()+"/"+kidModel.getChildSex()+"/"+kidModel.getChildAge()+"岁";
        mTitle.setText(title);
        mUploadPresenter = new UploadFilePresenter(this);
        mDialogPresenter = new ExamineDialogPresenter(this, mContext, mFragment);
        mDialogPresenter.initLeftData();
        mDialogPresenter.initRightData();
        //左边GridView
        mLeftGrid.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mLeftGrid.setOnItemClickListener(mDialogPresenter);
        //右边拍照GridView
        mRightGrid.setOnItemClickListener(mDialogPresenter);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.observe:
                        flag = "需要观察";
                        break;
                    case R.id.medicine:
                        flag = "委托吃药";
                        break;
                    case R.id.alert:
                        flag = "传染病预警";
                        break;
                }
            }
        });
    }

    @OnClick({R.id.close, R.id.in_preschool, R.id.out_preschool})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close:
                dismiss();
                break;
            case R.id.in_preschool:
                mRequestExamineModel.setIsEnterSchool(1);
                mView=view;
                uploadImage();
                break;
            case R.id.out_preschool:
                mRequestExamineModel.setIsEnterSchool(0);
                mView=view;
                uploadImage();
                break;
        }
    }

    private void uploadImage() {
        List<ReceiveSymptomModel> list = mDialogPresenter.getSymptomTmpData();
        if (list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (ReceiveSymptomModel model : list) {
                sb.append(model.getName() + ",");
            }
            int index = sb.lastIndexOf(",");
            sb.deleteCharAt(index);
            mRequestExamineModel.setSymptom(sb.toString());
        }
        mRequestExamineModel.setMedicalOrders(flag);
        mRequestExamineModel.setComment(mRemark.getText().toString());
        mRequestExamineModel.setInspectTime(DateUtils.getDateToTimeStamp(DateUtils.getDateNow()));
       if(mDialogPresenter.verify(mRequestExamineModel)){
           setButtonBg();
           List<String> listPath = mDialogPresenter.getListPath();
           if (listPath.size() > 0) {
               mUploadPresenter.upload(listPath);
               ToastUtil.showShort("正在提交...");
           } else {
               commit();
           }
       }

    }

    private void commit() {
        mDialogPresenter.addInspectInfo(mRequestExamineModel);
    }


    public void addPath() {
        mDialogPresenter.addPath();
    }


    @Override
    public void leftGridViewAdapter(QuickAdapter adapter) {
        mLeftGrid.setAdapter(adapter);
    }

    @Override
    public void rightGridViewAdapter(QuickAdapter adapter) {
        mRightGrid.setAdapter(adapter);
    }

    @Override
    public void uploadFileCompleted(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str + ",");
        }
        int index = sb.lastIndexOf(",");
        sb.deleteCharAt(index);
        mRequestExamineModel.setPictures(sb.toString());
        commit();
    }

    @Override
    public void callBack() {
        mView.setEnabled(true);
        mView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bt_in_enable_bg));
        dismiss();
        ToastUtil.showShort("提交成功!");
    }

    @Override
    public void callBackFail() {
        mView.setEnabled(true);
        mView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bt_in_enable_bg));
    }

    @Override
    public void setButtonBg() {
//        mView.setEnabled(false);
//        mView.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bt_in_enable1_bg));
    }

    public  void set(ClassModel classModel, KidModel kidModel){
        this.classModel=classModel;
        this.kidModel=kidModel;
        mRequestExamineModel.setClassId(classModel.getClassId());
        mRequestExamineModel.setChildId(kidModel.getChildId());
    }

    public void setInspectType(int inspectType) {
        this.inspectType = inspectType;
        mRequestExamineModel.setInspectType(inspectType);
    }
}
