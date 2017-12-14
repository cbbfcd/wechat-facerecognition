package com.example.demo.utils;

import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 模板消息工具类
 * @time 11:31 2017/11/28
 * @modified by:
 * @modified time:
 */
public class TemplateTextUtils {

    /**
     * 行业设置
     * @throws Exception
     */
    public static void setIndustry() throws Exception{
        HashMap<String, String> params = new HashMap<>();
        String url = GlobalConstants.getString("tempalteIndustry") + "?access_token=" + GlobalConstants.getString("access_token");
        params.put("industry_id1", GlobalConstants.getString("industry_id1"));
        params.put("industry_id2", GlobalConstants.getString("industry_id2"));
        String res = HttpUtils.sendPost(url, params);
        System.out.println(res);
    }

    /**
     * 获取行业设置信息
     * @return
     * @throws Exception
     */
    public static String getIndustryByToken() throws Exception{
        HashMap<String, String> params = new HashMap();
        params.put("access_token", GlobalConstants.getString("access_token"));
        String res = HttpUtils.sendGet(GlobalConstants.getString("getIndustryUrl"), params);
        return res;
    }

    /**
     * 获取模板id
     * @return
     * @throws Exception
     */
    public static String getTemplateId() throws Exception{
        String url = GlobalConstants.getString("getTempIdUrl") + "?access_token=" + GlobalConstants.getString("access_token");
        HashMap<String,String> p = new HashMap<>();
        p.put("template_id_short", GlobalConstants.getString("tempNum"));
        String res = HttpUtils.sendPost(url, p);
        return res;
    }

    /**
     * 获取所有的模板列表
     * @return
     * @throws Exception
     */
    public static String getAllTemp() throws Exception{
        String url = GlobalConstants.getString("getAllTemp");
        HashMap<String,String> p = new HashMap<>();
        p.put("access_token", GlobalConstants.getString("access_token"));
        String res = HttpUtils.sendGet(url, p);
        return res;
    }

    /**
     * 删除模板
     * @param template_id
     * @throws Exception
     */
    public static void delTemplateById(String template_id) throws Exception{
        HashMap<String, String> params = new HashMap<>();
        params.put("template_id", template_id);
        String res = HttpUtils.sendPost(GlobalConstants.getString("delTempUrl"), params);
        System.out.println(res);
    }

    /**
     * 发送模板消息的测试
     * @throws Exception
     */
    public static void sendTemplate(String openid, String tempId) throws Exception{
        String url = GlobalConstants.getString("sendTempUrl") + "?access_token=" + GlobalConstants.getString("access_token");
        JSONObject params = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value","请去认证啊");
        data.put("first",first);
        params.put("touser", openid);
        params.put("template_id", tempId);
        params.put("url", "http://1895r385n5.imwork.net/auth.jsp");
        params.put("data", data);
        String res = HttpUtils.sendPostBuffer(url, params.toString());
        System.out.println(res);
    }

}
