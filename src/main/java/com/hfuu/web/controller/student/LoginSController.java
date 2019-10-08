package com.hfuu.web.controller.student;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.entity.*;
import com.hfuu.web.service.CourseService;
import com.hfuu.web.service.StuService;
import com.hfuu.web.service.SubmitService;
import com.hfuu.web.service.TaskService;
import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author :
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :
 */
@Controller
@RequestMapping("")
@SessionAttributes({"studentLogin"})
public class LoginSController {
    @Resource
    private StuService stuService;
    @Resource
    private TaskService taskService;
    @Resource
    private CourseService courseService;
    @Resource
    private SubmitService submitService;

    /**
     * 前往主页面
     *
     * @return
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String toIndex() {
        return "index";
    }

    /**
     * 前往学生端的主页面
     *
     * @return
     */
    @RequestMapping(value = {"/indexs"}, method = RequestMethod.GET)
    public String toindexS(Model model) {
        StudentEntity studentEntity = stuService.findById(4);
        model.addAttribute("studentLogin", studentEntity);
        return "student/index";
    }

    /**
     * 前往学生端 个人所在班级课程所有详情页面
     *
     * @return
     */
    @RequestMapping(value = {"/homes"}, method = RequestMethod.GET)
    public String toHomeS(@RequestParam(value = "studentNum", required = false) String studentNum, Model model) {
        List<StudentEntity> studentEntities = stuService.findByHql("from StudentEntity s where s.stuNum=" + studentNum);
        int[] total = {};//任务总数
        int[] ytj = {};//待提交任务数
        for (StudentEntity studentEntity : studentEntities) {
            Set<CourseEntity> classEntities = studentEntity.getClassEntity().getCoursesFromClass();
            int i = 0;
            int courseCount = classEntities.size();
            total = new int[courseCount];//任务总数
            ytj = new int[courseCount];
            for (CourseEntity courseEntity : classEntities) {
                Set<TaskEntity> taskEntities = courseEntity.getTasksFromCoz();
                int n = 0;
                for (TaskEntity taskEntity : taskEntities) {
                  Set<SubmitEntity> submitEntities=taskEntity.getSubmitsFromTask();
                    Iterator<SubmitEntity> iterator=submitEntities.iterator();
                    while (iterator.hasNext()){//删除非此用户的提交信息
                        SubmitEntity submit=iterator.next();
                        if(!submit.getStuEntity().getStuNum().equals(studentNum)){
                            iterator.remove();
                        }
                    }

                  for (SubmitEntity submitEntity:submitEntities){

                      if(submitEntity.getStuEntity().getStuNum().equals(studentNum) && submitEntity.getSubState().equals("已批阅")){
                          n++;
                      }

                      if(submitEntity.getStuEntity().getStuNum().equals(studentNum) && submitEntity.getSubState().equals("待批阅")){
                          n++;
                      }
                  }

                  System.out.println(studentEntities.toString());

                }
                total[i] = taskEntities.size();
                ytj[i] = n;
                i++;
            }
        }


        model.addAttribute("student", studentEntities);
        model.addAttribute("total", total);
        model.addAttribute("ytj", ytj);
        return "student/home";
    }

    /**
     * 测试子界面--测试
     *
     * @return
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String toTestS(@RequestParam(value = "studentNum", required = false) String studentNum, Model model) {

        System.out.println(studentNum);
        return "student/test";
    }


    /**
     * 前往 实验编辑页面
     *
     * @return
     */
    @RequestMapping(value = {"/edits"}, method = RequestMethod.GET)
    public String toEditS() {
        return "student/edit";
    }

















    /*@RequestMapping(value = {"/uindex"}, method = RequestMethod.GET)
    public String toUindex(@RequestParam(value = "asd", required = false) String asd) {
        System.err.println(asd);
        return "student/uindex";
    }*/


    /**
     * 前往子界面--登录
     *
     * @return
     */
    @RequestMapping(value = {"/logins"}, method = RequestMethod.GET)
    public String toLoginS() {
        return "student/login";
    }

    /**
     * 前往子界面--控制台
     *
     * @return
     */
    @RequestMapping(value = {"/consoles"}, method = RequestMethod.GET)
    public String toConsoleS() {
        return "student/console";
    }


    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/personaldetas"}, method = RequestMethod.GET)
    public String toPersonalDetaS() {
        return "student/personaldeta";
    }

    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/userinfos"}, method = RequestMethod.GET)
    public String toUserInfoS() {
        return "student/userInfo";
    }

    /**
     * 前往子界面--文件上传
     *
     * @return
     */
    @RequestMapping(value = {"/uploads"}, method = RequestMethod.GET)
    public String toUploadS() {
        return "student/upload";
    }


    /**
     * 前往子界面--文件上传 带有进度条
     *
     * @return
     */
    @RequestMapping(value = {"/uploadbars"}, method = RequestMethod.GET)
    public String toUpTestS() {
        return "student/uploadBar";
    }
}
