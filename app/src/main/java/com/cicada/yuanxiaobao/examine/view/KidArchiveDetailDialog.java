package com.cicada.yuanxiaobao.examine.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.view.KidArchiveDetailFragment;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.DeviceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tanglei on 16/7/22.
 */
public class KidArchiveDetailDialog extends AppCompatDialogFragment {


    @Bind(R.id.close)
    ImageView mClose;

    private KidModel kidModel;
    private long inspectTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kid_archive_detail_dialog_layout, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        AppTools.expandViewTouchDelegate(mClose,50,50,50,50);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
        lp.width = DeviceUtils.dip2px(getContext(),700);
        lp.height = DeviceUtils.dip2px(getContext(),600);
        getDialog().getWindow().setAttributes(lp);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        KidArchiveDetailFragment fragment = new KidArchiveDetailFragment();
        getChildFragmentManager().beginTransaction().add(R.id.content, fragment).commit();
        fragment.setKidModel(kidModel,true,inspectTime);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.close)
    public void onClick() {
        dismiss();
    }

    public void setKidModel(KidModel kidModel,long inspectTime) {
        this.kidModel = kidModel;
        this.inspectTime=inspectTime;
    }
}
