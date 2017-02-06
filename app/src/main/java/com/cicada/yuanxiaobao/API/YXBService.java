package com.cicada.yuanxiaobao.API;

/**
 * AUTHOR: TL
 * DATE: 17/11/2015 21:34
 */
public interface YXBService {

    final String URL = "http://dev.imzhiliao.com/";
    final String UPLOAD_URL="http://dev.imzhiliao.com/";

    /** 登录*/
    final String LOGIN_URL = URL+"kidscare/pad/login/";

    /** 根据用户ID获取用户信息*/
    final String QUERYUSERINFOBYUSERID_URL = URL+"uc/user/queryUserInfoByUserId/";

    /** 根据学校ID查询年级和班级信息 */
    final String QUERYGRADEANDCLASS_URL = URL+"kidscare/pad/common/queryGradeAndClass/";

    /** 根据班级ID查询学生信息/分男女 */
    final String QUERYCHILDBYCLASSID_URL = URL+"kidscare/pad/common/queryChildByClassId/";

    /** 增加晨午检记录 */
    final String ADDINSPECTINFO_URL = URL+"kidscare/pad/inspect/addInspectInfo/";

    /** 根据班级ID查询晨午检结果 */
    final String QUERYCHILDBYCLASSIDS_URL = URL+"kidscare/pad/inspect/queryChildByClassIds/";

    /** 获取考勤列表 */
    final String STUDENTATTENDANCE_URL = URL+"kidscare/pad/attendance/studentAttendance/";

    /** 编辑考勤 */
    final String ADDATTENDANCE_URL = URL+"kidscare/pad/attendance/addAttendance/";

    /** 获取病假追踪信息 */
    final String SICKLEAVELIST_URL = URL+"kidscare/pad/attendance/sickLeaveList/";

    /** 根据孩子ID查询家长信息 */
    final String QUERYFAMILYMEMBER_URL = URL+"kidscare/pad/common/queryFamilyMember/";

    /** 查询孩子某天的晨午检记录 */
    final String QUERYCHILDINSPECT_URL = URL+"kidscare/pad/inspect/queryChildInspect/";

    /** 查询晨午检特别情况 */
    final String QUERYINSPECTSPECIAL_URL = URL+"kidscare/pad/inspect/queryInspectSpecial/";

    /** 查询晨午检特别情况 */
    final String SAVEINSPECTSPECIAL = URL+"kidscare/pad/inspect/saveInspectSpecial/";

    /** 编辑病假追踪信息 */
    final String UPDATESICKLEAVEINFO_URL = URL+"kidscare/pad/attendance/updateSickLeaveInfo/";

    /** 获取教师所在班级 */
    final String GETTEACHERCLASSES_URL = URL+"kidscare/pad/attendance/getTeacherClasses/";

}
