package com.cicada.yuanxiaobao.attendance.view;

import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ISpecialFocusAction;
import com.cicada.yuanxiaobao.attendance.presenter.SpecialFousPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.widget.MyGridView;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidDiseaseAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;

import java.util.List;

import butterknife.Bind;

/**
 * Created by tanglei on 16/7/8.
 */
public class SpecialFocusLeftFragment extends BaseFragment implements ISpecialFocusAction, AdapterView.OnItemClickListener {

    @Bind(R.id.kid_gridview)
    MyGridView mKidGridview;
    @Bind(R.id.gridview)
    MyGridView mGridview;
    private SpecialFousPresenter mPresenter;
    private ClassKidDiseaseAdapter diseaseAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.special_focus_left_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mPresenter = new SpecialFousPresenter(this, mContext);
    }

    @Override
    public void initView() {
        mKidGridview.setOnItemClickListener(this);
    }

    public void setClassIdTime(long classId, long time) {
        try {
            mPresenter.queryChildByClassIds(classId + "", time, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAdapter(QuickAdapter adapter) {
        diseaseAdapter = (ClassKidDiseaseAdapter) adapter;
        mKidGridview.setAdapter(adapter);
        List<KidModel> list = diseaseAdapter.getData();
        ((SpecialFocusFragemnt)getParentFragment()).setListKidModel(list);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        List<KidModel> list = diseaseAdapter.getData();
        KidModel kidModel = list.get(i);
        ((SpecialFocusFragemnt)getParentFragment()).setKidModel(kidModel);
    }
}
