package com.cicada.yuanxiaobao.common;

/**
 * 接收数据
 * AUTHOR: Alex
 * DATE: 10/11/2015 17:02
 */
public class BaseReceivedModel {

    private int result;//	1成功0失败
    private String errorMsg;//	执行结果内容


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
