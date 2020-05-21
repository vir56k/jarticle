package com.zhangyfvir.jarticle.articleservice.contract;

import com.zhangyfvir.jarticle.common.entity.Result;

public interface ArticleProtectedInterface {
    Result<Article> getArticle(String id);

    Result<Article[]> getNameList();

    Result save(String id, String title, String body);

}
