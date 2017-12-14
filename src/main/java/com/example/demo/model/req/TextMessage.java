package com.example.demo.model.req;

/**
 * @author huangteng
 * @version 1.0.0
 * @description for world peace
 * @time 10:57 2017/11/22
 * @modified by:
 * @modified time:
 */
public class TextMessage extends BaseMessage{
    // 文本内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
