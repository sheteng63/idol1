package com.tengs.idol.core.interceptor;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


public class SignRequestInterceptor implements WebRequestInterceptor {
    private static Logger logger = LoggerFactory.getLogger(SignRequestInterceptor.class);

    @Override
    public void preHandle(WebRequest request) throws Exception {

        //获取token

    }


    @Override
    public void postHandle(WebRequest request, ModelMap modelMap) throws Exception {
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
