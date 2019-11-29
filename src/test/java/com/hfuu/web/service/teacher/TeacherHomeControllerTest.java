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
import java.util.List;

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
        List<StudentEntity> list = teacherControllerService.getStudents("1604012003", "191", "1704092", 10);

        for(StudentEntity s : list){
            System.out.println(s);
        }

    }

    @Test
    @Transactional()
    public void insert(){
        SubmitEntity submitEntity = new SubmitEntity();
        StudentEntity stu = new StudentEntity();
        stu.setStuNum("1604012011");
        TaskEntity task = new TaskEntity();
        task.setTaskId(14);
        submitEntity.setStuEntity(stu);
        submitEntity.setTaskEntity(task);
        submitEntity.setSubId(12);
        submitEntity.setSubTime(new Timestamp(System.currentTimeMillis()));
        submitDao.insert(submitEntity);

    }
}