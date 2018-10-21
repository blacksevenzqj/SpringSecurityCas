package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.model.datacomparison.BenchmarkUser4A;
import ass.management.admin.common.excel.model.datacomparison.OaMatchPerson;
import ass.management.admin.common.excel.parsing.ExcelError;
import ass.management.admin.common.excel.result.ExcelExportResult;
import ass.management.admin.common.excel.result.ExcelImportResult;
import ass.management.admin.modules.business.exceldatacomparison.service.BenchmarkUser4AServiceImpl;
import ass.management.db.utils.PageUtils;
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
    public void importOaMatchPerson() throws Exception{
        FileInputStream in = new FileInputStream(new File("C:\\Users\\dell\\Desktop\\excel新\\4A基准用户_少量数据.xlsx"));
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





}
