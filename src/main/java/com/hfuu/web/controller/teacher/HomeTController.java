package com.hfuu.web.controller.teacher;


import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @date :
 * @author :
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("")
public class HomeTController {
    static private Logger log = Logger.getLogger(HomeTController.class);
    @Resource
    TeacherControllerService teacherControllerService;

    @RequestMapping(value = {"/teacher/home"}, method = RequestMethod.GET)
    public String toHome() {

        log.debug("跳转到:teacher/home.jsp");
        return "teacher/home";
    }

    @RequestMapping(value = {"/teacher/console"}, method = RequestMethod.GET)
    public String toConsole(HttpServletRequest request, HttpServletResponse response) {
        Map map = teacherControllerService.getCourseByTeacherNum("1604012003");

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

    @RequestMapping(value = {"/teacher/json_test"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    @ResponseBody
    public Map<String, Object>jsonTest(){
        Map<String, Object> json = new HashMap<>(4);
        List<Object> data = new ArrayList<>();
        Map<String, Object> user = new HashMap<>(2);
        user.put("username", "whh");
        user.put("age", 21);
        data.add(user);
        data.add(user);
        data.add(user);
        data.add(user);
        data.add(user);
        data.add(user);
        data.add(user);
        data.add(user);
        json.put("data", data);
        json.put("code", 0);
        json.put("count", data.size());
        json.put("msg", "");
        return json;
    }
}
