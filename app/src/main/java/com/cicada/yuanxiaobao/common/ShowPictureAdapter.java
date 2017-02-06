package com.cicada.yuanxiaobao.common;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @class MyAdapter.java [V 1.0.0]
 * 
 * @author 唐磊
 * 
 * @time 2014-4-22 下午1:46:00
 * 
 * @TODO [图片展示适配器]
 */
public class ShowPictureAdapter extends PagerAdapter {
	private List<View> listView;

	public ShowPictureAdapter(List<View> listView) {
		super();
		this.listView = listView;
	}

	@Override
	public int getCount() {
		return listView.size();

	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub

		return POSITION_NONE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager) container).removeView((View) object);

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		System.out.println(position);
		((ViewPager) container).addView(listView.get(position));

		return listView.get(position);
	}

}
