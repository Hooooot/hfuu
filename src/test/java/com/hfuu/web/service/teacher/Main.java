package com.hfuu.web.service.teacher;

import com.hfuu.web.others.utils.UploadFileUtils;
import org.junit.Test;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :2019/10/8 16:57
 */
public class Main {
    @Test
    public void test(){
        String s = UploadFileUtils.getFileRealName("files\\1234asd7a8sd78as.asd.txt");

//        String s =  System.getProperty("user.dir");
        System.out.println(s);
    }

}
