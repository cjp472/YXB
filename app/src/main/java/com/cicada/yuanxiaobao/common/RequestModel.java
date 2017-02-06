package com.cicada.yuanxiaobao.common;

/**
 * Created by tanglei on 16/6/29.
 * 网络数据请求实体类
 */
public class RequestModel {

    private Object data;
    private ClientInfoModel clientInfo;
    private String style;
    private boolean isSelect;


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ClientInfoModel getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoModel clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
