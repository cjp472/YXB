package com.cicada.yuanxiaobao.attendance.model;

/**
 * Created by tanglei on 16/7/23.
 */
public class RequestStudentAttendanceModel {

    /**
     * schoolId : 6101130180
     * classId : 22812
     */

    private long schoolId;
    private long classId;
    private long attendanceTime;

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(long attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
