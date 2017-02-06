package com.cicada.yuanxiaobao.examine.view;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.widget.MyGridView;
import com.cicada.yuanxiaobao.examine.action.IExamineResultAction;
import com.cicada.yuanxiaobao.examine.action.ISingleMultiSelect;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.presenter.ExamineResultPresenter;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DateUtils;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by tanglei on 16/6/24.
 * 检查结果
 */
public class ExamineResultFragment extends BaseFragment implements Action1<Integer>, ISingleMultiSelect, IExamineResultAction, RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.date)
    TextView mDate;
    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;
    @Bind(R.id.scrollView)
    ScrollView mScrollView;
    @Bind(R.id.class_layout)
    LinearLayout mClassLayout;
    @Bind(R.id.enter_gridview)
    MyGridView mEnterGridview;
    @Bind(R.id.enter_layout)
    LinearLayout mEnterLayout;
    @Bind(R.id.out_gridview)
    MyGridView mOutGridview;
    @Bind(R.id.out_layout)
    LinearLayout mOutLayout;

    private ClassFragment mClassFragment;
    private int columns;
    private ExamineResultPresenter mResultPresenter;
    private int mn = 1;
    private long inspectTime;
    private  String classIds;



    @Override
    protected int getLayoutResId() {
        return R.layout.examin_result_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mResultPresenter};
    }

    @Override
    protected void onInitPresenters() {
        int h = ((MornAndNoonExamineFragment) getParentFragment()).itemheight;
        mResultPresenter = new ExamineResultPresenter(this, mContext, h);
    }

    @Override
    public void initView() {
        mRadioGroup.setOnCheckedChangeListener(this);
        mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(DateUtils.getDateNow()));
        mClassFragment = (ClassFragment) getChildFragmentManager().findFragmentById(R.id.class_fragment);
        mClassFragment.setTableTop(true);
        mEnterGridview.setOnItemClickListener(mResultPresenter);
        mOutGridview.setOnItemClickListener(mResultPresenter);
    }

    @OnClick(R.id.date)
    public void onClick() {
        AppTools.obtainData(getActivity(), mHandler,0);
    }

    @Override
    public void call(Integer i) {
        columns = 11 - i;
        mEnterGridview.setNumColumns(columns);
        mOutGridview.setNumColumns(columns);
        ((LinearLayout.LayoutParams) mClassLayout.getLayoutParams()).weight = columns;
        ((LinearLayout.LayoutParams) mScrollView.getLayoutParams()).weight = i;
        mResultPresenter.setColumns(columns);

    }


    @Override
    public void singleSelection(ClassModel model) {

        if ("".equals(model.getClassIds())) {
            classIds = model.getClassId() + "";
        } else classIds = model.getClassIds();
        mResultPresenter.queryChildByClassIds(classIds, getInspectTime(),mn);
    }

    @Override
    public void multipleSelection(List<ClassModel> list) {

    }


    @Override
    public void setEnterAdapter(QuickAdapter adapter) {
        mEnterLayout.setVisibility(View.VISIBLE);
        mEnterGridview.setAdapter(adapter);

    }

    @Override
    public void setOutAdapter(QuickAdapter adapter) {
        mOutLayout.setVisibility(View.VISIBLE);
        mOutGridview.setAdapter(adapter);
    }

    @Override
    public void setEnterLayout() {
        mEnterLayout.setVisibility(View.GONE);
    }

    @Override
    public void setOutLayout() {
        mOutLayout.setVisibility(View.GONE);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.morn:
                mn = 1;
                break;
            case R.id.noon:
                mn = 2;
                break;
        }
        mResultPresenter.queryChildByClassIds(classIds, getInspectTime(),mn);
    }

    public long getInspectTime() {
        String DataString= mDate.getText().toString();
        Date date=DateUtils.getStringToDate_YYYY_MM_DD_EN(DataString);
        inspectTime=DateUtils.getDateToTimeStamp(DateUtils.getTimeOfDayStart(date));
        return inspectTime;
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Date date= (Date) msg.obj;
            mDate.setText(DateUtils.getDateToString_YYYY_MM_DD_EN(date));
            mResultPresenter.queryChildByClassIds(classIds, getInspectTime(),mn);

        }
    };
}
