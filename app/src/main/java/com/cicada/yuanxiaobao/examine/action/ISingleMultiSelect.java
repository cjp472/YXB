package com.cicada.yuanxiaobao.examine.action;

import com.cicada.yuanxiaobao.examine.model.ClassModel;

import java.util.List;

/**
 * Created by tanglei on 16/7/20.
 */
public interface ISingleMultiSelect {

    public void singleSelection(ClassModel model);

    public void multipleSelection(List<ClassModel> list);
}
