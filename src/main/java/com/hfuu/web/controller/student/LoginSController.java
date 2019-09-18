package com.hfuu.web.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
public class LoginSController {


    /**
     * 前往主页面
     *
     * @return
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String toIndex() {
        return "index";
    }


    /*@RequestMapping(value = {"/uindex"}, method = RequestMethod.GET)
    public String toUindex(@RequestParam(value = "asd", required = false) String asd) {
        System.err.println(asd);
        return "student/uindex";
    }*/
    /**
     * 前往学生端主页面
     *
     * @return
     */
    @RequestMapping(value = {"/indexs"}, method = RequestMethod.GET)
    public String toindexS() {
        return "student/index";
    }

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
     * 测试子界面--测试
     *
     * @return
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String toTestS(@RequestParam(value = "asd", required = false) String asd,HttpServletRequest request) {
        System.err.println(asd);
        request.setAttribute("asd",asd);
        return "student/test";
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
