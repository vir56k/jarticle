package com.zhangyfvir.jarticle.articleservice.services;

import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.articleservice.repo.ArticlePublicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlePublicService {

    @Autowired
    ArticlePublicRepo repo;

    public Article[] getArticleList() {
        return repo.getArticleList();
    }

    public Article[] getNameList() {
        return repo.getNameList();
    }

    public Article getArticle(String id) {
        return repo.getArticle(id);
    }
}
