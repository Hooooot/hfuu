package com.hfuu.web.controller.teacher;


import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.teacher.ITeacherService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("")
public class HomeTController {
    static private Logger log = Logger.getLogger(HomeTController.class);
    @Resource(name = "teacherService")
    private ITeacherService teacherService;

    @RequestMapping(value = {"/teacher/home"}, method = RequestMethod.GET)
    public String toHome() {
        TeacherEntity t = (TeacherEntity) teacherService.getById(2);
        System.err.println(t.getTcName());

        log.debug("跳转到:teacher/home.jsp");
        return "teacher/home";
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
}
