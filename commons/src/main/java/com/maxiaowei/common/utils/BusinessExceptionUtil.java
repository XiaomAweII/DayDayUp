package com.maxiaowei.common.utils;

import com.maxiaowei.common.BusinessException;
import com.maxiaowei.common.IMsgEnum;

/**
 * 构建通用异常信息
 * <p>
 * 作者: maxiaowei
 */
public class BusinessExceptionUtil {
    public static BusinessException getBusinessException(String code, String msg) {
        return new BusinessException(code, msg);
    }

    public static BusinessException getBusinessException(String code, String msg, Exception e) {
        return new BusinessException(code, msg, e);
    }

    public static BusinessException getBusinessException(String code, IMsgEnum msgEnum) {
        return new BusinessException(code, msgEnum.getMessage());
    }

    public static BusinessException getBusinessException(String code, IMsgEnum msgEnum, Throwable cause) {
        return new BusinessException(code, msgEnum.getMessage(), cause);
    }
}
