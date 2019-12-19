package com.hfuu.web.service.teacher;

import com.hfuu.web.others.utils.EmailUtils;
import org.junit.Test;

import javax.mail.MessagingException;

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
        try{
            EmailUtils.sendEmail("主题", "<h1>123456</h1>", "2446926687@qq.com");
        }catch (MessagingException me){
            System.out.println(me.getNextException().getMessage());
        }
    }

    @Test
    public void icode() {
        String ic = EmailUtils.getIdentifyingCode(20);
        System.out.println(ic);
    }
}
