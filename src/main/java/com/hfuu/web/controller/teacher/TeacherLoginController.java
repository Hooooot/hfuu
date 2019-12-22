package com.hfuu.web.controller.teacher;


import com.hfuu.web.others.ConstValues;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;


/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :教师登录控制器
 * @date :2019年10月10日 16点50分
 */
@Controller
@RequestMapping("")
@SessionAttributes(names = {"teacher", "forgetPasswordTeacher"})
public class TeacherLoginController {
    private static Logger log = Logger.getLogger(TeacherLoginController.class);

    @Resource
    TeacherControllerService teacherControllerService;

    @RequestMapping(value = {"/logintPage"}, method = RequestMethod.GET)
    public String toLogintPage() {
        log.debug("跳转到：teacher/login.jsp");
        return "teacher/login";
    }

    @RequestMapping(value = {"/teacher/forget_password"}, method = RequestMethod.GET)
    public String toForgetPassword(){
        log.debug("跳转到：teacher/forget_password.jsp");
        return "teacher/forget_password";
    }

    @RequestMapping(value = {"/teacher/next_step"}, method = RequestMethod.GET)
    public String toSendEmailPage(Model model){
        if(model.asMap().get(ConstValues.FORGET_PWD_TC)!=null){
            log.debug("跳转到:teacher/send_email_page.jsp");
            return "teacher/send_email_page";
        }else{
            return "redirect: teacher/forget_password";
        }
    }

    @RequestMapping(value = {"/teacher/new_password"}, method = RequestMethod.GET)
    public String toNewPassword(Model model){
        if(model.asMap().get(ConstValues.FORGET_PWD_TC)!=null){
            log.debug("跳转到:teacher/send_email_page.jsp");
            return "teacher/new_password";
        }else{
            return "redirect: teacher/forget_password";
        }
    }

}
