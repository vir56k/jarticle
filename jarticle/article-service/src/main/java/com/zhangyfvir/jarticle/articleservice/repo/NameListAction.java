package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.config.RepoConfig;
import com.zhangyfvir.jarticle.articleservice.contract.Article;
import com.zhangyfvir.jarticle.utils.PathUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NameListAction {

    public static Article[] run() {
        File dataDir = RepoConfig.getDataDir();
        File[] files1 = dataDir.listFiles();
        if (files1 == null) return new Article[0];
        List<Article> lst = new ArrayList<>();
        for (File i : files1) {
            if (".md".equals(PathUtil.getExtentionName(i.getName()))) {
                String title = PathUtil.getOnlyName(i.getName());
                Article a = new Article(title, title, "");
                lst.add(a);
            }
        }
        return lst.toArray(new Article[0]);
    }
}
