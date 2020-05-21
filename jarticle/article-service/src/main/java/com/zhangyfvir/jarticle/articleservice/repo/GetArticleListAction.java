package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.component.markdown.MarkdownHelper;
import com.zhangyfvir.jarticle.articleservice.config.RepoConfig;
import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.utils.PathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetArticleListAction {

    /**
     * 获得文章列表，不含文章的markdown源代码
     *
     * @return
     */
    public static Article[] run() {
        File dataDir = RepoConfig.getDataDir();
        File[] files1 = dataDir.listFiles();
        if (files1 == null) return new Article[0];
        List<Article> lst = new ArrayList<>();
        for (File i : files1) {
            if (".md".equals(PathUtil.getExtentionName(i.getName()))) {
                Article article = GetArticleAction.readFile(i);
                //不含文章的markdown源代码
                article = bodyToHTML(article);
                article.setBody("");
                lst.add(article);
            }
        }
        return lst.toArray(new Article[0]);
    }

    /**
     * @param article
     * @return
     */
    private static Article bodyToHTML(Article article) {
        if (article == null) return null;
        String html = MarkdownHelper.parse(article.getBody());
        article.setHtml(html);
        article.setBody("");
        return article;
    }

}
