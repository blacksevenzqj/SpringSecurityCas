package ass.management.admin.common.aspect;


import ass.management.admin.common.annotation.EsBusiness;
import ass.management.admin.common.utils.BsEntity2EsEntity;
import ass.management.elasticsearch.service.EsServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ass.management.business.hot.entity.TodayNews;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(AspectOrderConfig.ELASTICSEARCH)
public class EsBusinessAspect {

    @Autowired
    EsServiceImpl esServiceImpl;

    @Pointcut("@annotation(ass.management.admin.common.annotation.EsBusiness)")
    public void savePointCut() {

    }
    @After("savePointCut()")
    public void after(JoinPoint joinPoint) throws Throwable {
        Signature sig = joinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = joinPoint.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        Class targetClass =  currentMethod.getAnnotation(EsBusiness.class).classType();
        String serviceUrl = currentMethod.getAnnotation(EsBusiness.class).serviceUrl();
        if(StringUtils.isBlank(serviceUrl)){
            throw new IllegalArgumentException("该注解缺少ServiceUrl参数");
        }
        Object[] objs = joinPoint.getArgs();
        TodayNews todayNews = (TodayNews)objs[0];
        if(currentMethod.getAnnotation(EsBusiness.class).save()){
            esServiceImpl.createIndexDoc(targetClass, BsEntity2EsEntity.TodayNews2EsHotNew(todayNews, serviceUrl));
        }else if(currentMethod.getAnnotation(EsBusiness.class).update()){
            esServiceImpl.upDateIndexDoc(targetClass, BsEntity2EsEntity.TodayNews2EsHotNew(todayNews, serviceUrl));
        }else if(currentMethod.getAnnotation(EsBusiness.class).delete()){
            esServiceImpl.deleteIndexDoc(targetClass, BsEntity2EsEntity.TodayNews2EsHotNew(todayNews, serviceUrl));
        }
    }


}
