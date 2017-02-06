package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.IRecordTrackAction;
import com.cicada.yuanxiaobao.attendance.model.RequestSickLeaveInfoModel;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.QuickAdapter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.adapter.SymptomAdapter;
import com.cicada.yuanxiaobao.examine.model.ReceiveSymptomModel;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.util.ArrayList;

/**
 * Created by tanglei on 16/7/25.
 */
public class RecordTrackPresenter extends BasePresenter<IRecordTrackAction> implements AdapterView.OnItemClickListener {
    ArrayList<ReceiveSymptomModel> symptomData;
    ArrayList<String> tmp = new ArrayList<>();
    SymptomAdapter mSymptomAdapter;

    public RecordTrackPresenter(IRecordTrackAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.UPDATESICKLEAVEINFO_URL:
                view.callBack();
                break;
            default:
                break;
        }


    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void initLeftData() {
        symptomData = new ArrayList<>();
        String[] str = mContext.getResources().getStringArray(R.array.symptom_array);
        for (int i = 0; i < str.length; i++) {
            ReceiveSymptomModel model = new ReceiveSymptomModel();
            model.setName(str[i]);
            symptomData.add(model);
        }
        mSymptomAdapter = new SymptomAdapter(mContext, R.layout.examin_dialog_leftgridview_item, symptomData, 6);
        view.setSymptomsAdapter(mSymptomAdapter);
    }

    public void updateSickLeaveInfo(RequestSickLeaveInfoModel model) {
       if(verify(model)) ApiDataFactory.serviceAPI(YXBService.UPDATESICKLEAVEINFO_URL, RequestDataUtils.getRequestModel(model, false), this, true);
    }

    private boolean verify(RequestSickLeaveInfoModel model){
        if(TextUtils.isEmpty(model.getSymptoms())){
            ToastUtil.showShort("请选择主要症状");
            return false;
        }
        if(TextUtils.isEmpty(model.getHospital())){
            ToastUtil.showShort("请填写就医医院");
            return false;
        }
        if(model.getIsInfection()==-1){
            ToastUtil.showShort("请选择是否是传染病");
            return false;
        }
        if(model.getTreatmentResult()==0){
            ToastUtil.showShort("请选择居家/住院");
            return false;
        }
        if(model.getState()==0){
            ToastUtil.showShort("请选目前状况");
            return false;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ReceiveSymptomModel symptomModel = symptomData.get(i);
        if (symptomModel.isChoose()) {
            symptomModel.setChoose(false);
            tmp.remove(symptomModel.getName());
        } else {
            symptomModel.setChoose(true);
            tmp.add(symptomModel.getName());
        }
        mSymptomAdapter.notifyDataSetChanged();
    }

    public void showPopupWindow(PopupWindow popupWindow, View view, QuickAdapter adapter) {
        int w = view.getWidth();
        ListView listView = (ListView) LayoutInflater.from(mContext).inflate(R.layout.parents_popup_window1, null);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this.view);
        popupWindow.setContentView(listView);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(w);
        if (adapter.getData().size() > 5) {
            popupWindow.setHeight(DeviceUtils.dip2px(mContext, 200));
        } else popupWindow.setHeight(DeviceUtils.dip2px(mContext, 40 * adapter.getData().size()));
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        Drawable dw = ContextCompat.getDrawable(mContext, R.drawable.list_bg);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(view, 0, -1);
    }

    public ArrayList<String> getTmp() {
        return tmp;
    }


}
