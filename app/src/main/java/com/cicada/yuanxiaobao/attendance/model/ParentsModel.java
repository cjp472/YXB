package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/12.
 */
public class ParentsModel {

    /**
     * phoneNum : 18629199024
     * registerTime : 0
     * relation : 爸爸
     * userId : 0
     * userName : 郭浩龙
     */

    private String phoneNum;
    private int registerTime;
    private String relation;
    private int userId;
    private String userName;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(int registerTime) {
        this.registerTime = registerTime;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
