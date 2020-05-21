package com.zhangyfvir.jarticle.userservice;

import com.zhangyfvir.jarticle.common.jcontext.UserContextInterceptor;
import com.zhangyfvir.jarticle.utils.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RestController
@ComponentScan({"com.zhangyfvir.jarticle"})
public class UserServiceApplication {
    private static final String TAG = UserServiceApplication.class.getSimpleName();

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }


    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        LogUtil.d(TAG, "getRestTemplate...");

        RestTemplate template = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(
                    new UserContextInterceptor()));
            LogUtil.d(TAG, "附加拦截器...size=" + template.getInterceptors().size());
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
            LogUtil.d(TAG, "附加拦截器...size=" + template.getInterceptors().size());
        }
        return template;
    }

}
