package com.cicada.yuanxiaobao.home.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.Constant;
import com.cicada.yuanxiaobao.home.adapter.NoticeListAdapter;
import com.cicada.yuanxiaobao.home.model.NoticeModel;
import com.cicada.yuanxiaobao.widget.MyLoadListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by tanglei on 16/6/22.
 * 首页公告
 */
public class HomeNoticeListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MyLoadListView.OnLoadListener, AdapterView.OnItemClickListener {
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.listview)
    MyLoadListView mListview;
    @Bind(R.id.swip)
    SwipeRefreshLayout mSwip;
    @Bind(R.id.view)
    View mViewLine;
    @Bind(R.id.layout)
    LinearLayout mLayout;

    private int flag;
    private boolean isHideTitle;
    private NoticeListAdapter mNoticeListAdapter;
    private List<NoticeModel> data = new ArrayList<>();

    public HomeNoticeListFragment() {
        super();
    }

    @SuppressLint("ValidFragment")
    public HomeNoticeListFragment(int flag) {
        super();
        this.flag = flag;
    }

    public HomeNoticeListFragment(int flag, boolean isHideTitle) {
        this.flag = flag;
        this.isHideTitle = isHideTitle;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.home_notice_fragment;
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
        if (isHideTitle) {
            mLayout.setBackground(null);
            mTitle.setVisibility(View.GONE);
            mViewLine.setVisibility(View.GONE);
        }
        switch (flag) {
            case 1:
                mTitle.setText("通告公知");
                break;
            case 2:
                mTitle.setText("园宝帮帮");
                break;
        }

        mSwip.setColorSchemeColors(Color.GREEN);
        mSwip.setOnRefreshListener(this);
        mListview.setOnLoadListener(this);
        mListview.setOnItemClickListener(this);
        for (int i = 0; i < 30; i++) {
            NoticeModel model = new NoticeModel();
            data.add(model);
        }
        mNoticeListAdapter = new NoticeListAdapter(getActivity(), R.layout.home_notice_list_item, data);
        mListview.setAdapter(mNoticeListAdapter);
    }


    @Override
    public void onRefresh() {
        data.addAll(data);
        mNoticeListAdapter.notifyDataSetChanged();
        mSwip.setRefreshing(false);
    }

    @Override
    public void onLoad() {

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (isHideTitle) {

        } else {
            Fragment fragment=new HomeNoticeDetailsFragment();
            ((FragmentMainActivity)getActivity()).startFragment(fragment, Constant.HOME_FLAG);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
