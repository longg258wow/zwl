package com.socket.NIO式长连接;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioClient {
    private Selector selector;

    public void connect(String host, int port) throws IOException {
        selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);// Must be non-blocking
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(host, port));

    }

    public void process() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));// Input from the console
        while (true) {
            System.out.println(1111111);
            int keyCount = selector.select();  //这里会阻塞 等待触发的事件

            if (keyCount <= 0) {
                continue;
            }
            Set readyKeys = selector.selectedKeys();
            Iterator keyIt = readyKeys.iterator();
            while (keyIt.hasNext()) {
                SelectionKey key = (SelectionKey) keyIt.next();
                keyIt.remove();
                if (key.isConnectable()) {

                    SocketChannel channel = (SocketChannel) key.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_WRITE);// Notice
                    System.out.println("Connected.");
                    break;
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    String readed = this.read(channel);
                    System.out.println("isReadable readed = "+readed);
                    channel.register(selector, SelectionKey.OP_WRITE);// Notice
                } else if (key.isWritable()) {
                    System.out.println("isWritable: ");
                    String inputString = input.readLine();
                    SocketChannel channel = (SocketChannel) key.channel();
                    this.write(channel, inputString);
                    channel.register(selector, SelectionKey.OP_READ);// Notice
                }
            }
        }
    }

    private String read(SocketChannel channel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        return msg;
    }

    private void write(SocketChannel channel, String str) throws IOException {
        ByteBuffer requestBuffer = ByteBuffer.wrap(str.getBytes());
        while (requestBuffer.hasRemaining()) {
            channel.write(requestBuffer);
        }
    }

    public static void main(String[] args) {
        try {
            NioClient client = new NioClient();
            client.connect("127.0.0.1", 9001);
           client.process();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}