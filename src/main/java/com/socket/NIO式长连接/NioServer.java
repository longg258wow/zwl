package com.socket.NIO式长连接;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    /**
     *  select这里会阻塞 等待触发的事件
     */
    private static final int NIO_SERVER_PORT = 9001;
    private Selector selector;
    private ServerSocketChannel serverChannel;
    private ByteBuffer buffer;

    public void init() throws IOException {
        selector = Selector.open();

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);

        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.bind(new InetSocketAddress(NIO_SERVER_PORT));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        buffer = ByteBuffer.allocate(1024);
    }

    public void process() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));// Input from the console

        while (true) {
            System.out.println("等待");
            int keys = selector.select();// Blocked
            System.out.println("keys = "+ keys);
            if (keys <= 0) {
                continue;
            }
            Set readyKeys = selector.selectedKeys();
            Iterator it = readyKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();// If not, loop 0
                if (key.isAcceptable()) {
                    System.out.println("连接了");
                    this.accept(key);
                    System.out.println("Client connected");
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String receivedString = this.read(channel);
                    System.out.println("Server Receive: " + receivedString);
                    String response = "--->" + receivedString;
                    this.write(channel, response);
                }
//                else if (key.isWritable()) {
//                    System.out.println("11111");
//                    String inputString = input.readLine();
//                    System.out.println("写入:"+inputString);
//                    SocketChannel channel = (SocketChannel) key.channel();
//                    this.write(channel, inputString);
//                }

            }
        }
    }

    private void accept(final SelectionKey key) throws IOException {
        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()) .accept();
        if (clientChannel != null) {
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_READ);
   //        clientChannel.register(selector, SelectionKey.OP_WRITE);
        }
    }

    private String read(final SocketChannel channel) throws IOException {
        buffer.clear();// not actually erase the data.
        int numRead = channel.read(buffer);// Once
        byte[] newBytes = new byte[numRead];
        System.arraycopy(buffer.array(), 0, newBytes, 0, numRead);
        String receivedString = new String(newBytes);
        return receivedString;
    }

    private void write(final SocketChannel channel, final String resp)
            throws IOException {
        ByteBuffer responseBuffer = ByteBuffer.wrap(resp.getBytes());
        while (responseBuffer.hasRemaining()) {
            channel.write(responseBuffer);
        }
    }

    public static void main(String[] args) {
        try {
            NioServer server = new NioServer();
            server.init();
            server.process();// Single thread
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
