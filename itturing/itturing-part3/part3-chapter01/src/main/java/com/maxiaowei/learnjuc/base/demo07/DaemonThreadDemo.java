package com.maxiaowei.learnjuc.base.demo07;

import com.maxiaowei.learnjuc.base.utils.SleepTools;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述: 守护线程例子
 * <p>
 * 作者: maxiaowei
 */
@Slf4j
public class DaemonThreadDemo {
    public static void main(String[] args) {
        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            SleepTools.second(3);
            log.debug("运行结束...");
        }, "t1");

        // 设置t1线程为守护线程
        t1.setDaemon(true);
        t1.start();
        SleepTools.second(1);
        log.debug("运行结束...");
    }
}
