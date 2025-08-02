package com.maxiaowei.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 通用分页返回结果
 * <p>
 * 作者: maxiaowei
 */
@Getter
@Setter
@NoArgsConstructor
public class PageResult<T> extends Result<T> {
    /**
     * 当前页码
     */
    private long current;

    /**
     * 每页数量
     */
    private long size;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页数
     */
    private long pages;

    public PageResult(boolean success, T data, long current, long size, long total) {
        super(success, data, null);
        this.current = current;
        this.size = size;
        this.total = total;
        this.pages = (size == 0) ? 0 : (total + size - 1) / size;
    }
}
