package com.cicada.yuanxiaobao.attendance.model;

import com.cicada.yuanxiaobao.examine.model.KidModel;

import java.util.List;

/**
 * Created by tanglei on 16/7/23.
 */
public class ReceiveStudentAttendanceModel {
    private List<KidModel> toAttendanceList;//待考勤
    private List<KidModel> absenceList;//缺勤
    private List<KidModel> normalList;//出勤
    private long schoolId;
    private long attendanceDate;

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(long attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public List<KidModel> getToAttendanceList() {
        return toAttendanceList;
    }

    public void setToAttendanceList(List<KidModel> toAttendanceList) {
        this.toAttendanceList = toAttendanceList;
    }

    public List<KidModel> getAbsenceList() {
        return absenceList;
    }

    public void setAbsenceList(List<KidModel> absenceList) {
        this.absenceList = absenceList;
    }

    public List<KidModel> getNormalList() {
        return normalList;
    }

    public void setNormalList(List<KidModel> normalList) {
        this.normalList = normalList;
    }
}
