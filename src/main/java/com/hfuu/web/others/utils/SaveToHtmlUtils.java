package com.hfuu.web.others.utils;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.io.*;
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

    /**
     * @param session HttpSession对象的实例
     * @param content 富文本里需要保存的内容
     * @return 保存的文件名
     * @Description: 富文本内容保存
     * @Author: Starry the Night
     * @Date: 2019/10/23 17:02
     */
    public static String saveContentToHtml(HttpSession session, String content) {
        String uploadedPath = session.getServletContext().getRealPath("/") + "..\\..\\src\\main\\webapp\\WEB-INF\\";
        //  32位UUID防止出现文件名重复
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //保存文件名
        String htmlName = uuid + ".html";
        //模板路径
        String templatePath = uploadedPath + "template/template.html";
        //保存文件存放路径
        String targetDir = uploadedPath + "uploaded/richtext/" + htmlName;
        //模板里内容
        String templateContent = "";
        try {
            templateContent = getHtmlContent(templatePath);
            //把模板中内容替换成content中的内容
            templateContent = templateContent.replaceAll(templateContent, content);
            //建立文件输出流
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(targetDir), "utf-8");
            oStreamWriter.append(templateContent);
            oStreamWriter.close();

        } catch (Exception e) {
            //保存失败
            return null;
        }

        return htmlName;
    }

    /**
     * @Description: 指定html文件获取文件内容
     * @param htmlPathAndName  html文件 绝对路径
     * @Author: Starry the Night
     * @Date:  2019/10/23 19:46
     * @return 返回文件内容
     */
    public static String getHtmlContent(String htmlPathAndName) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(htmlPathAndName), "UTF-8"));
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
     * @Description:
     * @param htmlPathAndName html文件 绝对路径
     * @param content 修改文件内容
     * @Author: Starry the Night
     * @Date:  2019/10/23 19:44
     * @return void
     */
    public static void modifyHtmlContent(String htmlPathAndName,String content){
        String htmlContent=getHtmlContent(htmlPathAndName);
        htmlContent=htmlContent.replaceAll(htmlContent,content);
        try{
            //建立文件输出流
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(htmlPathAndName), "utf-8");
            oStreamWriter.append(htmlContent);
            oStreamWriter.close();
        }catch (Exception e){

        }

    }
}
