package com.cicada.yuanxiaobao.common;

/**
 * Created by 唐磊 on 2015/11/5.
 * 常量类
 */
public interface Constant {

    String HOME_FLAG="首页";
    String EXAMINE_FLAG="晨午检";
    String ATTENDACE_FLAG="考勤";
    String ARCHIVE_FLAG="幼儿档案";
    String ASSESSMENT_FLAG="体质测评";

    /**
     * 系统图库选择图片
     **/
    public static int SELECT_PICTURE = 0x01;

    /**
     * 调用系统相机拍照
     **/
    public static int CAMERA_REQUEST_CODE = 0x02;
}
