package com.maxiaowei.common.enums;

import com.maxiaowei.common.IMsgEnum;
import lombok.Getter;

import static com.maxiaowei.common.constants.ErrorCode.*;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
@Getter
public enum ErrorMsgEnum implements IMsgEnum {
    PARAMETER_INVALID(INVALID_PARAM, "Parameter error", "参数不合法"),
    SYSTEM_ERROR(SYS_ERROR, "System error", "系统错误", "系统问题请联系管理员！");

    private String code;
    private String msgEn;
    private String msgCn;
    private String recommend;

    ErrorMsgEnum(String code, String msgEn, String msgCn) {
        this.code = code;
        this.msgEn = msgEn;
        this.msgCn = msgCn;
    }

    ErrorMsgEnum(String code, String msgEn, String msgCn, String recommend) {
        this.code = code;
        this.msgEn = msgEn;
        this.msgCn = msgCn;
        this.recommend = recommend;
    }

    ErrorMsgEnum(ErrorMsgEnum iMsgEnum, String recommend) {
        this.code = iMsgEnum.getCode();
        this.msgEn = iMsgEnum.getMsgEn();
        this.msgCn = iMsgEnum.getMsgCn();
        this.recommend = recommend;
    }

    @Override
    public String getMessage() {
        return IMsgEnum.super.getMessage();
    }

}
