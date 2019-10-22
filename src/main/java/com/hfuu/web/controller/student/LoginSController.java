package com.hfuu.web.controller.student;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.entity.*;
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
        //学号
        Map map=studentControllerService.getCourseByStuNum(studentNum);
        System.out.println(map);
        model.addAttribute("course",map);
        return "student/home";
    }


    @ResponseBody
    @RequestMapping(value = {"/json_test"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
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


    /**
     * @param
     * @return java.lang.String
     * @Description: //TODO
     * @Author: Starry the Night
     * @Date: 2019/10/18 20:58
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
        //学号
        String stuNum = "1706072019";
        //课程名称
        String cozName = "数据结构与算法";
        //判断传入的参数，
       /* if(stuNum == null||cozName==null){
            return;
        }
*/
        List<Map> data=studentControllerService.getTaskFromCourse(stuNum,cozName);
        System.out.println(data);


        return "student/upload";
    }


    /**
     * 前往子界面--文件上传 带有进度条
     *
     * @return
     */
    @RequestMapping(value = {"/uploadbars"}, method = RequestMethod.GET)
    public String toUpTestS() {

        //学号
        String stuNum = "1706072019";

        Map map=studentControllerService.getCourseByStuNum(stuNum);
        System.out.println(map);
        return "student/uploadBar";
    }
}
