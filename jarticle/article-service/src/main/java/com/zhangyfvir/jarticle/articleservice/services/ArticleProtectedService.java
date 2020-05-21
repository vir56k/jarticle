package com.zhangyfvir.jarticle.articleservice.services;

import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.articleservice.repo.ArticleProtectedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ArticleProtectedService {

    @Autowired
    ArticleProtectedRepo repo;

    public Article GetArticle(String id) {
        return repo.getArticle(id);
    }

    public Article[] GetNameList() {
        return repo.getNameList();
    }

    public void save(Article article) throws IOException {
        repo.save(article);
    }
}
