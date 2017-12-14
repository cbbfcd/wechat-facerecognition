package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 回复图文消息实体
 * @time 14:06 2017/11/22
 * @modified by:
 * @modified time:
 */
public class Article {
    // 图文消息名称
    private String title;
    // 图文消息描述
    private String Description;
    // 图片链接，支持 JPG、PNG 格式，较好的效果为大图 640*320，小图 80*80
    private String PicUrl;
    // 点击图文消息跳转链接
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

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
