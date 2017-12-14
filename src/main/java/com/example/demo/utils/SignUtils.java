package com.example.demo.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 接入微信公众号平台工具类
 * @time 16:13 2017/11/21
 * @modified by:
 * @modified time:
 */
public class SignUtils {

    // 和平台上填写的填写服务器配置中的token保持一致。
    private final static String token = "xzoidcyunweibu";

    /**
     * 验证签名
     * 将 timestamp、nonce、token排序后，用sha-1加密，然后与signature比较
     * @param  signature
     * @param  timestamp
     * @param  nonce
     * @return {boolean}
     */
    public static  boolean checkSignature(String signature, String timestamp, String nonce){
        // 排序
        String[] arr = new String[]{timestamp, nonce, token};
        Arrays.sort(arr);
        // 排序了再拼成字符串
        StringBuilder sb = new StringBuilder();
        for(int i = 0, len = arr.length ; i < len ; i++){
            sb.append(arr[i]);
        }
        // 加密、比较
        MessageDigest md = null;
        String temp = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(sb.toString().getBytes());
            temp = byte2str(digest);
        }catch (Exception e){
            // 暂时这么处理
            e.printStackTrace();
        }
        // 清空一下
        sb = null;
        return temp != null ? temp.equals(signature.toUpperCase()) : false;
    }

    /**
     * byte -> 16进制字符串
     * @param digest
     * @return str
     */
    public static  String byte2str(byte[] digest){
        String str = "";
        for(int i = 0,len = digest.length; i<len; i++){
            str += byteToHexStr(digest[i]);
        }
        return str;
    }

    /**
     * 字节转16机制字符串
     * @param byteItem
     * @return str
     */
    public static String byteToHexStr(byte byteItem){
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(byteItem >>> 4) & 0X0F];
        tempArr[1] = Digit[byteItem & 0X0F];
        String str = new String(tempArr);
        return str;
    }

}
