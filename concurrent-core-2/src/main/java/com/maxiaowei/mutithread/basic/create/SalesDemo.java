package com.maxiaowei.mutithread.basic.create;

import com.maxiaowei.util.Print;

import java.util.concurrent.atomic.AtomicInteger;

import static com.maxiaowei.util.ThreadUtil.getCurThreadName;
import static com.maxiaowei.util.ThreadUtil.sleepMilliSeconds;

/**
 * 功能描述: 演示 逻辑和数据更好地分离
 * <p>
 * 作者: maxiaowei
 */
public class SalesDemo {
    // 商品数量
    public static final int MAX_ACOUNT = 5;

    // 商店商品类（销售线程类），一个商品一个销售线程，每个线程异步销售4次
    static class StoreGoods extends Thread {
        StoreGoods(String name) {
            super(name);
        }

        private int goodsAmount = MAX_ACOUNT;

        public void run() {
            for (int i = 0; i < MAX_ACOUNT; i++) {
                if (this.goodsAmount > 0) {
                    Print.cfo(getCurThreadName() + " 卖出一件, 还剩: " + (--goodsAmount));
                    sleepMilliSeconds(10);
                }
            }
            Print.cfo(getCurThreadName() + " 运行结束.");
        }
    }

    // 商场商品类型（target销售线程的目标类），一个商品最多销售4次，可以多人销售
    static class MallGoods implements Runnable {
        // 多人销售可能导致数据出错，使用原子数据类型保障数据安全
        private AtomicInteger goodsAmount = new AtomicInteger(MAX_ACOUNT);

        @Override
        public void run() {
            for (int i = 0; i < MAX_ACOUNT; i++) {
                if (this.goodsAmount.get() > 0) {
                    Print.cfo(getCurThreadName() + " 卖出一件, 还剩: " + (goodsAmount.decrementAndGet()));
                }
            }
            Print.cfo(getCurThreadName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        Print.hint("商店版本的销售");
        for (int i = 0; i < 2; i++) {
            Thread thread = null;
            thread = new StoreGoods("店员-" + i);
            thread.start();
        }
        sleepMilliSeconds(1000);
        Print.hint("商场版本的销售");
        MallGoods mallGoods = new MallGoods();
        for (int i = 0; i < 2; i++) {
            Thread thread = null;
            thread = new Thread(mallGoods, "商场销售员-" + i);
            thread.start();
        }
        Print.cfo(getCurThreadName() + " 运行结束.");
    }
}
