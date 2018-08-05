//package ass.management.security.common.config.initconfig;
//
//import ass.management.security.common.executor.DefaultExecutors;
//import org.springframework.stereotype.Component;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//@Component
//public class InitializationConfiguration {
//
//    @PostConstruct
//    public void init(){
//        DefaultExecutors.getDefaultExecutors();
//    }
//
//    @PreDestroy
//    public void dostory(){
//        DefaultExecutors.getDefaultExecutors().shutdown();
//    }
//
//}
