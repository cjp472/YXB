package com.cicada.yuanxiaobao.home.model;

/**
 * Created by tanglei on 16/7/23.
 */
public class SchoolInfo {

    /**
     * createDateAsDate : 1469093226796
     * lastModDate : 1469093226701
     * lastModDateAsDate : 1469093226701
     * schoolId : 6101130154
     * schoolName : 第一中学
     */

    private long createDateAsDate;
    private long lastModDate;
    private long lastModDateAsDate;
    private long schoolId;
    private String schoolName;

    public long getCreateDateAsDate() {
        return createDateAsDate;
    }

    public void setCreateDateAsDate(long createDateAsDate) {
        this.createDateAsDate = createDateAsDate;
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

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
