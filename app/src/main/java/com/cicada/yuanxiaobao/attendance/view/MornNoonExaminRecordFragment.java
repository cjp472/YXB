package com.cicada.yuanxiaobao.attendance.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IMornNoonExaminResultAction;
import com.cicada.yuanxiaobao.attendance.adapter.PictureAdapter;
import com.cicada.yuanxiaobao.attendance.model.MornNoonExamineRecord;
import com.cicada.yuanxiaobao.attendance.model.ReceiveMornNoonRecordModel;
import com.cicada.yuanxiaobao.attendance.model.RequestKidMornNoonRecord;
import com.cicada.yuanxiaobao.attendance.presenter.MornNoonExaminRecordPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.ShowPictureActivity;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.utils.DateUtils;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

/**
 * Created by tanglei on 16/7/13.
 * 晨午检结果
 */
public class MornNoonExaminRecordFragment extends BaseFragment implements IMornNoonExaminResultAction, AdapterView.OnItemClickListener {
    @Bind(R.id.date)
    MyFontTextView mDate;
    @Bind(R.id.entrust_morn)
    MyFontTextView mEntrustMorn;
    @Bind(R.id.entrust_noon)
    MyFontTextView mEntrustNoon;
    @Bind(R.id.isEnterSchool_mron)
    MyFontTextView mIsEnterSchoolMron;
    @Bind(R.id.isEnterSchool_noon)
    MyFontTextView mIsEnterSchoolNoon;
    @Bind(R.id.symptom_morn)
    MyFontTextView mSymptomMorn;
    @Bind(R.id.symptom_noon)
    MyFontTextView mSymptomNoon;
    @Bind(R.id.pictures_mron_grid)
    GridView mPicturesMronGrid;
    @Bind(R.id.pictures_noon_grid)
    GridView mPicturesNoonGrid;
    @Bind(R.id.comment_morn)
    MyFontTextView mCommentMorn;
    @Bind(R.id.comment_noon)
    MyFontTextView mCommentNoon;
    @Bind(R.id.recorderName_morn)
    MyFontTextView mRecorderNameMorn;
    @Bind(R.id.recorderName_noon)
    MyFontTextView mRecorderNameNoon;

    private long childId;
    private long inspectTime;
    private MornNoonExaminRecordPresenter mRecordPresenter;
    private ArrayList<String> mornList=new ArrayList<>();
    private ArrayList<String> noonList=new ArrayList<>();
    private  KidArchiveDetailFragment mFragment;
    private  RequestKidMornNoonRecord mornNoonRecord;


    @Override
    protected int getLayoutResId() {
        return R.layout.morn_noon_examin_result_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mRecordPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mRecordPresenter = new MornNoonExaminRecordPresenter(this, mContext);
    }

    @Override
    public void initView() {
        mornNoonRecord = new RequestKidMornNoonRecord();
        mornNoonRecord.setInspectTime(inspectTime);
        mornNoonRecord.setChildId(childId);
        mRecordPresenter.queryChildInspect(mornNoonRecord);
        mPicturesMronGrid.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mPicturesNoonGrid.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mPicturesMronGrid.setOnItemClickListener(this);
        mPicturesNoonGrid.setOnItemClickListener(this);
    }

    public void onrefresh(){
        mRecordPresenter.queryChildInspect(mornNoonRecord);
    }

    @Override
    public void callBack(ReceiveMornNoonRecordModel noonRecordModel) {
        mDate.setText(DateUtils.getTimeStampToYYYY_MM_DD_EN(inspectTime));
        MornNoonExamineRecord moring = noonRecordModel.getMorning();
        MornNoonExamineRecord noon = noonRecordModel.getNoon();

        if (moring != null) {
            mFragment.setClassName(moring.getClassName());
            mEntrustMorn.setText(moring.getMedicalOrders());
            mIsEnterSchoolMron.setText(moring.getIsEnterSchool() == 1 ? "是" : "否");
            mSymptomMorn.setText(moring.getSymptom());
            mCommentMorn.setText(moring.getComment());
            mRecorderNameMorn.setText(moring.getRecorderName());
            String mornPictures = moring.getPictures();
            if (!TextUtils.isEmpty(mornPictures)) {
                String[] mornStr = mornPictures.split(",");
                mornList.clear();
                mornList.addAll(Arrays.asList(mornStr));
                PictureAdapter morningAdapter = new PictureAdapter(mContext, R.layout.picture_item, mornList);
                mPicturesMronGrid.setAdapter(morningAdapter);
            }
        }


        if (noon != null) {
            mEntrustNoon.setText(noon.getMedicalOrders());
            mIsEnterSchoolNoon.setText(noon.getIsEnterSchool() == 1 ? "是" : "否");
            mSymptomNoon.setText(noon.getSymptom());
            mCommentNoon.setText(noon.getComment());
            mRecorderNameNoon.setText(noon.getRecorderName());
            String noonPictures = noon.getPictures();
            if (!TextUtils.isEmpty(noonPictures)) {
                String[] noonStr = noonPictures.split(",");
                noonList.clear();
                noonList.addAll(Arrays.asList(noonStr));
                PictureAdapter nooningAdapter = new PictureAdapter(mContext, R.layout.picture_item, noonList);
                mPicturesNoonGrid.setAdapter(nooningAdapter);
            }
        }

    }

    public void setChildId(long childId) {
        this.childId = childId;

    }

    public void setInspectTime(long inspectTime) {
        this.inspectTime = inspectTime;
    }

    public void setFragment(KidArchiveDetailFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            String str;
            int index = 0;
            Intent intent = new Intent(mContext, ShowPictureActivity.class);
            switch (adapterView.getId()) {
                case R.id.pictures_mron_grid:
                    intent.putStringArrayListExtra("listPath", mornList);
                    str = mornList.get(i);
                    index = mornList.indexOf(str);
                    break;
                case R.id.pictures_noon_grid:
                    intent.putStringArrayListExtra("listPath", noonList);
                    str = noonList.get(i);
                    index = noonList.indexOf(str);
                    break;
            }
            intent.putExtra("index", index);
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
