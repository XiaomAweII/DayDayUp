package com.maxiaowei.common.utils;

import cn.hutool.core.net.URLEncodeUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.maxiaowei.common.entities.excel.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通用导出工具类
 * <p>
 * 作者: maxiaowei
 */
public class ExcelExportUtil {
    /**
     * 构建Sheet的头列表
     *
     * @param request 导出请求
     * @return Sheet页的头列表
     */
    public static List<ExcelHead> buildSheetHeadList(ExcelExportRequest request) {
        List<ExcelExportField> fieldList = request.getFieldList();
        List<ExcelHead> excelHeadList = new ArrayList<>(fieldList.size());
        for (ExcelExportField excelExportField : fieldList) {
            ExcelHead excelHead = new ExcelHead();
            excelHead.setFieldName(excelExportField.getFieldName());
            excelHead.setFieldDesc(excelExportField.getFieldDesc());
            excelHeadList.add(excelHead);
        }
        return excelHeadList;
    }

    /**
     * 构建Sheet页内容数据
     *
     * @param dataList 数据列表
     * @param request  导出请求
     * @return List<Map < String, String>> list值为行数据, key为头字段名称, value为该行字段值
     */
    public static List<Map<String, String>> buildSheetDataList(List<?> dataList, ExcelExportRequest request) {
        if (CollectionUtil.isEmpty(dataList)) {
            return CollectionUtil.emptyArrayList();
        }
        List<Map<String, String>> sheetDataList = new ArrayList<>(dataList.size());
        List<ExcelExportField> fieldList = request.getFieldList();
        List<String> exportFieldNameList = CollectionUtil.convertList(fieldList, ExcelExportField::getFieldName);

        for (Object data : dataList) {
            HashMap<String, String> dataMap = new HashMap<>();
            for (String fileName : exportFieldNameList) {
                Object filedValue = ReflectUtil.getFieldValue(data, fileName);
                dataMap.put(fileName, convertToString(filedValue));
            }
            sheetDataList.add(dataMap);
        }
        return sheetDataList;
    }

    /**
     * 构建导出返回数据结果
     *
     * @param dataList 数据列表
     * @param request  导出请求
     * @return
     */
    public static ExcelExportResponse build(List<?> dataList, ExcelExportRequest request) {
        // 1. 组装excel导出的结果
        ExcelExportResponse result = new ExcelExportResponse();
        result.setExcelName(request.getExcelName());

        // 2. 组装sheet页
        List<ExcelSheet> sheetList = new ArrayList<>();
        result.setSheetList(sheetList);

        // 只保留单个sheet页
        ExcelSheet excelSheet = new ExcelSheet();
        // 设置sheet页名称
        excelSheet.setSheetName(request.getSheetName());
        // 设置sheet页的头行
        excelSheet.setHeadList(buildSheetHeadList(request));
        // 设置sheet中表格的数据，是个二维数组，类型 List<Map<String, String>>
        excelSheet.setDataList(buildSheetDataList(dataList, request));
        // 将sheet页放入sheet列表
        sheetList.add(excelSheet);

        // 返回组装后导出结果
        return result;
    }

    /**
     * 下载: 将导出响应结果生成下载链接
     *
     * @param excelExportResult excel导出结果
     * @throws IOException io异常
     */
    public static void writeExcelToResponse(ExcelExportResponse excelExportResult) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        OutputStream outputStream = response.getOutputStream();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncodeUtil.encode(excelExportResult.getExcelName() + ".xlsx"));
        write(excelExportResult, outputStream);
    }

    /**
     * 将excel写入到指定的流中
     *
     * @param result excel导出结果
     * @param outputStream 输出流
     */
    public static void write(ExcelExportResponse result, OutputStream outputStream) {
        List<ExcelSheet> sheetList = result.getSheetList();
        try (ExcelWriter writer = EasyExcel.write(outputStream).build();) {
            for (int sheetNo = 0; sheetNo < sheetList.size(); sheetNo++) {
                ExcelSheet excelSheet = sheetList.get(sheetNo);
                List<List<String>> head = buildEasyExcelHead(excelSheet);
                List<List<String>> dataList = buildEasyExcelDataList(excelSheet);

                WriteSheet writeSheet = EasyExcel
                        .writerSheet(sheetNo, excelSheet.getSheetName())
                        .head(head).build();
                writer.write(dataList, writeSheet);
            }
        }
    }

    /**
     * 通过 ExcelSheet 得到easyExcel中当前sheet需要的头
     *
     * @param excelSheet sheet页
     * @return sheet 头行
     */

    public static List<List<String>> buildEasyExcelHead(ExcelSheet excelSheet) {
        if (excelSheet == null || excelSheet.getHeadList() == null) {
            return CollectionUtil.emptyArrayList();
        }
        return excelSheet.getHeadList().stream().map(item -> CollectionUtil.newArrayList(item.getFieldDesc())).collect(Collectors.toList());
    }

    /**
     * 通过 ExcelSheet 得到easyExcel中当前sheet需要的数据
     *
     * @param excelSheet sheet页
     * @return sheet页数据列表
     */
    public static List<List<String>> buildEasyExcelDataList(ExcelSheet excelSheet) {
        if (excelSheet == null || excelSheet.getHeadList() == null || excelSheet.getDataList() == null) {
            return CollectionUtil.newArrayList();
        }
        List<String> filedNameList = CollectionUtil.convertList(excelSheet.getHeadList(), ExcelHead::getFieldName);
        List<List<String>> dataList = new ArrayList<>(excelSheet.getDataList().size());

        for (Map<String, String> row : excelSheet.getDataList()) {
            List<String> list = new ArrayList<>();
            for (String filedName : filedNameList) {
                list.add(row.get(filedName));
            }
            dataList.add(list);
        }
        return dataList;
    }

    private static String convertToString(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
