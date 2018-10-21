package ass.management.admin.test.elasticsearch.exceldatacomparison;

import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.client.EsClient;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import ass.management.elasticsearch.entity.datacomparison.BenchmarkUser4AData;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.service.Es6ServiceImpl;
import ass.management.elasticsearch.util.CustomParamUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BenchmarkUser4AEs6 {

    @Autowired
    EsClient esClient;

    @Autowired
    Es6ServiceImpl es6ServiceImpl;


    @Test
    public void save() throws Exception {
        BenchmarkUser4AData benchmarkUser4AData = new BenchmarkUser4AData();
        benchmarkUser4AData.setId("123123");
        benchmarkUser4AData.setUserId("7d3d961eb37d498082755689e9d6cc0a");
        benchmarkUser4AData.setUserName("杨娟");
        es6ServiceImpl.createIndexDoc(BenchmarkUser4AData.class, benchmarkUser4AData);
        Thread.currentThread().sleep(1000);
    }




    @Test
    public void getById() throws Exception {
        RestResult<EquipmentData> restResult = es6ServiceImpl.getById(EquipmentData.class, "61d533a353624e03bcff1aae1a748d5e");
        EquipmentData equipmentData = restResult.getData();
        System.out.println(equipmentData);
//        if(equipmentData.getColumn10() == null){
//            System.out.println("Column10 is null");
//        }
    }

    @Test
    public void getByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchTermByFiled(EquipmentData.class,
                "equipment_id", "8588ceaf5d70499e93fb1f824bc85ba1",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

    @Test
    public void getMatchByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchMatchByField(EquipmentData.class,
                "remarks", "seven go",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

//    @Test
//    public void update() throws Exception {
//        EquipmentData equipmentData = new EquipmentData();
//        equipmentData.setDbId("000");
//        equipmentData.setCreateBy("001");
//        equipmentData.setUpdateBy("001");
//        equipmentData.setCreateDate("2018-08-16 13:30:45");
//        equipmentData.setUpdateDate("2018-08-16 15:40:50");
//        equipmentData.setEquipmentId("8588ceaf5d70499e93fb1f824bc85ba1");
//        equipmentData.setEquipmentCode("8588ceaf5d70499e93fb1f824bc85ba1");
//        equipmentData.setColumn1("kkk");
//        equipmentData.setColumn1("mmm");
//
//        equipmentData.setEsId("WgCOTGUBr_Mjdtnuyij5");
//        es6ServiceImpl.upDateIndexDoc(EquipmentData.class, equipmentData);
//        Thread.currentThread().sleep(1000);
//    }
//    @Test
//    public void delete() throws Exception {
//        es6ServiceImpl.deleteIndexDoc(EquipmentData.class, "WwC_TGUBr_MjdtnuNyhG");
//        Thread.currentThread().sleep(1000);
//    }
//    @Test
//    public void processDocBulk() throws Exception {
//        List<EsBaseEntity> updateList = new ArrayList();
//        EquipmentData equipmentData = new EquipmentData();
//        equipmentData.setDbId("002");
//        equipmentData.setCreateBy("002");
//        equipmentData.setUpdateBy("002");
//        equipmentData.setCreateDate("2018-08-16 13:30:45");
//        equipmentData.setUpdateDate("2018-08-16 15:40:50");
//        equipmentData.setEquipmentId("eeeeeeeeeeeeeeeeee");
//        equipmentData.setEquipmentCode("eeeeeeeeeeeeeeeeee");
//        equipmentData.setColumn1("kkk");
//        equipmentData.setColumn1("mmm");
//        equipmentData.setEsId("WgCOTGUBr_Mjdtnuyij5");
//        updateList.add(equipmentData);
//
//        List<String> deleteList = new ArrayList();
//        deleteList.add("XADWTGUBr_Mjdtnu1yjf");
//
//        es6ServiceImpl.processDocBulk(EquipmentData.class, null, updateList, deleteList);
//        Thread.currentThread().sleep(1000);
//    }


    /**
     *
     {
         "esPageInfo":{"pageNum":1,"pageSize":10,"pageStart":0},
         "shouldTerm":{"create_date":["2018-08-06","2018-08-09"]},
         "range":{"create_date":["2018-08-06 14:40:07","2018-08-17"]},
         "term":{"equipment_id":"8588ceaf5d70499e93fb1f824bc85ba1"}
     }
     */
    @Test
    public void pageQueryRequest() throws Exception {
//        Map<String, Object> termMap = new HashMap<>();
//        termMap.put("equipment_id", "8588ceaf5d70499e93fb1f824bc85ba1");

//        Map<String, Object[]> rangeMap = new HashMap<>();
//        Object[] obj = new Object[2];
////        obj[0] = "2018-08-06";
//        obj[0] = "2018-08-06 15:40:07";
//        obj[1] = "2018-08-08 13:30:45";
//        rangeMap.put("update_date", obj);

//        Map<String, Object[]> shouldMap = new HashMap<>();
//        Object[] objShould = new Object[2];
//        objShould[0] = "2018-08-06";
//        objShould[1] = "2018-08-09";
//        shouldMap.put("update_date", objShould);

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(EquipmentData.class);
        queryEntry.getEsPageInfo().setPageSize(4);
        queryEntry.getEsPageInfo().setPageNum(2);

//        queryEntry.setTerm(termMap);
//        queryEntry.setRange(rangeMap);
//        queryEntry.setShouldTerm(shouldMap);

        String str = JSON.toJSONString(queryEntry);
        System.out.println(str);

        RestResult<PageUtils<EquipmentData>> restResult = es6ServiceImpl.pageQueryRequest(queryEntry);
        System.out.println(restResult);
    }


    @Test
    public void searchMatchScrollByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchMatchScrollByField(EquipmentData.class,
                "remarks", "go", 1);

        System.out.println(restResult.getData().size() + "___" + restResult.getData());
    }


    @Test
    public void aggQueryRequest() throws Exception {

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(EquipmentData.class);
        queryEntry.setSortField("update_date");

        String str = JSON.toJSONString(queryEntry);
        System.out.println(str);

        AggQueryEntry aggQueryEntry = new AggQueryEntry();

        AggQueryEntry.AggQueryEntryType maxByUpdateDate = aggQueryEntry.new AggQueryEntryType();
        maxByUpdateDate.setGroupName("max_by_field");
        maxByUpdateDate.setFieldName("count");
        maxByUpdateDate.setAggType(EsConfig.AggQuery.MAX);

        aggQueryEntry.getAggQueryList().add(maxByUpdateDate);

        RestResult<List<AggResultEntry>> restResult = es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
        System.out.println("!!!!!!!!!!!!!!!!!" + restResult.getData());
        List<AggResultEntry> list = restResult.getData();
        for(AggResultEntry aggResultEntry : list){
            System.out.println(aggResultEntry);
        }
    }

    @Test
    public void aggQueryRequest2() throws Exception {
        QueryEntry<EquipmentData> queryEntry = new QueryEntry();
        queryEntry.setTClass(EquipmentData.class);
        AggQueryEntry aggQueryEntry = CustomParamUtils.getAggQueryEntry(EquipmentData.class);
        RestResult<List<AggResultEntry>> restResult = es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
        System.out.println("!!!!!!!!!!!!!!!!!" + restResult.getData());
        List<AggResultEntry> list = restResult.getData();
        for(AggResultEntry aggResultEntry : list){
            System.out.println(aggResultEntry);
        }
    }

}
