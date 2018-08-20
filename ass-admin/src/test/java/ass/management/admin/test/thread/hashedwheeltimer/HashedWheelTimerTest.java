package ass.management.admin.test.thread.hashedwheeltimer;

import ass.management.admin.common.concurrent.hashedwheeltimer.watch.impl.VisaDelayMessageWatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HashedWheelTimerTest {

    @Autowired
    VisaDelayMessageWatch visaDelayMessageWatch;

    @Test
    public void test1() throws Exception{
        visaDelayMessageWatch.startDelayMessage("111");

        Thread.currentThread().sleep(6 * 60 * 1000);
    }

}
