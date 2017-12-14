package com.example.demo.schedule;

import com.example.demo.utils.GlobalConstants;
import com.example.demo.utils.HttpUtils;
import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 2小时微信token刷新，通过定时任务去获取更新的token
 * @time 11:34 2017/11/24
 * @modified by:
 * @modified time:
 */
@Component
public class WeChatGetTokenSchedule {

    @Scheduled(cron = "0 0 */1 * * ?")
    public void getWechatTokenTask() throws Exception{
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", GlobalConstants.getString("appid"));
        params.put("secret", GlobalConstants.getString("AppSecret"));
        String jstoken = HttpUtils.sendGet(GlobalConstants.getString("tokenUrl"), params);
        String access_token = JSONObject.fromObject(jstoken).getString("access_token");
        GlobalConstants.interfaceUrlProperties.put("access_token", access_token);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"token为"+access_token);

        // 获取 jsapi_ticket
        params.clear();
        params.put("access_token", access_token);
        params.put("type", "jsapi");
        String getTicketUrl = GlobalConstants.getString("getTicket");
        String jsticket = HttpUtils.sendGet(getTicketUrl, params);
        String jsapi_ticket = JSONObject.fromObject(jsticket).getString("ticket");
        GlobalConstants.interfaceUrlProperties.put("jsapi_ticket", jsapi_ticket);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"jsapi_ticket: "+jsapi_ticket);
    }

    /**
     * 测试主动推送信息给用户
     */
//    @Scheduled(cron = "0 0/2 * * * ?")
//    public void sendTempToUserTest() {
//        try{
//            TemplateTextUtils.sendTemplate("oO0yw1MebFJk711cXLTfjjsme0o4", "tnQG0CVvPLvl647nhyPjwQCkKbTpxl1OAI9mLWsqwyc");
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }
//    }
}
