/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.parsing;

import ass.management.admin.common.excel.ExcelDefinitionReader;
import ass.management.admin.common.excel.config.ExcelConfig;
import ass.management.admin.common.excel.config.ExcelDefinition;
import ass.management.admin.common.excel.config.FieldValue;
import ass.management.admin.common.excel.exception.ExcelException;
import ass.management.admin.common.excel.result.ExcelExportResult;
import ass.management.admin.common.utils.ReflectUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.TypeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel导出实现类
 * @author zhaoqingjie
 */
public class ExcelExport extends AbstractExcelResolver {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelExport.class);

    public ExcelExport(ExcelDefinitionReader definitionReader) {
        super(definitionReader);
    }

    /**
     * 创建导出Excel,如果集合没有数据,返回null
     * @param id	 ExcelXML配置Bean的ID
     * @param beans  ExcelXML配置的bean集合
     * @param header Excel头信息(在标题之前)
     * @param fields 指定导出的字段
     * @return
     * @throws Exception
     */
    public ExcelExportResult createExcel(String id, List<?> beans, ExcelHeader header, List<String> fields) throws Exception {
        ExcelExportResult exportResult = null;
        if (CollectionUtils.isNotEmpty(beans)) {
            //从注册信息中获取Bean信息
            ExcelDefinition excelDefinition = definitionReader.getRegistry().get(id);
            if (excelDefinition == null) {
                throw new ExcelException("没有找到 [" + id + "] 的配置信息");
            }
            //实际传入的bean类型
            Class<?> realClass = beans.get(0).getClass();
            LOG.info(String.valueOf(realClass == excelDefinition.getClazz()));
            //传入的类型是excel配置class的类型,或者是它的子类,直接进行生成
            if (realClass == excelDefinition.getClazz()
                    || TypeUtils.isAssignable(excelDefinition.getClazz(), realClass)) {
                //导出指定字段的标题不是null,动态创建,Excel定义
                excelDefinition = dynamicCreateExcelDefinition(excelDefinition, fields);
            }
            //传入的类型是excel配置class的类型的父类,那么进行向上转型,只获取配置中父类存在的属性
            else if (TypeUtils.isAssignable(realClass, excelDefinition.getClazz())) {
                excelDefinition = extractSuperClassFields(excelDefinition, fields, realClass);
            } else {
                //判断传入的集合与配置文件中的类型拥有共同的父类,如果有则向上转型
                Object superClass = ReflectUtil.getEqSuperClass(realClass, excelDefinition.getClazz());
                if (superClass != Object.class) {
                    excelDefinition = extractSuperClassFields(excelDefinition, fields, realClass);
                } else {
                    throw new ExcelException("传入的参数类型是:" + beans.get(0).getClass().getName() + "但是 配置文件的类型是: "
                            + excelDefinition.getClazz().getName() + ",参数既不是父类,也不是其相同父类下的子类,无法完成转换");
                }

            }
            exportResult = doCreateExcel(excelDefinition, beans, header);
        }
        return exportResult;
    }

    /**
     * 创建Excel,模板信息
     * @param id	 ExcelXML配置Bean的ID
     * @param header Excel头信息(在标题之前)
     * @param fields 指定导出的字段
     * @return
     * @throws Exception
     */
    public Workbook createExcelTemplate(String id, ExcelHeader header, List<String> fields) throws Exception {
        //从注册信息中获取Bean信息
        ExcelDefinition excelDefinition = definitionReader.getRegistry().get(id);
        if (excelDefinition == null) {
            throw new ExcelException("没有找到 [" + id + "] 的配置信息");
        }
        excelDefinition = dynamicCreateExcelDefinition(excelDefinition, fields);
        return doCreateExcel(excelDefinition, null, header).build();
    }

    //抽取父类拥用的字段,同时从它的基础只上在进行筛选指定的字段
    private ExcelDefinition extractSuperClassFields(ExcelDefinition excelDefinition, List<String> fields,
            Class<?> realClass) {
        //抽取出父类所拥有的字段
        List<String> fieldNames = ReflectUtil.getFieldNames(realClass);
        excelDefinition = dynamicCreateExcelDefinition(excelDefinition, fieldNames);
        //抽取指定的字段
        //导出指定字段的标题不是null,动态创建,Excel定义
        excelDefinition = dynamicCreateExcelDefinition(excelDefinition, fields);
        return excelDefinition;
    }

    /**
     * 动态创建ExcelDefinition
     * @param excelDefinition 原来的ExcelDefinition
     * @param fields 
     * @return
     */
    private ExcelDefinition dynamicCreateExcelDefinition(ExcelDefinition excelDefinition, List<String> fields) {
        if (CollectionUtils.isNotEmpty(fields)) {
            ExcelDefinition newDef = new ExcelDefinition();
            ReflectUtil.copyProps(excelDefinition, newDef, "fieldValues");
            List<FieldValue> oldValues = excelDefinition.getFieldValues();
            List<FieldValue> newValues = new ArrayList<FieldValue>(oldValues.size());
            //按照顺序,进行添加
            for (String name : fields) {
                for (FieldValue field : oldValues) {
                    String fieldName = field.getName();
                    if (fieldName.equals(name)) {
                        newValues.add(field);
                        break;
                    }
                }
            }
            newDef.setFieldValues(newValues);
            return newDef;
        }
        return excelDefinition;

    }

    protected ExcelExportResult doCreateExcel(ExcelDefinition excelDefinition, List<?> beans, ExcelHeader header) throws Exception {
        // 创建Workbook
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = null;
        if (excelDefinition.getSheetname() != null) {
            sheet = workbook.createSheet(excelDefinition.getSheetname());
        } else {
            sheet = workbook.createSheet();
        }
        //创建标题之前,调用buildHeader方法,完成其他数据创建的一些信息
        if (header != null) {
            header.buildHeader(sheet, excelDefinition, beans);
        }

        Row titleRow = createTitle(excelDefinition, sheet, workbook);
        //如果listBean不为空,创建数据行
        if (beans != null) {
            createRows(excelDefinition, sheet, beans, workbook, titleRow);
        }
        ExcelExportResult exportResult = new ExcelExportResult(excelDefinition, sheet, workbook, titleRow, this);
        return exportResult;
    }

    /**
     * 创建Excel标题
     * @param excelDefinition
     * @param sheet
     * @return 标题行
     */
    protected Row createTitle(ExcelDefinition excelDefinition, Sheet sheet, Workbook workbook) {
        //标题索引号
        int titleIndex = sheet.getPhysicalNumberOfRows();   // EXCEL的行从0开始计算。
        Row titleRow = sheet.createRow(titleIndex);
        if (excelDefinition.getDefaultRowHeightInPoints() != null) {
            titleRow.setHeightInPoints(excelDefinition.getDefaultRowHeightInPoints());
        }
        List<FieldValue> fieldValues = excelDefinition.getFieldValues();
        for (int i = 0; i < fieldValues.size(); i++) {
            FieldValue fieldValue = fieldValues.get(i);

            // 如果是 关联子EXCEL字段，则直接跳过。
            if(StringUtils.isNotBlank(fieldValue.getAssExcelId())){
                continue;
            }

            //设置单元格宽度
            if (fieldValue.getColumnWidth() != null) {
                sheet.setColumnWidth(i, fieldValue.getColumnWidth());
            }
            //如果默认的宽度不为空,使用默认的宽度
            else if (excelDefinition.getDefaultColumnWidth() != null) {
                sheet.setColumnWidth(i, excelDefinition.getDefaultColumnWidth());
            }
            Cell cell = titleRow.createCell(i);
            if (excelDefinition.getEnableStyle()) {
                if (fieldValue.getAlign() != null || fieldValue.getVertical() != null
                        || fieldValue.getTitleBgColor() != null || fieldValue.getTitleFountColor() != null
                        || excelDefinition.getDefaultAlign() != null || excelDefinition.getDefaultVertical() != null) {
                    cell.setCellStyle(workbook.createCellStyle());
                    //设置水平cell 对齐方式
                    setAlignStyle(fieldValue, workbook, cell, excelDefinition);
                    // 设置垂直cell对齐方式
                    setVerticalStyle(fieldValue, workbook, cell, excelDefinition);
                    //设置标题背景色
                    setTitleBgColorStyle(fieldValue, workbook, cell);
                    //设置标题字体色
                    setTitleFountColorStyle(fieldValue, workbook, cell);
                }
            }
            setCellValue(cell, fieldValue.getTitle());
        }
        return titleRow;
    }

    /**
     * 创建行
     * @param excelDefinition
     * @param sheet
     * @param beans
     * @param workbook
     * @param titleRow
     * @throws Exception
     */
    public void createRows(ExcelDefinition excelDefinition, Sheet sheet, List<?> beans, Workbook workbook, Row titleRow) throws Exception {
        for (int i = 0; i < beans.size(); i++) {
            int rowNum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.createRow(rowNum);  // 2、用这个方法最省事。
            if (excelDefinition.getEachRowHeightInPoints() != null) {
                row.setHeightInPoints(excelDefinition.getEachRowHeightInPoints());
            } else if (excelDefinition.getDefaultRowHeightInPoints() != null) {
                row.setHeightInPoints(excelDefinition.getDefaultRowHeightInPoints());
            }
            createRow(excelDefinition, row, beans.get(i), workbook, sheet, titleRow, rowNum);
        }
    }

    /**
     * 创建行
     * @param excelDefinition
     * @param row
     * @param bean
     * @param workbook
     * @param sheet
     * @param titleRow
     * @param rowNum
     * @throws Exception
     */
    protected void createRow(ExcelDefinition excelDefinition, Row row, Object bean, Workbook workbook, Sheet sheet, Row titleRow, int rowNum) throws Exception {
        LOG.info(String.valueOf(rowNum));
		List<FieldValue> fieldValues = excelDefinition.getFieldValues();
		for(int i=0;i<fieldValues.size();i++) {
            FieldValue fieldValue = fieldValues.get(i);
            if (StringUtils.isNotBlank(fieldValue.getAssExcelId())) {   //约定大于配置：ExcelId字段必须配置在 最后的字段位置。
                createChildExcel(fieldValue, bean, workbook, sheet);
            } else {
                String name = fieldValue.getName();
                Object value = ReflectUtil.getProperty(bean, name);
                Map map = new HashMap<>();
                map.put(ExcelConfig.Convert.DEFAULT_KEY, value);
                //从解析器获取值
                Object val = convert(bean, map, fieldValue, Type.EXPORT, rowNum);
                Cell cell = row.createCell(i);
                //cell样式是否与标题一致,如果一致,找到对应的标题样式进行设置
                if (excelDefinition.getEnableStyle()) {
                    if (fieldValue.isUniformStyle()) {
                        //获取标题行
                        //获取对应的标题行样式
                        Cell titleCell = titleRow.getCell(i);
                        CellStyle cellStyle = titleCell.getCellStyle();
                        cell.setCellStyle(cellStyle);
                    }
                }
                setCellValue(cell, val);
            }
        }
	}

    private void createChildExcel(FieldValue parentFieldValue, Object parentBean, Workbook workbook, Sheet sheet) throws Exception {
        if (parentBean != null) {
            //从注册信息中获取Bean信息
            ExcelDefinition childExcelDefinition = definitionReader.getRegistry().get(parentFieldValue.getAssExcelId());
            if (childExcelDefinition == null) {
                throw new ExcelException("没有找到 [" + parentFieldValue.getAssExcelId() + "] 的配置信息");
            }
            //实际传入的bean类型
            Class<?> childClass = childExcelDefinition.getClazz();
            String parentFieldName = parentFieldValue.getName();
            Object listValues = ReflectUtil.getProperty(parentBean, parentFieldName);
            if(listValues == null){
                return;
            }
            List<?> list;
            if(listValues instanceof List<?>){
                list = (List<?>)listValues;
            }else{
                throw new Exception("子属性类型错误：应为List");
            }
            if(list.size() > 0){
                LOG.info(String.valueOf(list.get(0).getClass() == childExcelDefinition.getClazz()));
                doCreateChildExcel(childExcelDefinition, list, workbook, sheet);
            }
        }
    }
    private void doCreateChildExcel(ExcelDefinition childExcelDefinition, List<?> beans, Workbook workbook, Sheet sheet) throws Exception {
        Row titleRow = createTitle(childExcelDefinition, sheet, workbook);
        //如果listBean不为空,创建数据行
        if (beans != null && beans.size() > 0) {
            createRows(childExcelDefinition, sheet, beans, workbook, titleRow);
        }
    }


    //设置cell 水平对齐方式
    private void setAlignStyle(FieldValue fieldValue, Workbook workbook, Cell cell, ExcelDefinition excelDefinition) {
        if (fieldValue.getAlign() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setAlignment(fieldValue.getAlign());
            cell.setCellStyle(cellStyle);
        } else if (excelDefinition.getDefaultAlign() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setAlignment(excelDefinition.getDefaultAlign());
            cell.setCellStyle(cellStyle);
        }
    }

    //设置cell 垂直对齐方式
    private void setVerticalStyle(FieldValue fieldValue, Workbook workbook, Cell cell, ExcelDefinition excelDefinition) {
        if (fieldValue.getVertical() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setVerticalAlignment(fieldValue.getVertical());
            cell.setCellStyle(cellStyle);
        } else if (excelDefinition.getDefaultVertical() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setVerticalAlignment(excelDefinition.getDefaultVertical());
            cell.setCellStyle(cellStyle);
        }
    }

    //设置cell 背景色方式
    private void setTitleBgColorStyle(FieldValue fieldValue, Workbook workbook, Cell cell) {
        if (fieldValue.getTitleBgColor() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            cellStyle.setFillForegroundColor(fieldValue.getTitleBgColor());
            cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
    }

    //设置cell 字体颜色
    private void setTitleFountColorStyle(FieldValue fieldValue, Workbook workbook, Cell cell) {
        if (fieldValue.getTitleFountColor() != null) {
            CellStyle cellStyle = cell.getCellStyle();
            Font font = workbook.createFont();
            font.setColor(fieldValue.getTitleFountColor());
            cellStyle.setFont(font);
        }
    }

}
