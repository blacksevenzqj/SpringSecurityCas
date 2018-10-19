/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel;

import ass.management.admin.common.excel.config.ExcelDefinition;

import java.util.Map;

/**
 * Excel定义接口
 * @author zhaoqingjie
 */
public interface ExcelDefinitionReader {

    /**
     * 获取 ExcelDefinition注册信息
     * @return
     */
    Map<String, ExcelDefinition> getRegistry();
}
