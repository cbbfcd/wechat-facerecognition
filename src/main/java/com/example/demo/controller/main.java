package com.example.demo.controller;

import com.example.demo.common.JSSDK_Config;
import com.example.demo.dispatcher.EventDispatcher;
import com.example.demo.dispatcher.MsgDispatcher;
import com.example.demo.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 微信公众号demo
 * @time 16:37 2017/11/20
 * @modified by:
 * @modified time:
 */
@Controller
@RequestMapping("/wechat")
public class main {

    /**
     * 微信服务器将发送GET请求到这个请求
     * @param request
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value="security", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value="signature", required = true) String signature,
                        @RequestParam(value="timestamp", required = true) String timestamp,
                        @RequestParam(value="nonce", required = true) String nonce,
                        @RequestParam(value="echostr", required = true) String echostr){

        PrintWriter pw = null;
        try {
            if(SignUtils.checkSignature(signature,timestamp,nonce)){
                pw = response.getWriter();
                pw.write(echostr);
                System.out.println("验证成功： echostr= "+echostr);
            }else{
                System.out.println("非法请求!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }

    /**
     * post请求用来接收接收微信服务端消息
     * 用户发送消息，点击等事件均会触发微信服务端向这个接口发起请求
     * @param request
     * @param response
     */
    @RequestMapping(value = "security", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        String resMsg = "";
        try{
            Map<String, String> map = MessageUtils.parseXML(request);
            String MsgType = map.get("MsgType");
            pw = response.getWriter();
            if(MessageUtils.REQ_MESSAGE_TYPE_EVENT.equals(MsgType)){
                resMsg = EventDispatcher.processEvent(map); // 进入事件分发器
            }else{
                resMsg = MsgDispatcher.processMessage(map); // 进入消息分发器
            }
            if(!StringUtils.isEmpty(resMsg)){
                pw.write(resMsg);
                pw.flush();
            }else{
                pw.write("哦霍~");
                pw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pw.close();
            pw = null;
        }
    }

    /**
     * 测试内网穿透
     */
    @RequestMapping("/test")
    public String netTest(){
        try {
            MenuUtils.createMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    /**
     * js_sdk
     */
    @RequestMapping("/jssdk")
    @ResponseBody
    public Map jsSDK_config(@RequestParam(value="url") String url){
        Map res = new HashMap<>();
        try {
            HashMap<String, String> map = JSSDK_Config.jsSDK_Sign(url);
            res.put("data", map);
            res.put("code", 1);
            res.put("msg", "success!");
        } catch (Exception e) {
            e.printStackTrace();
            res.put("data", null);
            res.put("code", 0);
            res.put("msg", "error!");
        }
        return res;
    }

    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    @ResponseBody
    public void upload(@RequestParam(value="serverId") String serverId){
        System.out.println("serverId: "+ serverId);
        HashMap<String, String> params = new HashMap<>();
        params.put("access_token", GlobalConstants.getString("access_token"));
        params.put("media_id", serverId);
        try {
            HttpUtils.downImg(GlobalConstants.getString("getImageUrl"), params, "F:\\screenshot\\src\\main\\webapp\\imgs\\"+serverId+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
