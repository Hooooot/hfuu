package com.hfuu.web.service.teacher;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Description : 测试类
 * @date : 2019年10月5日 00点33分
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:springmvc.xml", "classpath*:hibernate.cfg.xml"})
public class HomeTControllerTest {
    @Resource
    private TeacherControllerService teacherControllerService;
    @Test
    public void test() {
        Assert.assertNotNull(teacherControllerService);
        System.out.println("测试成功");
    }
}