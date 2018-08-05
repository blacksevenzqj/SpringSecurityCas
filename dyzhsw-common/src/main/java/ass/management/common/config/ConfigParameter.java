package ass.management.common.config;

public interface ConfigParameter {

    interface Executor{
        String EXECUTOR_FACTORY_NAME = "executors.factoryName";

        String DEFAULT_FACTORY_NAME = "executor.factory.default.factory_name";

        String DISRUPTOR = "disruptor";
        String THREADPOOL = "threadPool";

    }

}
