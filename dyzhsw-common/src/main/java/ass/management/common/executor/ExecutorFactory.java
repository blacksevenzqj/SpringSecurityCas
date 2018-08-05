package ass.management.common.executor;

public interface ExecutorFactory extends DefaultExecutorFactory{

    CloseableExecutor newExecutor(Target target, String name);

    enum Target {
        DEFAULT
    }

    String DEFAULT_EXECUTOR_CORE_WORKERS           = "executor.factory.default.core.workers";
    String DEFAULT_EXECUTOR_MAX_WORKERS            = "executor.factory.default.max.workers";
    String DEFAULT_EXECUTOR_QUEUE_TYPE             = "executor.factory.default.queue.type";
    String DEFAULT_EXECUTOR_QUEUE_CAPACITY         = "executor.factory.default.queue.capacity";
    String DEFAULT_DISRUPTOR_WAIT_STRATEGY_TYPE    = "executor.factory.default.disruptor.wait.strategy.type";
    String DEFAULT_THREAD_POOL_REJECTED_HANDLER    = "executor.factory.default.thread.pool.rejected.handler";
    String DEFAULT_AFFINITY_THREAD                 = "executor.factory.affinity.thread";

}
