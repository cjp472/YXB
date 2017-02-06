package com.cicada.yuanxiaobao.examine.view;

import android.widget.GridView;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.action.IClassAction;
import com.cicada.yuanxiaobao.examine.presenter.ClassPresenter;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by tanglei on 16/6/24.
 * 班级fragment
 */
public class ClassFragment extends BaseFragment implements IClassAction {

    @Bind(R.id.gridview)
    GridView mGridview;

    private boolean isMultiSelect = false;//是否多选
    private boolean isTableTop=false;//表头是否可点击
    private ClassPresenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.class_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mPresenter};
    }

    @Override
    protected void onInitPresenters() {
        int h= ((MornAndNoonExamineFragment)getParentFragment().getParentFragment()).itemheight;
        mPresenter=new ClassPresenter(this,mContext,h);
        mPresenter.setMultiSelect(isMultiSelect);
        mPresenter.setTableTop(isTableTop);
    }

    @Override
    public void initView() {
        mGridview.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mGridview.setOnItemClickListener(mPresenter);
        mPresenter.queryGradeAndClass();
    }




    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    public void setTableTop(boolean tableTop) {
        isTableTop = tableTop;
    }

    @Override
    public void setAdapter(QuickAdapter adapter) {
        mGridview.setAdapter(adapter);
    }

    @Override
    public void setNumColumns( int columns) {
        mGridview.setNumColumns(columns);
        Observable.just(columns).subscribe((Action1<Integer>) getParentFragment());
    }
}
