package com.cicada.yuanxiaobao.home.action;

import com.cicada.yuanxiaobao.common.QuickAdapter;

/**
 * Created by tanglei on 16/7/5.
 * 导航栏
 */
public interface INavigationAction {

    /**
     * 设置 适配器
     * @param adapter
     */
    public void setAdapter(QuickAdapter adapter);

    /**
     * 通过选中标题 切换内容
     * @param str
     */
    public void change(String str);
}
