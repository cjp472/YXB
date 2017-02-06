package com.cicada.yuanxiaobao.attendance.action;

import com.cicada.yuanxiaobao.common.QuickAdapter;

/**
 * Created by tanglei on 16/7/23.
 */
public interface ISickTrackAction {

    public void setAdapter(QuickAdapter adapter);

    public void noMoreData();

    public void refreshData();

    public void setRecords(int records);

    public void setState();
}
