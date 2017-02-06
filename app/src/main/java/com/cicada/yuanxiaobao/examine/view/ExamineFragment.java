package com.cicada.yuanxiaobao.examine.view;

import android.content.Intent;
import android.hardware.Camera;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.Constant;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.examine.action.IExamineAction;
import com.cicada.yuanxiaobao.examine.action.ISingleMultiSelect;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.presenter.ExaminePresenter;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.List;

import butterknife.Bind;
import rx.functions.Action1;

/**
 * Created by tanglei on 16/6/24.
 * 检查
 */
public class ExamineFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener,Action1<Integer>,IExamineAction,ISingleMultiSelect {

    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;
    @Bind(R.id.kid_gridview)
    GridView mKidGridview;

    private int mn = 1;
    private ClassFragment mClassFragment;
    private int columns;
    private ExaminePresenter mPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.examin_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mPresenter};
    }

    @Override
    protected void onInitPresenters() {
        int h= ((MornAndNoonExamineFragment)getParentFragment()).itemheight;
        mPresenter=new ExaminePresenter(this,mContext,h);
    }

    @Override
    public void initView() {
        mClassFragment= (ClassFragment) getChildFragmentManager().findFragmentById(R.id.class_fragment);
        mRadioGroup.setOnCheckedChangeListener(this);
        mKidGridview.setColumnWidth(DeviceUtils.getScreenWidth(mContext));
        mKidGridview.setOnItemClickListener(mPresenter);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.morn:
                mn = 1;
                break;
            case R.id.noon:
                mn = 2;
                break;
        }
        mPresenter.setInspectType(mn);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode== Constant.CAMERA_REQUEST_CODE) {
          mPresenter.getExamineDialog().addPath();
           close();
       }
    }

    private void close(){
        Camera camera = Camera.open();
        Camera.Parameters parameters=camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.release();
    }

    @Override
    public void call(Integer i) {
        columns=11-i;
        mKidGridview.setNumColumns(columns);
        mKidGridview.setVisibility(View.VISIBLE);
        mPresenter.setColumns(columns);
        ((LinearLayout.LayoutParams)mClassFragment.getView().getLayoutParams()).weight=columns;
        ((LinearLayout.LayoutParams)mKidGridview.getLayoutParams()).weight=i;
    }

    @Override
    public void setAdapter(QuickAdapter adapter) {
        mKidGridview.setAdapter(adapter);
    }


    @Override
    public void singleSelection(ClassModel model) {
        long classId=model.getClassId();
        mPresenter.queryChildByClassId(classId);
        mPresenter.setClassModel(model);
    }

    @Override
    public void multipleSelection(List<ClassModel> list) {}
}
