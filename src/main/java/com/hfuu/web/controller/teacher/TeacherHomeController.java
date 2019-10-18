package com.hfuu.web.controller.teacher;


import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.others.ConstValues;
import com.hfuu.web.others.utils.UploadFileUtils;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Description : 教师页面首页控制器
 * @date :2019年10月10日 16点49分
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("")
@SessionAttributes(names = {"teacher"})
public class TeacherHomeController {
    static private Logger log = Logger.getLogger(TeacherHomeController.class);
    @Resource
    TeacherControllerService teacherControllerService;
    @Resource
    TaskService taskService;

    @RequestMapping(value = {"/teacher/home"}, method = RequestMethod.GET)
    public String toHome(Model model){
        if (model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME) != null){
            log.debug("跳转到:teacher/home.jsp");
            return "teacher/home";
        }
        return "redirect: ../logintPage";
    }

    @ModelAttribute
    public void consoleModel(String tcNum, Model model){
        if(tcNum == null){
            return;
        }
        Map map = teacherControllerService.getCourseByTeacherNum(tcNum);
        model.addAttribute("course", map);
    }

    @RequestMapping(value = {"/teacher/console"}, method = RequestMethod.GET)
    public String toConsole() {
        log.debug("跳转到:teacher/console.jsp");
        return "teacher/console";
    }

    @RequestMapping(value = {"/teacher/already_homework"}, method = RequestMethod.GET)
    public String toTest() {
        log.debug("跳转到:teacher/already_homework.jsp");
        return "teacher/already_homework";
    }

    @RequestMapping(value = {"/teacher/deploy_homework"}, method = RequestMethod.GET)
    public String toDeployHomework() {
        log.debug("跳转到:teacher/deploy_homework.jsp");
        return "teacher/deploy_homework";
    }

    @RequestMapping(value = {"/teacher/account"}, method = RequestMethod.GET)
    public String toAccount() {
        log.debug("跳转到:teacher/account.jsp");
        return "teacher/account";
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/json_test"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object>jsonTest(String cozName, Model model){
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity)(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List<CourseEntity> cozList = teacherControllerService.getCourseByTeacherNum(tc.getTcNum()).get(cozName);
        List<Map> data = new ArrayList<>();
        for (CourseEntity c : cozList){
            Map<String, Object> m = c.toMap();
            Set tasks = (Set)(m.get("taskSet"));
            Map<String, Object> taskCount = taskService.getTaskClosedAndNotClosedCount(tasks);
            m.put("taskCount", taskCount);
            m.remove("taskSet");
            data.add(m);
        }
        json.put("data", data);
        json.put("code", 0);
        json.put("count", data.size());
        json.put("msg", "");
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/upload"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map upload(@RequestParam("file") MultipartFile file, HttpSession session){
        Map<String, Integer> json = new HashMap<>(1);
        UploadFileUtils.uploadFile(session, file, "files/");
        json.put("code", 0);
        return json;
    }
}
