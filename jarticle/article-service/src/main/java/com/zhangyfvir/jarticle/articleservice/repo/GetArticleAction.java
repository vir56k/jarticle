package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.component.markdown.MarkdownHelper;
import com.zhangyfvir.jarticle.articleservice.config.RepoConfig;
import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.utils.FileUtil;
import com.zhangyfvir.jarticle.utils.LogUtil;
import com.zhangyfvir.jarticle.utils.PathUtil;

import java.io.File;

public class GetArticleAction {

    private static final String TAG = "GetArticleAction";

    public static Article run(String id) {
        String filePath = id + ".md";
        File file = new File(RepoConfig.getDataDir(), filePath);
        Article article = readFile(file);
        LogUtil.d(TAG, "article:", article);
        return article;
    }

    public static Article readFile(File file) {
        String txt = FileUtil.readAllText(file.getAbsolutePath());
        String title = ArticleUtil.filePathToTitle(file.getName());
        Article article = new Article(title, title, txt);
        String html = MarkdownHelper.parse(article.getBody());
        article.setHtml(html);
        return article;
    }
}
