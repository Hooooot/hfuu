package com.hfuu.web.others.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :2019/12/19 0:05
 */
public class EmailUtils {
    /**
     * 使用QQ邮箱发送邮件
     * @param subject  邮件主题（标题）
     * @param content  邮件内容（支持HTML）
     * @param receiver  收件人邮箱
     * @author whh0987@foxmail.com
     * @date 2019年12月19日 01点08分
     */
    public static void sendEmail(String subject, String content, String receiver) throws MessagingException {
        // TODO 请填写发件人的QQ邮箱
        String sender = "";
        // TODO 请填写发件人的QQ邮箱的SMTP令牌
        String token = "";

        Properties props = new Properties();
        // 设置用户的认证方式
        props.setProperty("mail.smtp.auth", "true");
        // 设置传输协议
        props.setProperty("mail.transport.protocol", "smtp");
        // QQ邮箱的SSL加密
        props.setProperty("mail.smtp.ssl.enable", "true");
        // 设置发件人的SMTP服务器地址
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        Session mailSession = Session.getInstance(props);

        // 设置session,和邮件服务器进行通讯。
        MimeMessage message = new MimeMessage(mailSession);
        // 设置邮件主题
        message.setSubject(subject);
        // 设置邮件正文
        message.setContent(content, "text/html;charset=UTF-8");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        InternetAddress address = new InternetAddress(sender);
        // 设置邮件发送者的地址
        message.setFrom(address);
        // 设置邮件接收方的地址
        message.setRecipients(Message.RecipientType.TO, receiver);
        message.saveChanges();
        Transport transport = mailSession.getTransport();
        transport.connect(sender, token);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    /**
     * 生成6位长度的邮箱验证码
     * @return 验证码
     * */
    public static String getIdentifyingCode() {
        return EmailUtils.getIdentifyingCode(6);
    }

    /**
     * 生成邮箱验证码
     * @param length 验证码长度
     * @return 验证码
     * */
    public static String getIdentifyingCode(int length) {
        StringBuilder number = new StringBuilder();
        String chars = "0123456789";
        Random r = new Random();
        for (int i = 0; i < length; ++i) {
            number.append(chars.charAt(r.nextInt(chars.length())));
        }
        return number.toString();
    }
}
