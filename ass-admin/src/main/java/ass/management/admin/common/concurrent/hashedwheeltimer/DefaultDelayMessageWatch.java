package ass.management.admin.common.concurrent.hashedwheeltimer;

import ass.management.admin.common.concurrent.executor.DefaultExecutors;
import ass.management.common.concurrent.executor.CloseableExecutor;
import ass.management.admin.common.concurrent.hashedwheeltimer.delaymessage.DefaultDelayMessageThread;
import ass.management.common.concurrent.pool.threadfactory.NamedThreadFactory;
import ass.management.common.config.DefaultDelayMessageConfiguration;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class DefaultDelayMessageWatch<D extends DefaultBusinessService, E extends DefaultDelayMessageConfiguration> implements TimerTask {

    @Autowired
    D defaultBusinessService; // 注入Spring管理类单例：子类也将继承 本单例属性。
    public D getDefaultBusinessService() {
        return defaultBusinessService;
    }

    @Autowired
    E defaultDelayMessageConfig; // 注入Spring管理类单例：子类也将继承 本单例属性。
    public E getDefaultDelayMessageConfig() {
        return defaultDelayMessageConfig;
    }


    private static final String suo = "1";

    private static Timer timer = null; // static 属性：多个子类 都将 继承（共享） 本 static 属性。

    private static Map<Timeout, String> timeoutMap = new ConcurrentHashMap<Timeout, String>(); // static 属性：多个子类 都将 继承（共享） 本 static 属性。
    private static Map<String, Timeout> timeoutOrderMap = new ConcurrentHashMap<String, Timeout>(); // static 属性：多个子类 都将 继承（共享） 本 static 属性。

    @PostConstruct
    private void init(){
        if(timer == null) {
            synchronized (suo) {
                if (timer == null) {
                    timer = new HashedWheelTimer(new NamedThreadFactory("Hashedwheeltimer"), defaultDelayMessageConfig.getTickDurationSecond(),
                            TimeUnit.SECONDS, defaultDelayMessageConfig.getTicksPerWheelSecond());
                    log.info("DefaultDelayMessageWatch init() Running!!!=====================");
                }
            }
        }
    }

    @PreDestroy
    private void destory(){
        timer.stop();
        timeoutMap.clear();
        timeoutOrderMap.clear();
    }

    public void startDelayMessage(String orderNo){
        Timeout timeout = timer.newTimeout(this,
                    new BigDecimal(defaultDelayMessageConfig.getTimeOutSecond()).multiply(new BigDecimal(defaultDelayMessageConfig.getTimeOutHour())).longValue(),
                    TimeUnit.SECONDS);
        timeoutMap.put(timeout, orderNo);
        timeoutOrderMap.put(orderNo, timeout);
    }

    // 还没做全局数据锁。
    public boolean cancelDelayMessage(String orderNo){
        // TODO ... 全局数据锁
        try {
            Timeout timeout = timeoutOrderMap.get(orderNo);
            if(timeout != null){
                while (!timeout.cancel()) {
                    Thread.currentThread().sleep(10);
                }
                timeoutMap.remove(timeout);
                timeoutOrderMap.remove(orderNo);
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        DefaultDelayMessageThread thread = new DefaultDelayMessageThread(defaultBusinessService, timeout, timeoutMap, timeoutOrderMap);
        // 1：普通线程池
//        DefaultThreadPoolExecutorFactory.getDelayMessageThreadPoolExecutor().execute(thread);
        // 2：disruptor
        CloseableExecutor closeableExecutor = DefaultExecutors.getDefaultExecutors();
        closeableExecutor.execute(thread);
    }



    public Timer getTimer() {
        return timer;
    }
    public Map<Timeout, String> getTimeoutMap() {
        return timeoutMap;
    }
    public Map<String, Timeout> getTimeoutOrderMap() {
        return timeoutOrderMap;
    }
}
