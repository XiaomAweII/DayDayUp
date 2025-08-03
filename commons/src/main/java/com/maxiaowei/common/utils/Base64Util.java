package com.maxiaowei.common.utils;

import java.util.Base64;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class Base64Util {
    public static byte[] decode(String src) {
        return Base64.getDecoder().decode(src);
    }

    public static String decodeStr(String src) {
        return new String(decode(src));
    }
}
