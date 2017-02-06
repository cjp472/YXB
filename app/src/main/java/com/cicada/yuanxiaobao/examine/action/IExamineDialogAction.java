package com.cicada.yuanxiaobao.examine.action;

import com.cicada.yuanxiaobao.common.IUploadFile;
import com.cicada.yuanxiaobao.common.QuickAdapter;

/**
 * Created by tanglei on 16/7/21.
 */
public interface IExamineDialogAction extends IUploadFile {

    public void callBack();

    public void callBackFail();

    public void setButtonBg();

    public void leftGridViewAdapter(QuickAdapter adapter);

    public void rightGridViewAdapter(QuickAdapter adapter);
}
