package com.cicada.yuanxiaobao.common;

/**
 * Created by tanglei on 16/6/29.
 * 网络数据返回实体类
 */
public class ResponseModel {

    private String msg;
    private String rtnCode;
    private long ts;
    private Object bizData;

    public Object getBizData() {
        return bizData;
    }

    public void setBizData(Object bizData) {
        this.bizData = bizData;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
