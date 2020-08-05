package com.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) throws Exception{
        Socket socket =new Socket("127.0.0.1",9999);
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream =new DataOutputStream(outputStream);
        Scanner scanner =new Scanner(System.in);
       while(true){
           if(scanner.hasNext()){
               int type =1;
               String str = scanner.next();
               byte[] data = str.getBytes();
               int len = data.length +5;
               dataOutputStream.writeByte(type);
               dataOutputStream.writeInt(len);
               dataOutputStream.write(data);
           }
       }




    }
}
