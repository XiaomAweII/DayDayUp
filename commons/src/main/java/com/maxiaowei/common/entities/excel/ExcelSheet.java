package com.maxiaowei.common.entities.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 导出 Sheet页
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ExcelSheet {
    /**
     * sheet页名称
     */
    private String sheetName;

    /**
     * sheet的头行
     */
    private List<ExcelHead> headList;

    /**
     * sheet页当中的数据集合 - 对应每行数据
     * key为对应head头字段名称, value为该字段值
     */
    private List<Map<String, String>> dataList;

    public ExcelSheet(String sheetName, List<ExcelHead> headList, List<Map<String, String>> dataList) {
        this.sheetName = sheetName;
        this.headList = headList;
        this.dataList = dataList;
    }
}
