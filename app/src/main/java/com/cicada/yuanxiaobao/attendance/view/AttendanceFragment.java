package com.cicada.yuanxiaobao.attendance.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.ICurrentStack;

import java.util.Stack;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/20.
 */
public class AttendanceFragment extends BaseFragment implements ICurrentStack {

    public Stack<Fragment> sFragmentStack = new Stack<>();
    @Bind(R.id.image)
    AppCompatTextView mImage;
    @Bind(R.id.check_tv)
    AppCompatTextView mCheckTv;
    @Bind(R.id.sick_track_tv)
    AppCompatTextView mSickTrackTv;
    @Bind(R.id.special_focus_tv)
    AppCompatTextView mSpecialFocusTv;

    private CheckFragment mCheckFragment;
    private SickTrackFragment mSickTrackFragment;
    private SpecialFocusFragemnt mSpecialFocusFragemnt;
    private Fragment[] mFragments;
    private AppCompatTextView[] mTextViews;

    public AttendanceFragment() {
        sFragmentStack.push(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.attendance_fragment;
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
        mCheckFragment = (CheckFragment) getChildFragmentManager().findFragmentById(R.id.check_fragment);
        mSickTrackFragment = (SickTrackFragment) getChildFragmentManager().findFragmentById(R.id.sick_traack_fragment);
        mSpecialFocusFragemnt = (SpecialFocusFragemnt) getChildFragmentManager().findFragmentById(R.id.special_focus_fragment);
        mFragments = new Fragment[]{mCheckFragment, mSickTrackFragment, mSpecialFocusFragemnt};
        mTextViews = new AppCompatTextView[]{mCheckTv, mSickTrackTv, mSpecialFocusTv};
    }


    @Override
    public Stack<Fragment> get() {
        return sFragmentStack;
    }


    @OnClick({R.id.check_tv, R.id.sick_track_tv, R.id.special_focus_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_tv:
                changeTab(0);
                break;
            case R.id.sick_track_tv:
                changeTab(1);
                break;
            case R.id.special_focus_tv:
                changeTab(2);
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
                    mTextViews[i].setBackground(ContextCompat.getDrawable(mContext, R.drawable.bt_morn_noon_bg));
                } else
                    mTextViews[i].setBackground(ContextCompat.getDrawable(mContext, R.drawable.bt_morn_noon_bg1));
            } else {
                transaction.hide(mFragments[i]);
                mTextViews[i].setTextColor(ContextCompat.getColor(mContext, R.color.colorText));
                mTextViews[i].setBackgroundDrawable(null);
            }
        }
        transaction.commit();
    }
}
