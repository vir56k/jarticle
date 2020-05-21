package com.zhangyfvir.jarticle.common.jcontext;

import com.zhangyfvir.jarticle.utils.LogUtil;
import com.zhangyfvir.jarticle.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这个类是一个 HTTP Servlet 过滤器，用来拦戔进入服务的所有 HTTP 请求，
 *  并从 HTTP 请求中读取 关联 ID(和一些其它的值)
 */
@Component
public class UserContextFilter implements Filter {
    private static final String TAG = UserContextFilter.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("进入过滤器...");
        LogUtil.d(TAG,"进入过滤器...");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 你的过滤器从头部检索关联 ID 并在 UserContext 类设置该值。
        UserContextHolder.getContext().setCorrelationId(
                httpServletRequest.getHeader(UserContext.CORRELATION_ID));

        UserContextHolder.getContext().setUserId(
                httpServletRequest.getHeader(UserContext.USER_ID));

        UserContextHolder.getContext().setAuthToken(
                httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

        UserContextHolder.getContext().setOrgId(
                httpServletRequest.getHeader(UserContext.ORG_ID));

        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
