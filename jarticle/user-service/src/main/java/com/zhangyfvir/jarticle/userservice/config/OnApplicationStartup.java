package com.zhangyfvir.jarticle.userservice.config;

import com.zhangyfvir.jarticle.utils.ContextUtils;
import com.zhangyfvir.jarticle.utils.LogUtil;
import com.zhangyfvir.jarticle.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class OnApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static final String TAG = "ApplicationStartup";

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        LogUtil.d(TAG, "## onApplicationEvent");
        // 初始化完成后. 执行一次同步系统参数
        onSetupSystem(contextRefreshedEvent.getApplicationContext());

    }

    private void onSetupSystem(ApplicationContext context) {
        String pro = ContextUtils.getActiveProfileString(context);
        if ("dev".equals(pro)) {
            PrintUtil.printAllControllerURL(handlerMapping);
        }
    }
}
