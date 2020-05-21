package com.zhangyfvir.jarticle.utils;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

public class PrintUtil {

    public static void print(String tag, Enumeration<String> headerNames) {
        String str = enumerationToString(headerNames);
        LogUtil.d(tag, str);
    }

    /**
     * Enumeration 集合 到字符串
     * @param items
     * @param <T>
     * @return
     */
    private static <T> String enumerationToString(Enumeration<T> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (items.hasMoreElements()) {
            T t = items.nextElement();
            if (t == null) {
                //sb.append("");
            } else {
                sb.append(t.toString());
            }
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 输出所有 controller 的 url
     * @param handlerMapping
     */
    public static void printAllControllerURL(RequestMappingHandlerMapping handlerMapping) {
        System.out.println("****************************");
        Map<RequestMappingInfo, HandlerMethod> map = handlerMapping.getHandlerMethods();
        Set<RequestMappingInfo> set = map.keySet();
        for (RequestMappingInfo info : set) {
            HandlerMethod handlerMethod = map.get(info);
            // springmvc的url地址，不包含项目名
            PatternsRequestCondition patternsCondition = info.getPatternsCondition();
            System.out.println(patternsCondition);
        }
        System.out.println("****************************");
    }
}
