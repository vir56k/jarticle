package com.zhangyfvir.jarticle.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zhangyfvir.jarticle.zuul.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 如果在 HTTP 头丌存在 tmx-correlation-id，TrackingFilter 过滤器将生成和设置关联 ID。
 * 如果关联 ID 已绊存在，Zuul 丌会为关联 ID 做任何事情。
 */
@Component
public class TrackingFilter extends ZuulFilter {
    private static final String TAG = TrackingFilter.class.getSimpleName();
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private static final Logger logger =
            LoggerFactory.getLogger(TrackingFilter.class);

    @Autowired
    FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.PRE_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }


    private boolean isCorrelationIdPresent() {
        if (filterUtils.getCorrelationId() != null) {
            return true;
        }
        return false;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

    public Object run() {
        LogUtil.d(TAG,"进入 TrackingFilter ...");

        if (isCorrelationIdPresent()) {
            logger.debug("tmx-correlation-id found in tracking filter: {}.",
                    filterUtils.getCorrelationId());
        } else {
            filterUtils.setCorrelationId(generateCorrelationId());
            logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        // shouldFilter() 方法返回一个布尔值，表示过滤 器是否应该处于活动状态。
        // 它是实际检查 tmx -correlation - id 是否存在的辅 助方法，并且也可以使用 GUIID 值生成一个关联 ID
        // run() 方法的代码，每一次服务通过过滤器被执行。
        // 在 run () 方法中，你看看 tmxcorrelation -id 是否存在，如果不存在，生成一个相关值并将其设置在 HTTP 的 tmx - correlation - id

        logger.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
        return null;
    }
}
