package com.zhangyfvir.jarticle.articleservice.contract;

import com.zhangyfvir.jarticle.common.entity.Result;

public interface ArticlePublicInterface {
    Result<Article> getArticle(String id);

    Result<Article[]> getArticleList();

    Result<Article[]> getNameList();
}
