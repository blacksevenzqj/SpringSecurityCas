//package ass.management.common.concurrent.pool.executorfactory;
//
//import ass.management.common.concurrent.pool.rejected.DefaultRejectedExecutionHandler;
//import ass.management.common.concurrent.pool.threadfactory.NamedThreadFactory;
//import lombok.extern.slf4j.Slf4j;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//public class DefaultThreadPoolExecutorFactory {
//
//    // 线程池维护线程的最少数量
//    private static int corePoolSize = 60;
//    // 线程池维护线程的最大数量
//    private static int maximumPoolSize = 100;
//    // 线程池维护线程所允许的空闲时间，单位MS，超时将会终止该线程
//    private static int keepAliveTime = 1000;
//    // 线程池队列大小
//    private static int queueSize = 100;
//
//    private ThreadPoolExecutor delayMessagePoolExecutor = null;
//
//    private DefaultThreadPoolExecutorFactory(){
//        factoryMethod();
//    }
//
//    private void factoryMethod(){
//        final int numberOfCores = Runtime.getRuntime().availableProcessors();
//        final double blockingCoefficient = 0.9;
//        final int pollSize = (int) (numberOfCores/(1 - blockingCoefficient));
//        //40个线程：CPU核心数/(1 - 阻塞系数)
//        if(pollSize > corePoolSize){
//            corePoolSize = pollSize;
//        }
//        delayMessagePoolExecutor = new ThreadPoolExecutor(
//                corePoolSize,
//                maximumPoolSize,
//                keepAliveTime,
//                TimeUnit.MILLISECONDS,
//                new ArrayBlockingQueue<Runnable>(queueSize),
//                new NamedThreadFactory("DelayMessage"),
//                new DefaultRejectedExecutionHandler("DelayMessage")
//        );
//    }
//
//    private ThreadPoolExecutor getDelayMessagePoolExecutor(){
//        return delayMessagePoolExecutor;
//    }
//
//    private static class ThreadControlHandler{
//        private static DefaultThreadPoolExecutorFactory tc = new DefaultThreadPoolExecutorFactory();
//    }
//
//
//    public static ThreadPoolExecutor getDelayMessageThreadPoolExecutor(){
//        return ThreadControlHandler.tc.getDelayMessagePoolExecutor();
//    }
//
//    public static void destory() {
//        ThreadControlHandler.tc.getDelayMessagePoolExecutor().shutdown();
//    }
//
//}
//
//
