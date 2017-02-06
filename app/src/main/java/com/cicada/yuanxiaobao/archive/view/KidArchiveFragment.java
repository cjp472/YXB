package com.cicada.yuanxiaobao.archive.view;

import android.support.v4.app.Fragment;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.ICurrentStack;

import java.util.Stack;

/**
 * Created by tanglei on 16/6/20.
 */
public class KidArchiveFragment extends BaseFragment implements ICurrentStack {
    public  Stack<Fragment> sFragmentStack=new Stack<>();

    public KidArchiveFragment() {
        super();
        sFragmentStack.push(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.kid_archive_fragment;
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

    }

    @Override
    public Stack<Fragment> get() {
        return sFragmentStack;
    }
}
