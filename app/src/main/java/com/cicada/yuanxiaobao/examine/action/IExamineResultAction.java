package com.cicada.yuanxiaobao.examine.action;

import com.cicada.yuanxiaobao.common.QuickAdapter;

/**
 * Created by tanglei on 16/7/21.
 */
public interface IExamineResultAction {

    public void setEnterAdapter(QuickAdapter adapter);

    public void setOutAdapter(QuickAdapter adapter);

    public void setEnterLayout();

    public void setOutLayout();

}
