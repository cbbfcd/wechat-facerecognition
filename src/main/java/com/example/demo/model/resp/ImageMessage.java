package com.example.demo.model.resp;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 图片消息
 * @time 14:12 2017/11/22
 * @modified by:
 * @modified time:
 */
public class ImageMessage extends BaseMessage{
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
