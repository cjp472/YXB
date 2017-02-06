package com.cicada.yuanxiaobao.home.model;

/**
 * Created by tanglei on 16/6/17.
 */
public class NavigationListItemModel {

    private int drawableId;
    private int drawableId1;
    private String title;
    private Boolean isSelect=false;

    public int getDrawableId1() {
        return drawableId1;
    }

    public void setDrawableId1(int drawableId1) {
        this.drawableId1 = drawableId1;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
