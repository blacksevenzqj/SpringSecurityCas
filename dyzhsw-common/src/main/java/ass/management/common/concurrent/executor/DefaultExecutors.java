package ass.management.common.concurrent.executor;

import ass.management.common.config.ConfigParameter;
import ass.management.common.utils.JServiceLoader;
import ass.management.common.utils.SystemPropertyUtil;
import org.slf4j.LoggerFactory;

import static ass.management.utils.StackTraceUtil.stackTrace;


public class DefaultExecutors {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultExecutors.class);

    private CloseableExecutor executor;
    private CloseableExecutor secondExecutor;

    private DefaultExecutors(){
        factoryMethod();
    }

    private void factoryMethod(){
        String factoryName = SystemPropertyUtil.get(ConfigParameter.Executor.SYSTEM_FACTORY_NAME, ConfigParameter.Executor.DISRUPTOR);
        String secondFactoryName = SystemPropertyUtil.get(ConfigParameter.Executor.SYSTEM_SECOND_FACTORY_NAME, ConfigParameter.Executor.FORKJOIN);
        ExecutorFactory factory;
        ExecutorFactory secondFactory = null;
        try {
            factory = (ExecutorFactory) JServiceLoader.load(DefaultExecutorFactory.class)
                    .find(factoryName);
            if(ConfigParameter.Executor.DISRUPTOR.equalsIgnoreCase(factoryName)){ // 如果主线程框架为disruptor时，第二线程框架使用forkJoin。
                secondFactory = (ExecutorFactory) JServiceLoader.load(DefaultExecutorFactory.class).find(secondFactoryName);
            }
        } catch (Throwable t) {
            logger.warn("Failed to load default's executor factory [{}], cause: {}, " +
                    "[ThreadPoolExecutorFactory] will be used as default.", factoryName, stackTrace(t));

            factory = new ThreadPoolExecutorFactory();
        }

        executor = factory.newExecutor(ExecutorFactory.Target.DEFAULT, "default-processor");
        if(secondFactory != null){
            secondExecutor = secondFactory.newExecutor(ExecutorFactory.Target.DEFAULT, "second-processor");
        }
    }


    private static class DefaultExecutorsHandler{
        private static DefaultExecutors de = new DefaultExecutors();
    }


    public static CloseableExecutor getDefaultExecutors(){
        return DefaultExecutorsHandler.de.getCloseableExecutor();
    }
    private CloseableExecutor getCloseableExecutor(){
        return executor;
    }

    public static CloseableExecutor getSecondExecutors(){
        return DefaultExecutorsHandler.de.getSecondCloseableExecutor();
    }
    private CloseableExecutor getSecondCloseableExecutor(){
        return secondExecutor;
    }

}
