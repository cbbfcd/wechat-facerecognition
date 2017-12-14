package com.example.demo.dispatcher;

import com.example.demo.strategies.MsgStrategy;
import com.example.demo.utils.MessageUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 消息分发器
 * @time 11:30 2017/11/22
 * @modified by:
 * @modified time:
 */
public class MsgDispatcher {
    public static String processMessage(Map<String,String> map) throws Exception{
        String MsgType = map.get("MsgType");
        String res = "";
        if(!StringUtils.isEmpty(MsgType)){
            switch (MsgType){
                case MessageUtils.REQ_MESSAGE__TYPE_TEXT:
                    res += MsgStrategy.textStrategy(map);
                    break;
                case MessageUtils.REQ_MESSAGE__TYPE_IMAGE:
                    res += MsgStrategy.imageStrategy(map);
                    break;
                case MessageUtils.REQ_MESSAGE_TYPE_VOICE:
                    res += MsgStrategy.voiceStrategy();
                    break;
                case MessageUtils.REQ_MESSAGE_TYPE_LINK:
                    res += MsgStrategy.linkStrategy();
                    break;
                default:
                    System.out.println("no match msg");
                    break;
            }
        }
        return res;
    }
}
