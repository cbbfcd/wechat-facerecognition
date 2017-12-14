package com.example.demo.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

/**
 * @author huangteng
 * @version 1.0.0
 * @description http请求工具类
 * @time 15:19 2017/11/22
 * @modified by:
 * @modified time:
 */
@SuppressWarnings("deprecation")
public class HttpUtils {
    /**
     * 共用的get请求
     */
    @SuppressWarnings("resource")
    public static String sendGet(String reqUrl, Map<String, String> params) throws Exception{
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        try {
            String url = buildUrl(reqUrl, params);
            CloseableHttpClient httpClient = HttpClients.createDefault();

            request.setHeader("Accept-Encoding","gzip");
            request.setURI(new URI(url));

            HttpResponse response = httpClient.execute(request);
            inputStream = response.getEntity().getContent();
            String res = getJsonStringFromGZIP(inputStream);
            return res;
        } finally {
            if(inputStream != null){
                inputStream.close();
            }
            request.releaseConnection();
        }
    }

    /**
     * 共用post方法
     */
    @SuppressWarnings("resource")
    public static String sendPost(String reqUrl, Map<String, String> params) throws Exception{
        try{
            Set<String> set = params.keySet();
            List<NameValuePair> list = new ArrayList<>();
            for(String key: set){
                list.add(new BasicNameValuePair(key, params.get(key)));
            }
            if(list.size() > 0){
                try{
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    HttpPost post = new HttpPost(reqUrl);

                    post.setHeader("Accept-Encoding","gzip");
                    post.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));

                    HttpResponse response = httpClient.execute(post);

                    InputStream ins = response.getEntity().getContent();
                    try{
                        String result = getJsonStringFromGZIP(ins);
                        return result;
                    }finally {
                        ins.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    throw new Exception("网络连接失败,请连接网络后再试");
                }
            }else{
                throw new Exception("参数不全，请稍后重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("未知异常");
        }
    }

    /**
     * post请求json数据
     */
    public static String sendPostBuffer(String urls, String params) throws Exception {
        HttpPost request = new HttpPost(urls);

        StringEntity se = new StringEntity(params, HTTP.UTF_8);
        request.setEntity(se);
        // 发送请求
        @SuppressWarnings("resource")
        HttpResponse httpResponse = HttpClients.createDefault().execute(request);
        // 得到应答的字符串，这也是一个 JSON 格式保存的数据
        String retSrc = EntityUtils.toString(httpResponse.getEntity());
        request.releaseConnection();
        return retSrc;
    }

    /**
     * http 请求发送 xml 内容
     */
    public static String sendXmlPost(String urlStr, String xmlInfo) {
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(
                    con.getOutputStream());
            out.write(new String(xmlInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String lines = "";
            for (String line = br.readLine(); line != null; line = br
                    .readLine()) {
                lines = lines + line;
            }
            return lines; // 返回请求结果
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * 读取gzip流，转成JsonString
     */
    public static String getJsonStringFromGZIP(InputStream is) {
        String Jsonstr = "";
        BufferedInputStream bis = null;
        InputStreamReader reader = null;
        try {
            bis = new BufferedInputStream(is);
            bis.mark(2);
            // 取前两个字节
            byte[] header = new byte[2];
            int result = bis.read(header);
            // reset 输入流到开始位置
            bis.reset();
            // 判断支持gzip吗
            int headerData = getShort(header);
            // Gzip 流 的前两个字节是 0x1f8b
            if(result != -1 && headerData == 0x1f8b){
                is = new GZIPInputStream(bis);
            }else{
                is = bis;
            }

            reader = new InputStreamReader(is, "UTF-8");
            char[] data = new char[100];
            int readSize;
            StringBuffer sb = new StringBuffer();
            while ((readSize = reader.read(data)) > 0){
                sb.append(data, 0, readSize);
            }
            Jsonstr = sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bis.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Jsonstr;
    }

    private static int getShort(byte[] data){
        return (data[0] << 8) | data[1] & 0xFF;
    }

    /**
     * 构造url地址
     */
    public static String buildUrl(String reqUrl, Map<String, String> params){
        StringBuilder sb = new StringBuilder();
        Set<String> set = params.keySet();
        for(String key: set){
            sb.append(String.format("%s=%s&", key, params.get(key)));
        }
        return reqUrl + "?" + sb.toString();
    }

    /**
     * 下载图片
     */
    public static void downImg(String reqUrl, Map<String, String> params, String savePath){
        InputStream inputStream = null;
        HttpGet request = new HttpGet();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        String d_url = buildUrl(reqUrl, params);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            request.setURI(new URI(d_url));
            HttpResponse response = httpClient.execute(request);
            inputStream = response.getEntity().getContent();
            bis = new BufferedInputStream(inputStream);
            fos = new FileOutputStream(savePath);
            byte[] buff = new byte[8096];
            int rc = 0;
            while((rc = bis.read(buff)) > 0){
                fos.write(buff, 0, rc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                bis.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
