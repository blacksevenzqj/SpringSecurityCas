package ass.management.admin.test.thread.disruptor;

import ass.management.common.concurrent.executor.DefaultExecutors;
import ass.management.admin.modules.sys.service.SysUserServiceImpl;
import ass.management.common.concurrent.executor.CloseableExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisruptorTest {

    @Autowired
    SysUserServiceImpl sysUserServiceImpl;

    @Test
    public void test1() throws Exception{
        CloseableExecutor closeableExecutor = DefaultExecutors.getDefaultExecutors();
        for(int i = 0; i < 10; i++){
            Runnable runnable = new RunableTest(sysUserServiceImpl, 8L);
            closeableExecutor.execute(runnable);
        }

        Thread.currentThread().sleep(1000);
    }

}
