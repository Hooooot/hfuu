package com.hfuu.web.controller.teacher;

import com.hfuu.web.entity.*;
import com.hfuu.web.others.ConstValues;
import com.hfuu.web.others.utils.*;
import com.hfuu.web.service.SubmitService;
import com.hfuu.web.service.TaskService;
import com.hfuu.web.service.TeacherService;
import com.hfuu.web.service.teacher.TeacherControllerService;
import org.apache.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
@SessionAttributes({"teacher", "forgetPasswordTeacher", "forgetPasswordEmailVerifyCode"})
public class TeacherResponseBodyController {
    static private Logger log = Logger.getLogger(TeacherResponseBodyController.class);
    @Resource
    TeacherControllerService teacherControllerService;
    @Resource
    TaskService taskService;
    @Resource
    SubmitService submitService;
    @Resource
    TeacherService teacherService;


    @ResponseBody
    @RequestMapping(value = {"/teacher/login"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> toHome(@RequestParam String tcNum, @RequestParam String pw, Model model) {
        Map<String, Object> json = new HashMap<>(2);
        TeacherEntity tc = teacherControllerService.login(tcNum, pw);
        if (tc != null) {
            model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, tc);
            json.put("code", 0);
            json.put("url", "teacher/home");
        }else{
            json.put("msg", "账号或密码错误！");
            json.put("code", 1);
        }
        log.debug("跳转到:teacher/home.jsp");
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/student_table_json"}, method = RequestMethod.GET, produces = "application/json;charset=utf8")
    public Map<String, Object> studentTableData(int taskId, String clazzNum, Model model) {
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity) (model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List<StudentEntity> student = teacherControllerService.getStudents(tc.getTcNum(), TermUtils.getCurrentTerm(), clazzNum, taskId);
        ArrayList<Map<String, Object>> data = new ArrayList<>();
        for (StudentEntity s : student) {
            Map<String, Object> m = s.toMap();
            //noinspection unchecked
            Set<SubmitEntity> submits = (Set<SubmitEntity>) (m.get("submitSet"));
            for (SubmitEntity sub : submits) {
                if (sub.getTaskEntity().getTaskId() == taskId) {
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
    public Map<String, Object> taskTableData(String cozName, Model model) {
        Map<String, Object> json = new HashMap<>(4);
        TeacherEntity tc = (TeacherEntity) (model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME));
        List<CourseEntity> list = teacherControllerService.getCourseByTeacherNumAndTerm(tc.getTcNum(), TermUtils.getCurrentTerm());
        List<CourseEntity> cozList = teacherControllerService.groupByCozName(list).get(cozName);
        List<Map<String, Object>> data = new ArrayList<>();
        for (CourseEntity c : cozList) {
            Map<String, Object> m = c.toMap();
            //noinspection rawtypes
            Set tasks = (Set) (m.get("taskSet"));
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
                                       @RequestParam List<String> courseNum, @Nullable String enclosure, HttpSession session) {
        Map<String, Object> json = new HashMap<>(2);
        if (clazzNum.size() != courseNum.size()) {
            json.put("msg", "提交失败！参数错误！");
            json.put("status", -1);
            return json;
        }
        TeacherEntity tc = (TeacherEntity) session.getAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        String decPath = SaveToHtmlUtils.saveContentToHtml(session, description);
        Timestamp pubTime = new Timestamp(System.currentTimeMillis());
        List<CourseEntity> courses = teacherControllerService.getCourseByClassNumAndTcNumDuringThisTerm(clazzNum, tc.getTcNum());
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
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        Map<String, Object> json = new HashMap<>(2);
        String path = UploadFileUtils.uploadFile(session, file, "files/");
        json.put("code", 0);
        json.put("path", path + ConstValues.FILE_PATH_SEPARATOR);
        return json;
    }

    /**
     * 富文本编辑器里的图片
     * */
    @ResponseBody
    @RequestMapping(value = {"/teacher/layuiUploadImg"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> layuiUploadImg(@RequestParam("file") MultipartFile file, HttpSession session) {

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
    @RequestMapping(value = {"/teacher/layuiUploadAvatar"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> layuiUploadAvatar(@RequestParam MultipartFile file, @RequestParam String tcNum, HttpSession session, Model model) {
        Map<String, Object> json = new HashMap<>(3);
        String path = UploadFileUtils.uploadFile(session, file, "avatars/");
        String realPath = UploadFileUtils.getFileRealPath(path);
        teacherService.update(tcNum, realPath);
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        tc.setTcAvatar(realPath);
        model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, tc);
        json.put("code", 0);
        json.put("msg", "上传成功！头像已更新！");
        json.put("url", realPath);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/md5check"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> md5Check(@RequestParam String md5, HttpSession session) {
        Map<String, Object> json = new HashMap<>(2);
        String path = UploadFileUtils.getFilePathIfExist(session, md5);
        if (path != null) {
            json.put("code", 0);
        } else {
            json.put("code", 1);
        }
        json.put("path", path);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/correct"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> correct(@RequestParam int subId, @RequestParam short score) {
        Map<String, Object> json = new HashMap<>(2);
        int num;
        try {
            num = submitService.updateScoreAndStateById(subId, score, "已批阅");
        } catch (Exception e) {
            json.put("code", 0);
            json.put("msg", "服务器内部错误！提交失败！");
            return json;
        }
        if (num == 1) {
            json.put("code", 1);
            json.put("msg", "提交成功！");

        } else {
            json.put("code", 0);
            json.put("msg", "提交失败！");
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/set_info"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> setInfo(@RequestParam String tcNum, @RequestParam String tcName,
                                       @RequestParam String sex, @RequestParam String tcPhone,
                                       @RequestParam String tcEmail, Model model) {
        Map<String, Object> json = new HashMap<>(3);
        teacherService.update(tcNum, tcName, sex, tcPhone, tcEmail);
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        tc.setTcName(tcName);
        tc.setTcSex(sex);
        tc.setTcPhone(tcPhone);
        tc.setTcEmail(tcEmail);
        model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, tc);
        json.put("code", 0);
        json.put("msg", "更新完成！");
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/get_verify_code"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> getVerifyCode(HttpSession session) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        String base64 = VerifyCodeUtils.getVerifyCodeImgBase64(session, verifyCode);
        session.setAttribute(ConstValues.FORGET_PWD_IC, verifyCode);
        Map<String, Object> json = new HashMap<>(2);
        json.put("base64", base64);
        json.put("code", 0);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/forget_pwd_next"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> forgetPwdNextStep(HttpSession session, @RequestParam String tcNum,
                                                 @RequestParam String tcEmail, @RequestParam String verifyCode) throws IOException {
        Map<String, Object> json = new HashMap<>(2);
        String verify = (String) session.getAttribute(ConstValues.FORGET_PWD_IC);
        if (!verify.equalsIgnoreCase(verifyCode)){
            json.put("code", 1);
            return json;
        }
        TeacherEntity tc = teacherService.getTeacherByTcNumAndTcEmail(tcNum, tcEmail);
        if(tc == null){
            json.put("code", 2);
            return json;
        }
        session.setAttribute(ConstValues.FORGET_PWD_TC, tc);
        json.put("url", "next_step");
        json.put("code", 0);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/send_email"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> sendEmail(Model model){
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.FORGET_PWD_TC);
        Map<String, Object> json = new HashMap<>(2);
        String identifyCode = EmailUtils.getIdentifyingCode();
        model.addAttribute(ConstValues.FORGET_PWD_EMAIL_IC, identifyCode);
        try{
            EmailUtils.sendEmail("作业提交系统验证码", "<p>用户您好!您本次申请的验证码为：<h1>"
                    + identifyCode + "</h1></p><p>请在15分钟内使用。</p><p>如若不是您操作的请忽略。</p>" +
                    "<p>此邮件由系统发出，请勿直接回复。</p>", tc.getTcEmail());
        }catch (MessagingException me){
            json.put("msg", "发送失败！原因：" + me.getNextException().getMessage());
            json.put("code", 1);
        }
        json.put("msg", "发送成功！");
        json.put("code", 0);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/check_email_ic"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> checkEmailIc(@RequestParam String emailIc, Model model){
        String ic = (String) model.asMap().get(ConstValues.FORGET_PWD_EMAIL_IC);
        Map<String, Object> json = new HashMap<>(2);
        if(ic.equals(emailIc)){
            json.put("url", "new_password");
            json.put("code", 0);
        }else{
            json.put("msg", "验证码错误！");
            json.put("code", 1);
        }
        return json;
    }

    /**
     * 个人资料里的更新密码
     * */
    @ResponseBody
    @RequestMapping(value = {"/teacher/change_password_check"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> changePasswordCheck(@RequestParam String verifyCode, @RequestParam String verifyCodeAgain,
                                              @RequestParam String oldPwd, Model model){
        Map<String, Object> json = new HashMap<>(2);
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        if(!tc.getTcPw().equals(oldPwd)){
            json.put("code", 3);
            json.put("msg", "原密码输入错误！");
            return json;
        }
        if(!verifyCode.equals(verifyCodeAgain)){
            json.put("code", 1);
            json.put("msg", "两次密码输入不一致！");
            return json;
        }
        if(teacherService.updatePassword(tc.getTcNum(), verifyCode)){
            tc.setTcPw(verifyCode);
            model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, tc);
            json.put("msg", "密码修改成功！");
            json.put("code", 0);
        }else{
            json.put("msg", "密码修改失败！");
            json.put("code", 2);
        }
        return json;
    }


    /**
     * 忘记密码的更新密码
     * */
    @ResponseBody
    @RequestMapping(value = {"/teacher/update_password"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> updatePassword(@RequestParam String verifyCode, @RequestParam String verifyCodeAgain, Model model){
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.FORGET_PWD_TC);
        Map<String, Object> json = new HashMap<>(2);
        if(!verifyCode.equals(verifyCodeAgain)){
            json.put("code", 1);
            json.put("msg", "两次密码输入不一致！");
            return json;
        }
        if(teacherService.updatePassword(tc.getTcNum(), verifyCode)){
            model.addAttribute(ConstValues.FORGET_PWD_TC, null);
            json.put("msg", "密码修改成功！");
            json.put("code", 0);
        }else{
            json.put("msg", "密码修改失败！");
            json.put("code", 2);
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = {"/teacher/logout"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> logout(@RequestParam String tcNum, Model model){
        Map<String, Object> json = new HashMap<>(2);
        TeacherEntity tc = (TeacherEntity) model.asMap().get(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME);
        if(tc == null){
            json.put("msg", "你尚未登录！");
            json.put("code", 2);
            return json;
        }
        if (tc.getTcNum().equals(tcNum)) {
            model.addAttribute(ConstValues.TEACHER_LOGGED_IN_INSTANCE_NAME, null);
            json.put("msg", "退出成功");
            json.put("code", 0);
        }else{
            json.put("msg", "退出登录失败，此账号似乎尚未登录或登录已过期！");
            json.put("code", 1);
        }
        return json;
    }
}
