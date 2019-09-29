package com.hfuu.web.controller.admin;

import com.hfuu.web.entity.AdminEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
    private SubmitService submitService;
    @Resource
    private TaskService taskService;
    @Resource
    private AdminService adminService;

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
    public String inIndexA(Model model, AdminEntity admin) {
        admin = adminService.findById(2);
        model.addAttribute("admin", admin);
        return "admin/index";
    }

    @RequestMapping(value = {"/f1"}, method = RequestMethod.GET)
    public ModelAndView toF1(){

        //获取任务实体
        List<TaskEntity> tasks = taskService.findAll();
        int taskCount = tasks.size();
        Long[] dtj = new Long[taskCount];
        Long[] dpy = new Long[taskCount];
        Long[] ypy = new Long[taskCount];
        Long[] total = new Long[taskCount];

        //获取任务提交情况
        int i = 0;
        for (TaskEntity task : tasks) {
            int taskId = task.getTaskId();
            dtj[i] = submitService.countSubmitByState(taskId, "待提交");
            dpy[i] = submitService.countSubmitByState(taskId, "待批阅");
            ypy[i] = submitService.countSubmitByState(taskId, "已批阅");
            total[i] = dtj[i] + dpy[i] + ypy[i];
            i++;
        }

        //填充模型数据并转发页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", tasks);
        modelAndView.addObject("dtj", dtj);
        modelAndView.addObject("dpy", dpy);
        modelAndView.addObject("ypy",ypy);
        modelAndView.addObject("total",total);
        modelAndView.setViewName("admin/analyse");
        return modelAndView;
    }
}
