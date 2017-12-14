package com.example.demo.model.req;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 链接消息
 * @time 10:53 2017/11/22
 * @modified by:
 * @modified time:
 */
public class LinkMessage extends BaseMessage {
    // 消息标题
    private String title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
