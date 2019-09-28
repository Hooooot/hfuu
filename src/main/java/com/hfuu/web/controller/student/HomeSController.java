package com.hfuu.web.controller.student;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 16688
 */
@Controller
@RequestMapping("")
public class HomeSController {
    /**
     * 实验编辑页面图片上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"/upload"}, method = RequestMethod.POST, produces = "application/json;charset=utf8")
    public Map<String, Object> uploadFile(@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 获取项目路径
            String realPath = request.getSession().getServletContext()
                    .getRealPath("");
            // 获取图片文件名称
            String imgName = getFileName();


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

    private String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String str = RandomStringUtils.random(5,
                "abcdefghijklmnopqrstuvwxyz1234567890");
        String name = timeStr + str + ".jpg";
        return name;
    }
}
