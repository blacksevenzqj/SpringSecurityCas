package ass.management.security.test.thread;

import ass.management.common.executor.CloseableExecutor;
import ass.management.security.common.executor.DefaultExecutors;
import ass.management.security.modules.sys.service.UserInfoServiceImpl;
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
    UserInfoServiceImpl userInfoServiceImpl;

    @Test
    public void test1() throws Exception{
        CloseableExecutor closeableExecutor = DefaultExecutors.getDefaultExecutors();
        for(int i = 0; i < 10; i++){
            Runnable runnable = new RunableTest(userInfoServiceImpl, "thinkgem");
            closeableExecutor.execute(runnable);
        }
    }

}
