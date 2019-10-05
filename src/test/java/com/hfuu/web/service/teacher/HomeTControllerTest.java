package com.hfuu.web.service.teacher;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description : 测试类
 * @date : 2019年10月5日 00点33分
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class HomeTControllerTest {
    @Resource
    private TeacherControllerService teacherControllerService;

    @Test
    public void test() {
        Map map = teacherControllerService.getCourseByTeacherNum("1604012003");
        System.out.println(map.get("result"));
        System.out.println("测试成功！");
    }
}