package com.feelj.lean.english.word.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: feelj
 * @Date: 2019/7/8 23:14
 * @Description:  获取请求响应
 */
public class RequestUtils {
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    public static HttpServletResponse getResponse(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
