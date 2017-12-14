package com.example.demo.utils;

import com.example.demo.model.resp.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 微信的消息是xml格式的。所以用该工具类来处理xml格式
 * @time 9:43 2017/11/22
 * @modified by:
 * @modified time:
 */
public class MessageUtils {
    /**
     * 返回消息类型: 文本
     */
    public static final String RESP_MESSAGE__TYPE_TEXT = "text";
    /**
     * 返回消息类型: 音乐
     */
    public static final String RESP_MESSAGE__TYPE_MUSIC = "music";
    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE__TYPE_NEWS = "news";
    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE__TYPE_TEXT = "text";
    /**
     * 请求消息类型：图文
     */
    public static final String REQ_MESSAGE__TYPE_IMAGE = "image";
    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";
    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 事件类型：VIEW(自定义菜单 URl 视图)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     * 解析微信发过来的xml
     * @param request
     * @return map
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String,String> parseXML(HttpServletRequest request) throws Exception{
        // 用来存结果
        Map<String,String> map = new HashMap<>();
        // 用SAXReader读取输入流中的xml数据
        InputStream is = request.getInputStream();
        SAXReader reader = new SAXReader();
        // 根节点
        Document document = reader.read(is);
        Element root = document.getRootElement();
        // 遍历子节点
        List<Element> elements = root.elements();
        for(Element e : elements){
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        is.close();
        is = null;
        return map;
    }

    /**
     * 文本转xml
     */
    public static String textMessageToXml(TextMessage message){
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }

    /**
     * 图文消息转xml
     */
    public static String newsMessageToXml(NewsMessage news){
        xstream.alias("xml", news.getClass());
        xstream.alias("item",new Article().getClass());
        return xstream.toXML(news);
    }

    /**
     * 图片消息对象转换成 xml
     */
    public static String imageMessageToXml(ImageMessage img){
        xstream.alias("xml", img.getClass());
        return xstream.toXML(img);
    }

    /**
     * 语音消息对象转换成 xml
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage){
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成 xml
     */
    public static String videoMessageToXml(VideoMessage videoMessage){
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成 xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 对象到 xml 的处理
     */
    @SuppressWarnings("unchecked")
    public static XStream xstream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                // 对所有xml节点增加CDATA标记
                boolean cdata = true;
                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz){
                    super.startNode(upperCase(name), clazz);
                }

                @Override
                protected void writeText(QuickWriter qw, String text){
                    if(cdata){
                        qw.write("<![CDATA[");
                        qw.write(text);
                        qw.write("]]>");
                    }else{
                        qw.write(text);
                    }
                }
            };
        }
    });

    public static String upperCase(String str){
        String first = str.substring(0,1);
        if("x".equals(first)){
            return str;
        }else{
            return first.toUpperCase()+str.substring(1);
        }
    }
}
