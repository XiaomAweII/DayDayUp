package com.maxiaowei.common;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 定义通用异常枚举接口
 * <p>
 * 作者: maxiaowei
 */
public interface IMsgEnum {
    /**
     * 通用编码
     */
    String code = "";

    /**
     * 用于国际化下-英文提示
     */
    String msgEn = "";

    /**
     * 用于国际化下-中文提示
     */
    String msgCn = "";

    /**
     * 提示信息国际化     * @return
     */
    default String getMessage() {
        try {
            // 获取当前请求的语言环境
            Locale locale = LocaleContextHolder.getLocale();
            // 如果是中文环境返回中文提示，否则返回英文提示
            return Locale.CHINESE.getLanguage().equals(locale.getLanguage()) ? msgCn : msgEn;
        } catch (Exception e) {
            // 非web环境(如单测) 默认返回英文环境
            return msgEn;
        }
    }

    // TODO 常见方法待定义
}
