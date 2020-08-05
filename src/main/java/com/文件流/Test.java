package com.文件流;

import java.io.*;

public class Test {


    /**
     * read()  返回字符对应的ASCII码 并把指针移到下一个字符
     * read(byte[])返回读取的元素个数  并把数据写入byte[]   如果byte中有数据 会从前覆盖
     * read(byte[],offset,len) offset 把数据读入到byte[]的第offset位 读取len长度
     *
     * char类型： 2的16次方 65536
     * BufferedInputStream比FileInputStream多了一个缓冲区， BufferedInputStream的默认缓冲区大小是8192字节
     *
     * 字符流和字节流区别  字节流把数据读入字节数组 字符流读到字符数数组
     * 在字符流里不管英语数组中文标点英文标点中文都统一战一个字符
     *
     * 字符转换流 ：如果直接用字符流读取出现乱码 说明编码解码不一致  可以设置解码格式自动将字节流转成字符流
     *
     * DataInputStream:操作基本数据类型 只能读取自己创建的  再读取非自己创建的必须用转换流转  脱裤子放屁
     * 序列化就是对象转二进制
     *
     *  字节数组流就是往一个byte[]里写内容 然后再从byte里读内容
     *
     *  PipedInputStream 用于线程间传递二进制数据 管道两头在客户端连接
     *
     *  PushbackInputStream 回退流 读取流中标志位后将标志位退回
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

       // 字节流
     //  useFileInputStream();           //最直接的文件流
      //  useBufferedInputStream();       //做了缓存处理的缓存文件流

        //序列化流
   //     useObjectOutputStream();

        //数据操作流
     //   useDataOutStream();
    //    useDataInputStream();

        //字节数组流
      //  useByteArrayInputStream();

        //管道流 PipedStreamTest
       // PipedStreamTest

        //回退流
        // usePushbackInputStream();


        //字符流
    //    useInputStreamReader();    //字符转换流
    //      useFileReader();            //字符文件流    FileInputStream字符版
   //     useBufferedReader();       //字符缓冲流    BufferedInputStream的字符版


//        useByteArrayInputStream();

    }
    private static void  usePushbackInputStream()throws Exception{
        FileInputStream fileInputStream = new FileInputStream("C:/Users/wenlong.zhu/1.txt");
        PushbackInputStream pushbackInputStream = new PushbackInputStream(fileInputStream,2);
        byte[] bytes = new byte[2];
        int len = 0;
        while ((len = pushbackInputStream.read(bytes))!=-1){
            String str = new String(bytes, 0, len);

            if(str.equals("bb")){

                pushbackInputStream.unread(bytes,0,len);
                pushbackInputStream.read(bytes);
               // pushbackInputStream.skip(len);
                String strA = new String(bytes, 0, len);
                System.out.println("strA = "+strA);
           //     pushbackInputStream.skip(len);
            }else{
                System.out.println(str);
            }

        }
    }


    private static void  useByteArrayInputStream()throws Exception{
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
        while( bOutput.size()!= 10 ) {
            bOutput.write(System.in.read());
        }
        byte b [] = bOutput.toByteArray();
        System.out.println("Print the content");
        for(int x= 0 ; x < b.length; x++) {
            System.out.print((char)b[x]  + "   ");
        }
        System.out.println("   ");

        int c;
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        System.out.println("Converting characters to Upper case " );
        while(( c= bInput.read())!= -1) {
            System.out.println((char)c);
        }

        bInput.reset();




    }

    private static void  useDataOutStream()throws Exception{
        FileOutputStream outputStream = new FileOutputStream("C:/Users/wenlong.zhu/3.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.writeUTF("猪八戒");
        dataOutputStream.writeInt(222);
        dataOutputStream.flush();
        dataOutputStream.close();
//        BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
//        String count;
//        while((count = bufferedReader.readLine()) != null){
//            String u = count.toUpperCase();
//            System.out.println(u);
//
//        }
//        bufferedReader.close();

    }


    private static void  useDataInputStream()throws Exception{
        FileInputStream in = new FileInputStream("C:/Users/wenlong.zhu/1.txt");
   //     BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
 //       DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
   //     System.out.println(dataInputStream.readUTF());
   //     System.out.println(dataInputStream.readInt());


    //    DataInputStream dataInputStream = new DataInputStream(in);
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);



        String count;
        while((count = bufferedReader.readLine()) != null){
            String u = count.toUpperCase();
            System.out.println(u);
        }
        bufferedReader.close();

    }

    private static void useObjectOutputStream()throws Exception {
        User user = new User();
        user.setUserName("大灰狼");
        user.setPassWord("小红帽");
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/wenlong.zhu/2.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);
        objectOutputStream.close();;

        System.out.println("Serialized data is saved"); // 姓名，地址被序列化，年龄没有被序列化。
    }

    private static void useFileReader()throws Exception {
        FileReader fileReader = new FileReader("C:/Users/wenlong.zhu/1.txt");
        int len;
        char[] chars = new char[3];
        while ((len = fileReader.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }
        fileReader.close();
    }



    private static void useBufferedReader()throws Exception {
        InputStream in = new FileInputStream("C:/Users/wenlong.zhu/1.txt");
        InputStreamReader reader = new InputStreamReader( in );
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);// 爱生活，爱Android
        }
        bufferedReader.close();
    }
    private static void useInputStreamReader()throws Exception {
        InputStream in = new FileInputStream("C:/Users/wenlong.zhu/1.txt");
        InputStreamReader reader = new InputStreamReader( in );
        int len;
        char[] chars = new char[3];
        while ((len = reader.read(chars)) != -1) {
            System.out.println(new String(chars, 0, len));
        }
    }


    private static void useBufferedInputStream()throws Exception {
        InputStream in = new FileInputStream("C:/Users/wenlong.zhu/1.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);

        byte[] bytes = new byte[3];
        int len = 0;
        while ((len=bufferedInputStream.read(bytes))!=-1){
            System.out.println(new String(bytes, 0, len));
        }

        bufferedInputStream.close();
        in.close();
    }



    private static void useFileInputStream()throws Exception{
        FileInputStream fileInputStream = new FileInputStream("C:/Users/wenlong.zhu/1.txt");

        //一个字符一个字符的读取
//        int val =0;
//        while((val = fileInputStream.read())!=-1){
//            System.out.println((char) val + " ");// A B C D
//        }

        //一段一段的读
        byte[] b = new byte[9];
        int len = 0;
        while((len=fileInputStream.read(b))!=-1){
            System.out.println(new String(b, 0, len) + " ");// AB CD
        }

        //有偏移的读  偏移是在byte[]里面偏移
//        val = fileInputStream.read(b,1,2);
//        System.out.println("val:"+val);
//        for(int i=0;i<b.length;i++){
//            System.out.println(b[i]);
//        }

        fileInputStream.close();
    }
}
