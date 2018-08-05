package ass.management.security.test.thread;

import ass.management.common.executor.CloseableExecutor;
import ass.management.security.common.executor.DefaultExecutors;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DisruptorTest {


    @Test
    public void test1() throws Exception{
        Runnable Runnable = new RunableTest();
        CloseableExecutor closeableExecutor = DefaultExecutors.getDefaultExecutors();
        closeableExecutor.execute(Runnable);

        Thread.currentThread().sleep(3000);
    }

}
