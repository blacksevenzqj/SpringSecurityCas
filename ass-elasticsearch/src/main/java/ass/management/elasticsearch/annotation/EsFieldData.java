package ass.management.elasticsearch.annotation;

import ass.management.elasticsearch.common.AnalyzerConfigEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsFieldData {

    String dataName() default "";

    AnalyzerConfigEnum analyzerType() default AnalyzerConfigEnum.IK;

    AnalyzerConfigEnum analyzerSearchType() default AnalyzerConfigEnum.IK_SEARCH;

}
