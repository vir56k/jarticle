package com.zhangyfvir.jarticle.articleservice.repo;

import com.zhangyfvir.jarticle.articleservice.config.RepoConfig;
import com.zhangyfvir.jarticle.utils.PathUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class ArticleUtil {

    public static File titleToFilePath(String title) throws FileNotFoundException {
        if (null == title || "".equals(title)) throw new FileNotFoundException("文章标题不能为空");
        String fileName = String.format("%s.md", title);
        File newFile = new File(RepoConfig.getDataDir(), fileName);
        return newFile;
    }

    public static String filePathToTitle(String filePath) {
        return PathUtil.getOnlyName(filePath);
    }
}
