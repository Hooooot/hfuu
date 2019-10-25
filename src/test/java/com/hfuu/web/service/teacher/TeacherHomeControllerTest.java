package com.hfuu.web.service.teacher;

import com.hfuu.web.dao.SubmitDao;
import com.hfuu.web.entity.StudentEntity;
import com.hfuu.web.entity.SubmitEntity;
import com.hfuu.web.entity.TaskEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
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
public class TeacherHomeControllerTest {
    @Resource
    private TeacherControllerService teacherControllerService;
    @Resource
    private SubmitDao submitDao;


    @Test
    public void test() {

    }

    @Test
    @Transactional()
    public void insert(){
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        submitDao.executeHql("update SubmitEntity s set s.subTime=? where s.subId=?",timestamp,31);

    }
}