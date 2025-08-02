package com.maxiaowei.common.entities.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 导出请求
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ExcelExportRequest {
    /**
     * excel名称
     */
    private String excelName;

    /**
     * sheet页名称
     */
    private String sheetName;

    /**
     * 导出字段有序列表
     */
    private List<ExcelExportField> fieldList;

    public ExcelExportRequest(String excelName, String sheetName, List<ExcelExportField> fieldList) {
        this.excelName = excelName;
        this.sheetName = sheetName;
        this.fieldList = fieldList;
    }
}
