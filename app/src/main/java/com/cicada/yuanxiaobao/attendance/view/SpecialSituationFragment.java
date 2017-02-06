package com.cicada.yuanxiaobao.attendance.view;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ISpecialSituationAction;
import com.cicada.yuanxiaobao.attendance.adapter.SpecialSituationAdapter;
import com.cicada.yuanxiaobao.attendance.model.ReceiveSpecialSituatModel;
import com.cicada.yuanxiaobao.attendance.presenter.SpecialSituationPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.font.MyFontEditText;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.common.widget.MyListView;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/13.
 * 特别情况
 */
public class SpecialSituationFragment extends BaseFragment implements ISpecialSituationAction {
    @Bind(R.id.allergen)
    MyFontEditText mAllergen;
    @Bind(R.id.list_view)
    MyListView mListView;
    @Bind(R.id.add)
    MyFontTextView mAdd;
    @Bind(R.id.remark)
    MyFontEditText mRemark;
    @Bind(R.id.save)
    MyFontTextView mSave;

    private long childId;
    private SpecialSituationPresenter mPresenter;
    private List<ReceiveSpecialSituatModel.ContagionBean> contagion;
    private SpecialSituationAdapter adapter;
    private ReceiveSpecialSituatModel situatModel;


    @Override
    protected int getLayoutResId() {
        return R.layout.special_situation_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mPresenter = new SpecialSituationPresenter(this, mContext);
    }

    @Override
    public void initView() {
        mPresenter.queryInspectSpecial(childId);
    }

    public void onrefresh(){
        try {
            mPresenter.queryInspectSpecial(childId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    @OnClick({R.id.add, R.id.save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                ContagionTrackDialog dialog = new ContagionTrackDialog(mContext, mHandler);
                dialog.show();
                break;
            case R.id.save:
                try {
                    situatModel.setAnaphylactogen(mAllergen.getText().toString());
                    situatModel.setSpecialSituation(mRemark.getText().toString());
                    mPresenter.saveInspectSpecial(situatModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    @Override
    public void callBack(ReceiveSpecialSituatModel situatModel) {
        this.situatModel = situatModel;
        mAllergen.setText(situatModel.getAnaphylactogen());
        mRemark.setText(situatModel.getSpecialSituation());
        contagion = situatModel.getContagion();
        adapter = new SpecialSituationAdapter(mContext, R.layout.special_list_item, contagion);
        mListView.setAdapter(adapter);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                ReceiveSpecialSituatModel.ContagionBean bean = (ReceiveSpecialSituatModel.ContagionBean) msg.obj;
                bean.setId(0);
                contagion.add(bean);
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
