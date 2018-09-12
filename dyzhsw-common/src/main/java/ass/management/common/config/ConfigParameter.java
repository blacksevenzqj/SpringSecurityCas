package ass.management.common.config;

public interface ConfigParameter {

    interface Executor{
        String EXECUTOR_FACTORY_NAME = "executors.factoryName";

        String SYSTEM_FACTORY_NAME = "executorSystemFactoryName";

        String DISRUPTOR = "disruptor";
        String THREADPOOL = "threadPool";
        String FORKJOIN = "forkJoin";

    }

}
