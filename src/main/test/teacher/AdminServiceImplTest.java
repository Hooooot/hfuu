package teacher;

import com.hfuu.web.entity.AdminEntity;
import com.hfuu.web.service.AdminService;
import com.hfuu.web.service.impl.AdminServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @Decription : teacher的测试类
 * @CreateDate : 2019年9月25日 14点04分
 * @author : whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 */
@ComponentScan
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring.xml", "classpath:springmvc.xml"}, classes = AdminServiceImpl.class)
class AdminServiceImplTest extends AdminServiceImpl{
    @Autowired
    private AdminService adminService;
    @Test
    void test() {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setAdminId(2);
        adminEntity.setAdminName("12311");
        Assert.assertNotNull(adminService);
        adminService.insert(adminEntity);
        System.out.println("测试成功");
    }
}