package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/25.
 */
public class MornNoonExamineRecord {

    /**
     * childId : 165700
     * childName : 大明
     * classId : 10200
     * className : test
     * comment : 备注信息
     * createDate : 1468998119349
     * createDateAsDate : 1468998119349
     * creator : 492800
     * customName : test
     * id : 8
     * inspectTime : 1468998119306
     * inspectType : 2
     * isEnterSchool : 1
     * lastModDate : 1468998119048
     * lastModDateAsDate : 1468998119048
     * lastModifier : 492800
     * medicalOrders : 需要观察
     * pictures : http://7qnd84.com2.z0.glb.qiniucdn.com/1418653561299Wh1cFoihBp.jpg
     * recorderId : 492800
     * recorderName : 郭浩龙
     * status : 0
     * symptom : 发热,咳嗽,红眼
     */

    private long childId;
    private String childName;
    private long classId;
    private String className;
    private String comment;
    private long createDate;
    private long createDateAsDate;
    private int creator;
    private String customName;
    private int id;
    private long inspectTime;
    private int inspectType;
    private int isEnterSchool;
    private long lastModDate;
    private long lastModDateAsDate;
    private int lastModifier;
    private String medicalOrders;
    private String pictures;
    private int recorderId;
    private String recorderName;
    private int status;
    private String symptom;



    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getCreateDateAsDate() {
        return createDateAsDate;
    }

    public void setCreateDateAsDate(long createDateAsDate) {
        this.createDateAsDate = createDateAsDate;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(long inspectTime) {
        this.inspectTime = inspectTime;
    }

    public int getInspectType() {
        return inspectType;
    }

    public void setInspectType(int inspectType) {
        this.inspectType = inspectType;
    }

    public int getIsEnterSchool() {
        return isEnterSchool;
    }

    public void setIsEnterSchool(int isEnterSchool) {
        this.isEnterSchool = isEnterSchool;
    }

    public long getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(long lastModDate) {
        this.lastModDate = lastModDate;
    }

    public long getLastModDateAsDate() {
        return lastModDateAsDate;
    }

    public void setLastModDateAsDate(long lastModDateAsDate) {
        this.lastModDateAsDate = lastModDateAsDate;
    }

    public int getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(int lastModifier) {
        this.lastModifier = lastModifier;
    }

    public String getMedicalOrders() {
        return medicalOrders;
    }

    public void setMedicalOrders(String medicalOrders) {
        this.medicalOrders = medicalOrders;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public int getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(int recorderId) {
        this.recorderId = recorderId;
    }

    public String getRecorderName() {
        return recorderName;
    }

    public void setRecorderName(String recorderName) {
        this.recorderName = recorderName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
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
}
