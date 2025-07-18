package com.roy.redis;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Author： roy
 * Description：
 **/
public class MyRedisClient {

    OutputStream write;
    InputStream reader;

    public MyRedisClient(String host,int port) throws IOException {
        Socket socket = new Socket(host,port);
        write = socket.getOutputStream();
        reader = socket.getInputStream();

    }
    // auth 123qweasd
    public String auth(String password){
        //1 组装报文
        StringBuffer command = new StringBuffer();
        command.append("*2").append("\r\n");//参数数量
        command.append("$4").append("\r\n");//第一个参数长度
        command.append("AUTH").append("\r\n");//第一个参数值
        //socket编程需要关注二进制长度。
        command.append("$").append(password.getBytes().length).append("\r\n");//第二个参数长度
        command.append(password).append("\r\n");//第二个参数值

        //2 发送报文到
        try {
            write.write(command.toString().getBytes());
            //3 接收redis响应
            byte[] response = new byte[1024];
            reader.read(response);
            return new String(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String set(String key, String value){
        //1 组装报文
        StringBuffer command = new StringBuffer();
        command.append("*3").append("\r\n");//参数数量
        command.append("$3").append("\r\n");//第一个参数长度
        command.append("SET").append("\r\n");//第一个参数值
        //socket编程需要关注二进制长度。
        command.append("$").append(key.getBytes().length).append("\r\n");//第二个参数长度
        command.append(key).append("\r\n");//第二个参数值

        command.append("$").append(value.getBytes().length).append("\r\n");//第三个参数长度
        command.append(value).append("\r\n");//第三个参数值
        //2 发送报文到
        try {
            write.write(command.toString().getBytes());
            //3 接收redis响应
            byte[] response = new byte[1024];
            reader.read(response);
            return new String(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {

        MyRedisClient client = new MyRedisClient("192.168.65.214",6379);
        System.out.println(client.auth("123qweasd"));
        System.out.println(client.set("test","test"));
        //这样存进去的是一个ascii码，需要进行转移才能读成中文。
        System.out.println(client.set("test2","楼兰"));
    }
}