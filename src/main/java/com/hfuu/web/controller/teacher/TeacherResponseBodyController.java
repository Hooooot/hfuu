package com.hfuu.web.controller.teacher;

import com.hfuu.web.entity.*;
import com.hfuu.web.others.ConstValues;
import com.hfuu.web.others.utils.SaveToHtmlUtils;
import com.hfuu.web.others.utils.TermUtils;
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
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :2019/11/19 9:24
 */

@Controller
@RequestMapping("")
@SessionAttributes({"teacher"})
public class TeacherResponseBodyController {
    static private Logger log = Logger.getLogger(TeacherResponseBodyController.class);
    @Resource
    TeacherControllerService teacherControllerService;
    @Resource
    TaskService taskService;

    @ResponseBody
    @RequestMapping(value = {"/teacher/student_table_json"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object> studentTableData(int taskId, String clazzNum, Model model){
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity)(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List<StudentEntity> student = teacherControllerService.getStudents(tc.getTcNum(), TermUtils.getCurrentTerm(), clazzNum, taskId);
        List<Map> data = new ArrayList<>();
        for (StudentEntity s : student){
            Map<String, Object> m = s.toMap();
            //noinspection unchecked
            Set<SubmitEntity> submits = (Set)(m.get("submitSet"));
            m.put("status", 0);
            for (SubmitEntity sub : submits){
                if (sub.getTaskEntity().getTaskId() == taskId){
                    m.put("status", 1);
                    m.put("submit", sub.toMap());
                    break;
                }
            }
            m.remove("stuPw");
            m.remove("submitSet");
            data.add(m);
        }
        json.put("data", data);
        json.put("code", 0);
        json.put("count", data.size());
        json.put("msg", "");
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/task_table_json"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object> taskTableData(String cozName, Model model){
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity)(model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List list = teacherControllerService.getCourseByTeacherNumAndTerm(tc.getTcNum(), TermUtils.getCurrentTerm());
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
    @RequestMapping(value = {"/teacher/newTask"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> newTask(@RequestParam String taskName, @RequestParam String description,
                                       @RequestParam Timestamp date, @RequestParam List<String> clazzNum,
                                       @RequestParam List<String> courseNum, @Nullable String enclosure, HttpSession session){

        Map<String, Object> json = new HashMap<>(2);
        if(clazzNum.size() != courseNum.size()){
            json.put("msg", "提交失败！参数错误！");
            json.put("status", -1);
            return json;
        }
        TeacherEntity tc = (TeacherEntity) session.getAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        String decPath = SaveToHtmlUtils.saveContentToHtml(session, description);
        Timestamp pubTime = new Timestamp(System.currentTimeMillis());
        List courses = teacherControllerService.getCourseByClassNumAndTcNumDuringThisTerm(clazzNum, tc.getTcNum());
        for (Object cours : courses) {
            TaskEntity newTask = new TaskEntity();
            newTask.setTaskName(taskName);
            newTask.setTaskDesc(decPath);
            newTask.setPubTime(pubTime);
            newTask.setDeadline(date);
            // 设置新task的课程Id
            CourseEntity coz = (CourseEntity) cours;
            newTask.setCozEntity(coz);
            newTask.setTcEntity(tc);
            newTask.setTaskFiles(enclosure);
            teacherControllerService.insertTask(newTask);
        }
        json.put("msg", "提交成功！");
        json.put("status", 0);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/upload"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String, Object> json = new HashMap<>(2);
        String path = UploadFileUtils.uploadFile(session, file, "files/");
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
        if(path != null){
            json.put("code", 0);
        }else{
            json.put("code", 1);
        }
        json.put("path", path);
        return json;
    }

}
