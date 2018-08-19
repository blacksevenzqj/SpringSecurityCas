//package ass.management.common.concurrent.pool.rejected;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.concurrent.RejectedExecutionHandler;
//import java.util.concurrent.ThreadPoolExecutor;
//
//@Slf4j
//public class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {
//
//    private String threadFactoryName;
//
//    public DefaultRejectedExecutionHandler(String customThreadFactoryName) {
//        this.threadFactoryName = customThreadFactoryName;
//    }
//
//    @Override
//    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//        log.info("线程池" + threadFactoryName + "状态已满,"
//                + "线程池中计划被执行的任务总数：" + executor.getTaskCount() + ","
//                + "执行完毕的任务数：" + executor.getCompletedTaskCount() + ","
//                + "线程池中同时存在最大线程数：" + executor.getLargestPoolSize() + ","
//                + "当前正在执行的任务数：" + executor.getActiveCount() + ","
//                + "线程池中当前线程数：" + executor.getPoolSize()
//        );
//        try {
//            // 核心改造点，由blockingqueue的offer改成put阻塞方法
//            executor.getQueue().put(r);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
