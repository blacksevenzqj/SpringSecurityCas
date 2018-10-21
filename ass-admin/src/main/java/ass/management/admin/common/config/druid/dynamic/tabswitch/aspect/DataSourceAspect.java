package ass.management.admin.common.config.druid.dynamic.tabswitch.aspect;

import ass.management.admin.common.aspect.AspectOrderConfig;
import ass.management.db.datasource.tabswitch.DynamicSwitchDataSourceGlobal;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import ass.management.db.datasource.tabswitch.DynamicSwitchDataSourceHolder;
import ass.management.db.datasource.tabswitch.annotation.DataSource;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 1、注解方式：
     */
    @Pointcut("@annotation(ass.management.db.datasource.tabswitch.annotation.DataSource)")
    public void annotationDataSourcePointCut() {
    }
    @Around("annotationDataSourcePointCut()")
    public Object annotationAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        DataSource ds = method.getAnnotation(DataSource.class);
        if(StringUtils.isBlank(ds.name())){
            DynamicSwitchDataSourceHolder.putDataSource(DynamicSwitchDataSourceGlobal.MANAGEMENT);
            logger.info("set datasource is " + DynamicSwitchDataSourceGlobal.MANAGEMENT.name());
        }else {
            DynamicSwitchDataSourceHolder.putDataSource(DynamicSwitchDataSourceGlobal.getByName(ds.name()));
            logger.info("set datasource is " + ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicSwitchDataSourceHolder.clearDataSource();
            logger.info("clean datasource");
        }
    }


    /**
     * 2、表达式方式：
     * 一、扫描：父类、子类
     * 1、如果 匹配 子类方法、并且匹配父类方法将会调用2次（子类方法中调用父类方法）。
     * 2、如果 匹配 子类继承于父类中的方法，则只会调用1次。（子类没有重写父类方法）。
     * 二、扫描：子类
     * 子类 没有复写 父类中的方法，那么 子类继承于父类中的方法不会被 匹配到，也就不会发生切面事件。
     */
    @Pointcut(
//        "execution(* ass.management.db.service.*.*(..)) || " +  // 不能匹配父类，否则 同样继承CrudService的MANAGEMENT系统库也会受影响
        "execution(* ass.management.admin.modules.business.airticket.service.*.*(..)) || " +
        "execution(* ass.management.admin.modules.business.visa.service.*.*(..)) || " +
        "execution(* ass.management.admin.modules.business.businesshelp.service.*.*(..)) || " +
        "execution(* ass.management.admin.modules.business.hot.service.*.*(..)) || " +
        "execution(* ass.management.admin.modules.business.advisory.service.*.*(..)) || " +
        "execution(* ass.management.admin.modules.business.school.service.*.*(..)) ||" +
        "execution(* ass.management.admin.modules.business.exceldatacomparison.service.*.*(..))"
    		)
    public void patternDataSourcePointCut() {

    }
    @Around("patternDataSourcePointCut()")
    public Object patternAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DynamicSwitchDataSourceHolder.putDataSource(DynamicSwitchDataSourceGlobal.BUSINESS);
        try {
            return point.proceed();
        } finally {
            DynamicSwitchDataSourceHolder.clearDataSource();
            logger.info("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return AspectOrderConfig.DATASOURCE;
    }
}
