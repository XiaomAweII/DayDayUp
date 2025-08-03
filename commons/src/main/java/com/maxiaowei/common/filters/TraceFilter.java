package com.maxiaowei.common.filters;

import cn.hutool.core.util.IdUtil;
import com.maxiaowei.common.utils.TraceUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@WebFilter(urlPatterns = "/**", filterName = "TraceFilter")
public class TraceFilter extends OncePerRequestFilter {
    public static Logger logger = LoggerFactory.getLogger(TraceFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String traceID = IdUtil.fastSimpleUUID();
        TraceUtils.setTraceId(traceID);
        logger.info("请求start：{}", request.getRequestURL().toString());
        long st = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            long et = System.currentTimeMillis();
            logger.info("请求end：{}，耗时(ms)：{}", request.getRequestURL().toString(), (et - st));
            TraceUtils.removeTraceId();
        }
    }
}