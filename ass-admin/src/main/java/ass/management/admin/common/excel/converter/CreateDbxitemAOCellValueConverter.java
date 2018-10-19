package ass.management.admin.common.excel.converter;

import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.config.FieldValue;
import ass.management.admin.common.excel.exception.ExcelException;
import ass.management.admin.common.excel.parsing.CellValueConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class CreateDbxitemAOCellValueConverter implements CellValueConverter {

//    @Resource
//    IDcdbgzmbfjService dcdbgzmbfjService;
//
//    @Resource
//    IDcdbDbxService dcdbDbxService;

    @Override
    public Object convert(Object bean, Map<String, Object> value, FieldValue fieldValue, Type type, int rowNum)
            throws Exception {
        if (type == Type.IMPORT) {
//            int status = 0;
//            DbxitemAO dbxitemAO = (DbxitemAO) bean;
//            DcdbgzmbfjCriteria example = new DcdbgzmbfjCriteria();
//            example.createCriteria().andDcdbinfoidEqualTo(String.valueOf(value.get(ExcelConfig.Convert.DCDB_INFO_ID)))
//                    .andMbfjdseqEqualTo(dbxitemAO.getDbxsequence());
//            ServiceResult<List<DcdbgzmbfjAO>> searchServiceResult = dcdbgzmbfjService.selectByCriteria(example);
//            DcdbgzmbfjAO dcdbgzmbfjAO = null;
//            if (searchServiceResult != null && searchServiceResult.isSucceed() && searchServiceResult.getData() != null
//                    && searchServiceResult.getData().size() > 0) {
//                List<DcdbgzmbfjAO> list = searchServiceResult.getData();
//                dcdbgzmbfjAO = list.get(0);
//                if (dcdbgzmbfjAO != null) {
//                    Date firstDate = dcdbgzmbfjAO.getBfqx();
//                    String secondDateStr = String.valueOf(value.get(ExcelConfig.Convert.DEFAULT_KEY));
//                    Date secondDate = DateTimeUtils.parseStrToDate(secondDateStr, DateTimeUtils.FORMAT_YMD);
//                    int temp = secondDate.compareTo(firstDate);
//                    if (temp == 1) {
//                        status = 1;
//                    } else {
//                        dbxitemAO.setDcdbinfoid(dcdbgzmbfjAO.getDcdbinfoid());
//                        dbxitemAO.setDbmbfjid(dcdbgzmbfjAO.getId());
//                        dbxitemAO.setDbxzrunit(dcdbgzmbfjAO.getZrunitcode());
//                        dbxitemAO.setDbxzrunitname(dcdbgzmbfjAO.getZrunitname());
//                        status = 2;
//                    }
//                }
//            }
//            if (status == 2) {
//                return value.get(ExcelConfig.Convert.DEFAULT_KEY);
//            } else if (status == 1) {
//                StringBuilder err = new StringBuilder().append("第[").append(rowNum).append("行],[").append(
//                        fieldValue.getTitle()).append("]").append(
//                        "二级任务的办结期限[" + dbxitemAO.getDbxbfqxStr() + "] 不能大于 一级任务的办结期限[" + dcdbgzmbfjAO.getBfqxStr()
//                                + "]");
//                throw new ExcelException(err.toString());
//            } else {
//                StringBuilder err = new StringBuilder().append("第[").append(rowNum).append("行],[").append(
//                        fieldValue.getTitle()).append("]").append(
//                        "在数据库中没有找到二级任务对应的[" + dbxitemAO.getDbxsequence() + "]一级任务序号信息");
//                throw new ExcelException(err.toString());
//            }
        } else {
            log.info("第【" + rowNum + "】行Cell数据被创建");
        }
        return value.get(ExcelConfig.Convert.DEFAULT_KEY);
    }

}
