package com.cicada.yuanxiaobao.examine.model;

/**
 * Created by tanglei on 16/6/25.
 */
public class ReceiveSymptomModel {
    private boolean isChoose;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
