package com.cicada.yuanxiaobao.examine.model;

import java.util.List;

/**
 * Created by tanglei on 16/7/19.
 */
public class ReceiveClassModel {
    /**
     * gradeId : 1
     * gradeName : 大班
     */

    private int gradeId;
    private String gradeName;

    private List<ClassModel> classList;

    public List<ClassModel> getClassList() {
        return classList;
    }

    public void setClassList(List<ClassModel> classList) {
        this.classList = classList;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}

