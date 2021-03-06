package ass.management.elasticsearch.service;

import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.annotation.Es6Index;
import ass.management.elasticsearch.client.EsClient;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.RestCodeEnum;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.config.ElasticsClientProperties;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultAll;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.util.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Es6ServiceImpl{

    @Autowired
    EsClient esClient;

    @Autowired
    ElasticsClientProperties elasticsClientProperties;

    /**
     * 新增索引
     */
    // 传入：子类POJO的Class
    public <T> RestResult<T> createIndexMapping(Class<T> tClass) {
        try{
            esClient.createIndexMapping(tClass);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            return RestResult.getFailResult(RestCodeEnum.NOT_FOUND);
        }
    }

// ***************************************************************************************************

    /**
     * 新增、修改、删除 文档：
     */
    // 新增文档：
    // 传入：子类POJO的Class
    public <T> RestResult<T> createIndexDoc(Class<T> tClass, T obj){
        try {
            IndexRequest indexRequest = new IndexRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName()
            ).source(EsUtils.Class2Array(obj));
            esClient.createIndexDoc(indexRequest);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"新增文档失败");
    }
    /**
     *  更新文档
     *  边界限定符
     */
    public <T extends EsBaseEntity> RestResult<T> upDateIndexDoc(Class<T> tClass, T obj){
        try {
            UpdateRequest updateRequest = new UpdateRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName(),
                    obj.getEsId()
            ).doc(EsUtils.Class2Array(obj));
            esClient.upDateIndexDoc(updateRequest);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"更新文档失败");
    }
    // 删除文档：
    // 传入：子类POJO的Class
    public <T> RestResult<T> deleteIndexDoc(Class<T> tClass, String esId) {
        try {
            DeleteRequest request = new DeleteRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName(),
                    esId);
            esClient.deleteIndexDoc(request);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"删除文档失败");
    }

    /**
     *  通配符：上界限定
     */
//    public <T> RestResult<T> processDocBulk(Class<T> tClass, List<? extends EsBaseEntity> createList, List<? extends EsBaseEntity> upDateList, List<String> deleteList){
//        BulkRequest request = new BulkRequest();
//        try {
//            if(createList != null && !createList.isEmpty()) {
//                for (EsBaseEntity obj : createList) {
//                    IndexRequest indexRequest = new IndexRequest(
//                            tClass.getAnnotation(Es6Index.class).indexName(),
//                            tClass.getAnnotation(Es6Index.class).typeName()
//                    ).source(EsUtils.Class2Array(obj));
//                    request.add(indexRequest);
//                }
//            }
//            if(upDateList != null && !upDateList.isEmpty()) {
//                for (EsBaseEntity obj : upDateList) {
//                    UpdateRequest updateRequest = new UpdateRequest(
//                            tClass.getAnnotation(Es6Index.class).indexName(),
//                            tClass.getAnnotation(Es6Index.class).typeName(),
//                            obj.getEsId()
//                    ).doc(EsUtils.Class2Array(obj));
//                    request.add(updateRequest);
//                }
//            }
//            if(deleteList != null && !deleteList.isEmpty()) {
//                for (String esId : deleteList) {
//                    DeleteRequest deleteRequest = new DeleteRequest(
//                            tClass.getAnnotation(Es6Index.class).indexName(),
//                            tClass.getAnnotation(Es6Index.class).typeName(),
//                            esId);
//                    request.add(deleteRequest);
//                }
//            }
//            esClient.processDocBulk(request);
//            return RestResult.getSuccessResult();
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//        return RestResult.getFailResult(500,"新增文档失败");
//    }

    /**
     *  边界限定符
     */
    public <T extends EsBaseEntity> RestResult<T> processDocBulk(Class<T> tClass, List<T> createList, List<T> upDateList, List<String> deleteList){
        BulkRequest request = new BulkRequest();
        try {
            if(createList != null && !createList.isEmpty()) {
                for (EsBaseEntity obj : createList) {
                    IndexRequest indexRequest = new IndexRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName()
                    ).source(EsUtils.Class2Array(obj));
                    request.add(indexRequest);
                }
            }
            if(upDateList != null && !upDateList.isEmpty()) {
                for (EsBaseEntity obj : upDateList) {
                    UpdateRequest updateRequest = new UpdateRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName(),
                            obj.getEsId()
                    ).doc(EsUtils.Class2Array(obj));
                    request.add(updateRequest);
                }
            }
            if(deleteList != null && !deleteList.isEmpty()) {
                for (String esId : deleteList) {
                    DeleteRequest deleteRequest = new DeleteRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName(),
                            esId);
                    request.add(deleteRequest);
                }
            }
            esClient.processDocBulk(request);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"新增文档失败");
    }

// ***************************************************************************************************

    /**
     * 查询
     */
    // 传入：子类POJO的Class
    public <T> RestResult<T> getById(Class<T> tClass, String id) {
        GetRequest getRequest = new GetRequest(
                tClass.getAnnotation(Es6Index.class).indexName(),
                tClass.getAnnotation(Es6Index.class).typeName(),
                id
        );
        return RestResult.getSuccessResult(esClient.getById(getRequest, tClass));
    }

    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchTermByFiled(Class<T> tClass, String fieldName, String field, EsPageInfo esPageInfo, boolean sortState, String orderField, String orderType) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.types(tClass.getAnnotation(Es6Index.class).typeName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery(fieldName, field));
        searchSourceBuilder.from(esPageInfo.getPageStart());
        searchSourceBuilder.size(esPageInfo.getPageSize());
        if(sortState) {
            searchSourceBuilder.sort(EsUtils.createSortBuilder(tClass, orderField, orderType));
        }
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.search(searchRequest, tClass));
    }

    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchMatchByField(Class<T> tClass, String fieldName, String field, EsPageInfo esPageInfo,  boolean sortState, String orderField, String orderType) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.types(tClass.getAnnotation(Es6Index.class).typeName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(fieldName, field));
        searchSourceBuilder.from(esPageInfo.getPageStart());
        searchSourceBuilder.size(esPageInfo.getPageSize());
        if(sortState) {
            searchSourceBuilder.sort(EsUtils.createSortBuilder(tClass, orderField, orderType));
        }
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.search(searchRequest, tClass));
    }

    // 综合条件查询：
    public  <T> RestResult<PageUtils<T>> pageQueryRequest(QueryEntry<T> queryEntry){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(queryEntry.getTClass().getAnnotation(Es6Index.class).indexName());
        searchRequest.types(queryEntry.getTClass().getAnnotation(Es6Index.class).typeName());
        boolean miniMumShouldMatchState = false;

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 结果开始处
        if(queryEntry.getEsPageInfo() != null) {
            searchSourceBuilder.from(queryEntry.getEsPageInfo().getPageStart());
            // 查询结果终止处
            searchSourceBuilder.size(queryEntry.getEsPageInfo().getPageSize());
        }
        // 查询的等待时间
        searchSourceBuilder.timeout(new TimeValue(elasticsClientProperties.getSearchSourceTimeOutSeconds(), TimeUnit.SECONDS));

        // bool，将查询合并
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();

        Map<String, Object> matchMap = queryEntry.getMatch();
        if(matchMap != null && !matchMap.isEmpty()){
            for (Map.Entry<String, Object> entry : matchMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null) {
                    MatchQueryBuilder  matchbuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
                    boolBuilder.must(matchbuilder);
                }
            }
        }

        Map<String, Object> termMap = queryEntry.getTerm();
        if(termMap != null && !termMap.isEmpty()){
            for (Map.Entry<String, Object> entry : termMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null) {
                    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(entry.getKey(), entry.getValue());
                    boolBuilder.filter(termQueryBuilder);
                }
            }
        }

        Map<String, Object[]> termsMap = queryEntry.getTerms();
        if(termsMap != null && !termsMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : termsMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery(entry.getKey(), entry.getValue());
                    boolBuilder.filter(termsQueryBuilder);
                }
            }
        }

        Map<String, Object[]> rangeMap = queryEntry.getRange();
        if(rangeMap != null && !rangeMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : rangeMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length == 2) {
                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(entry.getKey());
                    rangeQueryBuilder.gte(entry.getValue()[0]);
                    rangeQueryBuilder.lte(entry.getValue()[1]);
                    boolBuilder.filter(rangeQueryBuilder);
                }
            }
        }

        Map<String, Object> shouldTermMap = queryEntry.getShouldTerm();
        if(shouldTermMap != null && !shouldTermMap.isEmpty()){
            for (Map.Entry<String, Object> entry : shouldTermMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null ) {
                    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(entry.getKey(), entry.getValue());
                    boolBuilder.should(termQueryBuilder);
                    miniMumShouldMatchState = true;
                }
            }
        }

        Map<String, Object[]> shouldTermsMap = queryEntry.getShouldTerms();
        if(shouldTermMap != null && !shouldTermMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : shouldTermsMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    for(int i = 0; i < entry.getValue().length; i++){
                        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(entry.getKey(), entry.getValue()[i]);
                        boolBuilder.should(termQueryBuilder);
                        if(!miniMumShouldMatchState){
                            miniMumShouldMatchState = true;
                        }
                    }
                }
            }
        }
        if(miniMumShouldMatchState) {
            boolBuilder.minimumShouldMatch(elasticsClientProperties.getMiniMumShouldMatch());
        }

        // 排序
        if(queryEntry.isSortState()) {
            searchSourceBuilder.sort(EsUtils.createSortBuilder(queryEntry.getTClass(), queryEntry.getSortField(), queryEntry.getSortType()));
        }
        // 最外层是否使用 constant_score filter，默认为true（全局查询不参与评分）
        if(queryEntry.isConstantScore()){
            ConstantScoreQueryBuilder constantScoreQueryBuilder = QueryBuilders.constantScoreQuery(boolBuilder);
            searchSourceBuilder.query(constantScoreQueryBuilder);
        }else{
            searchSourceBuilder.query(boolBuilder);
        }
        log.info(searchSourceBuilder.toString());
        return RestResult.getSuccessResult(esClient.search(searchRequest.source(searchSourceBuilder), queryEntry));
    }


    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchMatchScrollByField(Class<T> tClass, String fieldName, String field, int pageSize) {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(elasticsClientProperties.getScrollTimeWindowMinutes()));
        SearchRequest searchRequest = new SearchRequest(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(fieldName, field));
        searchSourceBuilder.size(pageSize == 0 || pageSize < 0 ? 10 : pageSize);
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.searchScroll(searchRequest, tClass, scroll));
    }



    public <T> RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<T> queryEntry, AggQueryEntry aggQueryEntry){

        if(aggQueryEntry == null || aggQueryEntry.getAggQueryList().isEmpty()){
            return RestResult.getFailResult(500, "查询参数为NULL");
        }

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(queryEntry.getTClass().getAnnotation(Es6Index.class).indexName());
        searchRequest.types(queryEntry.getTClass().getAnnotation(Es6Index.class).typeName());

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(queryEntry.getEsPageInfo() != null) {
            // 结果开始处
            searchSourceBuilder.from(queryEntry.getEsPageInfo().getPageStart());
            // 查询结果终止处
            searchSourceBuilder.size(queryEntry.getEsPageInfo().getPageSize());
        }
        // 查询的等待时间
        searchSourceBuilder.timeout(new TimeValue(elasticsClientProperties.getSearchSourceTimeOutSeconds(), TimeUnit.SECONDS));

        BoolQueryBuilder boolBuilder = null;
        Map<String, Object[]> termsMap = queryEntry.getTerms();
        if(termsMap != null && !termsMap.isEmpty()){
            boolBuilder = QueryBuilders.boolQuery();
            for (Map.Entry<String, Object[]> entry : termsMap.entrySet()) {
                log.info("aggKey = " + entry.getKey() + ", aggValue = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery(entry.getKey(), entry.getValue());
                    boolBuilder.filter(termsQueryBuilder);
                }
            }
        }

//        MaxAggregationBuilder updateDateBuilder = AggregationBuilders.max("max_by_updateDate").field("updateDate");
//        TermsAggregationBuilder onlineBuilder = AggregationBuilders.terms("group_by_online").field("online");
//        onlineBuilder.subAggregation(updateDateBuilder);
//        TermsAggregationBuilder clientidBuilder = AggregationBuilders.terms("group_by_clientid").field("clientid");
//        clientidBuilder.subAggregation(onlineBuilder);

        AggregationBuilder finalAggregationBuilder;
        List<AggregationBuilder> aggregationBuilderList = new ArrayList<>();
        List<AggQueryEntry.AggQueryEntryType> aggQueryList = aggQueryEntry.getAggQueryList();
        for(AggQueryEntry.AggQueryEntryType aggQueryEntryType : aggQueryList){
            if(EsConfig.AggQuery.MAX.equalsIgnoreCase(aggQueryEntryType.getAggType())){
                MaxAggregationBuilder maxDateBuilder = AggregationBuilders.max(aggQueryEntryType.getGroupName()).field(aggQueryEntryType.getFieldName());
                aggregationBuilderList.add(maxDateBuilder);
            }else if(EsConfig.AggQuery.TERMS.equalsIgnoreCase(aggQueryEntryType.getAggType())){
                TermsAggregationBuilder termsBuilder = AggregationBuilders.terms(aggQueryEntryType.getGroupName()).field(aggQueryEntryType.getFieldName());
                aggregationBuilderList.add(termsBuilder);
            }
        }
        if(aggregationBuilderList.size() > 1) {
            for (int i = 0, j = 1; j < aggregationBuilderList.size(); i++,j++) {
                aggregationBuilderList.get(j).subAggregation(aggregationBuilderList.get(i));
            }
            finalAggregationBuilder = aggregationBuilderList.get(aggregationBuilderList.size()-1);
        }else{
            finalAggregationBuilder = aggregationBuilderList.get(0);
        }

        // 排序
        searchSourceBuilder.sort(EsUtils.createSortBuilder(queryEntry.getTClass(), queryEntry.getSortField(), queryEntry.getSortType()));
        searchSourceBuilder.query(boolBuilder);
        searchSourceBuilder.aggregation(finalAggregationBuilder);
        searchSourceBuilder.size(elasticsClientProperties.getAggSearchSourceSize());
        log.info(searchSourceBuilder.toString());

        AggResultAll temp = esClient.aggSearch(searchRequest.source(searchSourceBuilder));
        return RestResult.getSuccessResult(getResultAggResult(temp));
    }

    private List<AggResultEntry> getResultAggResult(AggResultAll temp){
        List<AggResultEntry> aggResultEntryList = new ArrayList<>();
        if(temp != null && !temp.getAgg().isEmpty()){
            List<AggResultAll> list = temp.getAgg();
            for(AggResultAll agg : list){
                if(StringUtils.isNoneBlank(agg.getKeyName())){
                    AggResultEntry aggResultEntry = new AggResultEntry();
                    aggResultEntry.setClientId(agg.getKeyName());
                    aggResultEntry.setClientCount(agg.getKeyCount());
                    aggResultEntryList.add(aggResultEntry);
                    if(!agg.getAgg().isEmpty()){
                        getResultAggSubResult(aggResultEntry, agg.getAgg());
                    }
                }
            }
        }else{
            AggResultEntry aggResultEntry = new AggResultEntry();
            aggResultEntry.setMaxFieldValue(temp.getKeyMaxValue());
            aggResultEntryList.add(aggResultEntry);
        }
        return aggResultEntryList;
    }

    private void getResultAggSubResult(AggResultEntry aggResultEntry, List<AggResultAll> list){
        for(AggResultAll agg : list){
            if(EsConfig.AggQuery.CustomizeGroupName.ON_LINE.equalsIgnoreCase(agg.getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOnLine(agg.getKeyCount());
            }else if(EsConfig.AggQuery.CustomizeGroupName.OFF_LINE.equalsIgnoreCase(agg.getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOffLine(agg.getKeyCount());
            }else if(EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE.equalsIgnoreCase(agg.getKeyName()) &&
                    EsConfig.AggQuery.CustomizeGroupName.ON_LINE.equalsIgnoreCase(agg.getParent().getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOnLineMaxUpDate(agg.getKeyMaxDate());
            }else if(EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE.equalsIgnoreCase(agg.getKeyName()) &&
                    EsConfig.AggQuery.CustomizeGroupName.OFF_LINE.equalsIgnoreCase(agg.getParent().getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOffLineMaxUpDate(agg.getKeyMaxDate());
            }
            if(!agg.getAgg().isEmpty()){
                getResultAggSubResult(aggResultEntry, agg.getAgg());
            }
        }
    }

}
