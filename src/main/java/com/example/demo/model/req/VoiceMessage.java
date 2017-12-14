package com.example.demo.model.req;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 语音消息
 * @time 11:00 2017/11/22
 * @modified by:
 * @modified time:
 */
public class VoiceMessage extends BaseMessage{
    // 媒体 id
    private String MediaId;
    // 语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
