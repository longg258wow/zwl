package com.测试工具;

import java.util.Date;
import java.util.Map;
import com.google.gson.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.CountDownLatch;



public class HttpUtil {
    public static void main(String[] args) throws Exception{


       ThreadUtil.doTest();
    }


    public static void testPost(Integer threadNo){
        String url = "http://localhost:8080/en/en/getEnList";
        Date startDate = new Date();

        for(int i=0;i<1;i++){
            String param = "{\"id\":"+threadNo+"}";
            JsonObject jsonObject = HttpUtil.postDownloadJson(url,param);
    //        System.out.println("第"+i+"次结束：" + (new Date().getTime() - startDate.getTime()));
        //    Thread.sleep(3000);

        }
   //     System.out.println("end");
        System.out.println("请求结束"+threadNo +"  :  " + (new Date().getTime() - startDate.getTime()));
    }



    /**
     * get请求
     * @param requestUrl
     * @return
     */
    public static JsonObject getXpath(String requestUrl) {
        String res = "";
        JsonObject object = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            System.out.println(urlCon.getResponseCode());
            if (200 == urlCon.getResponseCode()) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String str = null;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JsonParser parse = new JsonParser();
                object = (JsonObject) parse.parse(res);
            } else {
                throw new Exception("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * post请求
     * @param
     * @return
     */
    public static JsonObject postDownloadJson(String path, String post) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 提交模式
            httpURLConnection.setRequestMethod("POST");
            //连接超时 单位毫秒
            httpURLConnection.setConnectTimeout(500*1000);
            //读取超时 单位毫秒
            httpURLConnection.setReadTimeout(500*1000);
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            //post的参数 xx=xx&yy=yy
            printWriter.write(post);
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            bos.close();
            JsonParser parse = new JsonParser();
            return (JsonObject) parse.parse(bos.toString("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
