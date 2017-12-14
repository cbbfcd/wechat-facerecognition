package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 回复消息的基类
 *              回复消息分为普通文本消息、多媒体回复消息
 * @time 14:00 2017/11/22
 * @modified by:
 * @modified time:
 */
public class BaseMessage {
    // 接受方的账号(openId)
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
