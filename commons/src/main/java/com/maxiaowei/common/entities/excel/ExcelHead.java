package com.maxiaowei.common.entities.excel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 导出 excel每个sheet页的 head头行
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class ExcelHead {
    /**
     * head头 字段名称
     */
    private String fieldName;

    /**
     * head头 字段描述
     */
    private String fieldDesc;

    public ExcelHead(String fieldName, String fieldDesc) {
        this.fieldName = fieldName;
        this.fieldDesc = fieldDesc;
    }
}
