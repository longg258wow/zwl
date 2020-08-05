package com.socket.阻塞式长连接;

import java.io.*;
import java.net.Socket;

public class BaseClient {
    private Socket socket = null;
    public void connect(String host, int port) throws Exception{

        if (socket == null) {
            this.socket = new Socket(host, port);
            this.socket.setKeepAlive(true);// Not important here.
            System.out.println("Connected.");
        }
    }

    public void process() throws IOException {
        assert socket != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(    this.socket.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));

        BufferedReader input = new BufferedReader(new InputStreamReader(   System.in));
        while (true) {
            System.out.print("Input：");
            String inputString = input.readLine();// Blocked
            writer.println(inputString);// Should end with '\n' if not println
            writer.flush();

            String responseString = reader.readLine();// Blocked
            System.out.println("Server response: " + responseString);
            if ("close".equalsIgnoreCase(inputString)) {
                this.socket.close();
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BaseClient client = new BaseClient();
        client.connect("127.0.0.1", 9000);
        client.process();
    }
}
