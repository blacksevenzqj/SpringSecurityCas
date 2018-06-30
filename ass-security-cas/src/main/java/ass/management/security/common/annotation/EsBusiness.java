package ass.management.security.common.annotation;

import ass.management.elasticsearch.entity.EsHotNew;

import java.lang.annotation.*;

/**
 * Es的AOP注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsBusiness {

	Class classType() default EsHotNew.class;

	String serviceUrl() default "";

	boolean save() default false;

	boolean update() default false;

	boolean delete() default false;
}
