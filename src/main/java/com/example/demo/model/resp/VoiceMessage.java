package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 回复语音消息
 * @time 14:18 2017/11/22
 * @modified by:
 * @modified time:
 */
public class VoiceMessage {
    private Voice Voice;

    public com.example.demo.model.resp.Voice getVoice() {
        return Voice;
    }

    public void setVoice(com.example.demo.model.resp.Voice voice) {
        Voice = voice;
    }
}
