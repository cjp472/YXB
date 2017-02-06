package com.cicada.yuanxiaobao.home.model;

/**
 * Created by tanglei on 16/7/20.
 */
public class UserInfo {

    /**
     * accountType : 1
     * createDateAsDate : 1469093226796
     * id : 173948
     * isOldUser : 0
     * lastModDate : 1469093226701
     * lastModDateAsDate : 1469093226701
     * phoneNum : 18629199024
     * userIcon : http://cicadafile.qiniudn.com/14613921573039a0VPcZBN8.jpg
     * userId : 492800
     * userName : 郭浩龙
     * userSex : 男
     */

    private int accountType;
    private long createDateAsDate;
    private int id;
    private int isOldUser;
    private long lastModDate;
    private long lastModDateAsDate;
    private String phoneNum;
    private String userIcon;
    private int userId;
    private String userName;
    private String userSex;

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

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

    public int getIsOldUser() {
        return isOldUser;
    }

    public void setIsOldUser(int isOldUser) {
        this.isOldUser = isOldUser;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
}
