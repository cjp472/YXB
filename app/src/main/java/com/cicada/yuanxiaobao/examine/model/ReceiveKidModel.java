package com.cicada.yuanxiaobao.examine.model;

import java.util.List;

/**
 * Created by tanglei on 16/6/25.
 */
public class ReceiveKidModel {
    private List<KidModel> boyList;
    private List<KidModel> girlList;



    public List<KidModel> getGirlList() {
        return girlList;
    }

    public void setGirlList(List<KidModel> girlList) {
        this.girlList = girlList;
    }

    public List<KidModel> getBoyList() {
        return boyList;
    }

    public void setBoyList(List<KidModel> boyList) {
        this.boyList = boyList;
    }
}
