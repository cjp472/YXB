package com.cicada.yuanxiaobao.common;

import com.cicada.yuanxiaobao.utils.DeviceUtils;

import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by tanglei on 16/6/29.
 */
public class RequestDataUtils {

    public static ClientInfoModel sClientInfoModel=new ClientInfoModel();
    static  {
        sClientInfoModel.setClientOs(DeviceUtils.getOS());
        sClientInfoModel.setVersion(DeviceUtils.getVersionName(MyApplication.getInstance()));
        sClientInfoModel.setClientType("android");
        sClientInfoModel.setClientModel(DeviceUtils.getDeviceModel());
    }

    /**
     * 请求数据按和后台协议封装
     * @param javaBean 请求参数实体
     * @param isSelect 是否查询
     * @return
     */
    public static RequestModel getRequestModel(Object javaBean,boolean isSelect) {
        sClientInfoModel.setCNet(DeviceUtils.getNetworkType(MyApplication.getInstance()));
        RequestModel requestModel=new RequestModel();
        requestModel.setData(javaBean==null?new Object():javaBean);
        requestModel.setStyle("black");
        requestModel.setClientInfo(sClientInfoModel);
        requestModel.setSelect(isSelect);
        return requestModel;
    }

    /**
     * 将javaBean转换成Map
     *
     * @param javaBean
     * @return
     */
    private static Map<String, Object> javaBeanToMap(Object javaBean) {
        Map<String, Object> map = new IdentityHashMap<String, Object>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[]) null);
                    map.put(field, null == value ? "" : value);
                }
            } catch (Exception e) {
            }
        }
        map.put("app","zhiliao");
        return map;
    }
}
