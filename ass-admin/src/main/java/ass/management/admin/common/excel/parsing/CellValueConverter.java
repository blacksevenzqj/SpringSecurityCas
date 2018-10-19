/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.parsing;


import ass.management.admin.common.excel.config.FieldValue;

import java.util.Map;

/**
 * CellValue转换器,用于解析cell值的规则
 * @author zhaoqingjie
 */
public interface CellValueConverter {

    /**
     * 操作类型，导入或导出
     */
    enum Type {
        EXPORT, IMPORT
    }

    /**
     * 转换cell的值
     * @param bean Excel配置的JavaBean对象
     * @param value Excel原值
     * @param fieldValue FieldValue信息
     * @param type 导入或导出
     * @param rowNum 行号
     * @return 解析结果对应的value
     * @throws Exception
     */
    public Object convert(Object bean, Map<String, Object> value, FieldValue fieldValue, Type type, int rowNum)
            throws Exception;
}
