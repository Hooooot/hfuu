package com.hfuu.web.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class LoginController {


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
     * 前往学生端主页面
     *
     * @return
     */
    @RequestMapping(value = {"/uindex"}, method = RequestMethod.POST)
    public String toUindex(@RequestParam(value = "asd", required = false) String asd) {
        System.err.println(asd);
        return "student/uindex";
    }

    /**
     * 前往子界面--登录
     *
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String toLogin() {
        return "student/ulogin";
    }

    /**
     * 前往子界面--控制台
     *
     * @return
     */
    @RequestMapping(value = {"/console"}, method = RequestMethod.GET)
    public String toConsole() {
        return "student/uconsole";
    }

    /**
     * 测试子界面--测试
     *
     * @return
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String toTest() {
        return "student/test";
    }

    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/personaldeta"}, method = RequestMethod.GET)
    public String toPersonalDeta() {
        return "student/upersonaldeta";
    }

    /**
     * 前往子界面--修改个人信息
     *
     * @return
     */
    @RequestMapping(value = {"/userinfo"}, method = RequestMethod.GET)
    public String toUserInfo() {
        return "student/userInfo";
    }
}
