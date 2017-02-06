package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ICheckAction;
import com.cicada.yuanxiaobao.attendance.adapter.KidAbsenteeismAdapter;
import com.cicada.yuanxiaobao.attendance.adapter.KidAttendanceAdapter;
import com.cicada.yuanxiaobao.attendance.model.ReceiveStudentAttendanceModel;
import com.cicada.yuanxiaobao.attendance.model.RequestStudentAttendanceModel;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.examine.adapter.ClassKidAdapter;
import com.cicada.yuanxiaobao.examine.model.KidModel;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.utils.DeviceUtils;
import com.cicada.yuanxiaobao.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/4.
 */
public class CheckPresenter extends BasePresenter<ICheckAction> implements AdapterView.OnItemClickListener {

    private int itemheight;
    //待考勤
    private List<KidModel> needCheckData = new ArrayList<>();
    //出勤
    private List<KidModel> attendanceData = new ArrayList<>();
    //缺勤
    private List<KidModel> absenteeismData = new ArrayList<>();
    //零时缓存
    private List<KidModel> tmpData = new ArrayList<>();

    private ClassKidAdapter needAdapter;
    private KidAttendanceAdapter mAttendanceAdapter;
    private KidAbsenteeismAdapter mAbsenteeismAdapter;
    private ReceiveStudentAttendanceModel studentAttendanceModel;
    private int absenteeism, casual, sick;
    private int cols = 11;//列数

    public CheckPresenter(ICheckAction view, Context mContext) {
        super(view, mContext);
        //表格 item 为正方形  1排最多11列
        itemheight = (DeviceUtils.getScreenWidth(mContext) - DeviceUtils.dip2Px(mContext, 150)) / cols;
        int dv = (DeviceUtils.getScreenWidth(mContext) - DeviceUtils.dip2Px(mContext, 150)) - itemheight * cols;
        view.updateLayoutPadding(dv);
    }

    @Override
    public void onSuccess(String url, String objectString) {

        switch (url) {
            case YXBService.STUDENTATTENDANCE_URL:
                studentAttendanceModel = gson.fromJson(objectString, ReceiveStudentAttendanceModel.class);
                initData(studentAttendanceModel);
                break;
            case YXBService.ADDATTENDANCE_URL:
                ToastUtil.showShort("提交成功!");
                view.setButtonBg(false);
                break;
        }
    }

    @Override
    public void onFail(String url, String rtnCodel) {

    }

    private void initData(ReceiveStudentAttendanceModel studentAttendanceModel) {
        needCheckData = studentAttendanceModel.getToAttendanceList();
        attendanceData = studentAttendanceModel.getNormalList();
        absenteeismData = studentAttendanceModel.getAbsenceList();


        if (needCheckData != null) {
            if(needCheckData.size()>0) view.setButtonBg(true);
            if(needCheckData.size()==0) view.setButtonBg(false);
            dataWrapper(needCheckData);
            needAdapter = new ClassKidAdapter(mContext, R.layout.morn_noon_gridview_item, needCheckData, cols, itemheight);
            view.setNeedCheckGridviewAdapter(needAdapter);
            view.updateNeedCheck(nonNullItemSize(needCheckData));
        } else view.updateNeedCheck(0);

        if (attendanceData != null) {
            dataWrapper(attendanceData);
            mAttendanceAdapter = new KidAttendanceAdapter(mContext, R.layout.morn_noon_gridview_item, attendanceData, cols, itemheight);
            view.setAttendanceGridviewAdapter(mAttendanceAdapter);
            view.updateAttendance(nonNullItemSize(attendanceData));
        } else view.updateAttendance(0);

        if (absenteeismData != null) {
            dataWrapper(absenteeismData);
            mAbsenteeismAdapter = new KidAbsenteeismAdapter(mContext, R.layout.morn_noon_gridview_item, absenteeismData, cols, itemheight);
            view.setAbsenteeismGridviewAdapter(mAbsenteeismAdapter);
            count();
            view.updateAbsenteeism(nonNullItemSize(absenteeismData), absenteeism, casual, sick);
        } else view.updateAbsenteeism(0, 0, 0, 0);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        KidModel classKidModel = needCheckData.get(i);
        if (classKidModel == null) return;
        if (classKidModel.isChoose()) {
            classKidModel.setChoose(false);
            tmpData.remove(classKidModel);
        } else {
            classKidModel.setChoose(true);
            tmpData.add(classKidModel);
        }
        needAdapter.notifyDataSetChanged();
    }

    /**
     * 出勤 事假 缺勤 病假 提示语
     * @return
     */
    private boolean prompt(){
        if (needCheckData != null && needCheckData.size() > 0){
            if(tmpData.size()==0){
                ToastUtil.showShort("请选择幼儿");
                return true;
            }
        }else if(needCheckData != null && needCheckData.size() == 0){
            if(tmpData.size()==0){
                ToastUtil.showShort("无待考勤人");
                return true;
            }
        }
        return false;
    }

    /**
     * 缺勤
     *
     * @param type 2:缺勤 5:事假  6:病假
     */
    public void absenteeism(int type) {
        try {
            if(prompt()) return;
            for (KidModel model : tmpData) {
                model.setAttendanceStatus(type);
            }
            absenteeismData.addAll(nonNullItemSize(absenteeismData), tmpData);
            dataWrapper(absenteeismData);
            mAbsenteeismAdapter.notifyDataSetChanged();
            removeNeedCheck();
            count();
            view.updateAbsenteeism(nonNullItemSize(absenteeismData), absenteeism, casual, sick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeNeedCheck() {
        needCheckData.removeAll(tmpData);
        dataWrapper(needCheckData);
        view.updateNeedCheck(nonNullItemSize(needCheckData));
        needAdapter.notifyDataSetChanged();
        tmpData.clear();
    }

    /**
     * 出勤
     */
    public void attendance() {
        try {
            if(prompt()) return;
            attendanceData.addAll(nonNullItemSize(attendanceData), tmpData);
            dataWrapper(attendanceData);
            mAttendanceAdapter.notifyDataSetChanged();
            removeNeedCheck();
            view.updateAttendance(nonNullItemSize(attendanceData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算缺勤 缺勤,事假,病假 人数
     */
    private void count() {
        absenteeism = 0;
        casual = 0;
        sick = 0;
        if (absenteeismData.size() > 0) {
            for (KidModel model : absenteeismData) {
                if (model == null) break;
                switch (model.getAttendanceStatus()) {
                    case 2:
                        absenteeism++;
                        break;
                    case 5:
                        casual++;
                        break;
                    case 6:
                        sick++;
                        break;
                }
            }
        }
    }

    /**
     * 封装数据  使数据为clos倍数
     *
     * @param data
     * @return
     */
    private List<KidModel> dataWrapper(List<KidModel> data) {
        int s = nonNullItemSize(data);
        int row = s % cols == 0 ? s / cols : s / cols + 1;
        int size = row * cols;
        int size1 = data.size();
        if (size > size1) {
            for (int i = 0; i < size - size1; i++) {
                data.add(null);
            }
        } else {
            for (int i = 0; i < size1 - size; i++) {
                data.remove(data.size() - 1);
            }
        }
        return data;
    }

    /**
     * data 中非空的item 数量
     *
     * @param data
     * @return
     */
    private int nonNullItemSize(List<KidModel> data) {
        int s = 0;
        int size = data.size();
        for (int i = 0; i < size; i++) {
            if (data.get(i) != null) s++;
        }
        return s;
    }

    public void studentAttendance(long classId,long attendanceTime) {
        String str= AppSelectSP.getString("Login");
        ReceiveLoginModel loginModel = gson.fromJson(str,ReceiveLoginModel.class);
        RequestStudentAttendanceModel model = new RequestStudentAttendanceModel();
        model.setClassId(classId);
        model.setSchoolId(loginModel.getSchoolInfo().getSchoolId());
        model.setAttendanceTime(attendanceTime);
        ApiDataFactory.serviceAPI(YXBService.STUDENTATTENDANCE_URL, RequestDataUtils.getRequestModel(model, true), this, true);
    }

    public void commit(long attendanceDate) {
        try {
            if (needCheckData != null && needCheckData.size() == 0) {
                String str= AppSelectSP.getString("Login");
                ReceiveLoginModel loginModel = gson.fromJson(str,ReceiveLoginModel.class);
                studentAttendanceModel.setAttendanceDate(attendanceDate);
                studentAttendanceModel.setSchoolId(loginModel.getSchoolInfo().getSchoolId());
                removeNull(   studentAttendanceModel.getAbsenceList());
                removeNull(   studentAttendanceModel.getNormalList());
                ApiDataFactory.serviceAPI(YXBService.ADDATTENDANCE_URL, RequestDataUtils.getRequestModel(studentAttendanceModel,false),this,true);
            } else {
                ToastUtil.showShort("还有待考勤人");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeNull(List<KidModel> list){
        List<KidModel> listmodel=new ArrayList<>();
        for (KidModel model:list) {
            if(model!=null){
                listmodel.add(model);
            }
        }
        list.clear();
        list.addAll(listmodel);
    }
}
