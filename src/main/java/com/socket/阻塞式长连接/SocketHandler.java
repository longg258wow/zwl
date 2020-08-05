package com.socket.阻塞式长连接;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler extends  Thread {
    private final Socket socket;
    public SocketHandler(final Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(  this.socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter( this.socket.getOutputStream()));
            System.out.println("Client connected");
            while (true) {
                String receiveString = reader.readLine();// Blocked
                System.out.println("Server Receive: " + receiveString);
                if ("Close".equalsIgnoreCase(receiveString)) {
                    writer.println("Connection closed");
                    writer.flush();
                    reader.close();
                    writer.close();
                    break;
                }else{
                    writer.println("--->" + receiveString);
                    writer.flush();
                }
            }
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
