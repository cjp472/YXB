package com.cicada.yuanxiaobao.examine.model;

/**
 * Created by tanglei on 16/7/21.
 */
public class RequestExaminResultModel {

    /**
     * classIds : 11200,22100
     * inspectTime : 1468298063000
     */

    private String classIds;
    private long inspectTime;
    private  int inspectType;

    public int getInspectType() {
        return inspectType;
    }

    public void setInspectType(int inspectType) {
        this.inspectType = inspectType;
    }

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public long getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(long inspectTime) {
        this.inspectTime = inspectTime;
    }
}
