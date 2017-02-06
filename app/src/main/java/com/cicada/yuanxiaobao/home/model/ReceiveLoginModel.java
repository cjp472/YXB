package com.cicada.yuanxiaobao.home.model;

import java.util.List;

/**
 * Created by tanglei on 16/7/23.
 */
public class ReceiveLoginModel {
    private String token;
    private UserInfo userInfo;
    private SchoolInfo schoolInfo;
    private List<MenuModel> rights;
    private List<String> indexPictures;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SchoolInfo getSchoolInfo() {
        return schoolInfo;
    }

    public void setSchoolInfo(SchoolInfo schoolInfo) {
        this.schoolInfo = schoolInfo;
    }

    public List<MenuModel> getRights() {
        return rights;
    }

    public void setRights(List<MenuModel> rights) {
        this.rights = rights;
    }

    public List<String> getIndexPictures() {
        return indexPictures;
    }

    public void setIndexPictures(List<String> indexPictures) {
        this.indexPictures = indexPictures;
    }
}
