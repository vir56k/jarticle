package com.zhangyfvir.jarticle.common.jcontext;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 仅仅是一个存储从传入的 HTTP 请求获取的值的 POJO 对象。
 *
 * UserContext 类被用于存储通过微服务正在处理的单独的服务客户端请求 HTTP 头的 值。
 * 它由一个 getter 和 setter 方法组成，用来从 java.lang.ThreadLocal 检索和存储值。
 */
@Component
@Data
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();



}
