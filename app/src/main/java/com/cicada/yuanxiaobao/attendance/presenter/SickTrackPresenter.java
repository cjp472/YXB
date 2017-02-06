package com.cicada.yuanxiaobao.attendance.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.cicada.yuanxiaobao.API.YXBService;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.attendance.action.ISickTrackAction;
import com.cicada.yuanxiaobao.attendance.adapter.SickListAdapter;
import com.cicada.yuanxiaobao.attendance.model.ReceiveStickTrackModel;
import com.cicada.yuanxiaobao.attendance.model.RequestSickTrackModel;
import com.cicada.yuanxiaobao.attendance.model.SickItemModel;
import com.cicada.yuanxiaobao.attendance.view.RecordTrackDialog;
import com.cicada.yuanxiaobao.common.ApiDataFactory;
import com.cicada.yuanxiaobao.common.BasePresenter;
import com.cicada.yuanxiaobao.common.RequestDataUtils;
import com.cicada.yuanxiaobao.home.model.ReceiveLoginModel;
import com.cicada.yuanxiaobao.utils.AppSelectSP;
import com.cicada.yuanxiaobao.widget.MyLoadListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanglei on 16/7/23.
 */
public class SickTrackPresenter extends BasePresenter<ISickTrackAction> implements MyLoadListView.OnLoadListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private SickListAdapter adapter;
    private RequestSickTrackModel mSickTrackModel = new RequestSickTrackModel();
    private List<SickItemModel> data = new ArrayList<>();
    private boolean isLoad;
    private int pageIndex = 1;
    private int pageSize = 20;
    private int state;
    private long classId;

    public SickTrackPresenter(ISickTrackAction view, Context mContext) {
        super(view, mContext);
    }

    @Override
    public void onSuccess(String url, String objectString) {
        switch (url) {
            case YXBService.SICKLEAVELIST_URL:
                ReceiveStickTrackModel receiveStickTrackModel = gson.fromJson(objectString, ReceiveStickTrackModel.class);
                List<SickItemModel> list = receiveStickTrackModel.getRows();
                if (!isLoad) {
                    data.clear();
                    view.refreshData();
                }
                if (list.size() < pageSize && isLoad) {
                    view.noMoreData();
                }
                data.addAll(list);
                adapter.notifyDataSetChanged();
                view.setRecords(receiveStickTrackModel.getRecords());
                break;
            default:
                break;
        }

    }

    @Override
    public void onFail(String url, String rtnCodel) {
        if (!isLoad) view.refreshData();
    }

    @Override
    public void onLoad() {
        isLoad = true;
        pageIndex++;
        sickLeaveList(pageIndex);
    }

    @Override
    public void onRefresh() {
        pageIndex = 1;
        isLoad = false;
        sickLeaveList(pageIndex);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            SickItemModel sickItemModel = data.get(i);
            RecordTrackDialog dialog = new RecordTrackDialog(mContext, mHandler);
            dialog.setSickItemModel(sickItemModel);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            state = 0;
            onRefresh();
            view.setState();
        }
    };

    public void setState(int state) {
        this.state = state;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public void init() {
        adapter = new SickListAdapter(mContext, R.layout.sick_list_item, data);
        view.setAdapter(adapter);
    }

    public void sickLeaveList(int pageIndex) {
        ReceiveLoginModel loginModel = BasePresenter.gson.fromJson(AppSelectSP.getString("Login"), ReceiveLoginModel.class);
        mSickTrackModel.setPageSize(pageSize);
        mSickTrackModel.setPageIndex(pageIndex);
        mSickTrackModel.setState(state);
        mSickTrackModel.setClassId(classId);
        mSickTrackModel.setSchoolId(loginModel.getSchoolInfo().getSchoolId());
        ApiDataFactory.serviceAPI(YXBService.SICKLEAVELIST_URL, RequestDataUtils.getRequestModel(mSickTrackModel, true), this, true);
    }


}
