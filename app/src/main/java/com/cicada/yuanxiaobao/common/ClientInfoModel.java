package com.cicada.yuanxiaobao.common;

/**
 * Created by tanglei on 16/6/29.
 */
public class ClientInfoModel {

    private String clientOs;
    private String clientModel;
    private String cNet;
    private String clientType;
    private String version;

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

    public String getClientModel() {
        return clientModel;
    }

    public void setClientModel(String clientModel) {
        this.clientModel = clientModel;
    }

    public String getCNet() {
        return cNet;
    }

    public void setCNet(String cNet) {
        this.cNet = cNet;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
