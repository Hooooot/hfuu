package com.hfuu.web.controller.admin;

import com.hfuu.web.entity.AdminEntity;
import com.hfuu.web.entity.DepEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author :
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :
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
    @Resource
    private DepService depService;
    @Resource
    private TeacherService teacherService;

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
    public String toIndexA(AdminEntity admin, HttpServletRequest request) {
        admin = adminService.findById(2);
        request.getSession().setAttribute("admin", admin);
        return "admin/index";
    }

    @RequestMapping(value = {"/analyseByTask"}, method = RequestMethod.GET)
    public ModelAndView toAnalyseByTask() {

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
        modelAndView.addObject("ypy", ypy);
        modelAndView.addObject("total", total);
        modelAndView.setViewName("admin/analyseByTask");
        return modelAndView;
    }

    @RequestMapping(value = {"/analyseByDep"}, method = RequestMethod.GET)
    public ModelAndView toAnalyseByDep() {

        //获取院系实体
        List<DepEntity> deps = depService.findAll();
        int depCount = deps.size();
        Long[] dtj = new Long[depCount];
        Long[] dpy = new Long[depCount];
        Long[] ypy = new Long[depCount];
        Long[] total = new Long[depCount];

        //获取各院系提交情况
        int i = 0;
        for (DepEntity dep : deps) {
            //获取某院系所有任务
            List<TaskEntity> tasks = taskService.findByHql(
                    "from TaskEntity t where t.cozEntity.classEntity.depEntity.depId = ? ", dep.getDepId());
            //初始化各项数据为0
            Long zero = new Long(0);
            dtj[i] = zero;
            dpy[i] = zero;
            ypy[i] = zero;
            total[i] = zero;
            //遍历某院系下所有任务的提交情况
            for (TaskEntity task : tasks) {
                int taskId = task.getTaskId();
                dtj[i] += submitService.countSubmitByState(taskId, "待提交");
                dpy[i] += submitService.countSubmitByState(taskId, "待批阅");
                ypy[i] += submitService.countSubmitByState(taskId, "已批阅");
                total[i] += dtj[i] + dpy[i] + ypy[i];
            }
            i++;
        }

        //填充模型数据并转发页面
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("deps", deps);
        modelAndView.addObject("dtj", dtj);
        modelAndView.addObject("dpy", dpy);
        modelAndView.addObject("ypy", ypy);
        modelAndView.addObject("total", total);
        modelAndView.setViewName("admin/analyseByDep");
        return modelAndView;
    }

    @RequestMapping(value = {"/editAdminInfo"}, method = RequestMethod.GET)
    public String toEditAdminInfo() {
        return "admin/editAdminInfo";
    }

    @RequestMapping(value = "/editAdminInfoSubmit", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String editAdminInfoSubmit(AdminEntity admin, MultipartFile avatarPath, HttpServletRequest request) throws Exception {

        AdminEntity newAdmin = adminService.findById(admin.getAdminId());
        //上传用户头像(图片组件的name不能和用户类的头像属性一致，否则会出现参数绑定出错，报400)
        String originalFilename = avatarPath.getOriginalFilename();
        if (avatarPath != null && originalFilename != null && originalFilename.length() > 0) {
            //新的图片名，随机数+扩展名(提取原始名称.及以后的部分)
            String avatarFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //存储图片的物理路径
            String realPath = request.getSession().getServletContext().getRealPath("");
            String parentPath = new File(new File(realPath).getParent()).getParent();
            String pic_path = parentPath + "/src/main/webapp/WEB-INF/images/avatar/admin/";
            //创建新的图片文件对象
            File newFile = new File(pic_path + avatarFilename);
//            File newFile2 = new File(realPath + "WEB-INF\\images\\admin\\avatar\\");
            //将内存中的用户头像写入磁盘
            avatarPath.transferTo(newFile);
            //将新的图片名写入po对象属性
            newAdmin.setAdminAvatar(avatarFilename);
        }

        //更新用户信息
        String result;
        newAdmin.setAdminName(admin.getAdminName());
        newAdmin.setAdminPhone(admin.getAdminPhone());
        newAdmin.setAdminSex(admin.getAdminSex());
        try {
            adminService.update(newAdmin);
            result = "success";
        } catch (Exception e) {
            e.printStackTrace();
            result = "error";
        }

        //重新注入session
        request.getSession().setAttribute("admin", newAdmin);
        return result;
    }

    @RequestMapping(value = {"/editTcInfo"}, method = RequestMethod.GET)
    public String toEditTcInfo(Model model) {
        List<TeacherEntity> tcs = teacherService.findAll();
        List<DepEntity> deps = depService.findAll();
        model.addAttribute("deps", deps);
        model.addAttribute("tcs", tcs);
        return "admin/editTcInfo";
    }

    @RequestMapping(value = {"/editTcInfoSubmit"}, method = RequestMethod.POST)
    @ResponseBody
    public void editTcInfoSubmit(TeacherEntity tc, String depNum) {
        TeacherEntity newTc = teacherService.findById(tc.getTcId());
        DepEntity dep = (DepEntity) depService.findByHql("from DepEntity where depNum = ?", depNum).get(0);
        newTc.setDepEntity(dep);
        newTc.setTcPw(tc.getTcPw());
        teacherService.update(newTc);
    }
}
