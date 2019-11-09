package com.hfuu.web.controller.teacher;


import com.hfuu.web.entity.ClassEntity;
import com.hfuu.web.entity.CourseEntity;
import com.hfuu.web.entity.TaskEntity;
import com.hfuu.web.entity.TeacherEntity;
import com.hfuu.web.others.ConstValues;
import com.hfuu.web.others.utils.SaveToHtmlUtils;
import com.hfuu.web.others.utils.UploadFileUtils;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description : 教师页面首页控制器
 * @date :2019年10月10日 16点49分
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("")
@SessionAttributes(names = {"teacher"})
public class TeacherHomeController {
    static private Logger log = Logger.getLogger(TeacherHomeController.class);
    @Resource
    TeacherControllerService teacherControllerService;
    @Resource
    TaskService taskService;

    @RequestMapping(value = {"/teacher/home"}, method = RequestMethod.GET)
    public String toHome(Model model){
        if (model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME) != null){
            log.debug("跳转到:teacher/home.jsp");
            return "teacher/home";
        }
        return "redirect: ../logintPage";
    }

    @ModelAttribute
    public void consoleModel(String tcNum, Model model){
        if(tcNum == null){
            return;
        }
        List list = teacherControllerService.getCourseByTeacherNum(tcNum);
        //noinspection unchecked
        Map map = teacherControllerService.groupByCozName(list);
        model.addAttribute("course", map);
    }

    @RequestMapping(value = {"/teacher/console"}, method = RequestMethod.GET)
    public String toConsole() {
        log.debug("跳转到:teacher/console.jsp");
        return "teacher/console";
    }

    @RequestMapping(value = {"/teacher/already_homework"}, method = RequestMethod.GET)
    public String toAlreadyHomework() {
        log.debug("跳转到:teacher/already_homework.jsp");
        return "teacher/already_homework";
    }

    @RequestMapping(value = {"/teacher/deploy_homework"}, method = RequestMethod.GET)
    public String toDeployHomework(@RequestParam(required = false) List<String> cozNum, @RequestParam(required = false) List<String> classNum, Model model) {
        TeacherEntity tc = (TeacherEntity)(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List<CourseEntity> courseList = teacherControllerService.getCourseByTeacherNum(tc.getTcNum());
        if(cozNum != null || classNum != null){
            model.addAttribute("selectedCoz", cozNum);
            model.addAttribute("selectedClass", classNum);
        }
        model.addAttribute("allCourseAndClass", teacherControllerService.duplicateRemoval(courseList));
        log.debug("跳转到:teacher/deploy_homework.jsp");
        return "teacher/deploy_homework";
    }

    @RequestMapping(value = {"/teacher/account"}, method = RequestMethod.GET)
    public String toAccount() {
        log.debug("跳转到:teacher/account.jsp");
        return "teacher/account";
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/newTask"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map newTask(@RequestParam String taskName, @RequestParam String description,
                       @RequestParam Timestamp date, @RequestParam List<Integer> clazzId,
                       @RequestParam List<Integer> courseId, @Nullable String enclosure, HttpSession session){
        Map<String, Object> json = new HashMap<>(2);
        int clazzSize = clazzId.size();
        if(clazzId.size() != courseId.size()){
            json.put("msg", "提交失败！参数错误！");
            json.put("status", -1);
            return json;
        }
        String decPath = SaveToHtmlUtils.saveContentToHtml(session, description);
        Timestamp pubTime = new Timestamp(System.currentTimeMillis());
        for (int i = 0; i < clazzSize; i++) {
            TaskEntity newTask = new TaskEntity();
            newTask.setTaskName(taskName);
            newTask.setTaskDesc(decPath);
            newTask.setPubTime(pubTime);
            newTask.setDeadline(date);
            ClassEntity clazz = new ClassEntity();
            clazz.setClassId(clazzId.get(i));
            CourseEntity coz = new CourseEntity();
            coz.setClassEntity(clazz);
            coz.setCozId(courseId.get(i));
            newTask.setCozEntity(coz);
            newTask.setTaskFiles(enclosure);
            teacherControllerService.insertTask(newTask);
        }
        json.put("msg", "提交成功！");
        json.put("status", 0);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/task_table_json"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object> taskTableData(String cozName, Model model){
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity)(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List list = teacherControllerService.getCourseByTeacherNum(tc.getTcNum());
        //noinspection unchecked
        List<CourseEntity> cozList = (List<CourseEntity>) teacherControllerService.groupByCozName(list).get(cozName);
        List<Map> data = new ArrayList<>();
        for (CourseEntity c : cozList){
            Map<String, Object> m = c.toMap();
            Set tasks = (Set)(m.get("taskSet"));
            Map<String, Object> taskCount = taskService.getTaskClosedAndNotClosedCount(tasks);
            m.put("taskCount", taskCount);
            m.put("classNum", c.getClassEntity().getClassNum());
            m.remove("taskSet");
            data.add(m);
        }
        json.put("data", data);
        json.put("code", 0);
        json.put("count", data.size());
        json.put("msg", "");
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/upload"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String, Object> json = new HashMap<>(2);
        String path = UploadFileUtils.uploadFile(session, file, "files/");
        // TODO
        System.out.println("上传文件被保存的路径：" + path);
        json.put("code", 0);
        json.put("path", path+ConstValues.FILE_PATH_SEPARATOR);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/layuiUploadImg"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map layuiUploadImg(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String, Object> json = new HashMap<>(3);
        Map<String, Object> data = new HashMap<>(1);
        String path = UploadFileUtils.uploadFile(session, file, "files/");
        String realPath = UploadFileUtils.getFileRealPath(path);
        data.put("src", "../uploaded/" + realPath);
        data.put("title", UploadFileUtils.getFileRealName(path));
        json.put("code", 0);
        json.put("msg", "");
        json.put("data", data);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/md5check"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map md5Check(@RequestParam String md5, HttpSession session){
        Map<String, Object> json = new HashMap<>(2);
        String path = UploadFileUtils.getFilePathIfExist(session, md5);
        // TODO
        System.out.println("请求md5：" + md5);
        if(path != null){
            json.put("code", 0);
        }else{
            json.put("code", 1);
        }
        json.put("path", path);
        return json;
    }

}
