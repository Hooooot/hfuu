package com.hfuu.web.controller.student;

import com.hfuu.web.others.utils.SaveToHtmlUtils;
import com.hfuu.web.service.student.StudentControllerService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author :16688
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :
 */
@Controller
@RequestMapping("")
public class StudentController {
    @Resource
    StudentControllerService studentControllerService;

    /**
     * 获取项目路径
     *
     * @param request
     * @return
     */
    private static String getRealPath(HttpServletRequest request) {

        return request.getSession().getServletContext().getRealPath("");
    }

    /**
     * @Description: //保存实验
     * @param session session
     * @param content 实验编写的内容
     * @param taskId 任务id
     * @param stuNum 学生学号
     * @param subRichTextPath 实验保存路径
     * @param subId 提交id
     * @Author: Starry the Night
     * @Date:  2019/10/25 18:09
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping(value = {"/student/saveHtml"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> saveHtml(HttpSession session, String content, int taskId, String stuNum, String subRichTextPath,int subId) {
        Map<String, Object> result = new HashMap<>(2);
        String htmlName = null;



       if (subRichTextPath.length() == 0) {
            //第一次提交
            htmlName = SaveToHtmlUtils.saveContentToHtml(session, content);
            studentControllerService.updateSubRichTextPath(taskId, stuNum, htmlName);

        } else {
            //非第一次提交
            Timestamp subTime = new Timestamp(System.currentTimeMillis());
            studentControllerService.updateSubmitSubTime(subId,subTime);
            String htmlPathAndName = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\richtext\\" + subRichTextPath;
            SaveToHtmlUtils.modifyHtmlContent(htmlPathAndName, content);
        }

        result.put("success", "true");
        return result;
    }

    /**
     * @Description: 提交实验
     * @param session session
     * @param content 实验编写的内容
     * @param subRichTextPath 实验保存路径
     * @param subId 提交id
     * @Author: Starry the Night
     * @Date:  2019/10/25 18:13
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @ResponseBody
    @RequestMapping(value = {"/student/submitHtml"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> submitHtml(HttpSession session,String content, String subRichTextPath,int subId) {
        Map<String, Object> result = new HashMap<>(2);
        Timestamp subTime = new Timestamp(System.currentTimeMillis());
        studentControllerService.updateSubmitSubTimeAndSubState(subId,subTime,"待批阅");

        String htmlPathAndName = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\richtext\\" + subRichTextPath;
        SaveToHtmlUtils.modifyHtmlContent(htmlPathAndName, content);
        result.put("success", "true");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = {"/student/getContent"}, method = RequestMethod.POST)
    public Map<String, Object> getContentOfHtml(HttpSession session, String subRichTextPath) {
        Map<String, Object> result = new HashMap<>(2);

        String htmlPathAndName = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\richtext\\" + subRichTextPath;
        if (subRichTextPath.length() == 0) {
            result.put("data", " ");
        } else {
            String content = SaveToHtmlUtils.getHtmlContent(htmlPathAndName);
            result.put("data", content);
        }


        return result;
    }

    /**
     * File上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/student/uploadFile"}, method = RequestMethod.POST)
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>(4);
        String contentType = file.getContentType();

        result.put("code", 0);
        result.put("msg", "");
        result.put("count", 1);
        result.put("data", new String[]{});
        return result;
    }

    /**
     * 实验编辑页面图片上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/student/uploadImg"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> uploadImg(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(2);
        try {
            // 获取项目路径
            String realPath = getRealPath(request);
            // 获取图片文件名称
            String imgName = UUID.randomUUID() + ".jpg";


            //1.....获取项目路径（本地路径）
            //获取项目的上上一级目录
            String parentPath = new File(new File(realPath).getParent()).getParent();
            //设置上传图片的保存路径
            String saveLoaclPath = parentPath + "/src/main/webapp/WEB-INF/images/upload";
            File fileLocal = new File(saveLoaclPath);
            if (!fileLocal.exists() && !fileLocal.isDirectory()) {
                System.out.println(saveLoaclPath + "目录不存在，已自动创建");
                // 创建目录
                fileLocal.mkdir();
            }
            //获取文件流
            InputStream inputStreamLocal = multipartFile.getInputStream();
            File imgLocal = new File(saveLoaclPath, imgName);
            FileUtils.copyInputStreamToFile(inputStreamLocal, imgLocal);


            //2..... 服务器根目录下路径 文件夹upload，存放上传图片
            String savePath = realPath + "WEB-INF\\images\\upload";
            //获取文件流
            InputStream inputStream = multipartFile.getInputStream();
            File img = new File(savePath, imgName);
            FileUtils.copyInputStreamToFile(inputStream, img);
            inputStream.close();

            // 返回图片访问路径
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + request.getContextPath() + "/images/upload/" + imgName;
            // 返回给 wangEditor 的数据格式
            result.put("errno", 0);
            result.put("data", new String[]{url});
            return result;
        } catch (Exception e) {

        }
        return null;
    }

}
