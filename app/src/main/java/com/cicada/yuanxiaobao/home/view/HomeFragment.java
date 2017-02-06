package com.cicada.yuanxiaobao.home.view;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.GlideRoundTransform;
import com.cicada.yuanxiaobao.common.ICurrentStack;
import com.cicada.yuanxiaobao.common.widget.ViewPagerScroller;
import com.cicada.yuanxiaobao.home.adapter.MyViewPagerAdapter;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import butterknife.Bind;

/**
 * Created by tanglei on 16/6/17.
 */
public class HomeFragment extends BaseFragment implements ICurrentStack, View.OnTouchListener {

    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private HomeNoticeListFragment mLeftHomeNoticeListFragment;
    private HomeNoticeListFragment mRightHomeNoticeListFragment;
    public Stack<Fragment> sFragmentStack = new Stack<>();

    public HomeFragment() {
        super();
        sFragmentStack.push(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_fragment;
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
        mLeftHomeNoticeListFragment = new HomeNoticeListFragment(1);
        mRightHomeNoticeListFragment = new HomeNoticeListFragment(2);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.left_notice_fragmet, mLeftHomeNoticeListFragment);
        transaction.add(R.id.right_notice_fragmet, mRightHomeNoticeListFragment);
        transaction.commit();
        initViewPage();
    }

    private void initViewPage() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mViewPager.getLayoutParams();
        lp.height = (int) ((DeviceUtils.getScreenWidth(getContext()) - DeviceUtils.dip2Px(getContext(), 130)) / 3.5f);
        List<ImageView> list = new ArrayList<>();
        String imagePath[] = {/*"http://img1.3lian.com/2015/w7/98/d/22.jpg",*/
                    "http://pic1.nipic.com/2008-12-09/200812910493588_2.jpg",
                "http://img.61gequ.com/allimg/2011-4/201142614314278502.jpg",
                "http://www.bz55.com/uploads/allimg/120724/1-120H4100302.jpg"};
        List<String> list1=new ArrayList<>();
        for (int i = 0; i <imagePath.length ; i++) {
            list1.add(imagePath[i]);
        }

        list1=getListPath(list1);

        for (int i = 0; i < list1.size(); i++) {
            ImageView image = new ImageView(mContext);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(getActivity()).load(list1.get(i)).transform(new GlideRoundTransform(getContext(), 5)).into(image);
            list.add(image);
        }
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(list);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(list.size() * 100);
        mHandler.sendEmptyMessageDelayed(0, 2000);
        mViewPager.setOnTouchListener(this);
        ViewPagerScroller scroller = new ViewPagerScroller(mContext);
        //这个是设置切换过渡时间为
        scroller.setScrollDuration(1000);
        scroller.initViewPagerScroll(mViewPager);
    }

    private List<String> getListPath(List<String> list){
        if(list.size()<4){
            list.addAll(list);
            getListPath(list);
        }
        return list;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int index = mViewPager.getCurrentItem() + 1;
            mViewPager.setCurrentItem(index, true);
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    public Stack<Fragment> get() {
        return sFragmentStack;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mHandler.removeMessages(0);
                break;
            case MotionEvent.ACTION_UP:
                mHandler.sendEmptyMessageDelayed(0, 2000);
                break;
        }
        return false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            mHandler.removeMessages(0);
        }else  mHandler.sendEmptyMessageDelayed(0, 2000);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeMessages(0);
    }
}
