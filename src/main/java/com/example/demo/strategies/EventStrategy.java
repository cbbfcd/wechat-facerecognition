package com.example.demo.strategies;

import com.example.demo.utils.TemplateTextUtils;

import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 事件策略
 * @time 11:21 2017/11/22
 * @modified by:
 * @modified time:
 */
public class EventStrategy {
    // 关注事件策略
    public static void subscribeStrategy(){
        System.out.println("这是一个关注事件");
    }

    // 取消关注事件策略
    public static void unsubscribeStrategy(){
        System.out.println("这是一个取消关注事件");
    }

    // 自定义菜单点击事件策略
    public static String clickStrategy(Map<String,String> map) throws Exception{
        String key = map.get("EventKey");
        if("HT001".equals(key)){
            String openId = map.get("FromUserName"); // 获取用户的openId
            /// 暂时注释
/*            HashMap<String, String> res = GetUseInfo.getUserInfoByOpenId(openId);
            String mid = map.get("ToUserName"); // 公众号的原始ID
            String Content = "来自"+ res.get("city") + "的" +res.get("nickname")+",  你点个锤子!";
            // 创建一个文本回复消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(openId);
            textMessage.setFromUserName(mid);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(MessageUtils.REQ_MESSAGE__TYPE_TEXT);
            textMessage.setContent(Content);
            return MessageUtils.textMessageToXml(textMessage);*/
            TemplateTextUtils.sendTemplate(openId, "tnQG0CVvPLvl647nhyPjwQCkKbTpxl1OAI9mLWsqwyc");
            return "";
        }else{
            return "";
        }
    }

    // 自定义菜单view事件策略
    public static void viewStrategy(){
        System.out.println("这是一个自定义菜单view事件");
    }
}
