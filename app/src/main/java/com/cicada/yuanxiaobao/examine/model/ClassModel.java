package com.cicada.yuanxiaobao.examine.model;

/**
 * Created by tanglei on 16/7/19.
 */
public class ClassModel {

    /**
     * classId : 11200
     * className : 中一班
     * customName : 钟楼班
     */

    private long classId;
    private boolean isChoose;
    private String className;
    private String customName;
    private String classIds="";

    public String getClassIds() {
        return classIds;
    }

    public void setClassIds(String classIds) {
        this.classIds = classIds;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }
}
