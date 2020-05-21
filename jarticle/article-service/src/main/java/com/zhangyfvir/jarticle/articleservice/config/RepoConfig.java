package com.zhangyfvir.jarticle.articleservice.config;

import com.zhangyfvir.jarticle.utils.FileUtil;
import com.zhangyfvir.jarticle.utils.PathUtil;

import java.io.File;
import java.nio.file.Files;

public class RepoConfig {

    public static String getCurrentDir() {
        File directory = new File(".");
        return directory.getAbsolutePath();
    }

    public static File getDataDir() {
        return new File(getCurrentDir(), "data");
    }
}
