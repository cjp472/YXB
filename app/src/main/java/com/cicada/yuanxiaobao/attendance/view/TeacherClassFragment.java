package com.cicada.yuanxiaobao.attendance.view;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IChooseClass;
import com.cicada.yuanxiaobao.attendance.action.ITeacherClassesAction;
import com.cicada.yuanxiaobao.attendance.adapter.SpinnerAdapter;
import com.cicada.yuanxiaobao.attendance.presenter.TeacherClassesPresenter;
import com.cicada.yuanxiaobao.common.BaseFragment;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.font.MyFontTextView;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by tanglei on 16/7/26.
 */
public class TeacherClassFragment extends BaseFragment implements ITeacherClassesAction, AdapterView.OnItemClickListener {

    @Bind(R.id.choose_class_tv)
    MyFontTextView mChooseClassTv;
    @Bind(R.id.choose_class_layout)
    LinearLayout mChooseClassLayout;

    private TeacherClassesPresenter mClassesPresenter;
    private List<ClassModel> list;
    private List<String> listString;
    private ClassModel mClassModel;
    private PopupWindow popupWindow;
    private SpinnerAdapter mSpinnerAdapter;
    private IChooseClass mIChooseClass;



    @Override
    protected int getLayoutResId() {
        return R.layout.teacher_class_fragment;
    }

    @Override
    protected BasePresenter[] getPresenters() {
        return new BasePresenter[]{mClassesPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mClassesPresenter = new TeacherClassesPresenter(this, mContext);
    }

    @Override
    public void initView() {
        listString=new ArrayList<>();
        mSpinnerAdapter=new  SpinnerAdapter(mContext, R.layout.spinner_item, listString);
        popupWindow=new PopupWindow(mContext);
        mClassesPresenter.getTeacherClasses();
    }

    @Override
    public void setClass(List<ClassModel> list) {
        this.list = list;
        if (list.size() > 0) {
            mClassModel = list.get(0);
            mIChooseClass.callBack(mClassModel);
            mChooseClassTv.setText(mClassModel.getClassName());
            for (int i = 0; i < list.size(); i++) {
                listString.add(list.get(i).getClassName());
            }
        }
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

    @OnClick(R.id.choose_class_layout)
    public void onClick() {
        showPopupWindow(popupWindow,mChooseClassLayout,mSpinnerAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mClassModel= list.get(i);
        mChooseClassTv.setText(mClassModel.getClassName());
        popupWindow.dismiss();
        mIChooseClass.callBack(mClassModel);
    }

    public void setIChooseClass(IChooseClass IChooseClass) {
        mIChooseClass = IChooseClass;
    }
}
