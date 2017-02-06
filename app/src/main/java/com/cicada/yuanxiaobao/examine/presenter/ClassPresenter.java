package com.cicada.yuanxiaobao.examine.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.action.IClassAction;
import com.cicada.yuanxiaobao.examine.action.ISingleMultiSelect;
import com.cicada.yuanxiaobao.examine.adapter.ClassAdapter;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveClassModel;
import com.cicada.yuanxiaobao.examine.model.RequestClassModel;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/19.
 */
public class ClassPresenter extends BasePresenter<IClassAction> implements AdapterView.OnItemClickListener {

    private int itemheight, cols, position;
    private boolean isMultiSelect = false;
    private boolean isTableTop=false;//表头是否可点击
    private List<ClassModel> data;
    private ClassAdapter adapter;
    private List<ClassModel> mList=new ArrayList<>();
    private ClassModel mClassModel;

    public ClassPresenter(IClassAction view, Context mContext, int itemheight) {
        super(view, mContext);
        this.itemheight = itemheight;
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYGRADEANDCLASS_URL:
                List<ReceiveClassModel> classModelList = gson.fromJson(objectString, new TypeToken<List<ReceiveClassModel>>() {
                }.getType());
                cols = classModelList.size();
                view.setNumColumns(cols);
                data = pretreatmentData(classModelList);
                adapter = new ClassAdapter(mContext, R.layout.class_gridview_item, data, cols, itemheight);
                adapter.setMultiSelect(isMultiSelect);
                adapter.setTableTop(isTableTop);
                view.setAdapter(adapter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryGradeAndClass() {
        String str=  AppSelectSP.getString("Login");
        ReceiveLoginModel loginModel=  BasePresenter.gson.fromJson(str, ReceiveLoginModel.class);
        RequestClassModel model = new RequestClassModel();
        model.setSchoolId(loginModel.getSchoolInfo().getSchoolId());
        ApiDataFactory.serviceAPI(YXBService.QUERYGRADEANDCLASS_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

    private int getMax(List<Integer> list) {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            max = Math.max(max, list.get(i).intValue());
        }
        return max;
    }

    /**
     * 数据预处理
     *
     * @param classModelList
     * @return
     */
    private List<ClassModel> pretreatmentData(List<ReceiveClassModel> classModelList) {
        List<ClassModel> listC = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < classModelList.size(); i++) {
            ReceiveClassModel model = classModelList.get(i);
            int size = model.getClassList().size() + 1;
            list.add(size);
        }
        int maxClass = getMax(list);
        int gradeSise = classModelList.size();
        for (int i = 0; i < maxClass * gradeSise; i++) {
            ClassModel model = null;
            if (i < gradeSise) {
                model = new ClassModel();
                model.setClassId(classModelList.get(i).getGradeId());
                model.setClassName(classModelList.get(i).getGradeName());
            } else {
                int index = i % gradeSise;
                List<ClassModel> classList = classModelList.get(index).getClassList();
                if (classList.size() > 0) model = classList.remove(0);
            }
            listC.add(model);
        }
        return listC;
    }

    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }

    public void setTableTop(boolean tableTop) {isTableTop = tableTop;}

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ClassModel model = data.get(i);
        if(model==null) return;
        if (isMultiSelect) {
            if (model.isChoose()) {
                model.setChoose(false);
                mList.remove(model);
            } else {
                model.setChoose(true);
                mList.add(model);
            }
        } else {
            if (i < cols&&!isTableTop) return;
            if(i < cols){
                StringBuilder sb=new StringBuilder();
                for (int j = i+cols; j <data.size() ; j+=cols) {
                    ClassModel cModel=  data.get(j);
                    if(cModel==null) break;
                    sb.append(cModel.getClassId()+",");
                }
                int index = sb.lastIndexOf(",");
                if(index!=-1){
                    sb.deleteCharAt(index);
                    model.setClassIds(sb.toString());
                }
            }
            data.get(position).setChoose(false);
            model.setChoose(true);
            position = i;
            mClassModel=model;
            ((ISingleMultiSelect)((Fragment)this.view).getParentFragment()).singleSelection(model);
        }
        adapter.notifyDataSetChanged();
    }

    public List<ClassModel> getList() {
        return mList;
    }

    public ClassModel getClassModel() {
        return mClassModel;
    }
}
