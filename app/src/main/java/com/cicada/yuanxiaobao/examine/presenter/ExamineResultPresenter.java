package com.cicada.yuanxiaobao.examine.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.action.IExamineResultAction;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidAdapter;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidDiseaseAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveExaminResultModel;
import com.cicada.yuanxiaobao.examine.model.RequestExaminResultModel;
import com.cicada.yuanxiaobao.examine.view.ExamineResultFragment;
import com.cicada.yuanxiaobao.examine.view.KidArchiveDetailDialog;

import java.util.List;

/**
 * Created by tanglei on 16/7/21.
 */
public class ExamineResultPresenter extends BasePresenter<IExamineResultAction> implements AdapterView.OnItemClickListener {
    private int columns, itemheight;
    private List<KidModel> enter, out;

    public ExamineResultPresenter(IExamineResultAction view, Context mContext, int itemheight) {
        super(view, mContext);
        this.itemheight = itemheight;
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYCHILDBYCLASSIDS_URL:
                ReceiveExaminResultModel resultModel = gson.fromJson(objectString, ReceiveExaminResultModel.class);
                disposeData(resultModel);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void queryChildByClassIds(String classIds, long inspectTime, int inspectType) {
        enter = null;
        out = null;
        if(TextUtils.isEmpty(classIds)){
//            ToastUtil.showShort("还没有选择班级或年级!");
            return;
        }
        RequestExaminResultModel model = new RequestExaminResultModel();
        model.setClassIds(classIds);
        model.setInspectTime(inspectTime);
        model.setInspectType(inspectType);
        ApiDataFactory.serviceAPI(YXBService.QUERYCHILDBYCLASSIDS_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

    private void disposeData(ReceiveExaminResultModel resultModel) {
        enter = resultModel.getEnter();
        out = resultModel.getOut();
        if (enter == null | enter.size() == 0) view.setEnterLayout();
        if (out == null | out.size() == 0) view.setEnterLayout();

        ClassKidDiseaseAdapter diseaseAdapter = new ClassKidDiseaseAdapter(mContext, R.layout.morn_noon_gridview_item, enter, columns, itemheight);
        view.setEnterAdapter(diseaseAdapter);

        ClassKidAdapter ckadpter = new ClassKidAdapter(mContext, R.layout.morn_noon_gridview_item, out, columns, itemheight);
        view.setOutAdapter(ckadpter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            KidModel kidModel = null;
            switch (adapterView.getId()) {
                case R.id.enter_gridview:
                    kidModel = enter.get(i);
                    break;
                case R.id.out_gridview:
                    kidModel = out.get(i);
                    break;
            }
            KidArchiveDetailDialog dialog=new KidArchiveDetailDialog();
            dialog.show(((Fragment)this.view).getChildFragmentManager(),"kid");
            dialog.setKidModel(kidModel,((ExamineResultFragment)this.view).getInspectTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
