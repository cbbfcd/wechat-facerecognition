package com.example.demo.utils;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 获取用户信息
 * @time 10:16 2017/11/28
 * @modified by:
 * @modified time:
 */
public class GetUseInfo {

    public static HashMap<String, String> getUserInfoByOpenId(String openId) throws Exception{
        String url = GlobalConstants.getString("userInfo");
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobalConstants.getString("access_token"));
        params.put("openid", openId);
        params.put("lang", "zh_CN");
        String res = HttpUtils.sendGet(url, params);
        params.clear();
        // 返回用户信息  https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839
        params.put("nickname", JSONObject.fromObject(res).getString("nickname")); // 昵称
        params.put("headimg", JSONObject.fromObject(res).getString("headimgurl"));// 头像
        params.put("province", JSONObject.fromObject(res).getString("province")); //省份
        params.put("city", JSONObject.fromObject(res).getString("city")); //城市

        return params;
    }
}
