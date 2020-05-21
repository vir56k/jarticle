package com.zhangyfvir.jarticle.common.jcontext;

import com.zhangyfvir.jarticle.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 确保关联 ID 的获取和向前 传播
 *
 * 这个类是用来注入关联 ID 到任何输出的基于 HTTP 的服务请求，该请求从 RestTemplate 实例中执行。
 * 这样做是 为了确保你可以在服务调用乀间建立联系。
 */
public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    private static final String TAG = UserContextInterceptor.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logger.debug("进入拦截器器...");
        LogUtil.d(TAG,"进入拦截器器...");

        HttpHeaders headers = request.getHeaders();
        headers.add(UserContext.CORRELATION_ID,
                UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        return execution.execute(request, body);
    }
}
