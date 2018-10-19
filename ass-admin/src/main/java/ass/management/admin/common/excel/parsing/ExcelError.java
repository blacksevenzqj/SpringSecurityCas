/*
 * Copyright (c) 2012-2013, Yunnan Yuan Xin technology Co., Ltd.
 * 
 * All rights reserved.
 */
package ass.management.admin.common.excel.parsing;

/**
 * Excel 导入时产生的错误消息
 * @author zhaoqingjie
 */
public class ExcelError {

    /** 第几行 */
    private int row;

    /** 错误消息 */
    private String errorMsg;

    public ExcelError(int row, String errorMsg) {
        this.row = row;
        this.errorMsg = errorMsg;
    }

    public int getRow() {
        return row;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
