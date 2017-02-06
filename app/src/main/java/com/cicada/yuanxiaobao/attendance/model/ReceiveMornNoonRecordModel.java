package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/25.
 */
public class ReceiveMornNoonRecordModel {
    private MornNoonExamineRecord morning;
    private MornNoonExamineRecord noon;


    public MornNoonExamineRecord getNoon() {
        return noon;
    }

    public void setNoon(MornNoonExamineRecord noon) {
        this.noon = noon;
    }

    public MornNoonExamineRecord getMorning() {
        return morning;
    }

    public void setMorning(MornNoonExamineRecord morning) {
        this.morning = morning;
    }


}
