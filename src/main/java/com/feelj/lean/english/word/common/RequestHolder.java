package com.feelj.lean.english.word.common;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: feelj
 * @Date: 2019/6/9 11:08
 * @Description:
 */
public class RequestHolder {

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }
    public static HttpServletResponse getResponse(){
        return ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
    }

}
