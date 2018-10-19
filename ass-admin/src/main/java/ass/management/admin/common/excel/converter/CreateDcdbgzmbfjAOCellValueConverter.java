/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 *
 * All rights reserved.
 */
package ass.management.admin.common.excel.converter;


import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.config.FieldValue;
import ass.management.admin.common.excel.exception.ExcelException;
import ass.management.admin.common.excel.parsing.CellValueConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
public class CreateDcdbgzmbfjAOCellValueConverter implements CellValueConverter {

//    @Resource
//    private IUnitService unitService;

    @Override
    public Object convert(Object bean, Map<String, Object> value, FieldValue fieldValue, Type type, int rowNum)
            throws Exception {
        if (type == Type.IMPORT) {
//            Unit var11 = new Unit();
//            if (fieldValue.getName().equalsIgnoreCase(ExcelConfig.Convert.ZRUNIT_NAME)) {
//                var11.setUnitname(String.valueOf(value.get(ExcelConfig.Convert.DEFAULT_KEY)));
//                var11.setOrgcode(String.valueOf(value.get(ExcelConfig.Convert.ORG_CODE)));
//                ServiceResult<List<Unit>> result11 = unitService.getListByDto(var11);
//                List<Unit> listUnit = result11.getData();
//                Unit temp = null;
//                for (Unit unit : listUnit) {
//                    int count = countChar(unit.getUnitcode(), "/".charAt(0));
//                    if (count == 1) {
//                        temp = unit;
//                        break;
//                    }
//                }
//                if (temp != null) {
//                    DcdbgzmbfjAO dcdbgzmbfjAO = (DcdbgzmbfjAO) bean;
//                    dcdbgzmbfjAO.setZrunitcode(temp.getUnitcode());
//                    return value.get(ExcelConfig.Convert.DEFAULT_KEY);
//                } else {
//                    StringBuilder err = new StringBuilder().append("第[").append(rowNum).append("行],[").append(
//                            fieldValue.getTitle()).append("]").append(
//                            "在数据库中没有找到[" + String.valueOf(value.get(ExcelConfig.Convert.DEFAULT_KEY)) + "]的主办部门信息");
//                    throw new ExcelException(err.toString());
//                }
//            } else if (fieldValue.getName().equalsIgnoreCase(ExcelConfig.Convert.XBUNIT_NAME)) {
//                String xbunitname = String.valueOf(value.get(ExcelConfig.Convert.DEFAULT_KEY));
//                if (xbunitname != null) {
//                    String xbs[] = xbunitname.split(",");
//                    StringBuilder tempString = new StringBuilder();
//                    for (String s : xbs) {
//                        var11.setUnitname(s);
//                        var11.setOrgcode(String.valueOf(value.get(ExcelConfig.Convert.ORG_CODE)));
//                        ServiceResult<List<Unit>> result11 = unitService.getListByDto(var11);
//                        List<Unit> listUnit = result11.getData();
//                        Unit temp = null;
//                        for (Unit unit : listUnit) {
//                            int count = countChar(unit.getUnitcode(), "/".charAt(0));
//                            if (count == 1) {
//                                temp = unit;
//                                break;
//                            }
//                        }
//                        if (temp != null) {
//                            tempString.append(temp.getUnitcode() + ",");
//                        } else {
//                            StringBuilder err = new StringBuilder().append("第[").append(rowNum).append("行],[").append(
//                                    fieldValue.getTitle()).append("]").append("在数据库中没有找到[" + s + "]的协办部门信息");
//                            throw new ExcelException(err.toString());
//                        }
//                    }
//                    String resultStr = tempString.toString();
//                    DcdbgzmbfjAO dcdbgzmbfjAO = (DcdbgzmbfjAO) bean;
//                    dcdbgzmbfjAO.setXbunitcode(resultStr.substring(0, resultStr.length() - 1));
//                    return value.get(ExcelConfig.Convert.DEFAULT_KEY);
//                }
//            }
        } else {
            log.info("第【" + rowNum + "】行Cell数据被创建");
        }
        return value.get(ExcelConfig.Convert.DEFAULT_KEY);
    }

    private int countChar(String str, char ch) {
        // 将字符串转换为字符数组
        char[] chs = str.toCharArray();
        // 定义变量count存储字符串出现次数
        int count = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ch) {
                count++;
            }
        }
        return count;
    }

}
