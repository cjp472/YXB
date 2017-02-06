package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/8.
 */
public class SickItemModel {
    private String attach;
    private long childId;
    private String childName;
    private String childSex;
    private long endTime;
    private String hospital;
    private int id;
    private int isInfection;
    private long leaveTime;
    private int medicalResult;
    private String resultInfo;
    private int state;
    private String symptoms;
    private int treatmentResult;

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
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

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsInfection() {
        return isInfection;
    }

    public void setIsInfection(int isInfection) {
        this.isInfection = isInfection;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getMedicalResult() {
        return medicalResult;
    }

    public void setMedicalResult(int medicalResult) {
        this.medicalResult = medicalResult;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public int getTreatmentResult() {
        return treatmentResult;
    }

    public void setTreatmentResult(int treatmentResult) {
        this.treatmentResult = treatmentResult;
    }
}
