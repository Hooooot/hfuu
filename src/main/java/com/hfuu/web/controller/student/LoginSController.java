package com.hfuu.web.controller.student;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.entity.*;
import com.hfuu.web.others.utils.SaveToHtmlUtils;
import com.hfuu.web.service.CourseService;
import com.hfuu.web.service.StuService;
import com.hfuu.web.service.SubmitService;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.student.StudentControllerService;
import com.mchange.v2.sql.filter.SynchronizedFilterDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description //TODO
 * @Author Starry the Night
 * @Date 2019/10/15 15:06
 * @return
 */
@Controller
@RequestMapping("")
@SessionAttributes({"studentLogin"})
public class LoginSController {
    @Resource
    private StuService stuService;
    @Resource
    StudentControllerService studentControllerService;

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
    @RequestMapping(value = {"/student/indexs"}, method = RequestMethod.GET)
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
    @RequestMapping(value = {"/student/homes"}, method = RequestMethod.GET)
    public String toHomeS(@RequestParam(value = "studentNum", required = false) String studentNum, Model model) {
        //学号
        Map map=studentControllerService.getCourseByStuNum(studentNum);
        System.out.println(map);
        model.addAttribute("course",map);
        return "student/home";
    }


    @ResponseBody
    @RequestMapping(value = {"/student/json_test"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object> jsonCourse(String stuNum,String cozName){
        Map<String, Object> json=new HashMap<>(4);
        List<Map> data=studentControllerService.getTaskFromCourse(stuNum,cozName);
        json.put("data", data);
        json.put("code", 0);
        json.put("count", data.size());
        json.put("msg", "");
        System.out.println(data);
        return  json;
    }


    /**
     * 测试子界面--测试
     *
     * @return
     */
    @RequestMapping(value = {"/student/test"}, method = RequestMethod.GET)
    public String toTestS(@RequestParam(value = "studentNum", required = false) String studentNum, Model model) {
        System.out.println(studentNum);
        return "student/test";
    }


    /**
     * 前往 实验编辑页面
     *
     * @return
     */
    @RequestMapping(value = {"/student/edits"}, method = RequestMethod.GET)
    public String toEditS() {
        return "student/edit";
    }


    /**
     * 前往子界面--控制台
     *
     * @return
     */
    @RequestMapping(value = {"/student/consoles"}, method = RequestMethod.GET)
    public String toConsoleS() {
        return "student/console";
    }


    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/student/personaldetas"}, method = RequestMethod.GET)
    public String toPersonalDetaS() {


        return "student/personaldeta";
    }

    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/student/userinfos"}, method = RequestMethod.GET)
    public String toUserInfoS() {
        return "student/userInfo";
    }

    /**
     * 前往子界面--文件上传
     *
     * @return
     */
    @RequestMapping(value = {"/student/uploads"}, method = RequestMethod.GET)
    public String toUploadS() {

        return "student/upload";
    }


    /**
     * 前往子界面--文件上传 带有进度条
     *
     * @return
     */
    @RequestMapping(value = {"/student/uploadbars"}, method = RequestMethod.GET)
    public String toUpTestS() {
     /*  String s=SaveToHtmlUtils.getHtmlContent("E:\\idea\\hfuu\\src\\main\\webapp\\WEB-INF\\uploaded\\richtext\\5f8df87af48a48c7b29eb8ea578f9b25.html");
        System.err.println(s);
*/
        return "student/uploadBar";

    }
}
