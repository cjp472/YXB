package com.cicada.yuanxiaobao.home.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by tanglei on 16/6/21.
 * 无限循环ViewPage Adapter
 */
public class MyViewPagerAdapter extends PagerAdapter {

    List<ImageView> mImageViewList;

    public MyViewPagerAdapter(List<ImageView> imageViewList) {
        mImageViewList = imageViewList;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mImageViewList.get((position) % mImageViewList.size()));
    }


    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(mImageViewList.get(position % mImageViewList.size()));
        return mImageViewList.get(position % mImageViewList.size());
    }
}
