package ass.management.elasticsearch.entity.search;

import ass.management.elasticsearch.annotation.EsBoolQuery;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import lombok.Data;

import java.util.Map;

@Data
public class QueryEntry<T> {

    private Class<T> tClass;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.MUST)
    private Map<String, Object> match;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object> term;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object[]> range;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.SHOULD)
    private Map<String, Object[]> shouldTerm;

    private String orderField;

    private String orderType;

    private EsPageInfo esPageInfo;

}
