package com.zhangyfvir.jarticle.articleservice.config;

import com.zhangyfvir.jarticle.utils.ContextUtils;
import com.zhangyfvir.jarticle.utils.LogUtil;
import com.zhangyfvir.jarticle.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 *
 */
@Component
public class OnApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static final String TAG = "ApplicationStartup";

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LogUtil.d(TAG, "***************************************");
        LogUtil.d(TAG, "## onApplicationEvent");

        LogUtil.d(TAG, "## CurrentDir: %s", RepoConfig.getCurrentDir());
        LogUtil.d(TAG, "## DataDir: %s", RepoConfig.getDataDir());

        // 打印所有的 url
        String pro = ContextUtils.getActiveProfileString(contextRefreshedEvent.getApplicationContext());
        if ("dev".equals(pro)) {
            PrintUtil.printAllControllerURL(handlerMapping);
        }
    }

}
