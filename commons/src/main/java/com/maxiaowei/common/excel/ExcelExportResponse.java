package com.maxiaowei.common.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 导出返回结果
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ExcelExportResponse {
    /**
     * 导出的excel文件名称
     */
    private String excelName;

    /**
     * sheet列表数据
     */
    private List<ExcelSheet> sheetList;

    public ExcelExportResponse(String excelName, List<ExcelSheet> sheetList) {
        this.excelName = excelName;
        this.sheetList = sheetList;
    }
}
