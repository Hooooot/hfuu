package com.hfuu.web.service.student;



import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.service.base.BaseService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Description:Student的Controller层service
 * @author Starry the Night
 * @PackageName:com.hfuu.web.service.student
 * @ClassName:StudentControllerService
 * @autor:Starry the Night
 * @Date:2019/10/18 20:11
 */
public interface StudentControllerService extends BaseService {


    /**
     * 获取该学生所在班级下所有的课程
     *
     * @param stuNum 学生学号
     * @return Map内包含按cozNum分组的courseList，key为cozNum，value为所有cozNum相同的CourseEntity类型的List
     */
    Map<String, List<CourseEntity>> getCourseByStuNum(String stuNum);

    /**
     * 获取该学生所在班级的所有课程
     *
     * @param stuNum 学生学号
     * @param cozName 课程号
     * @return
     */
    List<CourseEntity> getCourse(String stuNum, String cozName);

    /**
     * 获取该学生所在课程下所有实验
     *
     * @param stuNum 学生学号
     * @param cozName 课程号
     * @return
     */
    List<Map> getTaskFromCourse(String stuNum, String cozName);

    /**
     * 通过课程名来对CourseEntity类型的List进行切片
     * @param list 要切片的列表
     * @return 按cozName分组的 Map<String, List<CourseEntity>>
     * */
    Map groupByCozName(List<CourseEntity> list);


    /**
     * 对提交信息subRichTextPath字段数据进行更新
     * @param taskId 任务id
     * @param stuNum 学生学号
     * @param subRichTextPath 富文本保存成html的路径
     * @return
     * */
    void updateSubRichTextPath(int taskId,String stuNum,String subRichTextPath);

    /**
     * 更新数据库 提交时间
     * @param subId  提交id
     * @param subTime 当前时间
     * @Author: Starry the Night
     * @Date:  2019/10/24 22:51
     * @return java.lang.Integer
     */
    Integer updateSubmitSubTime(int subId,Timestamp subTime);

    /**
     * 更新数据库 提交时间
     * @param subId  提交id
     * @param subTime 当前时间
     * @param subState 提交状态
     * @Author: Starry the Night
     * @Date:  2019/10/24 22:51
     * @return java.lang.Integer
     */
    Integer updateSubmitSubTimeAndSubState(int subId,Timestamp subTime,String subState);


    /**
     * 直接提交（不保存后提交）
     * @param taskId 任务id
     * @param stuNum 学生学号
     * @param subState 提交状态
     * @param subRichTextPath  富文本保存成html的路径
     * @Author: Starry the Night
     * @Date:  2019/10/28 21:48
     */
    void directSubmission(int taskId,String stuNum,String subState,String subRichTextPath);


}