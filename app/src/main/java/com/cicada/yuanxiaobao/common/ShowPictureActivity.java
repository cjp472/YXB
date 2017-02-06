package com.cicada.yuanxiaobao.common;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.bumptech.glide.Glide;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.widget.ZoomImageView;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;

/**
 * @author 唐磊
 * @class WorkLogShowPicture.java [V 1.0.0]
 * @time 2014-5-6 下午2:45:45
 * @TODO [图片展示]
 */

public class ShowPictureActivity extends BaseActivity implements OnPageChangeListener {
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.navigation_layout)
    LinearLayout mNavigationLayout;

    private List<View> lists = new ArrayList<View>();
    private ShowPictureAdapter adapter;
    private ArrayList<String> listPath;

    @Override
    protected int getLayoutResId() {
        return R.layout.show_picture;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[0];
    }

    @Override
    protected void onInitPresenters() {

    }

    @Override
    protected void parseArgumentsFromIntent(Intent argIntent) {
        listPath = getIntent().getStringArrayListExtra("listPath");

    }

    @Override
    public void initView() {
        mViewpager.addOnPageChangeListener(this);
        initData();
    }





    protected void initData() {
        for (int i = 0; i < listPath.size(); i++) {
            ZoomImageView imageView = new ZoomImageView(this);
            lists.add(imageView);
        }

        for (int i = 0; i < listPath.size(); i++) {
            ImageView image = new ImageView(this);
            LayoutParams lp = new LayoutParams(DeviceUtils.dip2px(this, 8), DeviceUtils.dip2px(this, 8));
            lp.setMargins(DeviceUtils.dip2px(this, 10), 0, 0, 0);
            lp.gravity = Gravity.CENTER_VERTICAL;
            image.setLayoutParams(lp);
            if (i == 0) {
                image.setBackgroundResource(R.drawable.tishi_picture1);
            } else
                image.setBackgroundResource(R.drawable.tishi_picture2);
            mNavigationLayout.addView(image);
        }
        adapter = new ShowPictureAdapter(lists);
        mViewpager.setAdapter(adapter);
        int index = getIntent().getIntExtra("index", 0);
        if (index == 0) {
            loadImage(listPath.get(0), (ImageView) lists.get(0));
        } else {
            mViewpager.setCurrentItem(index);
        }

    }


    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int arg0) {

        for (int i = 0; i < listPath.size(); i++) {
            if (i == arg0) {
                mNavigationLayout.getChildAt(arg0).setBackgroundResource(R.drawable.tishi_picture1);
            } else
                mNavigationLayout.getChildAt(i).setBackgroundResource(R.drawable.tishi_picture2);
        }

        ImageView imageView = (ImageView) lists.get(arg0);
        if (imageView.getDrawable() == null) {// 判断图片是否已加载 null未加载
            loadImage(listPath.get(arg0), imageView);
        }
    }

    private void loadImage(String str, ImageView imageView) {

        if (str.startsWith("http")) {
            Glide.with(this).load(str).into(imageView);
        } else {
            Glide.with(this).load(new File(str)).into(imageView);
        }
    }





}
