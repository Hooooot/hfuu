package com.hfuu.web.listener;

import com.hfuu.web.others.ConstValues;
import com.hfuu.web.others.utils.UploadFileUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

/**
 * @author :whh0987@foxmail.com
 * @Description :HttpSession监听器
 * @date :2019/10/20 22:14
 * 最后修改时间：
 * 最后修改人：
 */
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        // 设置session超时时间，单位秒
        session.setMaxInactiveInterval(900);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        Map<String, String> tempFilePath = (Map<String, String>)session.getAttribute(ConstValues.TEMP_FILE_PATH);
        if (tempFilePath != null) {
            for (Map.Entry<String, String> en : tempFilePath.entrySet()) {
                UploadFileUtils.deleteFiles(session, en.getValue());
            }
        }
    }
}
