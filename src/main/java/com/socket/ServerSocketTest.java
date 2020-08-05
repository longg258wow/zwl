package com.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocketTest {
    /**
     * accept 方法和readLine 方法都会阻塞
     * readLine方法会监听客户端传来的结束信息 在没有收到此消息时 如果客户端退出将报错
     *
     * @param args
     * @throws Exception
     */


    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket  =new ServerSocket(9999);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        while (true) {  //对应多个客户端
            Socket socket = serverSocket.accept();
            Runnable runnable = () -> {
                System.out.println("new client");
                try {
                    InputStream inputStream = socket.getInputStream();
                    DataInputStream dataInputStream = new DataInputStream(inputStream);
                    while (true) { //对应多条记录
                        System.out.println("new record");
                        byte b = dataInputStream.readByte();  //1字节
                        int len = dataInputStream.readInt();  //四字节
                        byte[] data = new byte[len - 5];
                        dataInputStream.readFully(data);
                        String str = new String(data);
                        System.out.println("接受数据： b= " + b+" len = "+len+" str = "+str);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            };

            executorService.submit(runnable);
        }
    }
}
