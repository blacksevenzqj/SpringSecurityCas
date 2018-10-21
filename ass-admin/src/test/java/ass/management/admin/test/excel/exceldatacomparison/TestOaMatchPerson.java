package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.model.datacomparison.OaMatchPerson;
import ass.management.admin.modules.business.exceldatacomparison.service.OaMatchPersonServiceImpl;
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
public class TestOaMatchPerson {

    @Autowired
    OaMatchPersonServiceImpl oaMatchPersonServiceImpl;

    @Test
    public void oaMatchPerson() {
        OaMatchPerson oaMatchPerson = new OaMatchPerson();
        oaMatchPerson.setFullName("123");
        oaMatchPersonServiceImpl.saveOrUpDateOaMatchPerson(oaMatchPerson);
    }


    @Test
    public void queryOaMatchPerson() {
        Map map = new HashMap<>();
        PageUtils<OaMatchPerson> page = oaMatchPersonServiceImpl.oaMatchPersonQueryPageMap(map);
    }

}
