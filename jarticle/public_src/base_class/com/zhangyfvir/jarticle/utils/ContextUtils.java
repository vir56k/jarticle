package com.zhangyfvir.jarticle.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ContextUtils {
    private static final String TAG = "ContextUtil";

    /**
     * 获得在 配置文件  yum 中配置的 profiles 的 active 的值。类似下面
     * spring:
     *   profiles:
     *     active: dev
     * @param context
     * @return
     */
    public static String getActiveProfileString(ApplicationContext context) {
        String[] activeProfiles = context.getEnvironment().getActiveProfiles();
        String profile;
        if (ObjectUtils.isEmpty(activeProfiles)) {
            String[] defaultProfiles = context.getEnvironment().getDefaultProfiles();
            //"No active profile set, falling back to default profiles: "
            profile = StringUtils.arrayToCommaDelimitedString(defaultProfiles);
        } else {
            profile = StringUtils.arrayToCommaDelimitedString(activeProfiles);
        }
        LogUtil.d(TAG, "profile=%s", profile);
        return profile;
    }
}
