package ass.management.admin.common.concurrent.executor;

import ass.management.common.config.ConfigParameter;
import ass.management.common.concurrent.executor.CloseableExecutor;
import ass.management.common.concurrent.executor.DefaultExecutorFactory;
import ass.management.common.concurrent.executor.ExecutorFactory;
import ass.management.common.concurrent.executor.ThreadPoolExecutorFactory;
import ass.management.common.utils.JServiceLoader;
import ass.management.common.utils.SystemPropertyUtil;
import org.slf4j.LoggerFactory;

import static ass.management.utils.StackTraceUtil.stackTrace;


public class DefaultExecutors {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultExecutors.class);

    private CloseableExecutor executor;

    private DefaultExecutors(){
        factoryMethod();
    }

    private void factoryMethod(){
        String factoryName = SystemPropertyUtil.get(ConfigParameter.Executor.SYSTEM_FACTORY_NAME, ConfigParameter.Executor.DISRUPTOR);
        ExecutorFactory factory;
        try {
            factory = (ExecutorFactory) JServiceLoader.load(DefaultExecutorFactory.class)
                    .find(factoryName);
        } catch (Throwable t) {
            logger.warn("Failed to load default's executor factory [{}], cause: {}, " +
                    "[ThreadPoolExecutorFactory] will be used as default.", factoryName, stackTrace(t));

            factory = new ThreadPoolExecutorFactory();
        }

        executor = factory.newExecutor(ExecutorFactory.Target.DEFAULT, "default-processor");
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

}
