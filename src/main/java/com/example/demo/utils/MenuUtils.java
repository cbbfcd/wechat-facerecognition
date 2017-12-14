package com.example.demo.utils;

import net.sf.json.JSONObject;

import java.util.ArrayList;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 自定义菜单工具类
 * @time 14:52 2017/11/28
 * @modified by:
 * @modified time:
 */
public class MenuUtils {
    /**
     * 自定义菜单
     */
    public static void createMenu() throws Exception{
        JSONObject params = new JSONObject();
        ArrayList list = new ArrayList();
        JSONObject sub1 = new JSONObject();
        sub1.put("type", "view");
        sub1.put("url", "http://1895r385n5.imwork.net/login.jsp");
        sub1.put("name","注册");

        JSONObject sub2 = new JSONObject();
        sub2.put("name","授权");
        sub2.put("type","click");
        sub2.put("key","HT001");

        list.add(sub1);
        list.add(sub2);
        params.put("button", list);

        String url = GlobalConstants.getString("menuUrl") + "?access_token=" + GlobalConstants.getString("access_token");

        String res = HttpUtils.sendPostBuffer(url, params.toString());
        System.out.println("创建菜单响应"+ res);
    }
}
