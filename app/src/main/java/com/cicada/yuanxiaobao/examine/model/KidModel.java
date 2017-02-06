package com.cicada.yuanxiaobao.examine.model;

/**
 * Created by tanglei on 16/7/19.
 */
public class KidModel implements Cloneable{

    /**
     * childId : 10201
     * childName : 小明
     * childSex : 男
     */

    private String medicalOrders;
    private long childId;
    private int childAge;
    private String childName;
    private String childSex;
    private String childIcon;
    private int attendanceStatus;//1:正常考勤、2:缺勤、5:事假、6:病假
    private boolean isChoose;

    public String getMedicalOrders() {
        return medicalOrders;
    }

    public void setMedicalOrders(String medicalOrders) {
        this.medicalOrders = medicalOrders;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }


    public int getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(int attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public long getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSex() {
        return childSex;
    }

    public void setChildSex(String childSex) {
        this.childSex = childSex;
    }

    public int getChildAge() {
        return childAge;
    }

    public void setChildAge(int childAge) {
        this.childAge = childAge;
    }

    public String getChildIcon() {
        return childIcon;
    }

    public void setChildIcon(String childIcon) {
        this.childIcon = childIcon;
    }

    public void setChildId(long childId) {
        this.childId = childId;
    }
}
