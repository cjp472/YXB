package com.cicada.yuanxiaobao.attendance.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IChooseClass;
import com.cicada.yuanxiaobao.attendance.action.ISickTrackAction;
import com.cicada.yuanxiaobao.attendance.adapter.SpinnerAdapter;
import com.cicada.yuanxiaobao.attendance.presenter.SickTrackPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.widget.MyLoadListView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/1.
 * 病假追踪
 */
public class SickTrackFragment extends BaseFragment implements ISickTrackAction, IChooseClass, AdapterView.OnItemClickListener {

    @Bind(R.id.date)
    MyFontTextView mDate;
    @Bind(R.id.date_layout)
    LinearLayout mDateLayout;
    @Bind(R.id.list_view)
    MyLoadListView mListView;
    @Bind(R.id.swip)
    SwipeRefreshLayout mSwip;
    @Bind(R.id.records)
    MyFontTextView mRecords;

    private SickTrackPresenter mSickTrackPresenter;
    private TeacherClassFragment mTeacherClassFragment;
    private SpinnerAdapter mSpinnerAdapter;
    private PopupWindow popupWindow;
    private String str[] = {"全部", "稳定", "加重", "减轻", "痊愈"};
    private List<String> listString;
    private int state;


    @Override
    protected int getLayoutResId() {
        return R.layout.sick_track_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mSickTrackPresenter};
    }

    @Override
    protected void onInitPresenters() {
        listString = Arrays.asList(str);
        mSickTrackPresenter = new SickTrackPresenter(this, mContext);
        mTeacherClassFragment = (TeacherClassFragment) getChildFragmentManager().findFragmentById(R.id.class_fragment);
        mTeacherClassFragment.setIChooseClass(this);
        mDate.setText(listString.get(0));
        mSickTrackPresenter.setState(state);
    }

    @Override
    public void initView() {
        mSpinnerAdapter = new SpinnerAdapter(mContext, R.layout.spinner_item, listString);
        popupWindow = new PopupWindow(mContext);
        mSwip.setColorSchemeColors(Color.GREEN);
        mSwip.setOnRefreshListener(mSickTrackPresenter);
        mListView.setOnLoadListener(mSickTrackPresenter);
        mListView.setOnItemClickListener(mSickTrackPresenter);
        mSickTrackPresenter.init();
    }


    @OnClick({R.id.date_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.date_layout:
                showPopupWindow(popupWindow, mDateLayout, mSpinnerAdapter);
                break;
        }
    }


    @Override
    public void setAdapter(QuickAdapter adapter) {
        mListView.setAdapter(adapter);
    }

    @Override
    public void noMoreData() {
        mListView.noMoreData();
    }

    @Override
    public void refreshData() {
        mListView.refreshData();
        mSwip.setRefreshing(false);
    }

    @Override
    public void setRecords(int records) {
        mRecords.setText("病假("+records+"人)");
    }

    @Override
    public void setState() {
        mDate.setText(listString.get(0));
    }


    @Override
    public void callBack(ClassModel classModel) {
        mSickTrackPresenter.setClassId(classModel.getClassId());
        mSickTrackPresenter.onRefresh();

    }

    public void showPopupWindow(PopupWindow popupWindow, View view, QuickAdapter adapter) {
        int w = view.getWidth();
        ListView listView = (ListView) LayoutInflater.from(mContext).inflate(R.layout.parents_popup_window1, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        popupWindow.setContentView(listView);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(w);
        if (adapter.getData().size() > 5) {
            popupWindow.setHeight(DeviceUtils.dip2px(mContext, 200));
        } else popupWindow.setHeight(DeviceUtils.dip2px(mContext, 40 * adapter.getData().size()));
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        Drawable dw = ContextCompat.getDrawable(mContext, R.drawable.list_bg);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAsDropDown(view, 0, -1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mDate.setText(listString.get(i));
        state = i;
        mSickTrackPresenter.setState(state);
        mSickTrackPresenter.onRefresh();
        popupWindow.dismiss();
    }



}
