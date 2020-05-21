package com.zhangyfvir.jarticle.utils;

import java.io.File;

public class PathUtil {


    public static String combine(String path1, String path2) {
        File file1 = new File(path1);
        File file2 = new File(file1, path2);
        return file2.getPath();
    }

    public static String getExtentionName(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    public static String getOnlyName(String fileName) {
        if (TextUtils.isEmpty(fileName)) return fileName;
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
