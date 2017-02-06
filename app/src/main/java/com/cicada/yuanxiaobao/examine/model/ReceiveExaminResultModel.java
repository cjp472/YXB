package com.cicada.yuanxiaobao.examine.model;

import java.util.List;

/**
 * Created by tanglei on 16/7/21.
 */
public class ReceiveExaminResultModel {

    private List<KidModel> enter;

    private List<KidModel> out;

    public List<KidModel> getEnter() {
        return enter;
    }

    public void setEnter(List<KidModel> enter) {
        this.enter = enter;
    }

    public List<KidModel> getOut() {
        return out;
    }

    public void setOut(List<KidModel> out) {
        this.out = out;
    }


}



