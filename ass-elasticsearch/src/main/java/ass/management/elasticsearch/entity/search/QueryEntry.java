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
    private Map<String, Object[]> terms;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object[]> range;

    // 一个字段一个查询值
    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.SHOULD)
    private Map<String, Object> shouldTerm;

    // 一个字段多个查询值
    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.SHOULD)
    private Map<String, Object[]> shouldTerms;

    private String sortField;

    private String sortType;

    private EsPageInfo esPageInfo;


    // 最外层是否使用 constant_score filter
    private boolean constantScore = true;
    // 排序
    private boolean sortState = true;

}
