package com.example.demo.model.req;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 请求消息实体基类
 * @time 10:35 2017/11/22
 * @modified by:
 * @modified time:
 */
public class BaseMessage {
    // 开发者微信号
    private String ToUserName;
    // 发送方账号(它的openId)
    private String FromUserName;
    // 消息创建时间(long)
    private long CreateTime;
    // 消息类型
    private String MsgType;
    // id
    private long MsgId;

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

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }

}
