package com.hfuu.web.others.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

/**
 * @Description : 上传文件的工具类
 * @date :2019/10/17 17:05
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
public class UploadFileUtils {
    private static Logger log = Logger.getLogger(UploadFileUtils.class);

    /**
     * 上传文件的方法
     *
     * @param file controller接收到的文件
     * @param pathInUploaded file要保存到uploaded文件夹中的位置，不需带文件名
     * @param session HttpSession对象的实例
     * @return 上传文件的路径与带32位UUID的文件名（路径相对于uploaded文件夹）
     * */
    public static String uploadFile(HttpSession session, MultipartFile file, String pathInUploaded){
        String uploadedPath = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\";
        //  32位UUID防止出现文件名重复
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = uuid + "." + file.getOriginalFilename();
        try{
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadedPath + pathInUploaded, fileName));
        }catch (Exception e){
            log.error("文件上传异常", e);
        }
        return uploadedPath + fileName;
    }

    /**
     * 删除相对于uploaded文件夹的文件
     *
     * @param session HttpSession对象实例
     * @param filePath 带文件名的文件路径（相对于uploaded文件夹）
     * */
    public static void deleteFile(HttpSession session, String filePath){
        String uploadedPath = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\";
        try{
            FileUtils.forceDeleteOnExit(new File(uploadedPath + filePath));
        }catch (Exception e){
            log.error("删除文件失败", e);
        }
    }

    /**
     * 去除文件名里的UUID，返回文件名
     *
     * @param fileName 需要获取原始文件名的文件（可以带相对于uploaded文件夹的路径）
     * @return 文件原始的文件名
     * */
    public static String getFileRealName(String fileName){
        return fileName.substring(fileName.indexOf(".") + 1);
    }
}
