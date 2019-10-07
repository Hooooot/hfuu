package com.hfuu.web.controller.student;

import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.StudentEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.service.CourseService;
import com.hfuu.web.service.StuService;
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
import java.util.List;

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
    public String toHomeS(@RequestParam(value = "studentNum", required = false) String studentNum,Model model) {
        List<StudentEntity> studentEntities = stuService.findByHql("from StudentEntity s where s.stuNum=" + studentNum);

        model.addAttribute("student", studentEntities);
        return "student/home";
    }

    /**
     * 测试子界面--测试
     *
     * @return
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String toTestS(@RequestParam(value = "studentNum", required = false) String studentNum, Model model) {
        List<StudentEntity> studentEntities = stuService.findByHql("from StudentEntity s where s.stuNum=" + studentNum);

        model.addAttribute("student", studentEntities);
        System.out.println(studentEntities.toString());
        for(StudentEntity s:studentEntities){
            System.out.println(s.getClassEntity().getCoursesFromClass().toString());
        }

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
