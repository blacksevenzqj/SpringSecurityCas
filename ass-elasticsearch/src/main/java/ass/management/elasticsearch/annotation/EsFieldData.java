package ass.management.elasticsearch.annotation;

import ass.management.elasticsearch.common.AggDescEnum;
import ass.management.elasticsearch.common.AnalyzerConfigEnum;
import ass.management.elasticsearch.common.QueryDescEnum;
import ass.management.elasticsearch.common.SortDescEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsFieldData {

    String dataName() default "";

    String elName() default "";

    QueryDescEnum elQueryType() default QueryDescEnum.NULL;

    AggDescEnum elAggType() default AggDescEnum.NULL;

    SortDescEnum elSortType() default SortDescEnum.NULL;

    AnalyzerConfigEnum analyzerType() default AnalyzerConfigEnum.NULL;

    AnalyzerConfigEnum analyzerSearchType() default AnalyzerConfigEnum.NULL;

}
