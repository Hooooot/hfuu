package com.hfuu.web.others.utils;

import com.hfuu.web.others.ConstValues;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description : 上传文件的工具类
 * @date :2019/10/17 17:05
 */
public class UploadFileUtils {
    private static Logger log = Logger.getLogger(UploadFileUtils.class);
    // 项目里的uploaded文件夹路径
    // private static String toUploadPath = "..\\..\\src\\main\\webapp\\WEB-INF\\uploaded\\";
    /**
     * target路径下的uploaded文件夹，写入此文件夹会自动刷新文件目录，防止出现idea不刷新导致文件404的bug
     * */
    private static String toUploadPath = "\\WEB-INF\\uploaded\\";

    /**
     * 上传文件的方法
     *
     * @param file           controller接收到的文件
     * @param pathInUploaded file要保存到uploaded文件夹中的位置，不需带文件名
     * @param session        HttpSession对象的实例
     * @return 上传文件被保存的路径与MD5文件名（格式：路径/MD5文件名:原文件名）
     */
    public static String uploadFile(HttpSession session, MultipartFile file, String pathInUploaded) {
        String uploadedPath = session.getServletContext().getRealPath("/") + toUploadPath;
        String md5 = null;
        try {
            md5 = DigestUtils.md5DigestAsHex(file.getBytes());
            // 用MD5做文件名
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadedPath + pathInUploaded, md5));
        } catch (Exception e) {
            log.error("文件上传异常", e);
        }
        return pathInUploaded + md5 + ConstValues.FILE_NAME_SEPARATOR + file.getOriginalFilename();
    }

    /**
     * 删除paths中所有的文件（paths中的路径以“|”(竖杠)隔开，且路径是相对于upload文件夹的）
     *
     * @param session  HttpSession对象实例
     * @param filePath 要删除的文件路径（paths中的路径以“|”(竖杠)隔开，且路径是相对于upload文件夹的）
     */
    public static void deleteFiles(HttpSession session, String filePath) {
        if (filePath == null || "".equals(filePath)) {
            return;
        }
        String uploadedPath = session.getServletContext().getRealPath("/") + toUploadPath;
        try {
            String[] paths = filePath.split(ConstValues.FILE_PATH_SEPARATOR);
            for (String path : paths) {
                FileUtils.forceDelete(new File(uploadedPath + getFileRealPath(path)));
            }
        } catch (Exception e) {
            log.error("删除文件失败", e);
        }
    }

    /**
     * 判断uploaded/files/文件夹下是否存在MD5为md5的文件，若存在则返回路径，否则返回null
     *
     * @param session HttpSession对象实例
     * @param md5     要查找的文件的MD5
     * @return 若存在则返回路径，否则返回null
     */
    public static String getFilePathIfExist(HttpSession session, String md5) {
        return getFilePathIfExist(session, md5, "files/");
    }

    /**
     * 判断uploaded/"innerFolder/"文件夹下是否存在MD5为md5的文件，若存在则返回路径，否则返回null
     * 用法{@link #getFilePathIfExist(HttpSession session, String md5)}
     *
     * @param session     HttpSession对象实例
     * @param md5         要查找的文件的MD5
     * @param innerFolder uploaded文件夹下的文件夹名
     * @return 若存在则返回路径，否则返回null
     */
    public static String getFilePathIfExist(HttpSession session, String md5, String innerFolder) {
        String uploadedPath = session.getServletContext().getRealPath("/") + toUploadPath + innerFolder;
        String[] fileName = new File(uploadedPath).list();
        if (fileName == null) {
            return null;
        }
        for (String s : fileName) {
            if (md5.equals(s)) {
                return innerFolder + md5;
            }
        }
        return null;
    }

    /**
     * 去除文件名里的UUID，并返回不包含路径的文件名
     *
     * @param fileName 需要获取原始文件名的文件（可以带相对于uploaded文件夹的路径）
     * @return 文件原始的文件名（不含路径）
     */
    public static String getFileRealName(String fileName) {
        return fileName.substring(fileName.indexOf(":") + 1).replace("|", "");
    }

    /**
     * 去除文件名里的UUID，并返回不包含路径的文件名
     *
     * @param fileNames 需要获取原始文件名的文件（可以带相对于uploaded文件夹的路径）
     * @return 文件原始的文件名（不含路径）
     */
    public static List<String> getFileRealNames(String fileNames) {
        String[] names = fileNames.split("\\|");
        List<String> realNames = new ArrayList<>();
        for (String name : names) {
            realNames.add(name.substring(name.indexOf(":") + 1));
        }
        return realNames;
    }

    /**
     * 返回文件的保存路径
     *
     * @param fileName 需要获取原始文件名的文件（可以带相对于uploaded文件夹的路径）
     * @return 文件原始的路径
     */
    public static String getFileRealPath(String fileName) {
        return fileName.substring(0, fileName.indexOf(':'));
    }

    /**
     * 返回文件的保存路径
     *
     * @param fileNames 需要获取原始文件名的文件（可以带相对于uploaded文件夹的路径）
     * @return 文件原始的路径
     */
    public static List<String> getFileRealPaths(String fileNames) {
        String[] names = fileNames.split("\\|");
        List<String> realNames = new ArrayList<>();
        for (String name : names) {
            realNames.add(name.substring(0, name.indexOf(':')));
        }
        return realNames;
    }
}
