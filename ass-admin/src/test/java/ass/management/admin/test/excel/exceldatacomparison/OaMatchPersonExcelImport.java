package ass.management.admin.test.excel.exceldatacomparison;

import ass.management.admin.common.excel.ExcelContext;
import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.model.datacomparison.BenchmarkUser4A;
import ass.management.admin.common.excel.model.datacomparison.OaMatchPerson;
import ass.management.admin.common.excel.parsing.ExcelError;
import ass.management.admin.common.excel.result.ExcelExportResult;
import ass.management.admin.common.excel.result.ExcelImportResult;
import ass.management.admin.modules.business.exceldatacomparison.service.BenchmarkUser4AServiceImpl;
import ass.management.admin.modules.business.exceldatacomparison.service.OaMatchPersonServiceImpl;
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
public class OaMatchPersonExcelImport {

    @Autowired
    ExcelContext excelContext;

    @Autowired
    OaMatchPersonServiceImpl oaMatchPersonServiceImpl;

    @Test
    public void oaMatchPerson() {
        OaMatchPerson oaMatchPerson = new OaMatchPerson();
        oaMatchPerson.setFullName("123");
        oaMatchPersonServiceImpl.saveOrUpDateOaMatchPerson(oaMatchPerson);
    }


    @Test
    public void queryOaMatchPerson() {
        Map map = new HashMap<>();
        PageUtils<OaMatchPerson> page = oaMatchPersonServiceImpl.oaMatchPersonQueryPageMap(map);
    }


    @Test
    public void importOaMatchPerson() throws Exception{
        FileInputStream in = new FileInputStream(new File("C:\\Users\\dell\\Desktop\\excel新\\oa剩余未匹配的人员导出数据_新.xlsx"));
        ExcelImportResult excelImportResult = excelContext.readExcel(ExcelConfig.Bean.OA_MATCH_PERSON, 0, in,true);
        log.info(String.valueOf(excelImportResult.getHeader()));
        List<OaMatchPerson> oaMatchPersonList = excelImportResult.getListBean();
        for(OaMatchPerson oaMatchPerson : oaMatchPersonList){
            oaMatchPerson = oaMatchPersonServiceImpl.saveOrUpDateOaMatchPerson(oaMatchPerson);
            log.info(String.valueOf(oaMatchPerson));
        }
        //通过导入结果集的hasErrors方法判断
        if(excelImportResult.hasErrors()){
            log.error("导入包含错误，下面是错误信息：");
            for (ExcelError err : excelImportResult.getErrors()) {
                log.error(err.getErrorMsg());
            }
        }
    }


    @Test
    public void exportOaMatchPerson() throws Exception{
        Map map = new HashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 2000);
        PageUtils<OaMatchPerson> page = oaMatchPersonServiceImpl.oaMatchPersonQueryPageMap(map);
        ExcelExportResult exportResult = excelContext.createExcelForPart(ExcelConfig.Bean.OA_MATCH_PERSON, page.getList());
        OutputStream ops = new FileOutputStream("C:\\Users\\dell\\Desktop\\excel新\\oa剩余未匹配的人员导出数据_新导出.xlsx");
        Workbook workbook = exportResult.build();
        workbook.write(ops);
        ops.close();
        workbook.close();
    }





}
