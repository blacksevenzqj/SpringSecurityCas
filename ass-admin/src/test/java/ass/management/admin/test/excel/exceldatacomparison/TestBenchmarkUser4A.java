package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.model.datacomparison.BenchmarkUser4A;
import ass.management.admin.modules.business.exceldatacomparison.service.BenchmarkUser4AServiceImpl;
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
public class TestBenchmarkUser4A {

    @Autowired
    BenchmarkUser4AServiceImpl benchmarkUser4AServiceImpl;

    @Test
    public void benchmarkUser4A() {
        BenchmarkUser4A targetDataOne = new BenchmarkUser4A();
        targetDataOne.setUserId("666");
        targetDataOne.setHumanOrgId("666");
        benchmarkUser4AServiceImpl.saveOrUpDateTargetDataOne(targetDataOne);
    }


    @Test
    public void queryBenchmarkUser4A() {
        Map map = new HashMap<>();
        PageUtils<BenchmarkUser4A> page = benchmarkUser4AServiceImpl.targetDataOneQueryPageMap(map);
    }

}
