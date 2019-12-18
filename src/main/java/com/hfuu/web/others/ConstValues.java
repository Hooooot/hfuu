package com.hfuu.web.others;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :经常用到的常量写在此处，以避免出现魔法值警告
 * @date :2019/10/16 1:29
 */
public class ConstValues {
    /**
     * 教师登录后保存到Session中的TeacherEntity实例名
     */
    public static final String TEACHER_LOGGED_IN_INSTANCE_NAME = "teacher";
    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "LOGIN_FAIL";

    /**
     * 上传文件的临时文件路径（保存到HttpSession的Key值）
     */
    public static final String TEMP_FILE_PATH = "tempFilePath";

    /**
     * 文件路径的分隔符
     */
    public static final String FILE_PATH_SEPARATOR = "|";

    /**
     * 文件名分隔符（服务器保存名:原始文件名）
     */
    public static final String FILE_NAME_SEPARATOR = ":";
}
