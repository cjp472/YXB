package com.cicada.yuanxiaobao.home.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.Constant;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.FontManager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/6/22.
 */
public class HomeNoticeDetailsFragment extends BaseFragment {
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.vp_view)
    ViewPager mVpView;
    @Bind(R.id.back)
    AppCompatImageView mBack;

    private HomeNoticeListFragment homeNoticeListFragmentLeft, homeNoticeListFragmentRight;
    private HomeNoticeDetailsRightFragment mDetailsRightFragment;
    private Fragment[] mFragments;
    private FragmentManager mFragmentManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.home_notice_details_fragment;
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
        AppTools.expandViewTouchDelegate(mBack,50,50,50,50);
        mDetailsRightFragment = (HomeNoticeDetailsRightFragment) getChildFragmentManager().findFragmentById(R.id.detais_right_fragment);
        mFragmentManager = getActivity().getSupportFragmentManager();
        mTabs.setTabMode(TabLayout.MODE_FIXED);
        homeNoticeListFragmentLeft = new HomeNoticeListFragment(1, true);
        homeNoticeListFragmentRight = new HomeNoticeListFragment(2, true);
        mFragments = new Fragment[]{homeNoticeListFragmentLeft, homeNoticeListFragmentRight};
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(mFragmentManager);
        mVpView.setAdapter(adapter);
        mTabs.setupWithViewPager(mVpView);
        mTabs.setTabsFromPagerAdapter(adapter);
        FontManager.applyFont(mTabs);
    }


    @OnClick(R.id.back)
    public void onClick() {
        back();
    }

    @Override
    public void back() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(homeNoticeListFragmentLeft);
        transaction.remove(homeNoticeListFragmentRight);
        transaction.commit();
        ((FragmentMainActivity) getActivity()).backFragment(Constant.HOME_FLAG);

    }

    /**
     * FragmentsAdapter
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        String strTitle[] = {"通告公知", "园宝帮帮"};

        public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int arg0) {
            return mFragments[arg0];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return strTitle[position];
        }
    }


}
