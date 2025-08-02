package com.maxiaowei.common.aspects;

import com.maxiaowei.common.entities.excel.ExcelExportResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Excel导出切面
 * <p>
 * 作者: maxiaowei
 */
@Component
@Aspect
public class ExcelExportAspect {
    @Around("@annotation(com.maxiaowei.common.annotations.ExcelExport)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        if (result instanceof ExcelExportResponse) {
            ExcelExportResponse response = (ExcelExportResponse) result;
            // 下载excel
//            ExcelExportUtils.writeExcelToResponse(response);
            return null;
        }
        return result;
    }
}
