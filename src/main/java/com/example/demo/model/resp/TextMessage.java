package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 文本回复消息实体
 * @time 14:04 2017/11/22
 * @modified by:
 * @modified time:
 */
public class TextMessage extends BaseMessage{
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
