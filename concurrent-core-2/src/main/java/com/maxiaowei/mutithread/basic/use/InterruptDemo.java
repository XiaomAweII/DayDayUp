package com.maxiaowei.mutithread.basic.use;

import com.maxiaowei.util.Print;
import com.maxiaowei.util.ThreadUtil;
import org.junit.Test;

/**
 * 功能描述:
 * <p>
 * 在程序中,我们不能随便中断一个线程,因为我们无法知道这个线程正运行在什么状态,它可能持有某把锁,强行中断线程可能导致锁不能被释放的问题;
 * 或者线程可能在操作数据库,强行中断线程可能导致数据不一致的问题.
 * 使用stop方法来终止线程可能会产生不可预料的结果,因此并不推荐调用stop方法
 * <p>
 * 调用interrupt方法有两个作用:
 * 1. 如果此线程处于阻塞状态（如调用了Object.wait()方法），就会立马退出阻塞，并抛出InterruptedException异常，
 * 线程就可以通过捕获InterruptedException来做一定的处理，然后让线程退出。
 * 更确切地说，如果线程被Object.wait()、Thread.join()和Thread.sleep()三种方法之一阻塞，
 * 此时调用该线程的interrupt()方法，该线程将抛出一个InterruptedException中断异常（该线程必须事先预备好处理此异常），从而过早终结被阻塞状态。
 * <p>
 * 2. 如果此线程正处于运行中，线程就不受任何影响，继续运行，仅仅是线程的中断标记被设置为true。
 * 所以，程序可以在适当的位置通过调用isInterrupted()方法来查看自己是否被中断，并执行退出操作。
 * <p>
 * 如果线程的interrupt()方法先被调用，然后线程开始调用阻塞方法进入阻塞状态，
 * InterruptedException异常依旧会抛出。如果线程捕获InterruptedException异常后，继续调用阻塞方法，将不再触发InterruptedException异常。
 * 作者: maxiaowei
 */
public class InterruptDemo {
    private static final int SLEEP_GAP = 5000; // 睡眠时长
    private static final int MAX_TURN = 50; // 睡眠次数

    static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        public void run() {
            try {
                Print.tco(getName() + " 进入睡眠.");
                Thread.sleep(SLEEP_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Print.tco(getName() + " 发生被异常打断.");
                return;
            }
            Print.tco(getName() + " 运行结束.");
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new SleepThread();
        thread1.start();
        Thread thread2 = new SleepThread();
        thread2.start();

        ThreadUtil.sleepSeconds(2); // 主线程等待2秒
        thread1.interrupt();        // 打断线程1

        ThreadUtil.sleepSeconds(5); // 主线程等待5秒
        thread2.interrupt();        // 打断线程2, 此时线程2已经终止

        ThreadUtil.sleepSeconds(1); // 主线程等待1秒
        Print.tco("程序运行结束.");

    }

    // 测试用例: 获取异步调用的结果
    @Test
    public void testInterrupted2() {
        Thread thread = new Thread(){
          public void run() {
              Print.tco("线程启动了");
              // 一直循环
              while (true) {
                  Print.tco(isInterrupted());
                  ThreadUtil.sleepSeconds(5);
                  // 如果线程被中断, 退出死循环
                  if (isInterrupted()){
                      Print.tco("线程结束了");
                      return;
                  }
              }
          }
        };
        thread.start();
        ThreadUtil.sleepSeconds(2);
        thread.interrupt();
        ThreadUtil.sleepSeconds(2);
        thread.interrupt();
    }


}
