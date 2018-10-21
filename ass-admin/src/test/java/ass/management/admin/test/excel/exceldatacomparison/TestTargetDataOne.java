package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.model.datacomparison.TargetDataOne;
import ass.management.admin.modules.business.exceldatacomparison.service.TargetDataOneServiceImpl;
import ass.management.db.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTargetDataOne {

    @Autowired
    TargetDataOneServiceImpl targetDataOneServiceImpl;

    @Test
    public void targetDataOne() {
        TargetDataOne targetDataOne = new TargetDataOne();
        targetDataOne.setUserId("666");
        targetDataOne.setHumanOrgId("666");
        targetDataOneServiceImpl.saveOrUpDateTargetDataOne(targetDataOne);
    }


    @Test
    public void queryTargetDataOne() {
        Map map = new HashMap<>();
        PageUtils<TargetDataOne> page = targetDataOneServiceImpl.targetDataOneQueryPageMap(map);
    }

}
