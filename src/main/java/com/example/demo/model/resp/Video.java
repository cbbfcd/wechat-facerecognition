package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 回复视频消息实体
 * @time 14:15 2017/11/22
 * @modified by:
 * @modified time:
 */
public class Video {
    private String MediaId;
    private String Title;
    private String Description;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
