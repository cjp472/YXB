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
import com.cicada.yuanxiaobao.examine.action.IExamineAction;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidAdapter;
import com.cicada.yuanxiaobao.examine.model.ClassModel;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.examine.model.ReceiveKidModel;
import com.cicada.yuanxiaobao.examine.model.RequestKidModel;
import com.cicada.yuanxiaobao.examine.view.ExamineDialog;
import com.cicada.yuanxiaobao.utils.AppTools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanglei on 16/7/19.
 */
public class ExaminePresenter extends BasePresenter<IExamineAction> implements AdapterView.OnItemClickListener {
    private int itemheight;
    private List<KidModel> data = new ArrayList<>();
    private Map<String, ExamineDialog> mDialogMap = new HashMap<>();
    private ClassKidAdapter ckadpter;
    private ExamineDialog mExamineDialog;
    private int position;
    private int columns;
    private int inspectType=1;
    private ClassModel mClassModel;


    public ExaminePresenter(IExamineAction view, Context mContext, int itemheight) {
        super(view, mContext);
        this.itemheight = itemheight;
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.QUERYCHILDBYCLASSID_URL:
                ReceiveKidModel model = gson.fromJson(objectString, ReceiveKidModel.class);
                initData(model);
                ckadpter = new ClassKidAdapter(mContext, R.layout.morn_noon_gridview_item, data, columns, itemheight);
                view.setAdapter(ckadpter);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    public void queryChildByClassId(long classId) {
        data.clear();
        position=0;
        RequestKidModel model = new RequestKidModel();
        model.setClassId(classId);
        ApiDataFactory.serviceAPI(YXBService.QUERYCHILDBYCLASSID_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

    private List<KidModel> sort(List<KidModel> list) {
        Map<String, KidModel> map = new HashMap<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            KidModel model = list.get(i);
            String name = model.getChildName();
            if (name == null) {
                continue;
            }
            //把汉字转串化拼音串
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < name.length() && j < 3; j++) {
                char c = name.charAt(j);
                String[] vals;
                try {
                    if (AppTools.isChinese(c)) {
                        vals = PinyinHelper.toHanyuPinyinStringArray(c, format);
                        String str = Arrays.toString(vals);
                        str = str.replace("[", "");
                        str = str.replace("]", "");
                        sb.append(str);
                    } else {
                             /*是否为英文*/
                        if (Character.isLetter(c)) c = Character.toUpperCase(c);
                        sb.append(c);
                    }

                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
            String key = sb.toString();
            if (map.containsKey(key)) {
                map.put(key + i, model);
            } else map.put(key, model);
        }
        size = map.size();
        String keyString[] = new String[size];
        map.keySet().toArray(keyString);
        //排序
        Arrays.sort(keyString);
        List<KidModel> data = new ArrayList<>();
        for (int i = 0; i < keyString.length; i++) {
            data.add(map.get(keyString[i]));
        }
        return data;
    }

    private void initData(ReceiveKidModel model) {
        data.addAll(sort(model.getBoyList()));
        data.addAll(sort(model.getGirlList()));
        int size = data.size();
        int sc = size % columns;
        int s = 0;
        if (sc != 0) {
            s = columns - sc;
        }
        if (s != 0) {
            for (int i = 0; i < s; i++) {
                data.add(null);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        KidModel kidModel = data.get(i);
        if(kidModel==null) return;
        data.get(position).setChoose(false);
        kidModel.setChoose(true);
        position = i;
        ckadpter.notifyDataSetChanged();
        long childId=kidModel.getChildId();
        if (mDialogMap.get(childId+"+"+inspectType) == null) {
            mExamineDialog = new ExamineDialog(mContext, (Fragment) this.view);
            mExamineDialog.set(mClassModel,kidModel);
            mExamineDialog.setInspectType(inspectType);
            mExamineDialog.show();
            mDialogMap.put(childId+"+"+inspectType, mExamineDialog);
        } else {
            mExamineDialog= mDialogMap.get(childId+"+"+inspectType);
            mExamineDialog.setInspectType(inspectType);
            mExamineDialog.show();
        }

    }

    public ExamineDialog getExamineDialog() {
        return mExamineDialog;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setInspectType(int inspectType) {
        this.inspectType = inspectType;
    }



    public void setClassModel(ClassModel classModel) {
        mClassModel = classModel;
    }


}

