package com.example.demo.model.resp;

import java.util.List;

/**
 * @author huangteng
 * @version 1.0.0
 * @description 多图文回复实体
 * @time 14:08 2017/11/22
 * @modified by:
 * @modified time:
 */
public class NewsMessage extends BaseMessage{
    // 图文消息个数，限制为 10 条以内
    private int ArticleCount;
    // 多条图文消息信息，默认第一个 item 为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
