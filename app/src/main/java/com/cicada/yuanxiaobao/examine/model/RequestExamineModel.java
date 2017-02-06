package com.cicada.yuanxiaobao.examine.model;

/**
 * Created by tanglei on 16/7/20.
 */
public class RequestExamineModel {

    /**
     * childId : 165700
     * classId : 10200
     * symptom : 发热,咳嗽,红眼
     * pictures : http://7qnd84.com2.z0.glb.qiniucdn.com/1418653561299Wh1cFoihBp.jpg,http://7qnd84.com2.z0.glb.qiniucdn.com/14205993512809r2hCr0B5I.jpg
     * medicalOrders : 需要观察
     * isEnterSchool : 1
     * inspectType : 1
     * comment : 备注信息
     */

    private long childId;
    private long classId;
    private long inspectTime;
    private String symptom;
    private String pictures;
    private String medicalOrders;
    private int isEnterSchool;
    private int inspectType;
    private String comment;

    public long getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(long inspectTime) {
        this.inspectTime = inspectTime;
    }

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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getMedicalOrders() {
        return medicalOrders;
    }

    public void setMedicalOrders(String medicalOrders) {
        this.medicalOrders = medicalOrders;
    }

    public int getIsEnterSchool() {
        return isEnterSchool;
    }

    public void setIsEnterSchool(int isEnterSchool) {
        this.isEnterSchool = isEnterSchool;
    }

    public int getInspectType() {
        return inspectType;
    }

    public void setInspectType(int inspectType) {
        this.inspectType = inspectType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
