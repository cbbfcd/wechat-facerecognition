package com.example.demo.strategies;

import com.example.demo.model.resp.Image;
import com.example.demo.model.resp.ImageMessage;
import com.example.demo.model.resp.TextMessage;
import com.example.demo.utils.GetUseInfo;
import com.example.demo.utils.HttpPostUploadUtil;
import com.example.demo.utils.MessageUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 消息策略
 * @time 11:05 2017/11/22
 * @modified by:
 * @modified time:
 */
public class MsgStrategy {
    // 文本消息策略
    public static String textStrategy(Map<String,String> map) throws Exception{
        String openId = map.get("FromUserName"); // 获取用户的openId
        HashMap<String, String> res = GetUseInfo.getUserInfoByOpenId(openId);
        String mid = map.get("ToUserName"); // 公众号的原始ID
        String Content = "来自"+ res.get("city") + "的" +res.get("nickname")+",  你好!";
        // 创建一个文本回复消息
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(openId);
        textMessage.setFromUserName(mid);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setMsgType(MessageUtils.REQ_MESSAGE__TYPE_TEXT);
        textMessage.setContent(Content);
        return MessageUtils.textMessageToXml(textMessage);
    }

    // 图片消息策略
    public static String imageStrategy(Map<String,String> map) throws Exception{
        System.out.println("这比要斗图!");
        String openId = map.get("FromUserName"); // 获取用户的openId
        String mid = map.get("ToUserName"); // 公众号的原始ID
        // 创建一个图片消息
        Image image = new Image();
        HttpPostUploadUtil util = new HttpPostUploadUtil();
        String filepath = "E:\\JavaScript\\marko-demos\\marko.png";
        Map<String,String> textMap = new HashMap<>();
        textMap.put("name","bonita");
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("userfile", filepath);
        String res = util.formUpload(textMap, fileMap);
        String mediaId = JSONObject.fromObject(res).getString("media_id");
        image.setMediaId(mediaId);
        System.out.println("mediaId: "+mediaId);
        // 图片信息
        ImageMessage img = new ImageMessage();
        img.setToUserName(openId);
        img.setFromUserName(mid);
        img.setCreateTime(System.currentTimeMillis());
        img.setMsgType(MessageUtils.REQ_MESSAGE__TYPE_IMAGE);
        img.setImage(image);
        return MessageUtils.imageMessageToXml(img);
    }

    // 链接消息策略
    public static String linkStrategy(){
        System.out.println("接收到的是链接消息");
        return "回复你了";
    }

    // 位置消息策略
    public static String locationStrategy(){
        System.out.println("接收到的是位置消息");
        return "回复你了";
    }

    // 视频消息策略
    public static String videoStrategy(){
        System.out.println("接收到的是视频消息");
        return "回复你了";
    }

    // 语音消息策略
    public static String voiceStrategy(){
        System.out.println("接收到的是语言消息");
        return "回复你了";
    }
}
