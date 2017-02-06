package com.cicada.yuanxiaobao.attendance.action;

import com.cicada.yuanxiaobao.attendance.model.ParentsModel;
import com.cicada.yuanxiaobao.common.QuickAdapter;

import java.util.List;

/**
 * Created by tanglei on 16/7/25.
 */
public interface IFamilyMemberAction {

    public void setAdapter(QuickAdapter adapter,List<ParentsModel> data);
}
