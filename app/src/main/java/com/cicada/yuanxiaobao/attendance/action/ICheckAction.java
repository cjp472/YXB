package com.cicada.yuanxiaobao.attendance.action;

import com.cicada.yuanxiaobao.common.QuickAdapter;

/**
 * Created by tanglei on 16/7/4.
 */
public interface ICheckAction {

    public void updateLayoutPadding(int dv);

    /**
     * 设置待考勤 适配器
     * @param adapter
     */
    public void setNeedCheckGridviewAdapter(QuickAdapter adapter);

    /**
     * 设置出勤 适配器
     * @param adapter
     */
    public void setAttendanceGridviewAdapter(QuickAdapter adapter);

    /**
     * 设置缺勤 适配器
     * @param adapter
     */
    public void setAbsenteeismGridviewAdapter(QuickAdapter adapter);

    /**
     * 更新待考勤人数
     * @param size
     */
    public void updateNeedCheck(int size);

    /**
     * 更新出勤人数
     * @param size
     */
    public void updateAttendance(int size);

    /**
     * 更新 缺勤 病假 事假 人数
     * @param size   合计缺勤
     * @param absenteeism 缺勤
     * @param casual 事假
     * @param sick   病假
     */
    public void updateAbsenteeism(int size,int absenteeism,int casual, int sick );


    public void setButtonBg(boolean isEnable);
}
