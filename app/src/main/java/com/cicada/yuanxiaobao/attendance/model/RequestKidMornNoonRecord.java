package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/25.
 */
public class RequestKidMornNoonRecord {
    private long childId;
    private long classId;
    private long inspectTime;

    public long getChildId() {
        return childId;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(long inspectTime) {
        this.inspectTime = inspectTime;
    }
}
