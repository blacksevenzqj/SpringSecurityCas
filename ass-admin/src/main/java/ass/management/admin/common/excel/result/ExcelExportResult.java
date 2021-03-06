/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.result;

import ass.management.admin.common.excel.config.ExcelDefinition;
import ass.management.admin.common.excel.exception.ExcelException;
import ass.management.admin.common.excel.parsing.ExcelExport;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * Excel导出结果
 * @author zhaoqingjie
 */
public class ExcelExportResult {

    private ExcelDefinition excelDefinition;

    private Sheet sheet;

    private Workbook workbook;

    private Row titleRow;

    private ExcelExport excelExport;

    public ExcelExportResult(ExcelDefinition excelDefinition, Sheet sheet, Workbook workbook, Row titleRow,
                             ExcelExport excelExport) {
        super();
        this.excelDefinition = excelDefinition;
        this.sheet = sheet;
        this.workbook = workbook;
        this.titleRow = titleRow;
        this.excelExport = excelExport;
    }

    /**
     * 追加数据
     * @param beans ListBean
     * @return ExcelExportResult
     */
    public ExcelExportResult append(List<?> beans) {
        try {
            excelExport.createRows(excelDefinition, sheet, beans, workbook, titleRow);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
        return this;
    }

    /**
     * 导出完毕,获取WorkBook
     * @return
     */
    public Workbook build() {
        return workbook;
    }

}
