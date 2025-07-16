package com.maxiaowei.learnjuc.base.demo09.communication;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 功能描述: 管道输入输出流例子
 * <p>
 * 作者: maxiaowei
 */
public class Piped {
    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        // 将输出流和输入流进行连接，否则在使用时会抛出IOException
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "PrintThread");

        printThread.start();
        int receive = 0;
        try {
            // 读取键盘输入流
            while ((receive = System.in.read()) != -1) {
                // 写入管道
                out.write(receive);
            }
        } finally {
            out.close();
        }


    }

    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }


        @Override
        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1) {
                    System.out.println((char) receive);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
