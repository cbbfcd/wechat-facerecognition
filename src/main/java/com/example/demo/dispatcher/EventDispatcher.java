package com.example.demo.dispatcher;

import com.example.demo.strategies.EventStrategy;
import com.example.demo.utils.MessageUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 事件分發器
 * @time 11:41 2017/11/22
 * @modified by:
 * @modified time:
 */
public class EventDispatcher {
    public static String processEvent(Map<String,String> map) throws Exception{
        String Event = map.get("Event");
        String res = "";
        if(!StringUtils.isEmpty(Event)){
            switch (Event){
                case MessageUtils.EVENT_TYPE_CLICK:
                    res += EventStrategy.clickStrategy(map);
                    break;
                default:
                    break;
            }
        }
        return res;
    }
}
