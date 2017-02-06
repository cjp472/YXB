package com.cicada.yuanxiaobao.attendance.model;

import java.util.List;

/**
 * Created by tanglei on 16/7/25.
 */
public class ReceiveSpecialSituatModel {


    private String anaphylactogen;
    private int childId;
    private int classId;
    private String specialSituation;

    private List<ContagionBean> contagion;

    public String getAnaphylactogen() {
        return anaphylactogen;
    }

    public void setAnaphylactogen(String anaphylactogen) {
        this.anaphylactogen = anaphylactogen;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getSpecialSituation() {
        return specialSituation;
    }

    public void setSpecialSituation(String specialSituation) {
        this.specialSituation = specialSituation;
    }

    public List<ContagionBean> getContagion() {
        return contagion;
    }

    public void setContagion(List<ContagionBean> contagion) {
        this.contagion = contagion;
    }

    public static class ContagionBean {
        private String contagionName;
        private long diagnosedDate;
        private String hospital;
        private int id;
        private boolean isRecovered;
        private String treatment;
        private boolean isExpand;


        public String getContagionName() {
            return contagionName;
        }

        public void setContagionName(String contagionName) {
            this.contagionName = contagionName;
        }

        public long getDiagnosedDate() {
            return diagnosedDate;
        }

        public void setDiagnosedDate(long diagnosedDate) {
            this.diagnosedDate = diagnosedDate;
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

        public boolean isIsRecovered() {
            return isRecovered;
        }

        public void setIsRecovered(boolean isRecovered) {
            this.isRecovered = isRecovered;
        }

        public String getTreatment() {
            return treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }
    }
}
