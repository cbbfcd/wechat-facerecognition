package com.example.demo.common;

import com.example.demo.utils.GlobalConstants;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 用户微信前端页面的 jssdk 配置使用
 * @time 9:41 2017/12/13
 * @modified by:
 * @modified time:
 */
public class JSSDK_Config {

    /**
     * 生成签名
     * @param url
     * @return
     * @throws Exception
     */
    public static HashMap<String, String> jsSDK_Sign(String url) throws Exception{
        String nonce_str = UUID.randomUUID().toString();
        String jsapi_ticket = GlobalConstants.getString("jsapi_ticket");
        String timestap = System.currentTimeMillis()+"";
        String jssdk_url = "jsapi_ticket="+jsapi_ticket+"&noncestr="+nonce_str+"&timestamp="+timestap+"&url="+url;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.reset();
        md.update(jssdk_url.getBytes("UTF-8"));
        String signature = byteToHex(md.digest());
        HashMap<String, String> hs = new HashMap<>();
        hs.put("appId",GlobalConstants.getString("appid"));
        hs.put("timestamp", timestap);
        hs.put("nonceStr", nonce_str);
        hs.put("signature", signature);
        return hs;
    }

    private static String byteToHex(final byte[] hash){
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
