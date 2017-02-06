package com.cicada.yuanxiaobao.examine.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.common.ShowPictureActivity;
import com.cicada.yuanxiaobao.examine.action.IExamineDialogAction;
import com.cicada.yuanxiaobao.examine.adapter.PhotoAdapter;
import com.cicada.yuanxiaobao.examine.adapter.SymptomAdapter;
import com.cicada.yuanxiaobao.examine.model.PhotoPathModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveSymptomModel;
import com.cicada.yuanxiaobao.examine.model.RequestExamineModel;
import com.cicada.yuanxiaobao.utils.AppTools;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/21.
 */
public class ExamineDialogPresenter extends BasePresenter<IExamineDialogAction> implements AdapterView.OnItemClickListener {

    private SymptomAdapter mSymptomAdapter;
    private PhotoAdapter mPhotoAdapter;
    private List<ReceiveSymptomModel> symptomData;
    private List<ReceiveSymptomModel> symptomTmpData = new ArrayList<>();
    private List<PhotoPathModel> photoData;
    private ArrayList<String> listPath;
    private Fragment fragment;
    private String path;
    private int i;


    public ExamineDialogPresenter(IExamineDialogAction view, Context mContext, Fragment fragment) {
        super(view, mContext);
        this.fragment = fragment;
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.ADDINSPECTINFO_URL:
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
        mSymptomAdapter = new SymptomAdapter(mContext, R.layout.examin_dialog_leftgridview_item, symptomData, 3);
        view.leftGridViewAdapter(mSymptomAdapter);
    }

    public void initRightData() {
        listPath = new ArrayList<>();
        photoData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            PhotoPathModel model = new PhotoPathModel();
            photoData.add(model);
            listPath.add(null);
        }
        mPhotoAdapter = new PhotoAdapter(mContext, R.layout.examin_dialog_rightgridview_item, photoData);
        view.rightGridViewAdapter(mPhotoAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.left_grid:
                ReceiveSymptomModel symptomModel = symptomData.get(i);
                if (symptomModel.isChoose()) {
                    symptomModel.setChoose(false);
                    symptomTmpData.remove(symptomModel);
                } else {
                    symptomModel.setChoose(true);
                    symptomTmpData.add(symptomModel);
                }
                mSymptomAdapter.notifyDataSetChanged();
                break;
            case R.id.right_grid:
                if (TextUtils.isEmpty(photoData.get(i).getPathString())) {
                    this.i = i;
                    path = AppTools.getSystemCamera(fragment, fragment.getActivity());
                } else {
                    ArrayList<String> paths = (ArrayList<String>) listPath.clone();
                    removeNull(paths);
                    Intent intent = new Intent(mContext, ShowPictureActivity.class);
                    intent.putStringArrayListExtra("listPath", paths);
                    String str = photoData.get(i).getPathString();
                    int index = paths.indexOf(str);
                    intent.putExtra("index", index);
                    mContext.startActivity(intent);
                }
                break;
        }
    }

    private void removeNull(List<String> listPath) {
        if (listPath.contains(null)) {
            listPath.remove(null);
            removeNull(listPath);
        }
    }

    public void addPath() {
        File file = new File(path);
        if (file.exists()) {
            listPath.remove(i);
            listPath.add(i, path);
            photoData.get(i).setPathString(path);
            mPhotoAdapter.notifyDataSetChanged();
            AppTools.compressImage(path, 960, 720);
        }

    }



    public List<String> getListPath() {
        List<String> paths = (ArrayList<String>) listPath.clone();
        removeNull(paths);
        return paths;
    }

    public List<ReceiveSymptomModel> getSymptomTmpData() {
        return symptomTmpData;
    }

    public void addInspectInfo(RequestExamineModel model) {
            ApiDataFactory.serviceAPI(YXBService.ADDINSPECTINFO_URL, RequestDataUtils.getRequestModel(model, false), this, true);
    }

    public boolean verify(RequestExamineModel model) {
        model.getSymptom();
        if (TextUtils.isEmpty(model.getSymptom())) {
            ToastUtil.showShort("请选择症状");
            return false;
        }
        if (TextUtils.isEmpty(model.getMedicalOrders())) {
            ToastUtil.showShort("请选择嘱托");
            return false;
        }
        return true;
    }
}
