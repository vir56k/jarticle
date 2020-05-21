package com.zhangyfvir.jarticle.articleservice.contract;

import lombok.Data;

import java.io.Serializable;

@Data
public class Article implements Serializable {
    private String id;
    private String title;
    private String body;
    private String html;
    private String author;
    private String createdDate;
    private String lastModifyDate;

    public Article() {
    }

    public Article(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", html='" + html + '\'' +
                ", author='" + author + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", lastModifyDate='" + lastModifyDate + '\'' +
                '}';
    }
}
