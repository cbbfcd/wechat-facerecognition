package com.example.demo.model.req;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 图片消息
 * @time 10:51 2017/11/22
 * @modified by:
 * @modified time:
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

}
