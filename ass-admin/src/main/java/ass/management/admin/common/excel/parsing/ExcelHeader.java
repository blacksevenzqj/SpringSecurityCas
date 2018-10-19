/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.parsing;

import ass.management.admin.common.excel.config.ExcelDefinition;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * 导出Excel,标题之前的信息
 * @author zhaoqingjie
 */
public interface ExcelHeader {

    /**
     * 如何构建标题之前的数据
     * @param sheet Excel中的sheet页
     * @param excelDefinition XML中定义的信息
     * @param beans 导出的数据
     */
    void buildHeader(Sheet sheet, ExcelDefinition excelDefinition, List<?> beans);
}
