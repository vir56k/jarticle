package com.zhangyfvir.jarticle.articleservice.controllers;

import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.articleservice.contract.ArticlePublicInterface;
import com.zhangyfvir.jarticle.articleservice.services.ArticlePublicService;
import com.zhangyfvir.jarticle.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class ArticlePublicController implements ArticlePublicInterface {

    @Autowired
    ArticlePublicService articlePublicService;

    @Override
    @GetMapping("/articles/{id}")
    public Result<Article> getArticle(@PathVariable("id") String id) {
        Article article = articlePublicService.getArticle(id);
        return Result.success(article);
    }

    /**
     * 获得文章的集合，和缩略信息
     *
     * @return
     */
    @Override
    @GetMapping("/articles")
    public Result<Article[]> getArticleList() {
        Article[] articles = articlePublicService.getArticleList();
        return Result.success(articles);
    }


    @Override
    @GetMapping("/articleNameList")
    public Result<Article[]> getNameList() {
        Article[] strings = articlePublicService.getNameList();
        return Result.success(strings);
    }
}
