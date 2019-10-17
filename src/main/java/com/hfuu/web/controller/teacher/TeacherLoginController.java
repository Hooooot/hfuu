package com.hfuu.web.controller.teacher;


import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.others.ConstValues;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description :教师登录控制器
 * @date :2019年10月10日 16点50分
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("")
@SessionAttributes(names = {"teacher"})
public class TeacherLoginController {
    private static Logger log = Logger.getLogger(TeacherLoginController.class);

    @Resource
    TeacherControllerService teacherControllerService;

    @RequestMapping(value = {"/logintPage"}, method = RequestMethod.GET)
    public String toLogintPage() {
        log.debug("跳转到：\"teacher/login\"");
        return "teacher/login";
    }

    @ModelAttribute
    public void homeModel(String name, String pw, Model model){
        if(name == null || pw == null){
            return;
        }
        log.debug("name=" + name + ", pw=" + pw);
        if(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME) != null){
            return;
        }
        TeacherEntity tc = teacherControllerService.login(name, pw);
        if(tc != null){
            model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, tc);
        }
    }

    @RequestMapping(value = {"/teacher/login"}, method = RequestMethod.POST)
    @ResponseBody
    public String toHome(Model model) {
        if(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME) != null){
            log.debug("跳转到:teacher/home.jsp");
            return "teacher/home";
        }else{
            log.debug("登录失败");
            return ConstValues.LOGIN_FAIL;
        }
    }
}
