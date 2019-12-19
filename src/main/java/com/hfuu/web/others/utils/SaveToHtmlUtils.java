package com.hfuu.web.others.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author 16688
 * @PackageName:com.hfuu.web.others.utils
 * @ClassName:SaveToHtmlUtils
 * @Description:
 * @autor:Starry the Night
 * @Date:2019/10/23 16:21
 */
public class SaveToHtmlUtils {
    private static Logger log = Logger.getLogger(SaveToHtmlUtils.class);

    public static String saveContentToHtml(HttpSession session, String content) {
        return saveContentToHtml(session, content, "/richtext");
    }

    /**
     * @param session     HttpSession对象的实例
     * @param content     富文本里需要保存的内容
     * @param innerFolder uploaded文件夹下的文件夹名
     * @return 保存文件 的相对路径
     * @description : 富文本内容保存
     * @author : Starry the Night
     * @date : 2019/10/23 17:02
     * @since whh0987@foxmail.com于2019年12月19日 23点35分 重写，功能不变
     * */
    public static String saveContentToHtml(HttpSession session, String content, String innerFolder) {
        String uploadedPath = session.getServletContext().getRealPath("/") + "\\WEB-INF\\uploaded\\";
        //  32位UUID防止出现文件名重复
        String uuid = UUID.randomUUID().toString().replace("-", "");
        // 保存文件相对于uploaded的路径与文件名
        String htmlRelativePath = innerFolder + "\\" + uuid + ".html";
        // 保存文件存放路径
        String targetDir = uploadedPath + htmlRelativePath;
        try {
            //建立文件输出流
            FileOutputStream writerStream = new FileOutputStream(targetDir);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, StandardCharsets.UTF_8));
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //保存失败
            return null;
        }
        return htmlRelativePath;
    }

    /**
     * @param htmlPathAndName html文件 绝对路径
     * @return 返回文件内容
     * @Description : 指定html文件获取文件内容
     * @author : Starry the Night
     * @date : 2019/10/23 19:46
     */
    public static String getHtmlContent(String htmlPathAndName) {
        StringBuilder stringBuffer = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(htmlPathAndName), StandardCharsets.UTF_8));
            int tempChar;
            while ((tempChar = bufferedReader.read()) != -1) {
                stringBuffer.append((char) tempChar);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * @param htmlPathAndName html文件 绝对路径
     * @param content         修改文件内容
     * @Description :
     * @author: Starry the Night
     * @date : 2019/10/23 19:44
     */
    public static void modifyHtmlContent(String htmlPathAndName, String content) {
        String htmlContent = getHtmlContent(htmlPathAndName);
        htmlContent = htmlContent.replaceAll(htmlContent, content);
        try {
            //建立文件输出流
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(htmlPathAndName), StandardCharsets.UTF_8);
            oStreamWriter.append(htmlContent);
            oStreamWriter.close();
        } catch (Exception e) {

        }

    }
}
