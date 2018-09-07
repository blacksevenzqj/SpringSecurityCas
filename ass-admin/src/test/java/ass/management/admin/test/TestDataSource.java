package ass.management.admin.test;

import ass.management.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import ass.management.admin.modules.business.visa.service.VisaAdminServiceImpl;
import ass.management.admin.modules.sys.entity.SysLogEntity;
import ass.management.admin.modules.sys.entity.SysRoleEntity;
import ass.management.admin.modules.sys.entity.SysUserEntity;
import ass.management.admin.modules.sys.service.SysLogServiceImpl;
import ass.management.admin.modules.sys.service.SysRoleServiceImpl;
import ass.management.admin.modules.sys.service.SysUserServiceImpl;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataSource {

    @Autowired
    SysUserServiceImpl sysUserServiceImpl;

    @Autowired
    SysLogServiceImpl sysLogServiceImpl;

    @Autowired
    SysRoleServiceImpl sysRoleServiceImpl;

    @Autowired
    VisaAdminServiceImpl visaAdminServiceImpl;

    @Autowired
    @Qualifier(value = "redisUtils")
    RedisUtils redisUtils;


    @Test
    public void redis() {
        String str = redisUtils.get("test");
        if(str == null){
            System.out.println("result is null");
        }
        System.out.println("result is " + str);

//        redisUtils.set("test", "123");
    }

    @Test
    public void visa() {
//        BaseInformation baseInformation = new BaseInformation();
//        baseInformation.setName("666");
//        baseInformation.setDescription("999");
//        baseInformation.setComboContent("666666666");
//        visaAdminServiceImpl.saveBaseInformation(baseInformation);

//        System.out.println(visaAdminServiceImpl.getBaseInformationForJpa(1));
    }


    @Test
    public void queryAllMenuId() {
        List<Long> list = sysUserServiceImpl.queryAllMenuId(2L);
        for(Long l : list){
            log.info(l.toString());
        }
    }

    @Test
    public void sysUserTestSave() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserId(5L);
        sysUserEntity.setUsername("five");
        sysUserEntity = sysUserServiceImpl.save(sysUserEntity);
        log.info(sysUserEntity.toString());
//        sysUserServiceImpl.testSave();

    }


    @Test
    public void sysLogSave() {
        SysLogEntity sysLog = new SysLogEntity();
        sysLog.setUsername("3333");
        sysLog.setOperation("666");
        sysLogServiceImpl.save(sysLog);
    }

    @Test
    public void sysLogTestSave() {
        sysLogServiceImpl.testSave();
    }

    @Test
    public void sysRoleUpdate() {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setRoleId(1L);
//        sysRoleEntity.setRoleName("技术部_开发人员");
        sysRoleEntity.setRemark("sss");
        sysRoleServiceImpl.save(sysRoleEntity);
    }

}
