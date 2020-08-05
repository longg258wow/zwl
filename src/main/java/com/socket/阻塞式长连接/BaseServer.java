package com.socket.阻塞式长连接;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BaseServer extends Thread  {
    private static final int DEFAULT_PORT = 9000;
    private final int port;
    public BaseServer(final int port) {
        this.port = port;
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(this.port);
            while (true) {
                Socket clientSocket = serverSocket.accept();// Blocked
                new SocketHandler(clientSocket).start();// Many threads, Low performance
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BaseServer(DEFAULT_PORT).start();
        System.out.println("Server started on port: " + DEFAULT_PORT);
    }
}
