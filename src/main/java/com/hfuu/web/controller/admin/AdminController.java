package com.hfuu.web.controller.admin;

import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.DepEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.ClassService;
import com.hfuu.web.service.DepService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Decription :
 * @CreateDate :
 * @author :
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private DepService depService;

    /**
     * 前往管理员登录页
     *
     * @return
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String toLoginA() {
        return "admin/login";
    }

    /**
     * 前往管理员主页面
     *
     * @return
     */
    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String inIndexA() {
        return "admin/index";
    }

    /**
     * 机构服务测试
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public void depDaoTest(){
        Set<TeacherEntity> tcs = depService.findById(1).getTcsFromDep();
        for(TeacherEntity t : tcs){
            System.out.println(t.toString());
        }

    }
}
