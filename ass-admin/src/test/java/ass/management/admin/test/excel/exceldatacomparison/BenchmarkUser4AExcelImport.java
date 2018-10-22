package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.model.datacomparison.BenchmarkUser4A;
import ass.management.admin.common.excel.model.datacomparison.OaMatchPerson;
import ass.management.admin.common.excel.parsing.ExcelError;
import ass.management.admin.common.excel.result.ExcelExportResult;
import ass.management.admin.common.excel.result.ExcelImportResult;
import ass.management.admin.modules.business.exceldatacomparison.service.BenchmarkUser4AServiceImpl;
import ass.management.common.utils.beancopier.CachedBeanCopier;
import ass.management.db.utils.PageUtils;
import ass.management.elasticsearch.client.EsClient;
import ass.management.elasticsearch.common.EsConfig;
import ass.management.elasticsearch.common.RestResult;
import ass.management.elasticsearch.entity.base.EsBaseEntity;
import ass.management.elasticsearch.entity.base.EsPageInfo;
import ass.management.elasticsearch.entity.datacomparison.BenchmarkUser4AData;
import ass.management.elasticsearch.entity.group.EquipmentData;
import ass.management.elasticsearch.entity.search.AggQueryEntry;
import ass.management.elasticsearch.entity.search.AggResultEntry;
import ass.management.elasticsearch.entity.search.QueryEntry;
import ass.management.elasticsearch.service.Es6ServiceImpl;
import ass.management.elasticsearch.util.CharacterSegmentUtil;
import ass.management.elasticsearch.util.CustomParamUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BenchmarkUser4AExcelImport {

    @Autowired
    ExcelContext excelContext;

    @Autowired
    BenchmarkUser4AServiceImpl benchmarkUser4AServiceImpl;

    @Autowired
    EsClient esClient;

    @Autowired
    Es6ServiceImpl es6ServiceImpl;


    @Test
    public void benchmarkUser4A() {
        BenchmarkUser4A targetDataOne = new BenchmarkUser4A();
        targetDataOne.setUserId("666");
        targetDataOne.setHumanOrgId("666");
        benchmarkUser4AServiceImpl.saveOrUpDateBenchmarkUser4A(targetDataOne);
    }


    @Test
    public void queryBenchmarkUser4A() {
        Map map = new HashMap<>();
        PageUtils<BenchmarkUser4A> page = benchmarkUser4AServiceImpl.benchmarkUser4AQueryPageMap(map);
    }


    // 测试 将6万条数据进MYSQL库，实际中是进EL。
    @Test
    public void importBenchmarkUser4AToMysql() throws Exception{
        FileInputStream in = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\excel新\\4A基准用户_少量数据.xlsx"));
        ExcelImportResult excelImportResult = excelContext.readExcel(ExcelConfig.Bean.BENCH_MARK_USER_4A, 0, in,true);
        log.info(String.valueOf(excelImportResult.getHeader()));
        List<BenchmarkUser4A> benchmarkUser4AList = excelImportResult.getListBean();
        for(BenchmarkUser4A bnchmarkUser4A : benchmarkUser4AList){
            bnchmarkUser4A.setNewData(true);
            bnchmarkUser4A = benchmarkUser4AServiceImpl.saveOrUpDateBenchmarkUser4A(bnchmarkUser4A);
            log.info(String.valueOf(bnchmarkUser4A));
        }
        //通过导入结果集的hasErrors方法判断
        if(excelImportResult.hasErrors()){
            log.error("导入包含错误，下面是错误信息：");
            for (ExcelError err : excelImportResult.getErrors()) {
                log.error(err.getErrorMsg());
            }
        }
    }


//    @Test
//    public void exportOaMatchPerson() throws Exception{
//        Map map = new HashMap<>();
//        map.put("pageNum", 1);
//        map.put("pageSize", 2000);
//        PageUtils<OaMatchPerson> page = oaMatchPersonServiceImpl.oaMatchPersonQueryPageMap(map);
//        ExcelExportResult exportResult = excelContext.createExcelForPart(ExcelConfig.Bean.OA_MATCH_PERSON, page.getList());
//        OutputStream ops = new FileOutputStream("C:\\Users\\dell\\Desktop\\excel新\\oa剩余未匹配的人员导出数据_新导出.xlsx");
//        Workbook workbook = exportResult.build();
//        workbook.write(ops);
//        ops.close();
//        workbook.close();
//    }


    // EL部分
    // ================================================================================================================

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
    public void update() throws Exception {
        BenchmarkUser4AData benchmarkUser4AData = new BenchmarkUser4AData();

        es6ServiceImpl.upDateIndexDoc(BenchmarkUser4AData.class, benchmarkUser4AData);
        Thread.currentThread().sleep(1000);
    }
    @Test
    public void delete() throws Exception {
        es6ServiceImpl.deleteIndexDoc(BenchmarkUser4AData.class, "WwC_TGUBr_MjdtnuNyhG");
        Thread.currentThread().sleep(1000);
    }


    @Test
    public void importBenchmarkUser4AToEl() throws Exception{
        FileInputStream in = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\excel新\\4A基准用户_少量数据.xlsx"));
        ExcelImportResult excelImportResult = excelContext.readExcel(ExcelConfig.Bean.BENCH_MARK_USER_4A, 0, in,true);
        log.info(String.valueOf(excelImportResult.getHeader()));
        List<BenchmarkUser4A> benchmarkUser4AList = excelImportResult.getListBean();
        List<BenchmarkUser4AData> benchmarkUser4ADataCreateList = new ArrayList<>();
        for(BenchmarkUser4A bnchmarkUser4A : benchmarkUser4AList){
            bnchmarkUser4A.setNewData(true);
            log.info(String.valueOf(bnchmarkUser4A));
            BenchmarkUser4AData benchmarkUser4AData = new BenchmarkUser4AData();
            CachedBeanCopier.defaultCopy(bnchmarkUser4A, benchmarkUser4AData);
            String[] strs = CharacterSegmentUtil.SlashSegmentation(benchmarkUser4AData.getOrgPath(), CharacterSegmentUtil.REVERSE_SLANT);
            if(strs != null && strs.length > 0){
                for(int i=0,j=1; i < strs.length && j <= 10; i++,j++){
                    Method m = benchmarkUser4AData.getClass().getMethod("setOrgPath" + j, String.class);
                    m.invoke(benchmarkUser4AData, strs[i]);
                }
            }
            benchmarkUser4ADataCreateList.add(benchmarkUser4AData);
        }

        processDocBulk(benchmarkUser4ADataCreateList, null, null);

        //通过导入结果集的hasErrors方法判断
        if(excelImportResult.hasErrors()){
            log.error("导入包含错误，下面是错误信息：");
            for (ExcelError err : excelImportResult.getErrors()) {
                log.error(err.getErrorMsg());
            }
        }
    }
    private void processDocBulk(List<BenchmarkUser4AData> createList, List<BenchmarkUser4AData> updateList, List<String> deleteList) throws Exception {
        es6ServiceImpl.processDocBulk(BenchmarkUser4AData.class, createList, updateList, deleteList);
        Thread.currentThread().sleep(5000);
    }



    @Test
    public void getById() throws Exception {
        RestResult<BenchmarkUser4AData> restResult = es6ServiceImpl.getById(BenchmarkUser4AData.class, "1");
        BenchmarkUser4AData benchmarkUser4AData = restResult.getData();
        log.info(benchmarkUser4AData.toString());
    }

    @Test
    public void getByField() throws Exception {
        RestResult<List<BenchmarkUser4AData>> restResult = es6ServiceImpl.searchTermByFiled(BenchmarkUser4AData.class,
                "user_name", "杨娟",
                new EsPageInfo(), false, null, null);
        log.info(String.valueOf(restResult.getData()));
    }

    @Test
    public void getMatchByField() throws Exception {
        RestResult<List<BenchmarkUser4AData>> restResult = es6ServiceImpl.searchMatchByField(BenchmarkUser4AData.class,
                "remarks", "seven go",
                new EsPageInfo(), false, null, null);
        log.info(String.valueOf(restResult.getData()));
    }
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
        Map<String, Object> termMap = new HashMap<>();
        termMap.put("user_name", "杨娟");

//        Map<String, Object[]> rangeMap = new HashMap<>();
//        Object[] obj = new Object[2];
////        obj[0] = "2018-08-06";
//        obj[0] = "2018-08-06 15:40:07";
//        obj[1] = "2018-08-08 13:30:45";
//        rangeMap.put("update_date", obj);

        Map<String, Object[]> shouldMap = new HashMap<>();
        Object[] objShould = new Object[10];
        for(int i = 0,j=1; i < objShould.length && j <= 10; i++,j++){
            objShould[i] = "办公室（党委办公室、董事会办公室）";
            shouldMap.put("org_path" + j, objShould);
        }

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(BenchmarkUser4AData.class);
        EsPageInfo esPageInfo = new EsPageInfo();
        esPageInfo.setPageSize(2);
        esPageInfo.setPageNum(1);
        queryEntry.setEsPageInfo(esPageInfo);

        queryEntry.setTerm(termMap);
//        queryEntry.setRange(rangeMap);
//        queryEntry.setShouldTerm(shouldMap);
        queryEntry.setConstantScore(false);
        queryEntry.setSortState(false);

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
