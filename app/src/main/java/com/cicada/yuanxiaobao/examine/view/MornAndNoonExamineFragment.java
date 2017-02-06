package com.cicada.yuanxiaobao.examine.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.ICurrentStack;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.Stack;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/20.
 */
public class MornAndNoonExamineFragment extends BaseFragment implements ICurrentStack {

    public Stack<Fragment> sFragmentStack = new Stack<>();
    @Bind(R.id.image)
    AppCompatTextView mImage;
    @Bind(R.id.examine_tv)
    AppCompatTextView mExamineTv;
    @Bind(R.id.examine_result_tv)
    AppCompatTextView mExamineResultTv;

    private ExamineFragment mExamineFragment;
    private ExamineResultFragment mExamineResultFragment;
    private FragmentTransaction transaction;
    protected int itemheight;
    private Fragment[] mFragments;
    private AppCompatTextView[] mTextViews;


    public MornAndNoonExamineFragment() {
        super();
        sFragmentStack.push(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.morn_and_noon_examine_fragment;
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
        //表格 item 为正方形  1排最多11列
        itemheight = (DeviceUtils.getScreenWidth(mContext) - DeviceUtils.dip2Px(mContext, 160)) / 11;
        transaction = getChildFragmentManager().beginTransaction();
        mExamineFragment = (ExamineFragment) getChildFragmentManager().findFragmentById(R.id.examine_fragment);
        mExamineResultFragment = (ExamineResultFragment) getChildFragmentManager().findFragmentById(R.id.examine_result_fragment);
        mFragments = new Fragment[]{mExamineFragment, mExamineResultFragment};
        mTextViews = new AppCompatTextView[]{mExamineTv, mExamineResultTv};
    }

    @Override
    public Stack<Fragment> get() {
        return sFragmentStack;
    }


    @OnClick({R.id.examine_tv, R.id.examine_result_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.examine_tv:
                changeTab(0);
                break;
            case R.id.examine_result_tv:
                changeTab(1);
                break;
        }
    }

    private void changeTab(int index) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {
            if (i == index) {
                transaction.show(mFragments[i]);
                mTextViews[i].setTextColor(ContextCompat.getColor(mContext, R.color.colorBlue));
                if (index == 0) {
                    mTextViews[i].setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bt_morn_noon_bg));
                } else
                    mTextViews[i].setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bt_morn_noon_bg1));
            } else {
                transaction.hide(mFragments[i]);
                mTextViews[i].setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
                mTextViews[i].setBackgroundDrawable(null);
            }
        }
        transaction.commit();
    }
}
