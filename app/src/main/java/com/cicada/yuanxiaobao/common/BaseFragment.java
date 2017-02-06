package com.cicada.yuanxiaobao.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cicada.yuanxiaobao.utils.FontManager;

import butterknife.ButterKnife;

/**
 * Created by tanglei on 16/6/17.
 * Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    protected Context mContext;

    /**
     * 获取layout的id，具体由子类实现
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     *需要子类来实现，获取子类的IPresenter，一个activity有可能有多个IPresenter
     */
    protected abstract BasePresenter[] getPresenters();

    //初始化presenters，
    protected abstract void onInitPresenters();


    public abstract  void initView();

    /**
     *Fragment 有返回 需要子类来实现
     */
    public void back(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(getLayoutResId(),null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onInitPresenters();
        initView();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BasePresenter[] basePresenters=getPresenters();
        if (basePresenters != null) {
            for (int i = 0; i <basePresenters.length ; i++) {
                basePresenters[i].onDetach();
            }
        }

    }
}
