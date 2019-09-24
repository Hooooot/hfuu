package com.hfuu.web.controller.admin;

import com.hfuu.web.entity.DepartmentEntity;
import com.hfuu.web.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
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

        System.err.println(depService.count());
    }



}
