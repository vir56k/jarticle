package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.contract.Article;
import org.springframework.stereotype.Repository;

@Repository
public class ArticlePublicRepo {


    public Article getArticle(String id) {
        Article a = GetArticleAction.run(id);
        //隐藏实际的文字的源代码
        a.setBody("");
        return a;
    }

    public Article[] getArticleList() {
        return GetArticleListAction.run();
    }

    public Article[] getNameList() {
        return NameListAction.run();
    }

}
