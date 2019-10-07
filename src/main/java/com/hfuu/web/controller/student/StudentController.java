package com.hfuu.web.controller.student;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description :
 * @date :
 * @author :16688
 * 最后修改时间：
 * 最后修改人：
 */
@Controller
@RequestMapping("")
public class StudentController {

    /**
     * 获取项目路径
     * @param request
     * @return
     */
    private static String getRealPath(HttpServletRequest request){

        return request.getSession().getServletContext().getRealPath("");
    }

    @ResponseBody
    @RequestMapping(value = {"/saveHtml"}, method = RequestMethod.POST)
    public Map<String, Object> saveHtml(HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        String content=request.getParameter("content");//保存内容
        String experimentalName=request.getParameter("experimentalName");//实验名称
        String realPath=getRealPath(request);

        //1.....获取项目路径（本地路径）
        //获取项目的上上一级目录
        String parentPath = new File(new File(realPath).getParent()).getParent();

        String templateContent="";
        //filePath :设定的模板文件
        String filePath = parentPath + "/src/main/webapp/WEB-INF/experimental/template/template.html";
        //disrPath:生成html的存放路径
        String disrPath=parentPath + "/src/main/webapp/WEB-INF/experimental/";

       try{
            FileInputStream fileInputStream=new FileInputStream(filePath);//读取模板文件
            int length=fileInputStream.available();
            byte[] bytes=new byte[length];
            fileInputStream.read(bytes);
            fileInputStream.close();
            templateContent=new String(bytes);
            //把模板中###content###替换成content中的内容
            templateContent=templateContent.replaceAll("###content###",content);

            String fileame="test.html";
            fileame=disrPath+fileame;
            //建立文件输出流
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(fileame),"utf-8");
            oStreamWriter.append(templateContent);
            oStreamWriter.close();



        }catch(Exception e){

        }



        System.out.println(content);
        System.out.println(getRealPath(request));
        result.put("success","true");
        return result;
    }

    /**
     * File上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/uploadFile"}, method = RequestMethod.POST)
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file,
                                           HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        String contentType=file.getContentType();
        System.out.println(contentType);

        result.put( "code",0);
        result.put("msg","");
        result.put("count",1);
        result.put("data",new String[]{});
        return  result;
    }
    /**
     * 实验编辑页面图片上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/uploadImg"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> uploadImg(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 获取项目路径
            String realPath = getRealPath(request);
            // 获取图片文件名称
            String imgName = UUID.randomUUID()+".jpg";


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
