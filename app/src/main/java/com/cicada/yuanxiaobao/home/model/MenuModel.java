package com.cicada.yuanxiaobao.home.model;

/**
 * Created by tanglei on 16/7/23.
 */
public class MenuModel {

    /**
     * createDateAsDate : 1469093226796
     * id : 50
     * lastModDate : 1469093226702
     * lastModDateAsDate : 1469093226702
     * menuName : 体质测评
     */

    private long createDateAsDate;
    private int id;
    private long lastModDate;
    private long lastModDateAsDate;
    private String menuName;

    public long getCreateDateAsDate() {
        return createDateAsDate;
    }

    public void setCreateDateAsDate(long createDateAsDate) {
        this.createDateAsDate = createDateAsDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
