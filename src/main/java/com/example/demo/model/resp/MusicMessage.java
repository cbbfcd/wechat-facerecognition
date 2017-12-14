package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 音乐消息
 * @time 14:14 2017/11/22
 * @modified by:
 * @modified time:
 */
public class MusicMessage extends BaseMessage{
    // 音乐
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
