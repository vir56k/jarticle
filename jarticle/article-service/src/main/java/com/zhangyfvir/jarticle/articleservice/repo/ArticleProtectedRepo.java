package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.utils.FileUtil;
import com.zhangyfvir.jarticle.utils.LogUtil;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;

@Repository
public class ArticleProtectedRepo {


    private static final String TAG = "ArticleProtectedRepo";

    public Article getArticle(String id) {
        return GetArticleAction.run(id);
    }

    public Article[] getNameList() {
        return NameListAction.run();
    }

    public void save(Article article) throws IOException {
        File file = ArticleUtil.titleToFilePath(article.getTitle());
        LogUtil.d(TAG, "filePath=%s", file);
        //if(file.exists())
        FileUtil.writeAllText(file.getAbsolutePath(), article.getBody());
    }
}
